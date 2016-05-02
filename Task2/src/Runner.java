import java.util.Random;


public class Runner {

	final static int EDGE = 100000;
	
	public static void main(String[] args) {
		Random m = new Random();
		int[] items = new int[EDGE];
		
		for (int i = 0, size = items.length; i < size; i++) {
			items[i] = m.nextInt(5000);
		}
		
		// Start sorting
		long start = System.currentTimeMillis();
		
		for (int i = 0, size = items.length; i < size; i++) {
			for (int j = 0; j < size - 1; j++) {
				if (items[j + 1] < items[j]) {
					int tmp = items[j];
					items[j] = items[j + 1];
					items[j + 1] = tmp;
				}
			}
		}
		
		long end = System.currentTimeMillis();
		
		log(end - start); // ~ 48854ms
		
	}
	
	static void log(Object smth) {
		System.out.println(smth);
	}
	

}
