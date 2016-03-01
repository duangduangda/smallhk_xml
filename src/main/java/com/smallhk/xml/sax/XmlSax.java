package com.smallhk.xml.sax;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.smallhk.xml.sax.handler.XmlSAXHandler;

public class XmlSax {

	public void saxDocument() throws ParserConfigurationException, SAXException, IOException{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		InputStream is = new FileInputStream(new File("person.xml"));
		parser.parse(is, new XmlSAXHandler());
	}
	
	public void readXml() throws SAXException, IOException{
		XMLReader reader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
		DefaultHandler handler = new XmlSAXHandler();
		reader.setContentHandler(handler);
		reader.setErrorHandler(handler);
		reader.parse("person.xml");
	}
	
	
}
