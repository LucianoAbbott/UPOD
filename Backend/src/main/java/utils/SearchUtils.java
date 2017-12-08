package utils;

import datatypes.FrequencyMap;

/**
 * Utility functions used in the relevance sort
 * @author luciano
 *
 */
public class SearchUtils {

	/**
	 * Remove all non alpha characters, then make the string lower case
	 * 
	 * @param s
	 * @return
	 */
	// TODO Remove unhelpful search terms such as 'it' 'the' 'a' 'an'
	public static String cleanQuery(String s) {
		String result = s.replaceAll("[^a-zA-Z ]", "");
		result = result.toLowerCase();
		return result;
	}

	/**
	 * Calculate the frequency of words in the string storing the total number of
	 * words under
	 * 
	 * @param s
	 * @return
	 */
	public static FrequencyMap countWords(String s) {
		FrequencyMap frequencyMap = new FrequencyMap();
		for (String word : s.split(" ")) {
			frequencyMap.put(word);
		}
		return frequencyMap;
	}
}
