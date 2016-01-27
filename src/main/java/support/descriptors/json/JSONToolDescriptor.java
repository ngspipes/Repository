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
