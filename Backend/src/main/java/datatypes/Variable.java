package datatypes;

public class Variable {
	private String symbol;
	private String name;
	
	public Variable (String symbol, String name) {
		this.symbol = symbol;
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getName() {
		return name;
	}
}
