package configurators;

import java.util.List;

import repository.IRepository;


public class Configurator implements IConfigurator{
	
	private IRepository originRepository;
	public IRepository getOriginRepository(){ return originRepository; }
	public void setOriginRepository(IRepository originRepository){ this.originRepository = originRepository; }
	
	private final String name;
	private final String builder;
	private final String uri;
	private final List<String> setup;
	
	public Configurator(String name, String builder, String uri, List<String> setup) {
		this.name = name;
		this.builder = builder;
		this.uri = uri;
		this.setup = setup;
	}
	
	@Override
	public String getName(){
		return name;
	}
	
	@Override
	public String getBuilder(){
		return builder;
	}
	
	@Override
	public String getUri(){
		return uri;
	}
	
	@Override
	public List<String> getSetup(){
		return setup;
	}

}
