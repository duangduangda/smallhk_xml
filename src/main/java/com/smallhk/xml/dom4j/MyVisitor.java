package com.smallhk.xml.dom4j;

import org.dom4j.Attribute;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Namespace;
import org.dom4j.ProcessingInstruction;
import org.dom4j.Text;
import org.dom4j.Visitor;

public class MyVisitor implements Visitor {

	public void visit(Document document) {
		// TODO Auto-generated method stub
		
	}

	public void visit(DocumentType documentType) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Element node) {
		System.out.println(node.getName());
	}

	public void visit(Attribute node) {
		System.out.println(node.getName());
		
	}

	public void visit(CDATA node) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Comment node) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Entity node) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Namespace namespace) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ProcessingInstruction node) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Text node) {
		System.out.println(node.getStringValue());
		
	}

}
