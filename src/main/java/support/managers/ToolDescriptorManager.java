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
package support.managers;

import org.json.JSONException;

import support.descriptors.json.JSONToolDescriptor;
import support.descriptors.xml.XMLToolDescriptor;
import support.descriptors.xml.xmlObject.XMLException;
import descriptors.IToolDescriptor;
import exceptions.DescriptorException;

public class ToolDescriptorManager {
	
	public static IToolDescriptor xmlFactory(String content) throws DescriptorException {
		try{
			return new XMLToolDescriptor(content);
		} catch (XMLException e) {
			throw new DescriptorException("Error instanciating XMLToolDescriptor!", e);
		}
	}
	
	public static IToolDescriptor jsonFactory(String content) throws DescriptorException {
		try{
			return new JSONToolDescriptor(content);
		} catch (JSONException e) {
			throw new DescriptorException("Error instanciating JSONToolDescriptor!", e);
		}
	}

}
