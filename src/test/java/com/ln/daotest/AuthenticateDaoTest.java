package com.ln.daotest;

import org.junit.Assert;
import org.junit.Test;

import com.ln.dao.LoginDao;
import com.ln.utility.Constants;
import com.ln.utility.PropertyBag;
import com.ln.utility.Utility;

/**
 * Test Cases for Authentication and Authorization Flow
 * 
 * @author parampreetsethi
 * 
 */
public class AuthenticateDaoTest {

	/**
	 * API Key should be set in properties file
	 */
	@Test
	public void testAPIKey() {
		String key = PropertyBag.getProperty(Constants.API_KEY);
		Assert.assertEquals("Consumer key should be set in properties file.",
				true, Utility.isValid(key));
	}

	@Test
	public void testAPISecret() {
		String secret = PropertyBag.getProperty(Constants.API_SECRET);
		Assert.assertEquals(
				"Consumer secret should be set in properties file.", true,
				Utility.isValid(secret));
	}

	@Test
	public void testAuthUrl() {
		String authUrl = PropertyBag.getProperty(Constants.AUTH_URL);
		Assert.assertEquals(
				"The authorization URL should be set in properties file.",
				true, Utility.isValid(authUrl));
	}

	@Test
	public void testCallbackUrl() {
		String cbUrl = PropertyBag.getProperty(Constants.CALL_BACK);
		Assert.assertEquals(
				"The callback URL should be set in properties file.", true,
				Utility.isValid(cbUrl));
	}

	/**
	 * Test to check if API available or not. with timeout value of 3secs
	 * 
	 */
	@Test(timeout = 3000)
	public void authenticateTest() {
		String[] data = new LoginDao().authenticate();
		Assert.assertNotNull("Could not acquire valid Request Token", data[0]);
		Assert.assertNotNull("Could not acquire valid Request Token Secret",
				data[1]);
	}

}
