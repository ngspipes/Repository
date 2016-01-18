package descriptors;

import utils.Utils;


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
