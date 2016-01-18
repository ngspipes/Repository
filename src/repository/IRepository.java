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
