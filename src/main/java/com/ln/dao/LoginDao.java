package com.ln.dao;

/**
 * Authentication related
 * 
 * @author parampreetsethi
 * 
 */
public class LoginDao {

	/**
	 * Authenticate and return request token and secret
	 * 
	 * @return reqtoken, secret
	 */
	public String[] authenticate() {
		Connector connector = new ScribeConnectorImpl();
		return connector.authenticate();
	}

	/**
	 * Authorize and return accessToken and secret
	 * 
	 * @param reqToken
	 * @param secret
	 * @param code
	 * @return accesstoken,secret
	 */
	public String[] authorize(String reqToken, String secret, String code) {
		Connector scribe = new ScribeConnectorImpl();
		scribe.setRequestToken(reqToken, secret);
		return scribe.authorize(code);
	}

	/**
	 * Invalidate user token
	 * 
	 * @param access
	 *            token
	 * @param secret
	 * @return success code
	 */
	public int signout(String token, String secret) {
		Connector scribe = new ScribeConnectorImpl();
		scribe.init(token, secret);
		return scribe.signOut();
	}
}
