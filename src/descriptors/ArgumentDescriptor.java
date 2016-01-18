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
	private final int order;
	
	
	public ArgumentDescriptor(String name, String description,  String type, boolean required, int order) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.required = required;
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
