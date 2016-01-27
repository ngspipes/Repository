package descriptors;

public interface IOutputDescriptor {
	
	public ICommandDescriptor getOriginCommand();
	
	public void setOriginCommand(ICommandDescriptor originCommand); 
	
	public String getName();
	
	public String getType();
	
	public String getArgumentName();

	public String getValue();

	public String getDescription();
	
}
