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
	private int variableId;
	
	public Variable (String symbol, String name,String description, String url, int id) {
		this.symbol = symbol;
		this.name = name;
		this.description = description;
		this.url = url;
		this.variableId = id;
	}

	public Variable(){
		this.symbol = "";
		this.name = "";
		this.description = "";
		this.url = "";
		this.variableId = 0;
	}
	
	public Variable (ResultSet variableResult) throws SQLException {
		this(variableResult.getString("symbol"),variableResult.getString("name"),variableResult.getString("description"),variableResult.getString("URL"),variableResult.getInt("varId"));
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
	public int getId(){
		return id;	
	}
	public void setId(int id){
		this.variableId=id;
	}
	public void setName(String newName){
		this.name = newName;	
	}
	public void setDescription(String newDescription){
		this.description = newDescription;
	}
	public void setURL(String newUrl){
		this.url = newUrl;	
	}
	public void setSymbol(String newSymbol){
		this.symbol = newSymbol;	
	}
		
}
