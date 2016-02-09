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
package support.descriptors.xml.xmlObject;

import java.util.LinkedList;

import org.w3c.dom.Node;


public class XMLArray extends XML {

	private final LinkedList<Node> xmlElems;
	
	protected XMLArray(LinkedList<Node> xmlElems){
		this.xmlElems = xmlElems;
	}
	
	public int getInt(int index)throws XMLException{
		return super.getInt(xmlElems.get(index));
	}

	public char getChar(int index) throws XMLException{
		return super.getChar(xmlElems.get(index));
	}
	
	public boolean getBoolean(int index) throws XMLException{
		return super.getBoolean(xmlElems.get(index));
	}
	
	public String getString(int index) throws XMLException{
		return super.getString(xmlElems.get(index));
	}

	public XMLObject getXMLObject(int index) throws XMLException{
		return super.getXMLObject(xmlElems.get(index));
	}
	
	public XMLArray getXMLArray(int index) throws XMLException{
		return super.getXMLArray(xmlElems.get(index));
	}

	public int length(){
		return xmlElems.size();
	}

}
