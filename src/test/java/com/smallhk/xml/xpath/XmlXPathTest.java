package com.smallhk.xml.xpath;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlXPathTest {

	@Test
	public void testReadXml1() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("employee.xml");
		XPath xpath = XPathFactory.newInstance().newXPath();
		assertNotNull(document);
		assertNotNull(xpath);
		NodeList nodes = (NodeList) xpath.evaluate("/employees/employee", document, XPathConstants.NODESET);
		assertNotNull(nodes);
		assertTrue(nodes.getLength() > 0);
	}
	
	@Test
	public void testReadXml() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{
		XmlXPath xml = new XmlXPath();
		xml.readXml();
	}

}
