package datatypes;

/**
 * Data type representing a complete page in the wiki
 * @author luciano abbott
 * @author Lauren Hepditch
 */
public class Page {
  
  	private int pageId;
	private String title; 
	private String URL; 
	private Boolean editing;
	
	public Page(int pId, String pTitle, String pURL, Boolean pEditing){
		
		pageId = pId;
		title = pTitle;
		URL = pURL;
		editing = pEditing;
	}
}
