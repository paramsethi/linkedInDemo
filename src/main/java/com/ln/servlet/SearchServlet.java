package com.ln.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ln.dao.SearchDao;
import com.ln.model.SearchResult;
import com.ln.model.User;
import com.ln.utility.Constants;
import com.ln.utility.LnDemoException;
import com.ln.utility.PropertyBag;
import com.ln.utility.Utility;

/**
 * Search Connections
 * 
 * @author parampreetsethi
 * 
 */
public class SearchServlet extends HttpServlet implements Constants {
	private static final Logger log = Logger.getLogger(SearchServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String serverUrl = PropertyBag.getProperty(HOST_NAME);
		String errorUrl = serverUrl + "/lndemo/error?error=";
		String authUrl = serverUrl + "/lndemo/authenticate";
		String query = req.getParameter("query");
		int start = 0;
		int count = 10; // set to 10
		if (!Utility.isValid(query)) {
			log.error("Please provide valid query ");
			resp.sendRedirect(errorUrl + "Please provide valid search query.");
			return;
		}
		if (Utility.isValid(req.getParameter("start"))) {
			try {
				start = Integer.parseInt(req.getParameter("start"));
			} catch (NumberFormatException ex) {
				log.error("Invalid start parameter ", ex);
			}
		}
		String[] data = Utility.getCookieValue(req.getCookies());
		if (data == null) {
			resp.sendRedirect(authUrl);
			return;
		}
		try {
			SearchResult result = new SearchResult();
			result.setQuery(query);
			result.setRequestor(buildUserFromReq(req));
			result.setCount(count);
			result.setStart(start);
			new SearchDao(data[0], data[1]).search(result);
			req.setAttribute("result", result);
		} catch (LnDemoException exp) {
			log.error("An error occured: ", exp);
			resp.sendRedirect(errorUrl + exp.toString());
			return;
		}
		RequestDispatcher rd = req
				.getRequestDispatcher("/searchresults.jsp?query=" + query);
		rd.forward(req, resp);
	}

	/**
	 * build user from Request object
	 * 
	 * @param req
	 * @return user
	 */
	private User buildUserFromReq(HttpServletRequest req) {
		User user = new User();
		user.setFirstName(req.getParameter("firstname"));
		user.setLastName(req.getParameter("lastname"));
		user.setHeadline(req.getParameter("headline"));
		user.setId(req.getParameter("id"));
		return user;
	}

}
