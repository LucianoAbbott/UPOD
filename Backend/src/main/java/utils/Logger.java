package utils;

import datatypes.Page;

/**
 * Functions used to print system logs
 *
 */
public class Logger {
	/**
	 * Log message to system when a page is updated
	 * Preconditions: 
	 *		(Page) page - the page that was updated
	 * Postconditions: log is printed to system
	 * Exceptions: None.
	 * Date last changed:
	 * @author
	 */
	public static void logUpdate(Page page) {
		if (page == null) {
			System.out.println("Error: update page with null page");
		} else {
			System.out.println("Updating page #" + page.getId() + ", at URL:" + page.getUrl() + ", Title: " + page.getTitle());
		}
	}
	
	/**
	 * Log message to system when a page is deleted
	 * Preconditions: 
	 *		(Page) page - the page that was deleted
	 * Postconditions: log is printed to system
	 * Exceptions: None.
	 * Date last changed:
	 * @author
	 */
	public static void logDelete(int id) {
		System.out.println("Deleting page #" + id);
	}
	
	/**
	 * Log message to system when a page is requested
	 * Preconditions: 
	 *		(Page) page - the page that was requested
	 * Postconditions: log is printed to system
	 * Exceptions: None.
	 * Date last changed:
	 * @author
	 */
	public static void logGet(Page page) {
		if (page == null) {
			System.out.println("Error: get page returned null");
		} else {
			System.out.println("Getting page #" + page.getId() + ", at URL:" + page.getUrl() + ", Title: " + page.getTitle());
		}
	}

	/**
	 * Log message to system when a page is search for with a query string.
	 * Preconditions: 
	 *		(String) query - search query used
	 * Postconditions: log is printed to system
	 * Exceptions: None.
	 * Date last changed:
	 * @author
	 */
	public static void logSearch(String query) {
		if (query == null) {
			System.out.println("Error: search performed with bad query");
		} else {
			System.out.println("Search performed with query :\"" + query + "\"");
		}
	}
}
