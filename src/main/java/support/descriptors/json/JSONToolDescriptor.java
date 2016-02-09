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
package support.descriptors.json;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import descriptors.ICommandDescriptor;
import descriptors.ToolDescriptor;

public class JSONToolDescriptor extends ToolDescriptor {

	public static final String NAME_JSON_KEY = "name";
	private static final String REQUIRED_MEMORY_JSON_KEY = "requiredMemory";
	private static final String VERSION_JSON_KEY = "version";
	private static final String DESCRIPTION_JSON_KEY = "description";
	private static final String AUTHOR_JSON_KEY = "author";
	private static final String DOCUMENTATION_JSON_KEY = "documentation";
	public static final String COMMANDS_JSON_KEY = "commands";

	private static List<ICommandDescriptor> getCommands(JSONObject json)throws JSONException{
		List<ICommandDescriptor> commands = new LinkedList<>();

		JSONArray cmds = json.getJSONArray(COMMANDS_JSON_KEY);
		for(int i=0; i<cmds.length(); ++i)
			commands.add(new JSONCommandDescriptor(cmds.getJSONObject(i)));

		return commands;
	}
	
	private static final Collection<String> getDocumentation(JSONObject json) throws JSONException {
		Collection<String> docs = new LinkedList<String>();
		JSONArray array = json.getJSONArray(DOCUMENTATION_JSON_KEY);
		
		for(int i=0; i<array.length();++i)
			docs.add(array.getString(i));
		
		return docs;
	}

	protected final JSONObject json;

	public JSONToolDescriptor(String jsonContent) throws JSONException{
		this(new JSONObject(jsonContent));
	}

	public JSONToolDescriptor(JSONObject json)throws JSONException{
		this(json,  getCommands(json));
	}

	protected JSONToolDescriptor(JSONObject json, List<ICommandDescriptor> commands) throws JSONException{
		super(json.getString(NAME_JSON_KEY), json.getInt(REQUIRED_MEMORY_JSON_KEY), 
				json.getString(VERSION_JSON_KEY), json.getString(DESCRIPTION_JSON_KEY), json.getString(AUTHOR_JSON_KEY),
				getDocumentation(json), commands);
		
		this.json = json;
	}

	public JSONObject getJSONObject(){
		return json;
	}

}
