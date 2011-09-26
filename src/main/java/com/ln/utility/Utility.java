package com.ln.utility;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.ln.dao.Connector;
import com.ln.dao.Connector.SiteResponse;

/**
 * Common util functions here
 * 
 * @author parampreetsethi
 * 
 */
public class Utility implements Constants {

	/**
	 * make API call for given url
	 * 
	 * @param url
	 * @return xml response
	 * @throws LnDemoException
	 */
	public static String getXML(String url, Connector conn)
			throws LnDemoException {

		SiteResponse response = conn.get(url);
		if (response != null) {
			if (response.getCode() != 200) {
				throw new LnDemoException("An error occured: "
						+ response.getBody());
			}
			return response.getBody();
		}

		throw new LnDemoException("SiteResponse came as null");

	}

	public static String[] getCookieValue(Cookie[] cookies) {
		String tokenInfo[] = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase(COOKIE_NAME)) {
					String value = cookie.getValue();
					tokenInfo = value.split(COOKIE_SEPARATOR);
					break;
				}
			}
		}
		return tokenInfo;
	}

	public static String getCookieSecret(Cookie[] cookies) {
		String secret = null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equalsIgnoreCase(COOKIE_NAME)) {
				String value = cookie.getValue();
				secret = value.split(COOKIE_SEPARATOR)[1];
				break;
			}
		}
		return secret;
	}

	/**
	 * Check if cookie exists
	 * 
	 * If exists modify the existing cookie, else create a new one
	 * 
	 * @param token
	 * @param secret
	 * @param cookies
	 * @return
	 */
	public static Cookie setCookie(String token, String secret, Cookie[] cookies) {
		Cookie cookie = null;
		if (cookies != null) {
			for (Cookie temp : cookies) {
				if (temp.getName().equalsIgnoreCase(COOKIE_NAME)) {
					cookie = temp;
					break;
				}
			}
		}
		StringBuilder value = new StringBuilder(token);
		value.append(COOKIE_SEPARATOR).append(secret);
		if (cookie != null) {
			cookie.setValue(value.toString());
		} else {
			cookie = new Cookie(COOKIE_NAME, value.toString());
		}
		cookie.setMaxAge(COOKIE_MAX_AGE); // set max age to one hour
		return cookie;
	}
	
	/**
	 * Remove given cookie
	 * 
	 * @param cookies
	 * @param cookieName
	 * @return
	 */
	public static Cookie removeCookie(Cookie[] cookies, String cookieName){
		Cookie cookie = null;
		if (cookies != null) {
			for (Cookie temp : cookies) {
				if (temp.getName().equalsIgnoreCase(cookieName)) {
					cookie = temp;
					break;
				}
			}
		}
		if(cookie!=null){
			cookie.setMaxAge(0);
		}
		return cookie;
	}

	public static boolean isValid(String val) {
		if (null != val && val.trim().length() > 0)
			return true;

		return false;
	}
}
