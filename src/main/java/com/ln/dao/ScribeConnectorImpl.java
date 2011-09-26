package com.ln.dao;

import java.io.InputStream;
import java.util.Map;

import org.apache.log4j.Logger;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.ln.utility.Constants;
import com.ln.utility.PropertyBag;

/**
 * As given in tutorial to use scribe
 * 
 * @author parampreetsethi
 * 
 */
public class ScribeConnectorImpl implements Connector, Constants {

	private static final Logger log = Logger
			.getLogger(ScribeConnectorImpl.class);
	private Token accessToken;
	private Token requestToken;
	private final OAuthService service;

	public ScribeConnectorImpl() {
		this.service = new ServiceBuilder().provider(LinkedInApi.class)
				.apiKey(PropertyBag.getProperty(API_KEY))
				.apiSecret(PropertyBag.getProperty(API_SECRET))
				.callback(PropertyBag.getProperty(CALL_BACK)).build();
	}

	/**
	 * get request token and secret from linkedin using auth api call
	 * 
	 */
	public String[] authenticate() {
		// obtain request token
		this.requestToken = this.service.getRequestToken();
		// after this step we need to make user obtain permission
		String token = this.requestToken.getToken();
		String secret = this.requestToken.getSecret();
		String url = PropertyBag.getProperty(AUTH_URL)
				+ this.requestToken.getToken();
		return new String[] { token, secret, url };
	}

	/**
	 * Authorize current req token and return accesstoken and secret
	 */
	public String[] authorize(final String code) {
		final Verifier verifier = new Verifier(code);
		this.accessToken = this.service.getAccessToken(this.requestToken,
				verifier);
		final String token = this.accessToken.getToken();
		final String secret = this.accessToken.getSecret();
		if (this.accessToken == null || token == null || secret == null) {
			log.error("Failed to obtain valid authentication response");
		}
		return new String[] { token, secret };
	}

	public SiteResponse get(final String url) {
		final OAuthRequest request = new OAuthRequest(Verb.GET, url);
		this.service.signRequest(this.accessToken, request);
		return new ScribeResponseImpl(request.send());
	}

	public void setRequestToken(String reqToken, String secret) {
		this.requestToken = new Token(reqToken, secret);
	}

	public void init(final String authToken, final String authSecret) {
		this.accessToken = new Token(authToken, authSecret);
	}

	public int signOut() {
		return get(PropertyBag.getProperty(SIGN_OUT_URL)).getCode();
	}

	public class ScribeResponseImpl implements SiteResponse {
		private final Response response;

		public ScribeResponseImpl(final Response response) {
			super();
			this.response = response;
		}

		public String getBody() {
			return this.response.getBody();
		}

		public int getCode() {
			return this.response.getCode();
		}

		public String getHeader(final String name) {
			return this.response.getHeader(name);
		}

		public Map getHeaders() {
			return this.response.getHeaders();
		}

		public InputStream getStream() {
			return this.response.getStream();
		}
	}
}
