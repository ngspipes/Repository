package support.descriptors.json;

import org.json.JSONException;
import org.json.JSONObject;

import descriptors.ArgumentDescriptor;

public class JSONArgumentDescriptor extends ArgumentDescriptor{

	public static final String NAME_JSON_KEY = "name";
	public static final String TYPE_JSON_KEY = "argumentType";
	public static final String REQUIRED_JSON_KEY = "isRequired";
	private static final String DESCRIPTION_JSON_KEY = "description";

	protected final JSONObject json;

	public JSONArgumentDescriptor(String jsonContent, int order) throws JSONException{
		this(new JSONObject(jsonContent), order);
	}

	public JSONArgumentDescriptor(JSONObject json, int order) throws JSONException{
		super(json.getString(NAME_JSON_KEY), json.getString(DESCRIPTION_JSON_KEY), json.getString(TYPE_JSON_KEY), json.getBoolean(REQUIRED_JSON_KEY), order);
		this.json = json;
	}

	public JSONObject getJSONObject(){
		return json;
	}

}
