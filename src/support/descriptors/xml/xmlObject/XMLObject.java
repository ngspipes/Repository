package support.descriptors.xml.xmlObject;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.xml.sax.InputSource;

public class XMLObject extends XML {
	
	private final Element xmlElem;
	
	public XMLObject(String xmlContent) throws XMLException{
		try{
			DocumentBuilderFactory fctr = DocumentBuilderFactory.newInstance();
			DocumentBuilder bldr = fctr.newDocumentBuilder();
			InputSource insrc = new InputSource(new StringReader(xmlContent));
			
			xmlElem =  (Element) bldr.parse(insrc).getFirstChild();
			
		}catch(Exception e){
			throw new XMLException("Error instanciating XMLObject", e);
		}
	}
	
	protected XMLObject(Element xmlElem){
		this.xmlElem = xmlElem;
	}
	
	public int getInt(String tag)throws XMLException{
		return super.getInt(xmlElem.getElementsByTagName(tag).item(0));
	}

	public char getChar(String tag) throws XMLException{
		return super.getChar(xmlElem.getElementsByTagName(tag).item(0));
	}
	
	public boolean getBoolean(String tag) throws XMLException{
		return super.getBoolean(xmlElem.getElementsByTagName(tag).item(0));
	}
	
	public String getString(String tag) throws XMLException{
		return super.getString(xmlElem.getElementsByTagName(tag).item(0));
	}

	public XMLObject getXMLObject(String tag) throws XMLException{
		return super.getXMLObject(xmlElem.getElementsByTagName(tag).item(0));
	}
	
	public XMLArray getXMLArray(String tag) throws XMLException{
		return super.getXMLArray(xmlElem.getElementsByTagName(tag).item(0));
	}
	
	public boolean has(String tag){
		return xmlElem.getElementsByTagName(tag).getLength() == 0;
	}
	
}
