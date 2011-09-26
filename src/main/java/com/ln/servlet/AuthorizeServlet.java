package com.ln.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ln.dao.LoginDao;
import com.ln.utility.Constants;
import com.ln.utility.PropertyBag;
import com.ln.utility.Utility;

/**
 * For authorization
 * 
 * @author parampreetsethi
 * 
 */
public class AuthorizeServlet extends HttpServlet implements Constants {

	private static final Logger log = Logger.getLogger(AuthorizeServlet.class);

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
		String profileUrl = serverUrl + "/lndemo/profile";
		String code = req.getParameter("oauth_verifier");
		String token = req.getParameter("oauth_token");
		if (Utility.isValid(req.getParameter("oauth_problem"))) {
			if ("user_refused".equalsIgnoreCase(req
					.getParameter("oauth_problem"))) {
				resp.sendRedirect(errorUrl
						+ "Please authorize linkedIndemo to continue");
				return;
			} else
				resp.sendRedirect(errorUrl + req.getParameter("oauth_problem"));
			return;
		}
		String secret = Utility.getCookieSecret(req.getCookies());
		if (secret != null) {
			String[] data = new LoginDao().authorize(token, secret, code);
			resp.addCookie(Utility.setCookie(data[0], data[1], req.getCookies()));
			resp.sendRedirect(profileUrl);
			return;
		} else
			resp.sendRedirect(authUrl);
	}

}
