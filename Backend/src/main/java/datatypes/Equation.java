package datatypes;

/**
 * Data type representing an equation.
 * 
 * @author Ziyi Zhang
 */
public class Equation {
	private int equationId;
	private String equationURL;
	
	/**
	 * Constructor for class "Equation"
	 * Preconditions: 
	 * 		(int) equationId - an integer must be given to identify the new equation by.
	 *		(string) equationURL - a string formatted as a URL must be given for the equation.
	 * Postconditions:
	 *		An equation is created.
	 * Exceptions: None
	 * Date last changed: 11/24/2017
	 * @author Ziyi Zhang
	 */
	public Equation(int equationId, String equationURL) {

		this.equationId = equationId;
		this.equationURL = equationURL;
	}
	
	/**
	 * Method to get the equationId of an equation
	 * Preconditions:
	 * Postconditions:
	 *		returns the (int) equationId of the equation
	 * Exceptions: None
	 * Date last changed: 11/24/2017
	 * @author Ziyi Zhang
	 */
	public int getId(){
		return this.equationId;
	}
	
	/**
	 * Method to get the equationURL
	 * Preconditions:
	 * Postconditions:
	 * 		returns the (String) equationURL of the equation
	 * Exceptions: None
	 * Date last changed: 11/24/2017
	 * @author Ziyi Zhang
	 */
	public String getUrl() {
		return this.equationURL;
	}
}
