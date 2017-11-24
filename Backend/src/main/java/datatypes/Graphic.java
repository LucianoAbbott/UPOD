package datatypes;

import java.sql.*;
import com.teamUPOD.UPOD.UPOD.UpodDao;
import datatypes.Section;

/**
 * Data type representing a complete page in the wiki
 * @author Nathan Skof
 */
public class Graphic {
  
	private int graphicId;
	private String graphicURL; 
	private String description; 
	
	public Graphic(int gId, String gURL, String gDescription){
		
		this.graphicId = gId;
		this.graphicURL = gURL;
		this.description = gDescription;
	}
	
	public static void  setGraphicURL(Graphic graphic,String GraphicURL){
		
		
		Statement stmt = null;
		try {
			stmt = upodDao.getInstance();
			if(graphicExists(gId)){
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
	
	public static void  setDescription(Graphic graphic,String gDescription){
		
		Statement stmt = null;
		 try {
			stmt = upodDao.getInstance();
			if(graphicExists(int gId)){
				//update graphic object 
				graphic.description = gDescription;
				//update database
				stmt.executeUpdate("UPDATE GRAPHIC SET description = '"+graphic.description+"' WHERE graphicId ="+graphic.graphicId);
		}
			else{
				//update graphic object 
				graphic.description = gDescription;
				//create new graphic in the database
				stmt.executeUpdate("INSERT INTO GRAPHIC VALUES ("+graphic.graphicId+", '"+graphic.graphicURL+"', '"+graphic.description+"')");
		}
		}catch (SQLException e) {
			System.out.println("No connection");
		}
		 
	}
	public static int getGraphicCount(){
    		 Statement stmt = null;
		 int count = 0;
		 try {
			stmt = upodDao.getInstance();
			ResultSet rs2 = stmt.executeQuery("SELECT COUNT(*) FROM GRAPHIC"); 
			while(rs2.next()){
				count = rs2.getInt(1);
			}
			
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return count;
  	}
		   
	public boolean graphicExists(int gId) {
    		//return if graphic exists or not
		String Table = "GRAPHIC";
		String idType = "graphicId";
		return upodDao.idExists(Table,idType, gId);
  	}
}
