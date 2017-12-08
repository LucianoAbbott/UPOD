package utils;

import datatypes.Page;

public class Logger {
	public static void logUpdate(Page page) {
		if (page == null) {
			System.out.println("Error: update page with null page");
		} else {
			System.out.println("Updating page #" + page.getId() + ", at URL:" + page.getUrl() + ", Title: " + page.getTitle());
		}
	}
	public static void logDelete(int id) {
		System.out.println("Deleting page #" + id);
	}

	public static void logGet(Page page) {
		if (page == null) {
			System.out.println("Error: get page returned null");
		} else {
			System.out.println("Getting page #" + page.getId() + ", at URL:" + page.getUrl() + ", Title: " + page.getTitle());
		}
	}

	public static void logSearch(String query) {
		if (query == null) {
			System.out.println("Error: search performed with bad query");
		} else {
			System.out.println("Search performed with query :\"" + query + "\"");
		}
	}
}
