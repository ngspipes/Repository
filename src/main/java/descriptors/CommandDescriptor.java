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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Utils;

public class CommandDescriptor implements ICommandDescriptor{

	private IToolDescriptor originTool;
	public IToolDescriptor getOriginTool(){ return originTool; }
	public void setOriginTool(IToolDescriptor originTool){ this.originTool = originTool; }
	
	private final String name;
	private final String command;
	private final String description;
	private final String argumentsComposer;
	private final List<IArgumentDescriptor> arguments;
	private final List<IOutputDescriptor> outputs;
	private final Map<String, IArgumentDescriptor> args;
	private final Map<String, IOutputDescriptor> otps;
	private final int priority;
	
	public CommandDescriptor(String name, String command, String description, String argumentsComposer, 
							List<IArgumentDescriptor> arguments, List<IOutputDescriptor> outputs, int priority) {
		this.name = name;
		this.command = command;
		this.description = description;
		this.argumentsComposer = argumentsComposer;
		this.arguments = arguments;
		this.outputs = outputs;
		this.args = new HashMap<>();
		this.otps = new HashMap<>();
		this.priority = priority;
		
		for(IArgumentDescriptor arg : arguments){
			arg.setOriginCommand(this);
			args.put(arg.getName(), arg);
		}
			
		for(IOutputDescriptor out : outputs){
			out.setOriginCommand(this);
			otps.put(out.getName(), out);
		}
			
	}
	
	@Override
	public String getName(){
		return name;
	}
	
	@Override
	public String getCommand() {
		return command;
	}

	@Override
	public List<IArgumentDescriptor> getArguments() {
		return arguments;
	}

	@Override
	public List<IOutputDescriptor> getOutputs() {
		return outputs;
	}
	
	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public String getArgumentsComposer(){
		if (argumentsComposer == null)
			return originTool.getArgumentsComposer();

		return argumentsComposer;
	}


	@Override
	public IArgumentDescriptor getArgument(String argumentName) {
		return args.get(argumentName);
	}

	@Override
	public IOutputDescriptor getOutput(String outputName) {
		return otps.get(outputName);
	}

	public int getPriority(){
		return priority;
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null || !(o instanceof ICommandDescriptor))
			return false;
		
		if(this == o)
			return true;
		
		ICommandDescriptor other = (ICommandDescriptor)o;
		
		String myName = this.getName();
		String otherName = other.getName();
		
		IToolDescriptor myTool = this.getOriginTool();
		IToolDescriptor otherTool = other.getOriginTool();
			
		return Utils.equals(myName, otherName) && Utils.equals(myTool, otherTool);
	}
	
}
