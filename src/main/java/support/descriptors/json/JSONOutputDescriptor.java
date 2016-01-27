package support.descriptors.json;

import org.json.JSONException;
import org.json.JSONObject;

import descriptors.OutputDescriptor;


public class JSONOutputDescriptor extends OutputDescriptor{

	public static final String NAME_JSON_KEY = "name";
	public static final String VALUE_JSON_KEY = "value";
	private static final String DESCRIPTION_JSON_KEY = "description";
	private static final String TYPE_JSON_KEY = "outputType";
	private static final String ARGUMENT_NAME_JSON_KEY = "argument_name";

	private final JSONObject json;

	public JSONOutputDescriptor(JSONObject json) throws JSONException {
		super(	json.getString(NAME_JSON_KEY), json.getString(DESCRIPTION_JSON_KEY), 
				OutputDescriptor.getValue(json.getString(TYPE_JSON_KEY), json.getString(VALUE_JSON_KEY)), 
				json.getString(TYPE_JSON_KEY), json.getString(ARGUMENT_NAME_JSON_KEY));

		this.json = json;
	}

	public JSONOutputDescriptor(String jsonContent) throws JSONException {
		this(new JSONObject(jsonContent));
	}

	public JSONObject getJSONObject(){
		return json;
	}

}
