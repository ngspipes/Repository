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

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.LinkedList;

public abstract class XML {
	
	protected int getInt(Node node)throws XMLException{
		try{
			return Integer.parseInt(node.getTextContent());
		}catch(Exception e){
			throw new XMLException("Error parsing int value", e);
		}
	}

	protected char getChar(Node node) throws XMLException{
		try{
			return node.getTextContent().charAt(0);
		}catch(Exception e){
			throw new XMLException("Error parsing char value", e);
		}
	}
	
	protected boolean getBoolean(Node node) throws XMLException{
		try{
			return Boolean.parseBoolean(node.getTextContent());
		}catch(Exception e){
			throw new XMLException("Error parsing boolean value", e);
		}
	}
	
	protected String getString(Node node) throws XMLException{
		try{
			return node.getTextContent();
		}catch(Exception e){
			throw new XMLException("Error parsing String value", e);
		}
	}

	protected XMLObject getXMLObject(Node node) throws XMLException{
		try{
			return new XMLObject((Element)node);
		}catch(Exception e){
			throw new XMLException("Error parsing String value", e);
		}
	}
	
	protected XMLArray getXMLArray(Node node) throws XMLException{
		try{
			LinkedList<Node> childs = new LinkedList<>();
			node = node.getFirstChild();
	
			if(node!=null){
				while((node = node.getNextSibling()) != null)
					if (node.getNodeType() == Node.ELEMENT_NODE) 
						childs.add(node);	
			}
			
			return new XMLArray(childs);
		}catch(Exception e){
			throw new XMLException("Error parsing String value", e);
		}
	}

}
