package support.configurator;

import java.util.LinkedList;
import java.util.List;

import support.descriptors.xml.xmlObject.XMLArray;
import support.descriptors.xml.xmlObject.XMLException;
import support.descriptors.xml.xmlObject.XMLObject;
import configurators.Configurator;

public class XMLConfigurator extends Configurator{
	
	public static final String NAME_XML_TAG = "name";
	public static final String BUILDER_XML_TAG = "builder";
	public static final String URI_XML_TAG = "uri";
	public static final String SETUP_XML_TAG = "setup";

	private static List<String> getSetup(XMLArray setup)throws XMLException {
		List<String> setups = new LinkedList<>();
		
		for(int i=0; i<setup.length(); ++i)
			setups.add(setup.getString(i));

		return setups;
	}

	protected final XMLObject xml;

	public XMLConfigurator(String xmlContent) throws XMLException {
		this(new XMLObject(xmlContent));
	}

	public XMLConfigurator(XMLObject xml)throws XMLException{
		super(	xml.getString(NAME_XML_TAG), xml.getString(BUILDER_XML_TAG), 
				xml.getString(URI_XML_TAG), getSetup(xml.getXMLArray(SETUP_XML_TAG)));
		this.xml = xml;
	}


	public XMLObject getXMLObject(){
		return xml;
	}
}
