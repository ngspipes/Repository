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
import repository.UriBasedRepository.IFactory;
import support.Support;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;

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
		String location = this.connectionUri + "/" + toolName + "/" + LOGO_FILE_NAME;
		return existsLogo(location)? location : null;
	}

	private boolean existsLogo(String location) throws RepositoryException {
		try{
			URL url = new URL(location);
			HttpURLConnection huc =  (HttpURLConnection)  url.openConnection();
			huc.setRequestMethod("HEAD");

			return huc.getResponseCode() == HttpURLConnection.HTTP_OK;
		}catch(IOException e){
			throw new RepositoryException("Error checking tool logo", e);
		}
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
