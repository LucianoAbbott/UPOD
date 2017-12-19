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
	 * Postconditions: creates a new Graphic object.
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
	 * Preconditions: None.
	 * Postconditions: creates a new Graphic object.
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
	 *		(ResultSet) - the result of an query to the Graphic table in the database
	 * Postconditions: creates a new Graphic object.
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
	 * Preconditions: None.
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
	 * Preconditions: None.
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
	 * 		(String) description - String with a description of the graphic.
	 * Postconditions: A new (String) description is set for the graphic.
	 * Exceptions: None
	 * Date last changed: 11/29/2017
	 * @author Nathan Skof
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Method to set the graphicURL of a graphic
	 * Preconditions:
	 *		(String) gGraphicURL - String containing URL of the graphic.
	 * Postconditions: A new (string) graphicURL is set for the graphic.
	 * Exceptions: None
	 * Date last changed: 11/29/2017
	 * @author Nathan Skof
	 */
	public void setGraphicURL(String gGraphicURL){
		this.graphicURL = gGraphicURL;	
	}

}
