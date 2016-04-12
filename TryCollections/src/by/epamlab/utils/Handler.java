package by.epamlab.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Handler {
	final static String REGEX_DISTINGUISH_WORDS = "(\\W{2,}|\\s|\\n)";
	final static String MSG_ERR_FILE_NOT_FOUND = "File %s not found. The static text will be used instead%n";
	
	private Map<String, Integer> wordStats;
	
	public Handler(String file) {
		wordStats = new HashMap<>();
		Scanner sc = null;
		
		try {
			sc = new Scanner(new FileReader(new File(file)));
		} catch(FileNotFoundException e) {
			System.err.format(MSG_ERR_FILE_NOT_FOUND, file);
			
			// Fallback: uses a static text
			sc = new Scanner(getStaticText());
		}
		
		/*
		 * This regexp distinguishes words and specific words such as:
		 * 		abbreviated name - for example, R.J.Burger
		 * 		compound nouns - for example, spider-man
		 * 		words with apostrophe - i'm, isn't and etc.
		 */
		sc.useDelimiter(REGEX_DISTINGUISH_WORDS);
		
		while(sc.hasNext()) {
			String curWord = sc.next().toLowerCase();

			if (wordStats.containsKey(curWord)) {
				int oldNum = wordStats.get(curWord);
				
				wordStats.put(curWord, ++oldNum);
			} else {
				wordStats.put(curWord, 1);
			}
		}
		
		// Close scanner object
		sc.close();
	}
	
	/*
	 * Fallback
	 * 
	 * It's used when file with text isn't found
	 * */
	private String getStaticText() {
		StringBuilder text = new StringBuilder("A regular expression, specified as a string, ");
		
		text.append("must first be compiled into an instance of this class. ");
		text.append("The resulting pattern can then be used to create a Matcher ");
		text.append("object that can match arbitrary character sequences against ");
		text.append("the regular expression. All of the state involved in performing ");
		text.append("a match resides in the matcher, so many matchers can share the same pattern.");
		
		return text.toString();
	}
	
	public Map<String, Integer> getWordStats() {
		return wordStats;
	}
	
	public boolean isWordContained(String word) {
		return wordStats.containsKey(word);
	}
	
	public int getNumRepetitions(String word) {
		return isWordContained(word) ? wordStats.get(word) : 0;
	}

}
