package support.descriptors.xml.xmlObject;

import java.util.LinkedList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public abstract class XML {
	
	protected int getInt(Node node)throws XMLException{
		try{
			return Integer.parseInt(node.getTextContent());
		}catch(Exception e){
			throw new XMLException("Error parsing int value", e);
		}
	}

	protected char getChar(Node node) throws XMLException{
		try{
			return node.getTextContent().charAt(0);
		}catch(Exception e){
			throw new XMLException("Error parsing char value", e);
		}
	}
	
	protected boolean getBoolean(Node node) throws XMLException{
		try{
			return Boolean.parseBoolean(node.getTextContent());
		}catch(Exception e){
			throw new XMLException("Error parsing boolean value", e);
		}
	}
	
	protected String getString(Node node) throws XMLException{
		try{
			return node.getTextContent();
		}catch(Exception e){
			throw new XMLException("Error parsing String value", e);
		}
	}

	protected XMLObject getXMLObject(Node node) throws XMLException{
		try{
			return new XMLObject((Element)node);
		}catch(Exception e){
			throw new XMLException("Error parsing String value", e);
		}
	}
	
	protected XMLArray getXMLArray(Node node) throws XMLException{
		try{
			LinkedList<Node> childs = new LinkedList<Node>();
			node = node.getFirstChild();
	
			if(node!=null){
				while((node = node.getNextSibling()) != null)
					if (node.getNodeType() == Node.ELEMENT_NODE) 
						childs.add(node);	
			}
			
			return new XMLArray(childs);
		}catch(Exception e){
			throw new XMLException("Error parsing String value", e);
		}
	}

}
