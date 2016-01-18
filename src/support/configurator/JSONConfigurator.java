package support.configurator;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import configurators.Configurator;

public class JSONConfigurator  extends Configurator{
	
	public static final String NAME_JSON_KEY = "name";
	public static final String BUILDER_JSON_KEY = "builder";
	public static final String URI_JSON_KEY = "uri";
	public static final String SETUP_JSON_KEY = "setup";

	private static List<String> getSetup(JSONArray setup)throws JSONException{
		List<String> setups = new LinkedList<>();
		
		for(int i=0; i<setup.length(); ++i)
			setups.add(setup.getString(i));

		return setups;
	}

	protected final JSONObject json;

	public JSONConfigurator(String jsonContent) throws JSONException{
		this(new JSONObject(jsonContent));
	}

	public JSONConfigurator(JSONObject json)throws JSONException{
		super(	json.getString(NAME_JSON_KEY), json.getString(BUILDER_JSON_KEY),
				json.getString(URI_JSON_KEY), getSetup(json.getJSONArray(SETUP_JSON_KEY)));
		this.json = json;
	}


	public JSONObject getJSONObject(){
		return json;
	}
		
}
