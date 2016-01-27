package support.descriptors.xml;

import support.descriptors.xml.xmlObject.XMLException;
import support.descriptors.xml.xmlObject.XMLObject;
import descriptors.ArgumentDescriptor;

public class XMLArgumentDescriptor extends ArgumentDescriptor{

	public static final String NAME_XML_TAG = "name";
	public static final String TYPE_XML_TAG = "argumentType";
	public static final String REQUIRED_XML_TAG = "isRequired";
	private static final String DESCRIPTION_XML_TAG = "description";

	protected final XMLObject xml;

	public XMLArgumentDescriptor(XMLObject xml, int order) throws XMLException{
		super(xml.getString(NAME_XML_TAG), xml.getString(DESCRIPTION_XML_TAG), xml.getString(TYPE_XML_TAG), xml.getBoolean(REQUIRED_XML_TAG), order);
		this.xml = xml;
	}

	public XMLArgumentDescriptor(String xmlContent, int order) throws XMLException {
		this(new XMLObject(xmlContent), order);
	}

	public XMLObject getXMLObject(){
		return xml;
	}

}
