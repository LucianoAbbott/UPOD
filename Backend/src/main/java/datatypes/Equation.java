package datatypes;

/**
 * Data type representing an equation.
 * 
 * @author Ziyi Zhang
 */
public class Equation {
	private int equationId;
	private String equationURL;

	public Equation(int equationId, String equationURL) {

		this.equationId = equationId;
		this.equationURL = equationURL;
	}
	
	public int getId(){
		return this.equationId;
	}	

	public String getUrl() {
		return this.equationURL;
	}
}
