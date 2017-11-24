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
	private Section[] sections;
	
	public Page(int pId, String pTitle, String pURL, Boolean pEditing){
		
		pageId = pId;
		title = pTitle;
		URL = pURL;
		editing = pEditing;
		sections = null;
	}
	
	public Page(){
		
		pageId = 0;
		title = null;
		URL = null;
		editing = false;
		sections = null;
	}
	
	public int getId(){
		return this.pageId;	
	}
	
	public String getTitle(){
 		return this.title;	
	}
	
	public String getURL(){
		return this.URL;	
	}
	
	public Boolean getEditStatus(){
		return this.editing;	
	}
	
	public void setId(int id){
		this.pageId = id;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setURL(String URL){
		this.URL = URL;
	}
	
	public void setEditStatus(Boolean edit){
		this.editing = edit;
	}
}
