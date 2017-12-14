package datatypes;

import java.util.HashMap;

public class FrequencyMap {
	private HashMap<String, Integer> frequencyMap;
	private int wordCount;
	
	public FrequencyMap () {
		frequencyMap = new HashMap<String, Integer>();
		wordCount = 0;
	}
	
	public void put(String key) {
		if (frequencyMap.containsKey(key)) {
			frequencyMap.put(key, frequencyMap.get(key) + 1);
		} else {
			frequencyMap.put(key, 1);
		}
		wordCount++;
	}

	public int get(String key) {
		if (frequencyMap.containsKey(key)) return frequencyMap.get(key);
		return 0;
	}

	public int getWordCount() {
		return wordCount;
	}
}
