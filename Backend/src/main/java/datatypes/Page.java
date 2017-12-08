package datatypes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Data type representing a complete page in the wiki
 * @author luciano abbott
 * @author Lauren Hepditch
 */
public class Page implements Comparable<Page>{
	
	private int pageId;
	private String title; 
	private String url; 
	private ArrayList<Section> sections;
	
	private double relevance; // the relevance a page has to a query string, used to sort the pages before returning them
	
	/**
	 * Constructor for class "Page"
	 * Preconditions:
	 *		(int) pageId - unique id number for the given page
	 *		(String) title - the page title
	 *		(String) url - the page url
	 * Postconditions: creates a new Page object.
	 * Exceptions: None.
	 * Date last changed: 12/01/2017
	 * @author Lauren Hepditch
	 */
	public Page(int pageId, String title, String url){
		
		this.pageId = pageId;
		this.title = title;
		this.url = url;
		this.sections = new ArrayList<Section>();
	}

	/**
	 * Constructor for class "Page"
	 * Preconditions:
	 *		(ResultSet) pageResult - the result of an query to the page table in the database
	 * Postconditions: creates a new Page object.
	 * Exceptions: SQLException.
	 * Date last changed: 12/01/2017
	 * @author 
	 */
	public Page(ResultSet pageResult) throws SQLException {
		this(pageResult.getInt("pageId"), 
				pageResult.getString("title"), 
				pageResult.getString("URL"));
	}

	/**
	 * Constructor for class "Page"
	 * Preconditions: None.
	 * Postconditions: returns an empty page object
	 * Exceptions: None.
	 * Date last changed: 12/01/2017
	 * @author Lauren Hepditch
	 */
	public Page(){
		pageId = 0;
		title = null;
		url = null;
		sections = new ArrayList<Section>();
	}
	
	/**
	 * Method for setting pageId of a page.
	 * Preconditions:
	 *		(int) pageId - integer to be set as the pageId
	 * Postconditions: A new (int) pageId of the Page is set.
	 * Exceptions: None.
	 * Date last changed: 12/01/2017
	 * @author Lauren Hepditch
	 */
	public void setId(int pageId) {
		this.pageId = pageId;
	}
	
	/**
	 * Method for getting pageId of a page.
	 * Preconditions: None.
	 * Postconditions: 
	 *		returns the (int) pageId of the Page.
	 * Exceptions: None.
	 * Date last changed: 12/01/2017
	 * @author Lauren Hepditch
	 */
	public int getId(){
		return this.pageId;	
	}
	
	/**
	 * Method for getting title of a page.
	 * Preconditions: None.
	 * Postconditions: 
	 *		returns the (String) title of the Page.
	 * Exceptions: None.
	 * Date last changed: 12/01/2017
	 * @author Lauren Hepditch
	 */
	public String getTitle(){
 		return this.title;	
	}
	
	/**
	 * Method for setting the pageId of a page.
	 * Preconditions:
	 *		(int) pageId - integer to be set as the pageId
	 * Postconditions: A new (int) pageId of the Page is set
	 * Exceptions: None.
	 * Date last changed: 12/01/2017
	 * @author Lauren Hepditch
	 */
	public String getUrl(){
		return this.url;	
	}
	
	/**
	 * Method for setting the title of a page.
	 * Preconditions:
	 *		(String) title - String to be set as the title of the Page
	 * Postconditions: A new (String) title is set for the Page.
	 * Exceptions: None.
	 * Date last changed: 12/01/2017
	 * @author Lauren Hepditch
	 */
	public void setTitle(String title){
		this.title = title;
	}
	
	/**
	 * Method for setting the sections of a page.
	 * Preconditions:
	 *		(ArrayList<Section>) sections - new sections to be set.
	 * Postconditions: A new (ArrayList<Section>) sections of the Page is set.
	 * Exceptions: None.
	 * Date last changed: 12/01/2017
	 * @author 
	 */
	public void setSections (ArrayList<Section> sections) {
		this.sections = sections;
	}

	/**
	 * Method for getting the sections of a page.
	 * Preconditions: None.
	 * Postconditions:
	 *		returns (ArrayList<Section>) sections of the Page.
	 * Exceptions: None.
	 * Date last changed: 12/01/2017
	 * @author 
	 */	
	public ArrayList<Section> getSections () {
		return this.sections;
	}
	
	/**
	 * Method for getting the relevance of a page.
	 * Preconditions:
	 * Postconditions:
	 *		returns (ArrayList<Section>) sections of the Page.
	 * Exceptions: None.
	 * Date last changed: 12/01/2017
	 * @author 
	 */	
	public double getRelevance() {
		return relevance;
	}

	/**
	 * Method for setting the relevance of a page.
	 * Preconditions:
	 * Postconditions:
	 *		returns (ArrayList<Section>) sections of the Page.
	 * Exceptions: None.
	 * Date last changed: 12/01/2017
	 * @author 
	 */	
	public void setRelevance(double relevance) {
		this.relevance = relevance;
	}

	/** 
	 * Returns a string that contains all of the text in a page
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.title + "\n");
		for (Section s : this.getSections()) {
			sb.append(s.getTitle());
			sb.append('\n');
			sb.append(s.getText());
			sb.append('\n');
		}
		return sb.toString();
	}

	/**
	 * Sort pages by relevance largest to smallest
	 */
	@Override
	public int compareTo(Page that) {
		if (this.relevance > that.relevance) return -1;
		if (this.relevance < that.relevance) return 1;
		return 0;
	}
}
