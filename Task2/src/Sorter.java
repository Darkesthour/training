
public class Sorter implements Runnable {
	private int[] items;
	
	public Sorter(int[] items) {
		this.items = items;
	}

	@Override
	public void run() {
		for (int i = 0, size = items.length; i < size; i++) {
			for (int j = 0; j < size - 1; j++) {
				if (items[j + 1] < items[j]) {
					int tmp = items[j];
					items[j] = items[j + 1];
					items[j + 1] = tmp;
				}
			}
		}
	}
	
	public int[] getItems() {
		return items;
	}

}
