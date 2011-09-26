package com.ln.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ln.dao.LoginDao;
import com.ln.utility.Constants;
import com.ln.utility.Utility;

/**
 * Handle logout
 * 
 * @author parampreetsethi
 * 
 */
public class SignoutServlet extends HttpServlet implements Constants {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String[] data = Utility.getCookieValue(req.getCookies());
		String url ="/signout.jsp?msg=";
		String msg = "An error has occured. Please Try again.";
		if (data == null) {
			msg = "Signed out successfully.";
		} else {
			int success = new LoginDao().signout(data[0], data[1]);
			if (success > 0)
				msg = "Signed out successfully.";
		}
		Cookie cookie = Utility.removeCookie(req.getCookies(), COOKIE_NAME);
		if (cookie != null)
			resp.addCookie(cookie);
		RequestDispatcher rd = req.getRequestDispatcher(url + msg);
		rd.forward(req, resp);
	}
}
