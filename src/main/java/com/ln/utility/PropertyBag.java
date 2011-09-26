package com.ln.utility;

import java.util.Properties;
import java.io.*;

/**
 * File for setting and getting properties from lndemo.properties file
 * 
 * @author parampreetsethi
 * 
 */
public class PropertyBag {

	static {
		loadProperties();
	}

	/**
	 * @return the property
	 */
	public static String getProperty(String key) {
		if (propFile == null)
			loadProperties();
		return propFile.getProperty(key);
	}

	/**
	 * @param property
	 *            the property to set
	 */
	public static void setProperty(String property, String key) {
		if (propFile != null)
			propFile.setProperty(property, key);
	}

	private static Properties propFile = null;

	public static void loadProperties() {
		if (propFile == null) {
			propFile = new Properties();
			try {
				propFile.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(Constants.PROP_FILE));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}
	}
}
