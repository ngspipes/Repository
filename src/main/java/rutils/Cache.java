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
package rutils;

import java.util.HashMap;
import java.util.Map;

public class Cache<K, V> {
	
	private final Object lock = new Object();
	private Map<K, V> cache = new HashMap<>();
	
	
	public V get(K key){
		synchronized (lock) {
			return (cache.containsKey(key))? cache.get(key) : null;		
		}
	}
	
	public void remove(K key){
		synchronized (lock) {
			if(cache.containsKey(key))
				cache.remove(key);
		}
	}
	
	public void add(K key, V value){
		synchronized (lock) {
			cache.put(key, value);
		}
	}
	
	public void clear(){
		synchronized (lock) {
			cache = new HashMap<>();
		}
	}

}
