package datatypes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Data type representing a subsection of a page in the wiki
 * @author luciano abbott
 * @author Lauren Hepditch
 */
public class Section {
	private int sectionId;
	private String title;
	private String bodyText;
	private String equations;
	private Graphic graphic;
	private ArrayList<Variable> variables;
	
	
	/**
	 * Constructor for class "Section"
	 * Preconditions:
	 *		(int) sectionId - unique id of section with respect to its page
	 *		(String) title - title of the section
	 *		(String) bodyText - text contained within the section
	 *		(String) equations - formatted equations related to the section's content
	 *		(Graphic) graphic - a Graphic object related to the section's content
	 * Postconditions: creates a complete Section object
	 * Exceptions: None.
	 * Date last changed:
	 * @author Lauren Hepditch, Luciano Abbott
	 */
	public Section(int sectionId, String title, String bodyText, String equations, Graphic graphic){
		this.sectionId = sectionId;
		this.title = title;
		this.bodyText = bodyText;
		this.equations = equations;
		this.graphic = graphic;
		this.variables = new ArrayList<Variable>();
	} 
	
	/**
	 * Constructor for class "Section"
	 * Preconditions:
	 *		(Result) sectionResult - the result of an query to the section table in the database
	 * Postconditions:
	 * Exceptions: SQLException
	 * Date last changed:
	 * @author Lauren Hepditch, Luciano Abbott
	 */
	public Section(ResultSet sectionResult) throws SQLException{
		this(sectionResult.getInt("sectionId"), 
				sectionResult.getString("sectionTitle"), 
				sectionResult.getString("sectionText"), 
				sectionResult.getString("equations"),
				null);
	}

	/**
	 * Function to return sectionId attribute of Section
	 * Preconditions:
	 * Postconditions:
	 * 		returns (int) sectionId.
	 * Exceptions: None.
	 * Date last changed:
	 * @author Lauren Hepditch, Luciano Abbott
	 */
	public int getSectionId(){
		return this.sectionId;
	}
	
	/**
	 * Method for setting sectionId of a section.
	 * Preconditions:
	 *		(int) sectionId - an integer to be used to be the unique Id of the section.
	 * Postconditions: A new sectionId of the section is set.
	 * Exceptions: None.
	 * Date last changed:
	 * @author Lauren Hepditch, Luciano Abbott
	 */
	public void setSectionId(int sectionId){
		this.sectionId= sectionId;
	}
	
	/**
	 * Function to get equations attribute of Sections
	 * Preconditions: None.
	 * Postconditions:
	 *		returns (String) equations attribute of Sections.
	 * Exceptions: None.
	 * Date last changed:
	 * @author Lauren Hepditch, Luciano Abbott
	 */
	public String getEquations() {
		return equations;
	}

	/**
	 * Function to set equations attribute of Sections
	 * Preconditions:
	 *		(String) equation - string to be used to set the equations attribute of Sections.
	 * Postconditions: A new (string) equations attribute is set.
	 * Exceptions: None.
	 * Date last changed:
	 * @author Lauren Hepditch, Luciano Abbott
	 */
	public void setEquations(String equation) {
		this.equations = equation;
	}

	/**
	 * Function to get the attribute graphic from Sections
	 * Preconditions: None.
	 * Postconditions:
	 *		returns (Graphic) graphic attribute of Sections
	 * Exceptions: None.
	 * Date last changed:
	 * @author Lauren Hepditch, Luciano Abbott
	 */
	public Graphic getGraphic() {
		return graphic;
	}
	
	/**
	 * Function to set a new graphic attribute to Section
	 * Preconditions:
	 *		(Graphic) graphic - graphic object to be set as the Sections graphic.
	 * Postconditions: A new graphic attribute for Section is set.
	 * Exceptions: None.
	 * Date last changed:
	 * @author Lauren Hepditch, Luciano Abbott
	 */
	public void setGraphic(Graphic graphic) {
		this.graphic = graphic;
	}

	/**
	 * Function to get the title attribute of Section
	 * Preconditions: None.
	 * Postconditions:
	 *		returns the title attribute of Section.
	 * Exceptions: None.
	 * Date last changed:
	 * @author Lauren Hepditch, Luciano Abbott
	 */
	public String getTitle(){
		return this.title;
	}
	
	/**
	 * Function to get the bodyText attribute of Section
	 * Preconditions: None.
	 * Postconditions: 
	 *		Returns the (String) bodyText attribute of Section.
	 * Exceptions: None.
	 * Date last changed:
	 * @author Lauren Hepditch, Luciano Abbott
	 */
	public String getText(){
		return this.bodyText;
	}
	
	/**
	 * Funciton to set a new title attribute for the Section
	 * Preconditions:
	 *		(String) sTitle - string that represents the new title.
	 * Postconditions: A new (String) title attribute of Section is set.
	 * Exceptions: None.
	 * Date last changed:
	 * @author Lauren Hepditch, Luciano Abbott
	 */
	public void setTitle(String sTitle){
		this.title = sTitle;
	}
	
	/**
	 * Function to set a new bodyText attribute for Section
	 * Preconditions:
	 *		(String) sText - String to be set as bodyText attribute.
	 * Postconditions: A new (String) bodyText attribute of Section is set.
	 * Exceptions: None.
	 * Date last changed:
	 * @author Lauren Hepditch, Luciano Abbott
	 */
	public void setText(String sText){
		this.bodyText = sText;
	}
	
	/**
	 * <description here>
	 * Preconditions:
	 * Postconditions:
	 * Exceptions: None.
	 * Date last changed:
	 * @author Lauren Hepditch, Luciano Abbott
	 */
	public void addVariables(ArrayList<Variable> variables) {
		for (Variable v : variables) {
			this.variables.add(v);
		}
	}
	
	/**
	 * Function to get variables attribute of Section
	 * Preconditions: None.
	 * Postconditions:
	 *		returns the (ArrayList<Variable>) variables attribute of Section.
	 * Exceptions: None.
	 * Date last changed:
	 * @author Lauren Hepditch, Luciano Abbott
	 */
	public ArrayList<Variable> getVariables () {
		return this.variables;
	}
}
