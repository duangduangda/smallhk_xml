package com.smallhk.xml.dom4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlDom4J {

	private static final String xmlFile = "catalog.xml";
	
	public void generateDocument(){
		//Using Dom4j API factory method to create document instance 
		Document document = DocumentHelper.createDocument();
		//Create root element
		Element catalogElement = document.addElement("catalog");
		//Add comment
		catalogElement.addComment("An xml catalog");
		
		/* Element journal */
		Element journalElement = catalogElement.addElement("journal");
		journalElement.addAttribute("title", "XML Zone");
		journalElement.addAttribute("publisher", "IBM developerWorks");
		
		/* Element article */
		Element articleElement = journalElement.addElement("article");
		articleElement.addAttribute("level", "Intermediate");
		articleElement.addAttribute("date", "December-2001");
		
		/* Element title */
		Element titleElement = articleElement.addElement("title");
		titleElement.setText("Java configuration with XML Schema");
		
		/* Element author */
		Element authorElement = articleElement.addElement("author");

		/* Element title */
		Element firstnameElement = authorElement.addElement("firstname");
		firstnameElement.setText("Marcello");
		
		/* Element title */
		Element lastnameElement = authorElement.addElement("lastname");
		lastnameElement.setText("Vitaletti");
		
	    try {
			XMLWriter output = new XMLWriter(
			        new FileWriter(new File(xmlFile)));
			    output.write( document );
			    output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void modifyDocument(){
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(xmlFile);
			List<?> list = document.selectNodes("//artcile/@level");
			Iterator<?> it = list.iterator();
			while(it.hasNext()){
				Attribute attribute = (Attribute) it.next();
				if(attribute.getValue().equals("Intermediate")){
					attribute.setValue("Introductory");
				}
			}
			XMLWriter output = new XMLWriter(
				      new FileWriter(new File(xmlFile)));
				     output.write( document );
				     output.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public Document readDocument(){
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(new File(xmlFile));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}
	
	public Element getRootElement(){
		return readDocument().getRootElement();
	}
	
	/**
	 * <p>list the nodes with iterator
	 */
	public void readElements(){
		Document document = readDocument();
		Element root = document.getRootElement();
		for(Iterator<?> iterator = root.elementIterator();iterator.hasNext();){
			Element element = (Element) iterator.next();
			System.out.println(element.getName());
		}
	}
	
	/**
	 * <p>recursive treewalking to print all nodes
	 */
	public void treeWalk(){
		treeWalk(getRootElement());
	}

	public void treeWalk(Element element) {
		for(int i = 0,size = element.nodeCount();i < size;i++){
			Node node = element.node(i);
			if(node instanceof Element){
				System.out.println(node.getName());
				treeWalk((Element) node);
			}else{
				//do something
			}
		}
	}
	
	/**
	 * <p>using Visitor pattern to print all nodes
	 */
	public void visit(){
		Element root = readDocument().getRootElement();
		root.accept(new MyVisitor());
	}
	
	/**
	 * <p>xpath support
	 * 
	 */
	
	public String title(){
		Node node = readDocument().selectSingleNode("//journal/@title");
		return node.getText();
	}
	
	@SuppressWarnings("rawtypes")
	public void article(){
		List nodes = readDocument().selectNodes("//article/@level");
		for(Iterator it = nodes.listIterator();it.hasNext();){
			Attribute attribute = (Attribute) it.next();
			System.out.println(attribute.getValue());
		}
	}
	
	public void String2Xml(){
		Document document = readDocument();
		String text = document.asXML();
		System.out.println(text);
	}

	public void xml2String() {
		String text = "<person><name>duangduangda</name></person>";
		try {
			Document document = DocumentHelper.parseText(text);
			document.accept(new MyVisitor());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>Another document creation
	 */
	public Document createDocument(){
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("authors");
		root.addElement("author").addAttribute("name", "duangduangda").addText("he is a IT engineer");
		root.addElement("author").addAttribute("name", "eric").addText("he is a doctor");
		return document;
	}
	
	public void simpleOutputXmlFile(String filename) throws IOException{
		FileWriter out = new FileWriter(filename);
		readDocument().write(out);
	}
	
	public void prettyOutputXmlFile(String filename) throws IOException{
		Document document = readDocument();
		XMLWriter writer = new XMLWriter(new FileWriter(filename));
		writer.write(document);
		writer.close();
		
		//pretty format
		OutputFormat format = OutputFormat.createPrettyPrint();
		writer = new XMLWriter(System.out,format);
		writer.write(document);
		
	}
	
	public void compactOutputXmlFile(String filename) throws IOException{
		Document document = readDocument();
		XMLWriter writer = new XMLWriter(new FileWriter(filename));
		writer.write(document);
		writer.close();
		
		//compact format
		OutputFormat format = OutputFormat.createCompactFormat(); 
		writer = new XMLWriter(System.out,format);
		writer.write(document);
	}
}
