package com.ln.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ln.dao.LoginDao;
import com.ln.utility.Constants;
import com.ln.utility.Utility;

/**
 * For authentication
 * 
 * @author parampreetsethi
 * 
 */
public class AuthenticateServlet extends HttpServlet implements Constants {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String[] data = new LoginDao().authenticate();
		resp.addCookie(Utility.setCookie(data[0], data[1], req.getCookies()));
		resp.sendRedirect(data[2]);
	}
}
