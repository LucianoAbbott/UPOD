package datatypes;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data type representing a complete page in the wiki
 * @author Nathan Skof
 */
public class Graphic {
  
	private int graphicId;
	private String graphicURL; 
	private String description; 
	
	/**
	 * Constructor for class "Graphic"
	 * Preconditions:
	 *		(int) gId - integer number to be used to represent graphicID
	 *		(String) gURL - String in URL format to represent graphicURL
	 *		(String) gDescription - String to be used as the description
	 * Postconditions:
	 * Exceptions: None.
	 * Date last changed: 11/29/2017
	 * @author Nathan Skof
	 */
	public Graphic(int gId, String gURL, String gDescription){
		
		this.graphicId = gId;
		this.graphicURL = gURL;
		this.description = gDescription;
	}
	
	/**
	 * Constructor for class "Graphic"
	 * Preconditions:
	 * Postconditions:
	 * Exceptions: None.
	 * Date last changed: 11/29/2017
	 * @author Nathan Skof
	 */
	public Graphic(){
		this.graphicId = 0;
		this.graphicURL = "";
		this.description = "";
	}
	
	/**
	 * Constructor for class "Graphic"
	 * Preconditions:
	 * Postconditions:
	 * Exceptions: SQLException
	 * Date last changed: 11/29/2017
	 * @author Nathan Skof
	 */
	public Graphic(ResultSet graphicResult) throws SQLException {
		this(graphicResult.getInt("graphicId"), 
				graphicResult.getString("graphicURL"),
				graphicResult.getString("description"));
	}

	/**
	 * Method to get the graphicId of a graphic
	 * Preconditions:
	 * Postconditions:
	 * 		returns the (int) graphicId
	 * Exceptions: None
	 * Date last changed: 11/29/2017
	 * @author 
	 */
	public int getGraphicId() {
		return graphicId;
	}

	/**
	 * Method to get the graphicURL of a graphic
	 * Preconditions:
	 * Postconditions:
	 * 		returns the (String) graphicURL
	 * Exceptions: None
	 * Date last changed: 11/29/2017
	 * @author Nathan Skof
	 */
	public String getGraphicURL() {
		return graphicURL;
	}
	
	/**
	 * Method to get the description of a graphic
	 * Preconditions:
	 * Postconditions:
	 * 		returns the (String) description
	 * Exceptions: None
	 * Date last changed: 11/29/2017
	 * @author Nathan Skof
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Method to set the description of a graphic
	 * Preconditions:
	 * 		description:
	 * Postconditions: A new (String) description is set for the graphic.
	 * Exceptions: None
	 * Date last changed: 11/29/2017
	 * @author Nathan Skof
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Method to get the graphicURL of a graphic
	 * Preconditions:
	 * Postconditions: A new (string) graphicURL is set for the graphic.
	 * Exceptions: None
	 * Date last changed: 11/29/2017
	 * @author Nathan Skof
	 */
	public void setGraphicURL(String gGraphicURL){
		this.graphicURL = gGraphicURL;	
	}

	
//	public static void  setGraphicURL(Graphic graphic,String GraphicURL){
//		
//		
//		Statement stmt = null;
//		try {
//			stmt = upodDao.getInstance();
//			if(graphicExists(gId)){
//				//update graphic object 
//				graphic.graphicURL = GraphicURL;
//				//update database
//				stmt.executeUpdate("UPDATE GRAPHIC SET graphicURL = '"+graphic.graphicURL+"' WHERE graphicId ="+graphic.graphicId);
//			
//		}
//			else{
//				//update graphic object 
//				graphic.graphicURL = GraphicURL;
//				//create new graphic in the database
//				stmt.executeUpdate("INSERT INTO GRAPHIC VALUES ("+graphic.graphicId+", '"+graphic.graphicURL+"', '"+graphic.description+"')");
//			}
//		}catch (SQLException e) {
//			System.out.println("No connection");
//		}
//		 
//	}
//	
//	public static void  setDescription(Graphic graphic,String gDescription){
//		
//		Statement stmt = null;
//		 try {
//			stmt = upodDao.getInstance();
//			if(graphicExists(int gId)){
//				//update graphic object 
//				graphic.description = gDescription;
//				//update database
//				stmt.executeUpdate("UPDATE GRAPHIC SET description = '"+graphic.description+"' WHERE graphicId ="+graphic.graphicId);
//		}
//			else{
//				//update graphic object 
//				graphic.description = gDescription;
//				//create new graphic in the database
//				stmt.executeUpdate("INSERT INTO GRAPHIC VALUES ("+graphic.graphicId+", '"+graphic.graphicURL+"', '"+graphic.description+"')");
//		}
//		}catch (SQLException e) {
//			System.out.println("No connection");
//		}
//		 
//	}
//	public static int getGraphicCount(){
//    		 Statement stmt = null;
//		 int count = 0;
//		 try {
//			stmt = upodDao.getInstance();
//			ResultSet rs2 = stmt.executeQuery("SELECT COUNT(*) FROM GRAPHIC"); 
//			while(rs2.next()){
//				count = rs2.getInt(1);
//			}
//			
//		 } catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 
//		 return count;
//  	}
//		   
//	public boolean graphicExists(int gId) {
//    		//return if graphic exists or not
//		String Table = "GRAPHIC";
//		String idType = "graphicId";
//		return upodDao.idExists(Table,idType, gId);
//  	}
}
