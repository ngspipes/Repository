package support.descriptors.xml;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import support.descriptors.xml.xmlObject.XMLArray;
import support.descriptors.xml.xmlObject.XMLException;
import support.descriptors.xml.xmlObject.XMLObject;
import descriptors.ICommandDescriptor;
import descriptors.ToolDescriptor;

public class XMLToolDescriptor extends ToolDescriptor{

	public static final String NAME_XML_TAG = "name";
	private static final String REQUIRED_MEMORY_XML_TAG = "requiredMemory";
	private static final String VERSION_XML_TAG = "version";
	private static final String AUTHOR_XML_TAG = "author";
	private static final String DESCRIPTION_XML_TAG = "description";
	private static final String DOCUMENTATION_XML_TAG = "documentation";
	public static final String COMMANDS_XML_TAG = "commands";
	

	private static List<ICommandDescriptor> getCommands(XMLObject xml)throws XMLException{
		List<ICommandDescriptor> commands = new LinkedList<>();

		XMLArray cmds = xml.getXMLArray(COMMANDS_XML_TAG);
		for(int i=0; i<cmds.length(); ++i)
			commands.add(new XMLCommandDescriptor(cmds.getXMLObject(i)));

		return commands;
	}
	
	private static final Collection<String> getDocumentation(XMLObject xml) throws XMLException {
		Collection<String> docs = new LinkedList<String>();
		XMLArray array = xml.getXMLArray(DOCUMENTATION_XML_TAG);
		
		for(int i=0; i<array.length();++i)
			docs.add(array.getString(i));
		
		return docs;
	}
	
	protected final XMLObject xml;

	public XMLToolDescriptor(String xmlContent) throws XMLException{
		this(new XMLObject(xmlContent));
	}

	public XMLToolDescriptor(XMLObject xml)throws XMLException{
		this(xml, getCommands(xml));
	}

	protected XMLToolDescriptor(XMLObject xml, List<ICommandDescriptor> commands) throws XMLException{
		super(xml.getString(NAME_XML_TAG), xml.getInt(REQUIRED_MEMORY_XML_TAG), 
				xml.getString(VERSION_XML_TAG), xml.getString(DESCRIPTION_XML_TAG), xml.getString(AUTHOR_XML_TAG),
				getDocumentation(xml), commands);
		this.xml = xml;
	}

	public XMLObject getXMLObject(){
		return xml;
	}

}
