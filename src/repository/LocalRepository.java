package repository;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import support.Support;
import utils.IO;
import configurators.IConfigurator;
import descriptors.IToolDescriptor;
import exceptions.RepositoryException;

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
    		throw new RepositoryException("Erro loading tool " + toolName + "!", e);
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
    		throw new RepositoryException("Erro loading tool " + toolName + "!", e);
    	}
	}
	
	private File FindConfigFile(String toolName, String configuratorName) throws RepositoryException {
		File toolDir = new File(this.location + "/" + toolName);
		
		String configName = configuratorName + ".";
		FileFilter filter = (f)->f.getName().startsWith(configName)&&!f.isDirectory();
		File[] config = toolDir.listFiles(filter);
		
		if(config==null || config.length==0)
			throw new RepositoryException("Inexistent configurator name " + configuratorName);
		
		return config[0];
	}
	
}
