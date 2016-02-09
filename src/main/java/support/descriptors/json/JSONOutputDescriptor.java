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
