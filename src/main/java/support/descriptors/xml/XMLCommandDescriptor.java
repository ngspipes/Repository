/*-
 * Copyright (c) 2016, NGSPipes Team <ngspipes@gmail.com>
 * All rights reserved.
 *
 * This file is part of NGSPipes <http://ngspipes.github.io/>.
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
		LinkedList<IArgumentDescriptor> arguments = new LinkedList<>();
		XMLArray args = xml.getXMLArray(ARGUMENTS_XML_TAG);

		for(int i=0; i<args.length(); ++i)
			arguments.addLast(new XMLArgumentDescriptor(args.getXMLObject(i), i));

		return arguments;
	}

	private static List<IOutputDescriptor> getOutputs(XMLObject xml) throws XMLException{
		LinkedList<IOutputDescriptor> outputs = new LinkedList<>();
		XMLArray otps = xml.getXMLArray(OUTPUTS_XML_TAG);

		for(int i=0; i<otps.length(); ++i)
			outputs.addLast(new XMLOutputDescriptor(otps.getXMLObject(i)));

		return outputs;
	}

	private static String getArgumentComposer(XMLObject xml) throws XMLException {
		if(xml.has(ARGUMENTS_COMPOSER_XML_TAG))
			return xml.getString(ARGUMENTS_COMPOSER_XML_TAG);
		return null;
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
				xml.getString(DESCRIPTION_XML_TAG), getArgumentComposer(xml), args, outputs, xml.getInt(PRIORITY_XML_TAG));
		this.xml = xml;
	}

	public XMLObject getXMLObject(){
		return xml;
	}

}
