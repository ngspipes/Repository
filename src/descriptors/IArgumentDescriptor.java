package descriptors;

public interface IArgumentDescriptor {
	
	public ICommandDescriptor getOriginCommand();
	
	public void setOriginCommand(ICommandDescriptor originCommand);
	
	public String getName();
	
	public String getType();
	
	public boolean getRequired();

	public String getDescription();

	public int getOrder();
	
}
