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

import descriptors.ICommandDescriptor;
import descriptors.ToolDescriptor;
import support.descriptors.xml.xmlObject.XMLArray;
import support.descriptors.xml.xmlObject.XMLException;
import support.descriptors.xml.xmlObject.XMLObject;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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
	
	private static Collection<String> getDocumentation(XMLObject xml) throws XMLException {
		Collection<String> docs = new LinkedList<>();
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
