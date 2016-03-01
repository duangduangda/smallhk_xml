package com.smallhk.xml.jdom;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.junit.Test;

public class XmlJDomTest {

	@Test
	public void testCreateDocument() {
		XmlJDom xmlJDom = new XmlJDom();
		Document doc = xmlJDom.createDocument();
		assertNotNull(doc);
	}
	
	@Test
	public void testDocument2Console() throws IOException{
		XmlJDom xmlJDom = new XmlJDom();
		Document document = xmlJDom.createDocument();
		xmlJDom.document2Console(document);
	}
	
	@Test
	public void testDocument2File() throws FileNotFoundException, IOException{
		XmlJDom xmlJDom = new XmlJDom(); 
		xmlJDom.document2File();
	}
	
	@Test
	public void testSaxDocument1() throws JDOMException, IOException{
		XmlJDom xmlJDom = new XmlJDom();
		xmlJDom.saxDocument(new File("person.xml"), System.out);
	}

	@Test
	public void testSaxDocument2() throws JDOMException, IOException{
		XmlJDom xmlJDom = new XmlJDom();
		xmlJDom.saxDocument(new File("person2.xml"), System.out);
		fail("Can not find the given xml file");
	}
}
