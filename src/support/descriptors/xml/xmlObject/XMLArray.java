package support.descriptors.xml.xmlObject;

import java.util.LinkedList;

import org.w3c.dom.Node;


public class XMLArray extends XML {

	private final LinkedList<Node> xmlElems;
	
	protected XMLArray(LinkedList<Node> xmlElems){
		this.xmlElems = xmlElems;
	}
	
	public int getInt(int index)throws XMLException{
		return super.getInt(xmlElems.get(index));
	}

	public char getChar(int index) throws XMLException{
		return super.getChar(xmlElems.get(index));
	}
	
	public boolean getBoolean(int index) throws XMLException{
		return super.getBoolean(xmlElems.get(index));
	}
	
	public String getString(int index) throws XMLException{
		return super.getString(xmlElems.get(index));
	}

	public XMLObject getXMLObject(int index) throws XMLException{
		return super.getXMLObject(xmlElems.get(index));
	}
	
	public XMLArray getXMLArray(int index) throws XMLException{
		return super.getXMLArray(xmlElems.get(index));
	}

	public int length(){
		return xmlElems.size();
	}

}
