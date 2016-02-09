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
