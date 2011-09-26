package com.ln.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ln.dao.UserDao;
import com.ln.model.User;
import com.ln.utility.Constants;
import com.ln.utility.LnDemoException;
import com.ln.utility.PropertyBag;
import com.ln.utility.Utility;

/**
 * User related Calls
 * 
 * @author parampreetsethi
 * 
 */
public class UserServlet extends HttpServlet implements Constants {

	private static final Logger log = Logger.getLogger(UserServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		String serverUrl = PropertyBag.getProperty(HOST_NAME);
		String errorUrl = serverUrl + "/lndemo/error?error=";
		String authUrl = serverUrl + "/lndemo/authenticate";
		// get user profile here.
		String tokenInfo[] = Utility.getCookieValue(req.getCookies());
		if (tokenInfo != null) {
			UserDao dao = new UserDao(tokenInfo[0], tokenInfo[1]);
			User user = null;
			try {

				if (Utility.isValid(id))
					user = dao.getUserProfile(id); // Get user for given id
				else
					user = dao.getUserProfile(); // Get current user
				if (user == null)
					throw new LnDemoException("User not found");
			} catch (LnDemoException exp) {
				log.error("An error has occured", exp);
				resp.sendRedirect(errorUrl + exp.getMessage());
				return;
			}
			req.setAttribute("user", user);
			RequestDispatcher dp = req.getRequestDispatcher("/showprofile.jsp");
			dp.forward(req, resp);
		} else
			resp.sendRedirect(authUrl);
	}

}
