import java.util.List;

import by.epamlab.utils.LFUCache;

public class Runner {
	
	static void printFrequencies(LFUCache cache, String preMsg) {
		System.out.println(preMsg + "\n");
		
		List<List<String>> freqListOuter = cache.getFrequencies();
		
		for(int i = 0, sizeOuter = freqListOuter.size(); i < sizeOuter; i++) {
			System.out.println(i + 1 + " frequense:");
			
			List<String> freqListInner = freqListOuter.get(i);
			
			for(int j = 0, sizeInner = freqListInner.size(); j < sizeInner; j++) {
				System.out.print(freqListInner.get(j) + " ");
			}
			
			System.out.println("\n");
		}
	}

	public static void main(String[] args) {

		// Creates cache object
		LFUCache cache = new LFUCache(4, 0.8);
		
		// Put to it some values 
		cache.put("a", 1);
		cache.put("b", 2);
		cache.put("c", 3);
		cache.put("d", 4);
		
		// See what's going on in the frequencies
		printFrequencies(cache, "Just see to the frequencies after added initial set of data");
		
		// Initiates reaching maximum cache size
		cache.put("h", 7);
		
		// See what's going on in the frequencies
		printFrequencies(cache, "Add a new entry and initiates reaching maximum cache size. See what's happen");
		
		// Well, let's add another data and access to it
		cache.put("j", 125);
		
		System.out.println("Access to key `j` after adding of it. It contains a value " + cache.get("j") + "\n");
		
		// And let's see what's happen with the frequencies
		printFrequencies(cache, "Location of the keys after getting some of them");
		
		System.out.println("Access again to key `j`. It contains a value " + cache.get("j") + "\n");
		System.out.println("Access to key `a`. It contains a value " + cache.get("a") + "\n");
		System.out.println("Access to key `j`. It contains a value " + cache.get("j") + "\n");
		System.out.println("Access to key `h`. It contains a value " + cache.get("h") + "\n");
		
		printFrequencies(cache, "Location of the keys after getting some of them");
		
		cache.put("t", 1);
		cache.put("t", 7);
		
		printFrequencies(cache, "Location of the keys after adding a new ones and getting some of them");
		
		System.out.println("Access to key `t`. It contains a value " + cache.get("t") + "\n");
		
		cache.put("y", 444);
		
		printFrequencies(cache, "Location of the keys after adding a new ones and getting some of them");
	}

}
