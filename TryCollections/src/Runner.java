import by.epamlab.utils.Handler;

public class Runner {
	final static String SRC_DIR = "src/";
	final static String SRC_FILE = "in.txt";
	final static String MSG_PART_CONTAINED = "is";
	final static String MSG_PART_IS_NOT_CONTAINED = "isn't";
	final static String MSG_COL_HAS_WORD = "A word `%s` %s contained in the collection";
	final static String MSG_NUM_REPETITIONS_WORD = " and repeated %d times";

	public static void main(String[] args) {
		Handler handler = new Handler(SRC_DIR + SRC_FILE);
		
		// Debug
		/*for(Map.Entry<String, Integer> entry: wordStats.entrySet()) {
			System.out.println(entry.getKey() + " repeated " + entry.getValue() + " times");
		}*/
		
		String[] words = {"is", "talk", "bear", "awesome", "language"};
		
		for(String word: words) {
			boolean isWordContained = handler.isWordContained(word);
			
			System.out.format(MSG_COL_HAS_WORD, word, isWordContained ? MSG_PART_CONTAINED : MSG_PART_IS_NOT_CONTAINED);
			
			if (isWordContained) {
				System.out.format(MSG_NUM_REPETITIONS_WORD, handler.getNumRepetitions(word));
			}
			
			System.out.println();
		}

	}

}
