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
