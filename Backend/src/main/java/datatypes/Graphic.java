package datatypes;

import java.sql.*;
import com.teamUPOD.UPOD.UPOD.UpodDao;
import datatypes.Section;

/**
 * Data type representing a complete page in the wiki
 * @author Lauren Hepditch
 * @author Nathan Skof
 */
public class Graphic {
  
	private int graphicId;
	private String graphicURL; 
	private String descrpition; 
	
	public Graphic(int gId, String gURL, String gDescription){
		
		graphicId = gId;
		graphicURL = gURL;
		description = gDescription;
	}
	
	public void setGraphicURL(Graphic graphic){
		
		if(graphicExists(graphic.graphicId)){
			//"UPDATE Graphic url"
			
		}
    		else{
			//"Create graphic url"
		      //call  graphic set Description 
      
		}
	}
	
	public void setDescription(Graphic graphic, String gDescription ){
		if(graphicExists(graphicId){
      			//Update 	
      
    		}
		else{
    			//create graphic
    		}
  
  	}
	public static int getGraphicCount(){
    		//return count of graphics
  	}
		   
	public boolean graphicExists(int gId) {
    		//return if graphic exists or not
  	}
}
