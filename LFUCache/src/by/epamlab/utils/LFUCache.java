package by.epamlab.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LFUCache {
	
	private int size;
	private double evictionFactor;
	
	private Map<String, Integer> vals;
	private List<List<String>> frequencies;
	
	public LFUCache(int size, double evictionFactor) {
		this.size = size;
		this.evictionFactor = evictionFactor;
		
		initStorages();
	}

	private void initStorages() {
		vals = new HashMap<>();
		frequencies = new LinkedList<List<String>>();
		
		for(int i = 0; i < size; i++) {
			frequencies.add(new LinkedList<String>());
		}
	}
	
	public void put(String key, int val) {
		// If the map already contains this key just update a value
		if (vals.containsKey(key)) {
			vals.put(key, val);
			
			return;
		}
		
		vals.put(key, val);
		
		insertIntoFrequence(key);
	}
	
	private void insertIntoFrequence(String key) {
		if (vals.size() > size) {
			int entriesOut = (int) (size * evictionFactor);
			
			for(int i = 0; i < size; i++) {
				List<String> freqList = frequencies.get(i);
				
				for(Iterator<String> it = freqList.iterator(); it.hasNext() && entriesOut > 0; entriesOut--) {
					String keyTmp = it.next();
					
					it.remove();
					
					// Removes key from the map
					vals.remove(keyTmp);
				}
			}
		}
		
		frequencies.get(0).add(key);
	}
	
	public List<List<String>> getFrequencies() {
		return frequencies;
	}
	
	public int get(String key) {
		int result = -1; // By default, the key isn't in the map
		
		if (vals.containsKey(key)) {
			changeFrequence(key);
			
			result = vals.get(key);
		}
		
		return result; 
	}
	
	/*
	 * Attention! If element is located at last frequency list then on reaching 
	 * maximum cache size the first entered elements will be removed first
	 */
	private void changeFrequence(String key) {
		
		outerLoop:
		for(int i = 0; i < size; i++) {
			// If the key is located at last frequency list do nothing
			if (i == size - 1) return;
			
			List<String> freqList = frequencies.get(i);
			
			for(Iterator<String> it = freqList.iterator(); it.hasNext();) {
				String keyTmp = it.next();
				
				if (keyTmp.equals(key)) {
					// removes key from frequency list
					it.remove();
					
					// and set it to the next
					frequencies.get(i+1).add(keyTmp);
					
					// or use return instead
					break outerLoop;
				}
			}
		}
	}

}
