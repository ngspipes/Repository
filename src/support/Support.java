package support;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import support.managers.ConfiguratorManager;
import support.managers.ToolDescriptorManager;
import configurators.IConfigurator;
import descriptors.IToolDescriptor;
import exceptions.ConfiguratorException;
import exceptions.DescriptorException;

public class Support {
	
	public static final List<String> SUPPORTED_TYPES = new LinkedList<>();;
	public static final String JSON_TYPE= "json";
	public static final String XML_TYPE= "xml";
	
	
	private static final Map<String, ConfiguratorFactory> CONFIGURATOR_FACTORIES = new HashMap<>();
	private static final Map<String, ToolDescriptorFactory> TOOL_DESCRIPTOR_FACTORIES = new HashMap<>();
	
	static{
		loadConfiguratorFactories();
		loadToolDescriptorFactories();
		
		SUPPORTED_TYPES.add(JSON_TYPE);
		SUPPORTED_TYPES.add(XML_TYPE);
	}
	
	
	//CONFIGURATORS
	@FunctionalInterface
	private static interface ConfiguratorFactory{
		public IConfigurator create(String content) throws ConfiguratorException;
	}
	
	private static void loadConfiguratorFactories(){
		CONFIGURATOR_FACTORIES.put(Support.JSON_TYPE, ConfiguratorManager::jsonFactory);
		CONFIGURATOR_FACTORIES.put(Support.XML_TYPE, ConfiguratorManager::xmlFactory);
	}
	
	public static IConfigurator getConfigurator(String type, String content) throws ConfiguratorException {
		return CONFIGURATOR_FACTORIES.get(type).create(content);
	}
	
	
	
	//TOOL DESCRIPTORS
	@FunctionalInterface
	public static interface ToolDescriptorFactory{
		public IToolDescriptor create(String content) throws DescriptorException;
	}

	private static void loadToolDescriptorFactories(){
		TOOL_DESCRIPTOR_FACTORIES.put(Support.JSON_TYPE, ToolDescriptorManager::jsonFactory);
		TOOL_DESCRIPTOR_FACTORIES.put(Support.XML_TYPE, ToolDescriptorManager::xmlFactory);
	}	

	public static IToolDescriptor getToolDescriptor(String type, String content) throws DescriptorException{
		return TOOL_DESCRIPTOR_FACTORIES.get(type).create(content);
	}
	
	

	
}
