package repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import support.Support;
import configurators.IConfigurator;
import descriptors.IToolDescriptor;
import exceptions.RepositoryException;

public class UriBasedRepository extends Repository {

	@FunctionalInterface
	public static interface IFactory<T> {
		public T getObj(String type, String Content) throws Exception;
	}

	
	public static final String NAME = "UriBased";
	
	
	public static HttpURLConnection getConnection(String url)throws IOException{
		return (HttpURLConnection) new URL(url).openConnection();
	}

	public static String readStream(URLConnection conn) throws IOException{
		BufferedReader br = null; 
		String line;
		StringBuilder sb = new StringBuilder();

		try{
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			while ((line = br.readLine()) != null) 
				sb.append(line);

		} finally {
			if(br!=null)
				br.close();
		}

		return sb.toString();
	}

	public static Collection<String> getStringCollection(String uri, String arrayKey) throws RepositoryException {

		LinkedList<String> collection = new LinkedList<String>();
		try {
			HttpURLConnection connection = getConnection(uri);
			JSONObject json = new JSONObject(readStream(connection));
			JSONArray array = json.getJSONArray(arrayKey);

			for(int i=0; i< array.length(); ++i)
				collection.add(array.getString(i));

		} catch (JSONException | IOException e) {
			throw new RepositoryException("Error loading names", e);
		}
		return collection;
	}

	private static <T> T getObject(String uri, IFactory<T> factory) throws RepositoryException {
		try {
			HttpURLConnection connection = getConnection(uri);
			String type = connection.getHeaderField("NGSPipes-Type");
			String content = readStream(connection);

			return factory.getObj(type, content);
		} catch (Exception e) {
			throw new RepositoryException("Error loading objects", e);
		}
	}

		
	public UriBasedRepository(String repositoryUri){
		super(repositoryUri, NAME);
	}
	
	public UriBasedRepository(){
		super(NAME);
	}

	
	@Override
	protected String loadToolLogo(String toolName) throws RepositoryException {
		String logoUri = this.location + "/" + toolName + "/logo";
		try {
			HttpURLConnection connection = getConnection(logoUri);
			return readStream(connection);
		} catch (Exception e) {
			throw new RepositoryException("Error loading objects", e);
		}
	}
	
	@Override
	protected Collection<String> loadToolsName() throws RepositoryException {
		return getStringCollection(this.location, "toolsName");
	}

	@Override
	protected IToolDescriptor loadTool(String toolName) throws RepositoryException {
		String descriptorUri = this.location + "/" + toolName + "/descriptor";
		try {
			return getObject(descriptorUri, Support::getToolDescriptor);
		} catch (Exception e) {
			throw new RepositoryException("Error getting tool", e);
		}
	}
	
	@Override
	protected Collection<String> loadConfiguratorsNameFor(String toolName) throws RepositoryException {
		return getStringCollection(this.location + "/" + toolName +  "/configurators", "configuratorsFileName");
	}
	
	@Override
	protected IConfigurator loadConfigurationFor(String toolName, String configuratorName) throws RepositoryException {
		String configuratorUri = this.location + "/"  + toolName + "/configurators/" + configuratorName;

		return getObject(configuratorUri, Support::getConfigurator);
	}

}