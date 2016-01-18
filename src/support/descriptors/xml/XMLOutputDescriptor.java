package support.descriptors.xml;

import support.descriptors.xml.xmlObject.XMLException;
import support.descriptors.xml.xmlObject.XMLObject;
import descriptors.OutputDescriptor;

public class XMLOutputDescriptor extends OutputDescriptor {

	public static final String NAME_XML_TAG = "name";
	public static final String VALUE_XML_TAG = "value";
	private static final String DESCRIPTION_XML_TAG = "description";
	private static final String TYPE_XML_TAG = "outputType";
	private static final String ARGUMENT_NAME_XML_TAG = "argument_name";

	private final XMLObject xml;

	public XMLOutputDescriptor(XMLObject xml) throws XMLException {
		super(xml.getString(NAME_XML_TAG), xml.getString(DESCRIPTION_XML_TAG),  
				OutputDescriptor.getValue(xml.getString(TYPE_XML_TAG), xml.getString(VALUE_XML_TAG)), 
				xml.getString(TYPE_XML_TAG), xml.getString(ARGUMENT_NAME_XML_TAG));

		this.xml = xml;
	}

	public XMLOutputDescriptor(String xmlContent) throws XMLException {
		this(new XMLObject(xmlContent));
	}

	public XMLObject getXMLObject(){
		return xml;
	}

}
