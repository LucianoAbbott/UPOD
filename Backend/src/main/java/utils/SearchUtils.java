package utils;

import datatypes.FrequencyMap;

/**
 * Utility functions used in the relevance sort
 * @author luciano
 *
 */
public class SearchUtils {
	
	/**
	 * Removes all non alpha characters, then makes the string lower case
	 * Preconditions:
	 * 		(string) s - a string of words to operate on.
	 * Postconditions:
	 *		returns (string) result - a clean string.
	 * Date last changed: 12/12/2017?
	 * @author Luciano Abbott
	 */
	// TODO Remove unhelpful search terms such as 'it' 'the' 'a' 'an'
	public static String cleanQuery(String s) {
		String result = s.replaceAll("[^a-zA-Z ]", "");
		result = result.toLowerCase();
		return result;
	}

	/**
	 * Calculates the frequency of words in the string storing the total number of
	 * words under
	 * Preconditions:
	 * 		(string) s - a string to calculate the word frequency of
	 * Postconditions:
	 * 		returns (FrequencyMap) frequencyMap - FrequencyMap object relating to the input string s.
	 * Date last changed: 12/12/2017?
	 * @author Luciano Abbott
	 */
	public static FrequencyMap countWords(String s) {
		FrequencyMap frequencyMap = new FrequencyMap();
		for (String word : s.split(" ")) {
			frequencyMap.put(word);
		}
		return frequencyMap;
	}
}
