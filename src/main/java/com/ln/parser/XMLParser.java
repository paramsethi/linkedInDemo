package com.ln.parser;

import java.io.CharArrayReader;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

/**
 * DOM XML parser
 * 
 * @author parampreetsethi
 * 
 */
public abstract class XMLParser {
	private static Logger logger = Logger.getLogger(XMLParser.class);

	/**
	 * DOM Parser
	 * 
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static Document getXMLDoc(String xml) throws Exception {
		try {
			// Get an instance of the parser
			// Create a factory
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			// Use document builder factory
			DocumentBuilder builder = factory.newDocumentBuilder();
			// Parse the document
			Reader reader = new CharArrayReader(xml.toCharArray());
			Document doc = builder.parse(new org.xml.sax.InputSource(reader));
			return doc;
		} catch (Exception e) {
			logger.error("An error occured while creating document ** ", e);
			throw new Exception(e.getMessage());
		}
	}
}
