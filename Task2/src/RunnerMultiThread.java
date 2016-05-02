import java.util.Random;


public class RunnerMultiThread  {
	final static int EDGE = 100000;

	public static void main(String[] args) {
		Random m = new Random();
		
		int[] items = new int[EDGE];
		
		for (int i = 0, size = items.length; i < size; i++) {
			items[i] = m.nextInt(5000);
		}
		
		// Start sorting
		long start = System.currentTimeMillis();
		
		int firstPartSize	= items.length / 2;
		int secondPartSize	= items.length - firstPartSize;
		
		int[] firstPart		= new int[firstPartSize];
		int[] secondPart	= new int[secondPartSize];
		
		System.arraycopy(items, 0, firstPart, 0, firstPartSize);
		System.arraycopy(items, firstPartSize, secondPart, 0, secondPartSize);
		
		Sorter s1 = new Sorter(firstPart);
		Thread t1 = new Thread(s1);
		
		Sorter s2 = new Sorter(secondPart);
		Thread t2 = new Thread(s2);
		
		t1.start();
		t2.start();
		
		// Block main thread
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Merge
		items = new int[EDGE];
		int firstCount = 0, secondCount = 0, resCount = 0;
		
		while(resCount != EDGE) {
			if (firstPart[firstCount] < secondPart[secondCount]) {
				items[resCount++] = firstPart[firstCount];
				
				if (firstCount < firstPartSize - 1) {
					firstCount++;
				}
			}
			else if (firstPart[firstCount] > secondPart[secondCount]) {
				items[resCount++] = secondPart[secondCount];
				
				if (secondCount < secondPartSize - 1) {
					secondCount++;
				}
			}
			else {
				items[resCount++] = firstPart[firstCount];
				items[resCount++] = secondPart[secondCount];
				
				if (firstCount < firstPartSize - 1) {
					firstCount++;
				}
				
				if (secondCount < secondPartSize - 1) {
					secondCount++;
				}
			}
		}
		
		long end = System.currentTimeMillis();
		
		log(end - start); // ~ 8754ms
	}
	
	static void log(Object smth) {
		System.out.println(smth);
	}

}
