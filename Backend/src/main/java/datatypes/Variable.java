package datatypes;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Variable object of symbol/name pair
 * 
 * @author luciano and Nathan
 *
 */
public class Variable {
	private String symbol;
	private String name;
	private String description;
	private String url;
	private int id;

	public Variable(String symbol, String name, String description, String url, int id) {
		this.symbol = symbol;
		this.name = name;
		this.description = description;
		this.url = url;
		this.id = id;
	}

	public Variable() {
		this.symbol = "";
		this.name = "";
		this.description = "";
		this.url = "";
		this.id = 0;
	}

	public Variable(ResultSet variableResult) throws SQLException {
		this(variableResult.getString("symbol"), variableResult.getString("name"),
				variableResult.getString("description"), variableResult.getString("URL"),
				variableResult.getInt("varId"));
	}

	public String getSymbol() {
		return this.symbol;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public String getURL() {
		return this.url;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public void setDescription(String newDescription) {
		this.description = newDescription;
	}

	public void setURL(String newUrl) {
		this.url = newUrl;
	}

	public void setSymbol(String newSymbol) {
		this.symbol = newSymbol;
	}

}
