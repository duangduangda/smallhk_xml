package com.smallhk.xml.jdom;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class XmlJDom {

	public Document createDocument(){
		Namespace ns = Namespace.getNamespace("linux","http://www.linux.org");
		Document document = new Document(
				new Element("persons",ns));
		Element root = document.getRootElement();
		
		Element person1 = new Element("person");
		person1.setAttribute("name","duangduangda");
		person1.setAttribute("age","20");
		person1.setAttribute("career","IT");
		person1.setText("I am an IT engineer");
		
		Element person2 = new Element("person");
		person2.setAttribute("name","eric");
		person2.setAttribute("age","22");
		person2.setAttribute("career","VC");
		person2.setText("I am a VCer");
		
		root.addContent(person1);
		root.addContent(person2);
		
		return document;
	}
	
	public void document2File() throws FileNotFoundException, IOException{
		Document document = createDocument();
		XMLOutputter fmt = new XMLOutputter();
		fmt.output(document,new FileOutputStream(new File("person.xml")));
	}
	
	public void document2Console(Document document) throws IOException{
		XMLOutputter fmt = new XMLOutputter();
		fmt.output(document, System.out);
	}
	
	public void saxDocument(File file,OutputStream out) throws JDOMException, IOException{
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(file);
		
		XMLOutputter fmt = new XMLOutputter();
		fmt.output(document, out);
	}
}
