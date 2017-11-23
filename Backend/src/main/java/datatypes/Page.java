package datatypes;

import java.sql.*;
import com.teamUPOD.UPOD.UPOD.UpodDao;
import datatypes.Section;

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
	
	public void setPage(Page page){
		Statement stmt = UpodDao.getStmt()
		
		if(pageExists(page.pageId)){
			stmt.executeUpdate("UPDATE PAGE SET title = '"+page.title+"', URL = '"+page.URL+"', editing = "+page.editing+" WHERE PageId= "+page.pageId+";");
			
		}else{
			stmt.executeUpdate("INSERT INTO PAGE VALUES('"+page.pageId+"','"+page.title+"','"page.URL+"','"+page.editing+"');");
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
				
		//get all section data in page
		
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
	private int numSections(int pageId){
		int num = 0;
		//get number of sections for given page id
		return num;
	}
  
}
