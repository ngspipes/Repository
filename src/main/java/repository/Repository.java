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
import java.util.LinkedList;

import utils.Cache;
import utils.Utils;
import configurators.IConfigurator;
import descriptors.IToolDescriptor;
import exceptions.RepositoryException;

public abstract class Repository implements IRepository {
	
	protected final Cache<String, String> imagesCache = new Cache<>();
	protected final Cache<String, IToolDescriptor> toolsCache = new Cache<>();
	protected final Cache<String, IConfigurator> configuratorsCache = new Cache<>();
	protected final Cache<String, Collection<String>> configuratorsNamesCache = new Cache<>();
	protected Collection<String> toolsName;
	protected String location;
	protected final String type;
	
	public Repository(String location, String type){
		this.type = type;
		this.location = location;
	}
	
	public Repository(String type){
		this(null, type);
	}
	
	
	@Override
	public String getType(){
		return type;
	}
	
	@Override
	public String getLocation(){
		return location;
	}
	
	@Override
	public void setLocation(String location){
		this.location = location;
		
		imagesCache.clear();
		toolsCache.clear();
		configuratorsCache.clear();
		configuratorsNamesCache.clear();
		toolsName = null;
	}
	
	@Override
	public String getToolLogo(String toolName) {
		String toolLogo = null;
		
		if((toolLogo=imagesCache.get(toolName))==null){
			
			try{
				toolLogo = loadToolLogo(toolName);	
			}catch(RepositoryException e){ /*In case of fail tool logo will be null*/}
			
			imagesCache.add(toolName, toolLogo);
		}
		
		return toolLogo;
	}
	
	@Override
	public Collection<String> getToolsName() throws RepositoryException {
		if(toolsName==null)
			toolsName = loadToolsName();
			
		return toolsName;
	}
	
	@Override
	public Collection<IToolDescriptor> getAllTools() throws RepositoryException {
		Collection<IToolDescriptor> tools = new LinkedList<>();
		
		for(String name : getToolsName())
			tools.add(getTool(name));
		
		return tools;
	}
	
	@Override
	public IToolDescriptor getTool(String toolName) throws RepositoryException {
		IToolDescriptor tool;
		
		if((tool=toolsCache.get(toolName))==null){
			tool = loadTool(toolName);
			tool.setOriginRepository(this);
			toolsCache.add(toolName, tool);
		}
		
		return tool;
	}
	
	@Override
	public Collection<String> getConfiguratorsNameFor(String toolName) throws RepositoryException {
		Collection<String> names;
		
		if((names=configuratorsNamesCache.get(toolName))==null){
			names = loadConfiguratorsNameFor(toolName);
			configuratorsNamesCache.add(toolName, names);
		}
		
		return names;
	}
	
	@Override
	public Collection<IConfigurator> getConfigurationsFor(String toolName) throws RepositoryException {
		Collection<String> configuratorsName = getConfiguratorsNameFor(toolName);
		LinkedList<IConfigurator> configurators = new LinkedList<>();

		for (String configuratorName : configuratorsName)
			configurators.add(getConfigurationFor(toolName, configuratorName));

		return configurators;
	}
	
	@Override
	public IConfigurator getConfigurationFor(String toolName, String configuratorName) throws RepositoryException {
		IConfigurator configurator;
		
		String key = toolName + "-" + configuratorName;
		if((configurator=configuratorsCache.get(key))==null){
			configurator = loadConfigurationFor(toolName, configuratorName);
			configurator.setOriginRepository(this);
			configuratorsCache.add(key, configurator);
		}
		
		return configurator;
	}

	@Override
	public boolean equals(Object o){
		if(o == null || !(o instanceof IRepository))
			return false;
		
		if(this == o)
			return true;
		
		IRepository other = (IRepository)o;
		
		String myType = this.getType();
		String otherType = other.getType();
		
		String myLocation = this.getLocation();
		String otherLocation = other.getLocation();
		
		return Utils.equals(myType, otherType) && Utils.equals(myLocation, otherLocation);
	}
	
	protected abstract String loadToolLogo(String toolName) throws RepositoryException;
	protected abstract Collection<String> loadToolsName() throws RepositoryException;
	protected abstract IToolDescriptor loadTool(String toolName) throws RepositoryException;
	protected abstract Collection<String> loadConfiguratorsNameFor(String toolName) throws RepositoryException;
	protected abstract IConfigurator loadConfigurationFor(String toolName, String configuratorName) throws RepositoryException;
	
}
