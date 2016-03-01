package com.smallhk.xml.sax;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

public class XmlSaxTest {

	@Test
	public void testSaxDocument() throws ParserConfigurationException, SAXException, IOException {
		XmlSax xmlSax = new XmlSax();
		xmlSax.saxDocument();
	}
	
	@Test
	public void testReadXml() throws SAXException, IOException{
		XmlSax xmlSax = new XmlSax();
		xmlSax.readXml();
	}

}
