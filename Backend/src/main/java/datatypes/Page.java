package datatypes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Data type representing a complete page in the wiki
 * @author luciano abbott
 * @author Lauren Hepditch
 */
public class Page {
	
	private int pageId;
	private String title; 
	private String url; 
	private Boolean editing; 
	private ArrayList<Section> sections;
	
	public Page(int pageId, String title, String url, Boolean editing){
		
		this.pageId = pageId;
		this.title = title;
		this.url = url;
		this.editing = editing;
		this.sections = new ArrayList<Section>();
	}

	public Page(ResultSet pageResult) throws SQLException {
		this(pageResult.getInt("pageId"), 
				pageResult.getString("title"), 
				pageResult.getString("URL"), 
				false);
	}

	
	public Page(){
		pageId = 0;
		title = null;
		url = null;
		editing = false;
		sections = new ArrayList<Section>();
	}
	
	public int getId(){
		return this.pageId;	
	}
	
	public String getTitle(){
 		return this.title;	
	}
	
	public String getURL(){
		return this.url;	
	}
	
	public Boolean isBeingEditted(){
		return this.editing;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setEditing (Boolean edit){
		this.editing = edit;
	}
	
	public void setSections (ArrayList<Section> sections) {
		this.sections = sections;
	}

	public ArrayList<Section> getSections () {
		return this.sections;
	}
}
