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
