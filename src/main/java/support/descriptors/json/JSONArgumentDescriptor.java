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

import org.json.JSONException;
import org.json.JSONObject;

import descriptors.ArgumentDescriptor;

public class JSONArgumentDescriptor extends ArgumentDescriptor{

	public static final String NAME_JSON_KEY = "name";
	public static final String TYPE_JSON_KEY = "argumentType";
	public static final String REQUIRED_JSON_KEY = "isRequired";
	private static final String DESCRIPTION_JSON_KEY = "description";
	private static final String ARGUMENTS_COMPOSER_JSON_KEY = "argumentsComposer";

	private static String getArgumentComposer(JSONObject json) throws JSONException {
		if(json.has(ARGUMENTS_COMPOSER_JSON_KEY))
			return json.getString(ARGUMENTS_COMPOSER_JSON_KEY);
		return null;
	}



	protected final JSONObject json;


	public JSONArgumentDescriptor(String jsonContent, int order) throws JSONException{
		this(new JSONObject(jsonContent), order);
	}

	public JSONArgumentDescriptor(JSONObject json, int order) throws JSONException{
		super(json.getString(NAME_JSON_KEY), json.getString(DESCRIPTION_JSON_KEY), json.getString(TYPE_JSON_KEY),
				json.getBoolean(REQUIRED_JSON_KEY), getArgumentComposer(json), order);
		this.json = json;
	}

	public JSONObject getJSONObject(){
		return json;
	}

}
