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
