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

import configurators.IConfigurator;
import descriptors.IToolDescriptor;
import exceptions.RepositoryException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import support.Support;
import utils.IO;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class LocalRepository extends Repository {
	
	public static final String NAME = "Local";
    private static final String TOOLS_NAMES_KEY = "toolsName";
    private static final String CONFIGURATORS_NAMES_KEY = "configuratorsFileName";
    private static final String TOOLS_NAMES_FILE = "Tools.json";
	private static final String CONFIGURATORS_NAMES_FILE = "Configurators.json";
    private static final String DESCRIPTOR_NAME  = "Descriptor";
	private static final String LOGO_FILE_NAME = "Logo.png";
	
	
	private static File getDescriptor(File tool) throws RepositoryException{
    	File[] descriptor = tool.listFiles((file)-> !file.isDirectory() && getFileName(file).equals(DESCRIPTOR_NAME));
    	
    	if(descriptor.length == 0)
    		throw new RepositoryException("Invalid Tool folder!\nTool folder must contain a descriptor file with name Descriptor.");
    	
    	return descriptor[0];
    }
	
    private static String getFileName(File file){
    	return file.getName().split("\\.")[0];
    }
  
    private static String getConfiguratorType(File configurator){
    	return getType(configurator);
    }
    
    private static String getDescriptorType(File descriptor){
    	return getType(descriptor);
    }
    
    private static String getType(File file){
		String name = file.getName();
		
		int startIndex = name.lastIndexOf(".") + ".".length(); 
		
		return name.substring(startIndex);
    }
    
    
	public LocalRepository(String repositoryDir){
		super(repositoryDir, NAME);
	}

	public LocalRepository(){
		super(NAME);
	}
	
	
	private File getToolDirectory(String toolName){
		return new File(this.location + "/" + toolName);
	}
	
	@Override
	protected String loadToolLogo(String toolName)throws RepositoryException {
		try{
			File logoFile = new File(getToolDirectory(toolName) + "/" + LOGO_FILE_NAME);
			
			if(!logoFile.exists())
				return null;
			
			return logoFile.toURI().toURL().toString();
			
		}catch(MalformedURLException e){
			throw new RepositoryException("MalFormed toolLogo uri!", e);
		}
	}

	@Override
	protected Collection<String> loadToolsName() throws RepositoryException {
		String toolsNames;
		try{
			toolsNames = IO.read(this.location + "/" + TOOLS_NAMES_FILE);	
		}catch(IOException ex){
			throw new RepositoryException("Error reading tools names file", ex);
		}

		try{
			JSONArray names = new JSONObject(toolsNames).getJSONArray(TOOLS_NAMES_KEY);
			List<String> tools = new LinkedList<String>();
			for(int i=0; i<names.length(); ++i)
				tools.add(names.getString(i));
			
			return tools;
		}catch(JSONException ex){
			throw new RepositoryException("Invalid tools name file", ex);
		}
	}
	
	@Override
	protected IToolDescriptor loadTool(String toolName) throws RepositoryException {
		try{
    		File toolDir = getToolDirectory(toolName);
        	
        	File descriptor = getDescriptor(toolDir);
        	String descriptorType = getDescriptorType(descriptor);
        	String descriptorContent = IO.read(descriptor.getPath());
           
        	return Support.getToolDescriptor(descriptorType, descriptorContent);	
    	}catch(Exception e){
    		throw new RepositoryException("Error loading tool " + toolName + "!", e);
    	}
	}
	
	@Override
	protected Collection<String> loadConfiguratorsNameFor(String toolName) throws RepositoryException {
		String configuratorsNames;
		try{
			configuratorsNames = IO.read(this.location + "/" + toolName + "/" + CONFIGURATORS_NAMES_FILE);	
		}catch(IOException ex){
			throw new RepositoryException("Error reading configurators names file", ex);
		}

		try{
			JSONArray names = new JSONObject(configuratorsNames).getJSONArray(CONFIGURATORS_NAMES_KEY);
			List<String> configs = new LinkedList<String>();
			for(int i=0; i<names.length(); ++i)
				configs.add(names.getString(i));
			
			return configs;
		}catch(JSONException ex){
			throw new RepositoryException("Invalid tools name file", ex);
		}
	}
	
	@Override
	protected IConfigurator loadConfigurationFor(String toolName, String configuratorName) throws RepositoryException {
		try{
			File config = FindConfigFile(toolName, configuratorName);
        	String configuratorType = getConfiguratorType(config);
        	String configuratorContent = IO.read(config.getPath());
           
        	return Support.getConfigurator(configuratorType, configuratorContent);	
    	}catch(Exception e){
    		throw new RepositoryException("Error loading tool " + toolName + "!", e);
    	}
	}
	
	private File FindConfigFile(String toolName, String configuratorName) throws RepositoryException {
		File toolDir = new File(this.location + "/" + toolName);
		
		String configName = configuratorName + ".";
		FileFilter filter = (f)->f.getName().startsWith(configName)&&!f.isDirectory();
		File[] config = toolDir.listFiles(filter);
		
		if(config==null || config.length==0)
			throw new RepositoryException("Nonexistent configurator name " + configuratorName);
		
		return config[0];
	}
	
}
