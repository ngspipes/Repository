package support.descriptors.xml;

import java.util.LinkedList;
import java.util.List;

import support.descriptors.xml.xmlObject.XMLArray;
import support.descriptors.xml.xmlObject.XMLException;
import support.descriptors.xml.xmlObject.XMLObject;
import descriptors.CommandDescriptor;
import descriptors.IArgumentDescriptor;
import descriptors.IOutputDescriptor;

public class XMLCommandDescriptor extends CommandDescriptor{

	public static final String NAME_XML_TAG = "name";
	public static final String COMMAND_XML_TAG = "command";
	public static final String ARGUMENTS_XML_TAG = "arguments";
	public static final String OUTPUTS_XML_TAG = "outputs";
	private static final String DESCRIPTION_XML_TAG = "description";
	private static final String ARGUMENTS_COMPOSER_XML_TAG = "argumentsComposer";
	private static final String PRIORITY_XML_TAG = "priority";

	private static List<IArgumentDescriptor> getArguments(XMLObject xml) throws XMLException{
		LinkedList<IArgumentDescriptor> arguments = new LinkedList<IArgumentDescriptor>();
		XMLArray args = xml.getXMLArray(ARGUMENTS_XML_TAG);

		for(int i=0; i<args.length(); ++i)
			arguments.addLast(new XMLArgumentDescriptor(args.getXMLObject(i), i));

		return arguments;
	}

	private static List<IOutputDescriptor> getOutputs(XMLObject xml) throws XMLException{
		LinkedList<IOutputDescriptor> outputs = new LinkedList<IOutputDescriptor>();
		XMLArray otps = xml.getXMLArray(OUTPUTS_XML_TAG);

		for(int i=0; i<otps.length(); ++i)
			outputs.addLast(new XMLOutputDescriptor(otps.getXMLObject(i)));

		return outputs;
	}

	protected final XMLObject xml;

	public XMLCommandDescriptor(XMLObject xml) throws XMLException {
		this(xml, getArguments(xml), getOutputs(xml));
	}

	public XMLCommandDescriptor(String xmlContent) throws XMLException{
		this(new XMLObject(xmlContent));
	}

	protected XMLCommandDescriptor(XMLObject xml, List<IArgumentDescriptor> args, List<IOutputDescriptor> outputs) throws XMLException{
		super(xml.getString(NAME_XML_TAG), xml.getString(COMMAND_XML_TAG),
				xml.getString(DESCRIPTION_XML_TAG), xml.getString(ARGUMENTS_COMPOSER_XML_TAG), args, outputs, xml.getInt(PRIORITY_XML_TAG));
		this.xml = xml;
	}

	public XMLObject getXMLObject(){
		return xml;
	}

}
