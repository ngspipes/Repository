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

import utils.Utils;


public class ArgumentDescriptor implements IArgumentDescriptor{

	private ICommandDescriptor originCommand;
	public ICommandDescriptor getOriginCommand(){ return originCommand; }
	public void setOriginCommand(ICommandDescriptor originCommand){ this.originCommand = originCommand; }
	
	private final String name;
	private final String description;
	private final String type;
	private final boolean required;
	private final String argumentComposer;
	private final int order;
	
	
	public ArgumentDescriptor(String name, String description,  String type, boolean required, String argumentComposer, int order) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.required = required;
		this.argumentComposer = argumentComposer;
		this.order = order;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public boolean getRequired() {
		return required;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getArgumentComposer() {
		if (argumentComposer == null)
			return originCommand.getArgumentsComposer();

		return argumentComposer;
	}
	
	public int getOrder(){
		return order;
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null || !(o instanceof IArgumentDescriptor))
			return false;
		
		if(this == o)
			return true;
		
		IArgumentDescriptor other = (IArgumentDescriptor)o;
		
		String myName = this.getName();
		String otherName = other.getName();
		
		ICommandDescriptor myCommand = this.getOriginCommand();
		ICommandDescriptor otherCommand = other.getOriginCommand();
			
		return Utils.equals(myName, otherName) && Utils.equals(myCommand, otherCommand);
	}
	
}
