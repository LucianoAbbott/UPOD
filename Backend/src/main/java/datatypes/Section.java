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
	 *	sectionId - unique id of section with respect to its page
	 *	title - title of the section
	 *	bodyText - text contained within the section
	 *	equations - formatted equations related to the section's content
	 *	graphic - a Graphic object related to the section's content
	 * Postconditions: returns a complete Section object
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
	 * <description here>
	 * Preconditions:
	 * Postconditions:
	 * Exceptions: None.
	 * Date last changed:
	 * @author
	 */
	public Section(ResultSet sectionResult) throws SQLException{
		this(sectionResult.getInt("sectionId"), 
				sectionResult.getString("sectionTitle"), 
				sectionResult.getString("sectionText"), 
				sectionResult.getString("equations"),
				null);
	}

	/**
	 * <description here>
	 * Preconditions:
	 * Postconditions:
	 * Exceptions: None.
	 * Date last changed:
	 * @author
	 */
	public int getSectionId(){
		return this.sectionId;
	}
	
	/**
	 * <description here>
	 * Preconditions:
	 * Postconditions:
	 * Exceptions: None.
	 * Date last changed:
	 * @author
	 */
	public void setSectionId(int sectionId){
		this.sectionId= sectionId;
	}
	
	/**
	 * <description here>
	 * Preconditions:
	 * Postconditions:
	 * Exceptions: None.
	 * Date last changed:
	 * @author
	 */
	public String getEquations() {
		return equations;
	}

	/**
	 * <description here>
	 * Preconditions:
	 * Postconditions:
	 * Exceptions: None.
	 * Date last changed:
	 * @author
	 */
	public void setEquations(String equation) {
		this.equations = equation;
	}

	/**
	 * <description here>
	 * Preconditions:
	 * Postconditions:
	 * Exceptions: None.
	 * Date last changed:
	 * @author
	 */
	public Graphic getGraphic() {
		return graphic;
	}
	
	/**
	 * <description here>
	 * Preconditions:
	 * Postconditions:
	 * Exceptions: None.
	 * Date last changed:
	 * @author
	 */
	public void setGraphic(Graphic graphic) {
		this.graphic = graphic;
	}

	/**
	 * <description here>
	 * Preconditions:
	 * Postconditions:
	 * Exceptions: None.
	 * Date last changed:
	 * @author
	 */
	public String getTitle(){
		return this.title;
	}
	
	/**
	 * <description here>
	 * Preconditions:
	 * Postconditions:
	 * Exceptions: None.
	 * Date last changed:
	 * @author
	 */
	public String getText(){
		return this.bodyText;
	}
	
	/**
	 * <description here>
	 * Preconditions:
	 * Postconditions:
	 * Exceptions: None.
	 * Date last changed:
	 * @author
	 */
	public void setTitle(String sTitle){
		this.title = sTitle;
	}
	
	/**
	 * <description here>
	 * Preconditions:
	 * Postconditions:
	 * Exceptions: None.
	 * Date last changed:
	 * @author
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
	 * @author
	 */
	public void addVariables(ArrayList<Variable> variables) {
		for (Variable v : variables) {
			this.variables.add(v);
		}
	}
	
	/**
	 * <description here>
	 * Preconditions:
	 * Postconditions:
	 * Exceptions: None.
	 * Date last changed:
	 * @author
	 */
	public ArrayList<Variable> getVariables () {
		return this.variables;
	}
}
