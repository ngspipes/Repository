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
package descriptors;

import rutils.Utils;


public class OutputDescriptor implements IOutputDescriptor{

	public static final String INDEPENDENT_TYPE = "independent";
	public static final String FILE_DEPENDENT_TYPE = "file_dependent";
	public static final String DIRECTORY_DEPENDENT_TYPE = "directory_dependent";	

	protected static String getValue(String type, String value) {
		return (value == null) ? "" : value;
	}
	
	
	private ICommandDescriptor originCommand;
	public ICommandDescriptor getOriginCommand(){ return originCommand; }
	public void setOriginCommand(ICommandDescriptor originCommand){ this.originCommand = originCommand; }
	
	private final String name;
	private final String description;
	private final String value;
	private final String argumentName;
	private final String type;
	
	public OutputDescriptor(String name, String description, String value,
							String type, String argumentName) {
		this.name = name;
		this.description = description;
		this.value = value;
		this.type = type;
		this.argumentName = argumentName;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getValue() {
		return value;
	}
	
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getArgumentName() {
		return argumentName;
	}

	@Override
	public boolean equals(Object o){
		if(o == null || !(o instanceof IOutputDescriptor))
			return false;
		
		if(this == o)
			return true;
		
		IOutputDescriptor other = (IOutputDescriptor)o;
		
		String myName = this.getName();
		String otherName = other.getName();
		
		ICommandDescriptor myCommand = this.getOriginCommand();
		ICommandDescriptor otherCommand = other.getOriginCommand();
			
		return Utils.equals(myName, otherName) && Utils.equals(myCommand, otherCommand);
	}
	
}
