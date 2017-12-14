package utils;

import static org.junit.Assert.*;

import org.junit.Test;

import datatypes.FrequencyMap;

public class SearchUtilsTest {
	@Test
	public void testCleanString() {
		String input = "Hello world! , a0s 4s drag.n";
		String expected = "hello world  as s dragn";
		
		String actual = SearchUtils.cleanQuery(input);

		assertEquals(expected, actual);
	}
	
	@Test
	public void testCountWords() {
		String input = "hi hi hi dog bad silly hello world";
		int expectedCount = 8;
		int expectedHi = 3;
		int expectedDog = 1;
		int expectedSilly = 1;
		int expectedHello = 1;
		int expectedWorld = 1;
		
		FrequencyMap actual = SearchUtils.countWords(input);
		
		assertEquals(expectedCount, actual.getWordCount());
		assertEquals(expectedHi, actual.get("hi"));
		assertEquals(expectedDog, actual.get("dog"));
		assertEquals(expectedSilly, actual.get("silly"));
		assertEquals(expectedHello, actual.get("hello"));
		assertEquals(expectedWorld, actual.get("world"));
	}
}
