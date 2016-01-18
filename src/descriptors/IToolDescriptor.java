package descriptors;

import java.util.Collection;
import java.util.List;

import repository.IRepository;

public interface IToolDescriptor {

public String getName();
	
	public IRepository getOriginRepository();
	
	public void setOriginRepository(IRepository originRepository);

	public String getAuthor();
	
	public String getVersion();
	
	public int getRequiredMemory();
	
	public Collection<String> getDocumentaton();
	
	public String getDescription();
	
	public List<ICommandDescriptor> getCommands();
	
	public ICommandDescriptor getCommand(String commandName);
	
}
