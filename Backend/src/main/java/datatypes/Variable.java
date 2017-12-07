package datatypes;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Variable object of symbol/name pair
 * 
 * @author Luciano Abbott and Nathan
 *
 */
public class Variable {
	private String symbol;
	private String name;
	private String description;
	private String url;
	private int id;

	/**
	 * Constructor for class "Variable"
	 * Preconditions:
	 *		(String) symbol - variable symbol represented as a string.
	 * 		(String) name - the name of the varible.
	 *		(String) description - the description of the Variable.
	 *		(String) url - the variables url.
	 *		(int) id - unique id number for the variable.
	 * Postconditions: creates a new Page object.
	 * Postconditions: creates a new Graphic object with all attributes set.
	 * Exceptions: None.
	 * Date last changed: 12/03/2017
	 * @author Luciano Abbott and Nathan
	 */		
	public Variable(String symbol, String name, String description, String url, int id) {
		this.symbol = symbol;
		this.name = name;
		this.description = description;
		this.url = url;
		this.id = id;
	}
	
	/**
	 * Constructor for class "Variable"
	 * Preconditions: None.
	 * Postconditions: creates a new Variable object with attributes set to 0 or blank.
	 * Exceptions: None.
	 * Date last changed: 12/03/2017
	 * @author Luciano Abbott and Nathan
	 */
	public Variable() {
		this.symbol = "";
		this.name = "";
		this.description = "";
		this.url = "";
		this.id = 0;
	}
	
	/**
	 * Constructor for class "Variable"
	 * Preconditions:
	 *		(Result) variableResult - the result of an query to the variable table in the database
	 * Postconditions: creates a new Variable objects witht the attributes set to the query.
	 * Exceptions: None.
	 * Date last changed: 12/03/2017
	 * @author Luciano Abbott and Nathan
	 */
	public Variable(ResultSet variableResult) throws SQLException {
		this(variableResult.getString("symbol"), variableResult.getString("name"),
				variableResult.getString("description"), variableResult.getString("URL"),
				variableResult.getInt("varId"));
	}
	
	/**
	 * Function to get the symbol attribute of Variable
	 * Preconditions: None.
	 * Postconditions:
	 *		returns (String) symbol attribute of Variable.
	 * Exceptions: None.
	 * Date last changed: 12/03/2017
	 * @author Luciano Abbott and Nathan
	 */
	public String getSymbol() {
		return this.symbol;
	}

	/**
	 * Function to get the name attribute of Variable
	 * Preconditions: None.
	 * Postconditions:
	 *		returns (String) name attribute of Variable.
	 * Exceptions: None.
	 * Date last changed: 12/03/2017
	 * @author Luciano Abbott and Nathan
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Function to get the description attribute of Variable
	 * Preconditions: None
	 * Postconditions:
	 *		returns (String) description attribute of Variable.
	 * Exceptions: None.
	 * Date last changed: 12/03/2017
	 * @author Luciano Abbott and Nathan
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Function to get the url attribute of Variable
	 * Preconditions: None.
	 * Postconditions:
	 *		returns (String) url attribute of Variable.
	 * Exceptions: None.
	 * Date last changed: 12/03/2017
	 * @author
	 */
	public String getURL() {
		return this.url;
	}

	/**
	 * Function to get the id attribute of Variable
	 * Preconditions:
	 * Postconditions:
	 *		returns (int) id attribute of Variable.
	 * Exceptions: None.
	 * Date last changed: 12/03/2017
	 * @author Luciano Abbott and Nathan
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Function to set the id attribute of Variable
	 * Preconditions:
	 *		(int) id - unique id number of the varibles to be set.
	 * Postconditions: a new (int) id of Variable is set.
	 * Exceptions: None.
	 * Date last changed: 12/03/2017
	 * @author Luciano Abbott and Nathan
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Function to set the name attribute of Variable
	 * Preconditions:
	 *		(String) name - new name attribute of Variable to be set.
	 * Postconditions: A new (String) name attribute of Variable is set.
	 * Exceptions: None.
	 * Date last changed: 12/03/2017
	 * @author
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * Function to set the description attribute of Variable
	 * Preconditions:
	 *		(String) newDescription - new description attribute of Variable to be set.
	 * Postconditions: A new (String) description attribute of Variable is set.
	 * Exceptions: None.
	 * Date last changed: 12/03/2017
	 * @author Luciano Abbott and Nathan
	 */
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}

	/**
	 * Function to set the url attribute of Variable
	 * Preconditions:
	 *		(String) url - new url attribute of Variable to be set.
	 * Postconditions: A new (String) url attribute of Variable is set.
	 * Exceptions: None.
	 * Date last changed: 12/03/2017
	 * @author Luciano Abbott and Nathan
	 */
	public void setURL(String newUrl) {
		this.url = newUrl;
	}

	/**
	 * Function to get the symbol attribute of Variable
	 * Preconditions:
	 *		(String) symbol - new symbol attribute of Variable to be set.
	 * Postconditions:  A new (String) symbol attribute of Variable is set.
	 * Exceptions: None.
	 * Date last changed: 12/03/2017
	 * @author Luciano Abbott and Nathan
	 */
	public void setSymbol(String newSymbol) {
		this.symbol = newSymbol;
	}

}
