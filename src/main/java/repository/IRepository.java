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
package repository;

import java.util.Collection;

import configurators.IConfigurator;
import descriptors.IToolDescriptor;
import exceptions.RepositoryException;

public interface IRepository {
	
	public String getLocation();
	
	public void setLocation(String location) throws RepositoryException;
	
	public String getType();
	
	public String getToolLogo(String toolName);
	
	public Collection<String> getToolsName() throws RepositoryException;
	
	public Collection<IToolDescriptor> getAllTools() throws RepositoryException;
	
	public IToolDescriptor getTool(String toolName) throws RepositoryException;
	
	public Collection<String> getConfiguratorsNameFor(String toolName) throws RepositoryException;
	
	public Collection<IConfigurator> getConfigurationsFor(String toolName) throws RepositoryException;
	
	public IConfigurator getConfigurationFor(String toolName, String configuratorName) throws RepositoryException;

}
