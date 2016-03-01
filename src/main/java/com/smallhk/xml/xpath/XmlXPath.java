package com.smallhk.xml.xpath;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlXPath {
	
	public void readXml() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("employee.xml");
		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList nodes = (NodeList) xpath.evaluate("/employees/employee", document, XPathConstants.NODESET);
		for(int i = 0;i <nodes.getLength();i++){
			Node node = nodes.item(i);
			if(node instanceof Element){
				System.out.println("------" + node.getNodeName() + "------");
			}
			NodeList subNodes = node.getChildNodes();
			for(int j = 0;j < subNodes.getLength();j++){
				Node subNode = subNodes.item(j);
				if(subNode instanceof Element){
					System.out.println(subNode.getNodeName() + ":" + subNode.getTextContent());
				}
			}
		}
	}
}	
