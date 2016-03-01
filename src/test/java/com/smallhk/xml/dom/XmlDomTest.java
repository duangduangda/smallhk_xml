package com.smallhk.xml.dom;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlDomTest {
	
	@Test
	public void testGenerateDocument() throws ParserConfigurationException{
		XmlDom xmlDom = new XmlDom();
		Document document = xmlDom.generateDocument();
		assertNotNull(document);
	}

	@Test
	public void testCreateXml() throws FileNotFoundException, ParserConfigurationException, TransformerException{
		XmlDom dom = new XmlDom();
		dom.createXml();
	}
	@Test
	public void testFile2DocumentByPath() throws ParserConfigurationException, SAXException, IOException {
		XmlDom xmlDom = new XmlDom();
		Document document = xmlDom.file2Document("person.xml");
		assertNotNull(document);
	}
	
	@Test
	public void testFile2DocumentByFile() throws ParserConfigurationException, SAXException, IOException {
		XmlDom xmlDom = new XmlDom();
		Document document = xmlDom.file2Document(new File("person.xml"));
		assertNotNull(document);
	}
	
	@Test
	public void testFile2DocumentByStream() throws ParserConfigurationException, SAXException, IOException {
		XmlDom xmlDom = new XmlDom();
		Document document = xmlDom.file2Document(new FileInputStream(new File("person.xml")));
		assertNotNull(document);
	}
	
	@Test
	public void testRoot() throws ParserConfigurationException, SAXException, IOException{
		XmlDom xmlDom = new XmlDom();
		Document document = xmlDom.file2Document(new File("person.xml"));
		Element root = document.getDocumentElement();
		assertNotNull(root);
		assertEquals("linux:persons", root.getNodeName());
	}
	
	@Test
	public void testRootAttribute() throws ParserConfigurationException, SAXException, IOException{
		XmlDom xmlDom = new XmlDom();
		Document document = xmlDom.file2Document(new File("person.xml"));
		Element root = document.getDocumentElement();
		NamedNodeMap map = root.getAttributes();
		assertEquals(1, map.getLength());
		assertEquals("xmlns:linux", map.item(0).getNodeName());
		assertEquals("http://www.linux.org", map.item(0).getNodeValue());
	}
	
	@Test
	public void testNode() throws ParserConfigurationException, SAXException, IOException{
		XmlDom xmlDom = new XmlDom();
		Document document = xmlDom.file2Document(new File("person.xml"));
		Element root = document.getDocumentElement();
		NodeList nodes = root.getChildNodes();
		assertNotNull(nodes);
		assertEquals(5, nodes.getLength());
		assertEquals("#text", nodes.item(0).getNodeName());
		assertEquals("person", nodes.item(1).getNodeName());
		assertEquals("#text", nodes.item(2).getNodeName());
		assertEquals("person", nodes.item(3).getNodeName());
		assertEquals("#text", nodes.item(4).getNodeName());
	}
	
	@Test
	public void testReadDocument() throws ParserConfigurationException, SAXException, IOException{
		XmlDom xmlDom = new XmlDom();
		xmlDom.readDocument(new File("person.xml"));
	}
	
}
