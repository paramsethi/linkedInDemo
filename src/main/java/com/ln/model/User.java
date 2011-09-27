package com.ln.model;

public class User {

	private String firstName;
	private String lastName;
	private String headline;
	private String id;
	private String city;
	private String country;
	private int distance;
	private String industry;
	private String currentShare;
	private String numConnections;
	private String summary;
	private String specialties;
	private String associations;
	private String honors;
	private String languages;
	private String pictureUrl;
	private String publicProfileUrl;
	private String numRecommenders;
	private String[] pastPositions;
	private String currPosition;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the headline
	 */
	public String getHeadline() {
		return headline;
	}

	/**
	 * @param headline
	 *            the headline to set
	 */
	public void setHeadline(String headline) {
		this.headline = headline;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * @param industry
	 *            the industry to set
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * @return the currentShare
	 */
	public String getCurrentShare() {
		return currentShare;
	}

	/**
	 * @param currentShare
	 *            the currentShare to set
	 */
	public void setCurrentShare(String currentShare) {
		this.currentShare = currentShare;
	}

	/**
	 * @return the numConnections
	 */
	public String getNumConnections() {
		return numConnections;
	}

	/**
	 * @param numConnections
	 *            the numConnections to set
	 */
	public void setNumConnections(String numConnections) {
		this.numConnections = numConnections;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the specialties
	 */
	public String getSpecialties() {
		return specialties;
	}

	/**
	 * @param specialties
	 *            the specialties to set
	 */
	public void setSpecialties(String specialties) {
		this.specialties = specialties;
	}

	/**
	 * @return the associations
	 */
	public String getAssociations() {
		return associations;
	}

	/**
	 * @param associations
	 *            the associations to set
	 */
	public void setAssociations(String associations) {
		this.associations = associations;
	}

	/**
	 * @return the honors
	 */
	public String getHonors() {
		return honors;
	}

	/**
	 * @param honors
	 *            the honors to set
	 */
	public void setHonors(String honors) {
		this.honors = honors;
	}

	/**
	 * @return the languages
	 */
	public String getLanguages() {
		return languages;
	}

	/**
	 * @param languages
	 *            the languages to set
	 */
	public void setLanguages(String languages) {
		this.languages = languages;
	}

	/**
	 * @return the pictureUrl
	 */
	public String getPictureUrl() {
		return pictureUrl;
	}

	/**
	 * @param pictureUrl
	 *            the pictureUrl to set
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	/**
	 * @return the publicProfileUrl
	 */
	public String getPublicProfileUrl() {
		return publicProfileUrl;
	}

	/**
	 * @param publicProfileUrl
	 *            the publicProfileUrl to set
	 */
	public void setPublicProfileUrl(String publicProfileUrl) {
		this.publicProfileUrl = publicProfileUrl;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the numRecommenders
	 */
	public String getNumRecommenders() {
		return numRecommenders;
	}

	/**
	 * @param numRecommenders
	 *            the numRecommenders to set
	 */
	public void setNumRecommenders(String numRecommenders) {
		this.numRecommenders = numRecommenders;
	}

	/**
	 * @return the pastPositions as String for simplicity
	 */
	public String getPastPositions() {
		StringBuilder past = new StringBuilder();
		if (this.pastPositions != null) {
			for (int i = 0; i < this.pastPositions.length; i++) {
				if (this.pastPositions[i] != null
						&& !(this.pastPositions[i].equalsIgnoreCase(currPosition)))
					past.append(this.pastPositions[i]).append("<br/>");
			}
		}
		return past.toString();
	}

	/**
	 * @param pastPositions
	 *            the pastPositions to set
	 */
	public void setPastPositions(String[] pastPositions) {
		this.pastPositions = pastPositions;
	}

	/**
	 * @return the currPosition
	 */
	public String getCurrPosition() {
		return currPosition;
	}

	/**
	 * @param currPosition
	 *            the currPosition to set
	 */
	public void setCurrPosition(String currPosition) {
		this.currPosition = currPosition;
	}

}
