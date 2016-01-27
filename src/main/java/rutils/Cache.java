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
