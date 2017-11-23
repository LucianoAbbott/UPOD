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
	
	public static void  setGraphicURL(Graphic graphic,String GraphicURL){
		String Table = "GRAPHIC";
		String idType = "graphicId";
		
		Statement stmt = null;
		try {
			stmt = upodDao.getInstance();
			if(upodDao.idExists(Table,idType, graphic.graphicId)){
				//update graphic object 
				graphic.graphicURL = GraphicURL;
				//update database
				stmt.executeUpdate("UPDATE GRAPHIC SET graphicURL = '"+graphic.graphicURL+"' WHERE graphicId ="+graphic.graphicId);
			
		}
			else{
				//update graphic object 
				graphic.graphicURL = GraphicURL;
				//create new graphic in the database
				stmt.executeUpdate("INSERT INTO GRAPHIC VALUES ("+graphic.graphicId+", '"+graphic.graphicURL+"', '"+graphic.description+"')");
			}
		}catch (SQLException e) {
			System.out.println("No connection");
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
