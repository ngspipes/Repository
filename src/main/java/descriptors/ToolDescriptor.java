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

import repository.IRepository;
import utils.Utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToolDescriptor implements IToolDescriptor {

	private IRepository originRepository;
	public IRepository getOriginRepository(){ return originRepository; }
	public void setOriginRepository(IRepository originRepository){ this.originRepository = originRepository; }
	
	private final String name;
	private final int requiredMemory;
	private final String version;
	private final String description;
	private final String author; 
	private final Collection<String> documentation;
	private final List<ICommandDescriptor> commandsDescriptors;
	private final Map<String, ICommandDescriptor> cmds;
	
	
	
	public ToolDescriptor(String name, int requiredMemory, String version, String description, String author,
							Collection<String> documentation, List<ICommandDescriptor> commandsDescriptors) {
		super();
		this.name = name;
		this.requiredMemory = requiredMemory;
		this.version = version;
		this.description = description;
		this.author = author;
		this.documentation = documentation;
		this.commandsDescriptors = commandsDescriptors;
		this.cmds = new HashMap<String, ICommandDescriptor>();
		
		for(ICommandDescriptor cmd : commandsDescriptors){
			cmd.setOriginTool(this);
			cmds.put(cmd.getName(), cmd);
		}

	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getRequiredMemory() {
		return requiredMemory;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public Collection<String> getDocumentation() {
		return  documentation;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public List<ICommandDescriptor> getCommands() {
		return commandsDescriptors;
	}	
	
	@Override
	public String getAuthor() {
		return author;
	}

	@Override
	public ICommandDescriptor getCommand(String commandName){
		return cmds.get(commandName);
	}

	@Override
	public boolean equals(Object o){
		if(o == null || !(o instanceof IToolDescriptor))
			return false;
		
		if(this == o)
			return true;
		
		IToolDescriptor other = (IToolDescriptor)o;
		
		String myName = this.getName();
		String otherName = other.getName();
		
		IRepository myRepository = this.getOriginRepository();
		IRepository otherRepository = other.getOriginRepository();
			
		return Utils.equals(myName, otherName) && Utils.equals(myRepository, otherRepository);
	}
	
}
