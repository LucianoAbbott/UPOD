package datatypes;

import java.sql.*;
import com.teamUPOD.UPOD.UPOD.UpodDao;
import datatypes.Section;

/**
 * Data type representing a complete page in the wiki
 * @author luciano abbott
 */
public class Page {
  
  private int pageId;
	private String title; 
	private String URL; 
	private Boolean editing; 
	
	private Page(int pId, String pTitle, String pURL, Boolean pEditing){
		
		pageId = pId;
		title = pTitle;
		URL = pURL;
		editing = pEditing;
			
		return self;
	}
	
	public void setPage(Page page){
		
		
		if(pageExists(page.pageId)){
			//"UPDATE PAGE SET title = "+page.title+", URL = "+page.URL+", editing = "+page.editing+" WHERE PageId= "+page.pageId+";"
			
		}else{
			//"INSERT INTO PAGE VALUES('"+page.pageId+"','"+page.title+"','"page.URL+"','"+page.editing+"');"
		}
	}
	
	public Page getPage(int pageId){
		Page page = null;
		//get a page from the database
		return page;
	}
	
	public Section[] getSections(int pageId){
		
		int nSections = 0;
		//get number of sections in page
		
		Section[] sections = new Section[nSections];
				
		//get all sections in page
		
		return sections;
	}
	
	public Boolean setStatus(int pageId){
		Boolean status = null;
		//set if page is being edited
		return status;
	}
	
	public Boolean getStatus(int pageId){
		Boolean status = null;
		//check if page is being edited
		return status;
	}
	
	public Boolean pageExists(int pageId){
		Boolean exists = false;
		//check if page exists 
		return exists;
	}
  
}
