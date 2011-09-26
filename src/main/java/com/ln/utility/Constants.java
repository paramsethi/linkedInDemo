package com.ln.utility;

/**
 * Application constants
 * 
 * @author parampreetsethi
 * 
 */
public interface Constants {
	String COOKIE_SEPARATOR = ":";
	String COOKIE_NAME = "lndemo";
	int COOKIE_MAX_AGE = 60 * 60;

	String ERROR_URL = "/lndemo/error?error=%s";

	// Property file related constants
	String PROP_FILE = "lndemo.properties";
	String API_KEY = "lndemo.api.key";
	String API_SECRET = "lndemo.api.secret";
	String SIGN_OUT_URL = "lndemo.signout.url";
	String AUTH_URL = "lndemo.auth.url";
	String CALL_BACK = "lndemo.callback";
	String HOST_NAME = "lndemo.hostname";
}
