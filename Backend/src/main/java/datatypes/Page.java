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
	private ArrayList<Section> sections;
	
	public Page(int pageId, String title, String url){
		
		this.pageId = pageId;
		this.title = title;
		this.url = url;
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
		sections = new ArrayList<Section>();
	}
	
	public void setId(int pageId) {
		this.pageId = pageId;
	}
	public int getId(){
		return this.pageId;	
	}
	
	public String getTitle(){
 		return this.title;	
	}
	
	public String getUrl(){
		return this.url;	
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setSections (ArrayList<Section> sections) {
		this.sections = sections;
	}

	public ArrayList<Section> getSections () {
		return this.sections;
	}
}
