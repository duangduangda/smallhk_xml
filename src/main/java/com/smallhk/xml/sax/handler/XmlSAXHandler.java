package com.smallhk.xml.sax.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlSAXHandler extends DefaultHandler {
	private StringBuffer sbf;
	
    @Override  
    public void startDocument() throws SAXException {  
    	sbf = new StringBuffer();
        System.out.println("---->startDocument() is invoked...");  
        super.startDocument();  
    }  
      
    @Override  
    public void endDocument() throws SAXException {  
        System.out.println("---->endDocument() is invoked...");  
        super.endDocument();  
    }  
      
    @Override  
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {  
        System.out.println("-------->startElement() is invoked...");  
        System.out.println("\n 元素: " + "["+qName+"]" +" 开始解析!");
        // 打印出属性信息
        for ( int i = 0; i < attributes.getLength(); i++ ) {
            System.out.println("\t属性名称:" + attributes.getLocalName(i)
                + " 属性值:" + attributes.getValue(i));
        }
    }  
  
    @Override  
    public void endElement(String uri, String localName, String qName) throws SAXException {  
        //打印出非空的元素内容并将StringBuffer清空                  
        String nullStr="";
          if (!sbf.toString().trim().equals(nullStr)){
             System.out.println("\t内容是: " + sbf.toString().trim());
          }
          sbf.setLength(0);
          //打印元素解析结束信息
          System.out.println("元素: "+"["+qName+"]"+" 解析结束!");  
    }  
      
    @Override  
    public void characters(char[] ch, int start, int length) throws SAXException {  
        System.out.println("------------>characters() is invoked...");  
        //add element content to sbf               
        sbf.append(ch,start,length);
    }  
    
    @Override 
    public void warning(SAXParseException exception ) {
        System.out.println("*******WARNING******");
        System.out.println("\t行:\t" + exception.getLineNumber());
        System.out.println("\t列:\t" + exception.getColumnNumber());
        System.out.println("\t错误信息:\t" + exception.getMessage());
        System.out.println("********************");
    }
    
    @Override 
    public void error(SAXParseException exception ) throws SAXException{
        System.out.println("******* ERROR ******");
        System.out.println("\t行:\t" + exception.getLineNumber());
        System.out.println("\t列:\t" + exception.getColumnNumber());
        System.out.println("\t错误信息:\t" + exception.getMessage());
        System.out.println("********************");
    }
    
    @Override 
    public void fatalError(SAXParseException exception ) throws SAXException {
        System.out.println("******** FATAL ERROR ********");
        System.out.println("\t行:\t" + exception.getLineNumber());
        System.out.println("\t列:\t" + exception.getColumnNumber());
        System.out.println("\t错误信息:\t" + exception.getMessage());
        System.out.println("*****************************");
    }
}
