package datatypes;

/**
 * Variable object of symbol/name pair
 * @author luciano
 *
 */
public class Variable {
	private String symbol;
	private String name;
	private String description;
	private String URL;
	
	public Variable (String symbol, String name,String description, String URL) {
		this.symbol = symbol;
		this.name = name;
		this.description = description;
		this.URL = URL;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getName() {
		return name;
	}
}
