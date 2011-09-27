package com.ln.dao;

import com.ln.model.SearchResult;
import com.ln.parser.LnXMLParser;
import com.ln.utility.LnDemoException;
import com.ln.utility.Utility;

/**
 * get data for Search queries
 * 
 * @author parampreetsethi
 * 
 */
public class SearchDao {
	private Connector conn;

	public SearchDao(String token, String secret) {
		conn = new ScribeConnectorImpl();
		conn.init(token, secret);
	}

	/**
	 * Populate Search Result
	 * 
	 * @param result
	 * @throws LnDemoException
	 */
	public void search(SearchResult result) throws LnDemoException {
		StringBuilder url = new StringBuilder(
				"http://api.linkedin.com/v1/people-search:(people:(id,first-name,last-name,headline,industry,distance,picture-url,location:(name,country:(code))),num-results)?keywords=");
		String query = result.getQuery().replaceAll(" ", "-");
		query = query.replaceAll("\\W", "-");

		url.append(query).append("&start=").append(result.getStart())
				.append("&count=").append(result.getCount());

		String xml = Utility.getXML(url.toString(), conn);
		LnXMLParser.getSearchResults(xml, result);

	}
}
