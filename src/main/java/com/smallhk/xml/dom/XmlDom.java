package com.smallhk.xml.dom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlDom {

	private static Logger logger = null;
	
	public XmlDom(){
		BasicConfigurator.configure();
		PropertyConfigurator.configure("log4j.properties");
		logger = Logger.getLogger(XmlDom.class); 
	}
	
	
	public Document generateDocument() throws ParserConfigurationException{
		logger.debug("start to build document....");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();
		logger.info("successfully generate document..");
		return document;
	}
	
	public void createXml() throws ParserConfigurationException, FileNotFoundException, TransformerException{
		Document document = generateDocument();
		logger.debug("start to build xml....");
		
		Element root = document.createElement("employees");
		document.appendChild(root);

		Element employee1 = document.createElement("employee");
		Element name1 = document.createElement("name");
		name1.appendChild(document.createTextNode("duangduangda"));
		Element sarary1 = document.createElement("sarary");
		sarary1.appendChild(document.createTextNode("1000"));
		employee1.appendChild(name1);
		employee1.appendChild(sarary1);
		root.appendChild(employee1);

		Element employee2 = document.createElement("employee");
		Element name2 = document.createElement("name");
		name2.appendChild(document.createTextNode("eric"));
		Element sarary2 = document.createElement("sarary");
		sarary2.appendChild(document.createTextNode("2000"));
		employee2.appendChild(name2);
		employee2.appendChild(sarary2);
		root.appendChild(employee2);

		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		DOMSource source = new DOMSource(document);
		transformer.setOutputProperty(OutputKeys.ENCODING, "gbk");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		PrintWriter pw = new PrintWriter(new FileOutputStream("employee.xml"));
		StreamResult result = new StreamResult(pw);
		transformer.transform(source, result);
		logger.info("successfully generate xml..");
	}
	
	public Document file2Document(String path) throws ParserConfigurationException, SAXException, IOException{
		return file2Document(new File(path));
	}

	public Document file2Document(File file) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(file);
		return document;
	}

	public Document file2Document(InputStream is) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(is);
		return document;
	}

	public void readDocument(String path) throws ParserConfigurationException, SAXException, IOException{
		doReadDocument(file2Document(path));
	}

	public void readDocument(File file) throws ParserConfigurationException, SAXException, IOException{
		doReadDocument(file2Document(file));
	}

	public void readDocument(InputStream is) throws ParserConfigurationException, SAXException, IOException{
		doReadDocument(file2Document(is));
	}
	
	private void doReadDocument(Document document) {
		if(null == document){
			System.err.println("document can not be null");
			return ;
		}
		Element root = document.getDocumentElement();
		NodeList subNodes = root.getChildNodes();
		getElements(subNodes);
	}

	private void getElements(NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if(!"#text".equals(node.getNodeName())){
				System.out.println("------" + node.getNodeName() + "------");
			}
			getAttributes(node);
			NodeList subnodes = node.getChildNodes();
			if (subnodes != null) {
				getElements(subnodes);
			}
			if(node instanceof Element){
				System.out.println(node.getTextContent().trim());
			}
//			if(node instanceof Text){//use org.w3c.dom.Node to parse xml,the tag '\n' as a node
//				System.out.println("############");
//			}
		}
	}

	private void getAttributes(Node node) {
		NamedNodeMap map = node.getAttributes();
		if(null == map){
			return;
		}
		for(int i = 0;i < map.getLength();i++){
			System.out.println(map.item(i).getNodeName() + ":" + map.item(i).getNodeValue());
		}
	}
}
