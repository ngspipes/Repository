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

import support.configurator.JSONConfigurator;
import support.configurator.XMLConfigurator;
import support.descriptors.xml.xmlObject.XMLException;
import configurators.IConfigurator;
import exceptions.ConfiguratorException;

public class ConfiguratorManager {
	
	public static IConfigurator xmlFactory(String content) throws ConfiguratorException {
		try{
			return new XMLConfigurator(content);
		} catch (XMLException e) {
			throw new ConfiguratorException("Error instantiating XMLConfigurator!", e);
		}
	}
	
	public static IConfigurator jsonFactory(String content) throws ConfiguratorException {
		try{
			return new JSONConfigurator(content);
		} catch (JSONException e) {
			throw new ConfiguratorException("Error instantiating JSONConfigurator!", e);
		}
	}

}
