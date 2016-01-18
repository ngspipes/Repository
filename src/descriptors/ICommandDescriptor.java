package descriptors;

import java.util.List;

public interface ICommandDescriptor {
	
	public IToolDescriptor getOriginTool();
	
	public void setOriginTool(IToolDescriptor originTool);
	
	public String getName();
	
	public String getCommand();
	
	public String getDescription();
	
	public String getArgumentsComposer();
	
	public List<IArgumentDescriptor> getArguments();
	
	public List<IOutputDescriptor> getOutputs();
	
	public IArgumentDescriptor getArgument(String argumentName);
	
	public IOutputDescriptor getOutput(String outputName);
	
	public int getPriority();
	
}
