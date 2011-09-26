package com.ln.dao;

import com.ln.model.User;
import com.ln.parser.LnXMLParser;
import com.ln.utility.LnDemoException;
import com.ln.utility.Utility;

public class UserDao {
	private Connector conn;

	public UserDao(String token, String secret) {
		conn = new ScribeConnectorImpl();
		conn.init(token, secret);
	}

	/**
	 * Get Current User from Linkedin API based on access token
	 * 
	 * @return User Bean object
	 * @throws LnDemoException
	 */
	public User getUserProfile() throws LnDemoException {
		String url = "http://api.linkedin.com/v1/people/~:(id,first-name,last-name,headline,num-recommenders,industry,distance,num-connections,summary,specialties,associations,honors,public-profile-url,picture-url,location:(name,country:(code)),positions:(title,is-current))";
		String xml = Utility.getXML(url, conn);
		return LnXMLParser.parseProfileXML(xml);
	}

	/**
	 * Get User from Linkedin API
	 * 
	 * @param id
	 *            of the user
	 * @return User bean object
	 * @throws LnDemoException
	 */
	public User getUserProfile(String id) throws LnDemoException {
		String url = "http://api.linkedin.com/v1/people/id="
				+ id
				+ ":(id,first-name,last-name,headline,num-recommenders,industry,distance,num-connections,summary,specialties,associations,honors,public-profile-url,picture-url,location:(name,country:(code)),positions:(title,is-current))";
		String xml = Utility.getXML(url, conn);
		return LnXMLParser.parseProfileXML(xml);
	}

}
