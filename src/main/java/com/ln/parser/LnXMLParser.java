package com.ln.parser;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ln.model.SearchResult;
import com.ln.model.User;
import com.ln.utility.LnDemoException;

/**
 * 
 * LinkedIn XML parser
 * 
 * This class contains parsing functions for various api calls
 * 
 * @author parampreetsethi
 * 
 */
public class LnXMLParser {
	private static final Logger log = Logger.getLogger(LnXMLParser.class);

	/**
	 * Parse XML as returned by Linkedin profile API call
	 * 
	 * @param xml
	 * @return user
	 * @throws LnDemoException
	 */
	public static User parseProfileXML(String xml) throws LnDemoException {
		User user = null;
		try {
			Document doc = XMLParser.getXMLDoc(xml);
			NodeList data = doc.getElementsByTagName("person");
			if (data != null && data.item(0) != null) {
				Node userNode = data.item(0);
				if (userNode != null
						&& "person".equalsIgnoreCase(userNode.getNodeName()))
					user = getUserFromNode(userNode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new LnDemoException("An error occured", e.getMessage());
		}
		return user;
	}

	/**
	 * Get users from Search results xml
	 * 
	 * @param xml
	 * @return List of users
	 * @throws LnDemoException
	 */
	public static void getSearchResults(String xml, SearchResult result)
			throws LnDemoException {
		List<User> users = null;
		try {
			Document doc = XMLParser.getXMLDoc(xml);
			NodeList peopleSrch = doc.getElementsByTagName("people-search");
			if (null != peopleSrch && null != peopleSrch.item(0)) {
				NodeList peopleChildren = peopleSrch.item(0).getChildNodes();
				for (int i = 0; i < peopleChildren.getLength(); i++) {
					Node tmp = peopleChildren.item(i);
					if (tmp != null) {
						if (tmp.getNodeName().equalsIgnoreCase("people")) {
							// parse people here
							NodeList persons = tmp.getChildNodes();
							users = new ArrayList<User>();
							if (null != persons) {
								for (int j = 0; j < persons.getLength(); j++) {
									Node person = persons.item(j);
									if (person != null
											&& "person".equalsIgnoreCase(person
													.getNodeName()))
										users.add(getUserFromNode(person));
								}
								result.setUsers(users);
							}
						} else if (tmp.getNodeName().equalsIgnoreCase(
								"num-results")) {
							// Set total count
							try {
								result.setTotalCount(Integer.parseInt(tmp
										.getTextContent()));
							} catch (NumberFormatException exp) {
								log.error("Invalid number ", exp);
							}
						}
					}
				}

			}
		} catch (Exception exp) {
			exp.printStackTrace();
			throw new LnDemoException("An error occured", exp.getMessage());
		}

	}

	/**
	 * Get user from XML node
	 * 
	 * @param userNode
	 * @return user
	 */
	private static User getUserFromNode(Node userNode) {
		User user = null;
		if (null != userNode
				&& "person".equalsIgnoreCase(userNode.getNodeName())) {
			NodeList children = userNode.getChildNodes();

			if (children != null) {
				user = new User();
				for (int i = 0; i < children.getLength(); i++) {
					Node temp = children.item(i);
					if (temp != null
							&& !(temp.getNodeName().equalsIgnoreCase("#text"))) {
						if (temp.getNodeName().equalsIgnoreCase("id"))
							user.setId(temp.getTextContent());
						else if (temp.getNodeName().equalsIgnoreCase(
								"first-name"))
							user.setFirstName(temp.getTextContent());
						else if (temp.getNodeName().equalsIgnoreCase(
								"last-name"))
							user.setLastName(temp.getTextContent());
						else if (temp.getNodeName()
								.equalsIgnoreCase("headline"))
							user.setHeadline(temp.getTextContent());
						else if (temp.getNodeName()
								.equalsIgnoreCase("industry"))
							user.setIndustry(temp.getTextContent());
						else if (temp.getNodeName()
								.equalsIgnoreCase("distance"))
							user.setDistance(temp.getTextContent());
						else if (temp.getNodeName().equalsIgnoreCase(
								"picture-url"))
							user.setPictureUrl(temp.getTextContent());
						else if (temp.getNodeName().equalsIgnoreCase(
								"num-connections"))
							user.setNumConnections(temp.getTextContent());
						else if (temp.getNodeName()
								.equalsIgnoreCase("location"))
							setLocationInfo(user, temp);
						else if (temp.getNodeName().equalsIgnoreCase(
								"num-recommenders"))
							user.setNumRecommenders(temp.getTextContent());
						else if (temp.getNodeName().equalsIgnoreCase("summary"))
							user.setSummary(temp.getTextContent());
						else if (temp.getNodeName().equalsIgnoreCase(
								"specialties"))
							user.setSpecialties(temp.getTextContent());
						else if (temp.getNodeName().equalsIgnoreCase("honors"))
							user.setHonors(temp.getTextContent());
						else if (temp.getNodeName().equalsIgnoreCase(
								"associations"))
							user.setAssociations(temp.getTextContent());
						else if (temp.getNodeName().equalsIgnoreCase(
								"languages"))
							user.setLanguages(temp.getTextContent());
						else if (temp.getNodeName().equalsIgnoreCase(
								"current-share"))
							user.setCurrentShare(temp.getTextContent());
						else if (temp.getNodeName().equalsIgnoreCase(
								"positions"))
							setPositions(temp, user);
						else if (temp.getNodeName().equalsIgnoreCase(
								"public-profile-url"))
							user.setPublicProfileUrl(temp.getTextContent());
					}
				}
			}

		}
		return user;
	}

	/**
	 * Set location information in the user bean
	 * 
	 * @param user
	 * @param location
	 */
	private static void setLocationInfo(User user, Node location) {
		NodeList children = location.getChildNodes();
		if (children != null) {
			for (int i = 0; i < children.getLength(); i++) {
				Node tmp = children.item(i);
				if (null != tmp) {
					if (tmp.getNodeName().equalsIgnoreCase("name"))
						user.setCity(tmp.getTextContent());
					else if (tmp.getNodeName().equalsIgnoreCase("country")) {
						if (tmp.getChildNodes() != null
								&& tmp.getChildNodes().item(0) != null) {
							user.setCountry(tmp.getChildNodes().item(0)
									.getTextContent());
						}
					}
				}
			}
		}
	}

	/**
	 * Set user positions
	 * 
	 * @param positions
	 * @param user
	 */
	private static void setPositions(Node positions, User user) {
		NodeList position = positions.getChildNodes();
		String[] pastPositions = new String[3];
		String currPosition = null;
		boolean isCurrent = false;
		if (position != null) {
			for (int i = 0; i < 3; i++) { // get only top 3
				Node tmp = position.item(i);
				if (null != tmp
						&& tmp.getNodeName().equalsIgnoreCase("position")) {
					NodeList children = tmp.getChildNodes();
					if (children != null) {
						for (int j = 0; j < children.getLength(); j++) {
							Node child = children.item(j);
							if (null != child
									&& !(child.getNodeName()
											.equalsIgnoreCase("#text"))) {
								if (child.getNodeName().equalsIgnoreCase(
										"title")) {
									pastPositions[i] = child.getTextContent();
									if (currPosition == null)
										currPosition = pastPositions[i];
								} else if (child.getNodeName()
										.equalsIgnoreCase("is-current")) {
									if (child.getTextContent()
											.equalsIgnoreCase("true")) {
										isCurrent = true;
									}
									if (!isCurrent)
										currPosition = null;
								}
							}
						}
					}
				}
			}
		}
		user.setCurrPosition(currPosition);
		user.setPastPositions(pastPositions);
	}
}
