package com.smallhk.xml.dom4j;


import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class XmlDom4jTest {

	@Test
	public void testGenerateDocument(){
		XmlDom4J dom4j=new XmlDom4J();
		dom4j.generateDocument();
	}
	
	@Test
	public void testModifyDocument(){
		XmlDom4J dom4j = new XmlDom4J();
		dom4j.modifyDocument();
	}
	
	@Test
	public void testReadDocument(){
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read("catalog.xml");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		assertNotNull(doc);
		Element rootElement = doc.getRootElement();
		assertNotNull(rootElement);
		assertEquals("catalog",rootElement.getName());
	}
	
	@Test
	public void testReadElements(){
		XmlDom4J dom = new XmlDom4J();
		dom.readElements();
	}
	
	@Test
	public void testGetRootElement(){
		XmlDom4J dom = new XmlDom4J();
		assertNotNull(dom.getRootElement());
		assertEquals("catalog", dom.getRootElement().getName());
	}
	
	@Test
	public void testTreeWalk(){
		XmlDom4J dom = new XmlDom4J();
		dom.treeWalk();
	}
	
	@Test
	public void testVist(){
		XmlDom4J dom = new XmlDom4J();
		dom.visit();
	}
	
	/**
	 * <p> xpath supoort testing
	 */
	
	@Test
	@SuppressWarnings("unchecked")
	public void testNodes(){
		XmlDom4J dom = new XmlDom4J();
		List<Node> nodes = dom.readDocument().selectNodes("//article");
		assertNotNull(nodes);
		assertEquals(2, nodes.size());
	}
	
	@Test
	public void testSingleNode1(){
		XmlDom4J dom = new XmlDom4J();
		@SuppressWarnings("unused")
		Node node = dom.readDocument().selectSingleNode("//article/@level");
		fail("There are two article nodes");
	}
	
	@Test
	public void testTitle(){
		XmlDom4J dom = new XmlDom4J();
		assertEquals("XML Zone", dom.title());
	}
	
	@Test
	public void testArticle(){
		XmlDom4J dom = new XmlDom4J();
		dom.article();
	}
	
	@Test
	public void testString2Xml(){
		new XmlDom4J().String2Xml();
	}
	
	@Test
	public void testXml2String(){
		new XmlDom4J().xml2String();
	}
	
	@Test
	public void testCreateDocument() throws IOException{
		Document document = new XmlDom4J().createDocument();
		XMLWriter output;
		output = new XMLWriter(new FileWriter(new File("author.xml")));
		output.write(document);
		output.close();
	}
	
	@Test
	public void testSimpleOutputXmlFile() throws IOException{
		new XmlDom4J().simpleOutputXmlFile("author.xml");
	}
	
	@Test
	public void testPrettyOutputXmlFile() throws IOException{
		new XmlDom4J().prettyOutputXmlFile("author.xml");
	}
	
	@Test
	public void testCompactOutputXmlFile() throws IOException{
		new XmlDom4J().compactOutputXmlFile("author.xml");
	}
}
