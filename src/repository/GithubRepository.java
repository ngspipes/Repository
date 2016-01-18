package repository;

import java.net.HttpURLConnection;
import java.util.Collection;

import repository.UriBasedRepository.IFactory;
import support.Support;
import configurators.IConfigurator;
import descriptors.IToolDescriptor;
import exceptions.RepositoryException;

public class GithubRepository extends Repository {
	
	public static final String NAME = "Github";
	private static final String CONNECTION_BASE_URI = "https://raw.githubusercontent.com"; 
	private static final String LOGO_FILE_NAME = "Logo.png";
	
	
	public static <T> T getObject(String uri, IFactory<T> factory) throws RepositoryException {
		try {
			HttpURLConnection connection = UriBasedRepository.getConnection(uri);
			String content = UriBasedRepository.readStream(connection);

			return factory.getObj("json", content);
		} catch (Exception e) {
			throw new RepositoryException("Error loading objects", e);
		}
	}


	private String connectionUri;
	
	
	public GithubRepository(String location) {
		super(location, NAME);
		setConnectionUri(location);
	}

	public GithubRepository() {
		this(NAME);
	}

	
	private void setConnectionUri(String location) {
		String repoLocation = location.substring(location.indexOf(".com/") + ".com".length());
		connectionUri = CONNECTION_BASE_URI + repoLocation + "/master";		
	}
	
	public void setLocation(String location){
		super.setLocation(location);
		setConnectionUri(location);
	}
	
	@Override
	protected String loadToolLogo(String toolName) throws RepositoryException {
		return this.connectionUri + "/" + toolName + "/" + LOGO_FILE_NAME;
	}

	@Override
	protected Collection<String> loadToolsName() throws RepositoryException {
		return UriBasedRepository.getStringCollection(this.connectionUri + "/Tools.json", "toolsName");
	}

	@Override
	protected IToolDescriptor loadTool(String toolName) throws RepositoryException {
		String descriptorUri = this.connectionUri + "/" + toolName + "/Descriptor.json";
		try {
			return getObject(descriptorUri, Support::getToolDescriptor);
		} catch (Exception e) {
			throw new RepositoryException("Error getting tool", e);
		}
	}

	@Override
	protected Collection<String> loadConfiguratorsNameFor(String toolName) throws RepositoryException {
		return UriBasedRepository.getStringCollection(this.connectionUri + "/" + toolName +  
													"/Configurators.json", "configuratorsFileName");
	}

	@Override
	protected IConfigurator loadConfigurationFor(String toolName, String configuratorName) throws RepositoryException {
		String configuratorUri = this.connectionUri + "/"  + toolName +  "/" + configuratorName + ".json";

		return getObject(configuratorUri, Support::getConfigurator);
	}

}
