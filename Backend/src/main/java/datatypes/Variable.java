package datatypes;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Variable object of symbol/name pair
 * @author luciano and Nathan
 *
 */
public class Variable {
	private String symbol;
	private String name;
	private String description;
	private String url;
	
	public Variable (String symbol, String name,String description, String URL) {
		this.symbol = symbol;
		this.name = name;
		this.description = description;
		this.url = URL;
	}
	public Variable(){
		this.symbol = "";
		this.name = "";
		this.description = "";
		this.url = "";
	}
	
	public Variable (ResultSet variableResult) throws SQLException {
		this(variableResult.getString("symbol"),variableResult.getString("name"),variableResult.getString("description"),variableResult.getString("URL"));
	}

	public String getSymbol() {
		return symbol;
	}

	public String getName() {
		return name;
	}
	public String getDescription(){
		return description;	
	}	
	public String getURL(){
		return url;	
	}
	public void setName(String newName){
		this.name = newName;	
	}
	public void setDescription(String newDescription){
		this.description = newDescriptionl 	
	}
	public void setUrl(String newUrl){
		this.url = newURL;	
	}
	public void setSymbol(String newSymbol){
		this.symbol = newSymbol;	
	}
		
}
