package datatypes;

/**
 * Data type representing an equation. 
 * @author Ziyi Zhang
 */
public class Equation {
	private int equationId;
	private String equationURL; 
	
	public Equation(int eId, String eURL){
			
			equationId = eId;
			equationURL = eURL;
		}
	
	public int getId(){
		return this.equationId;
	}	
	public String getURL(){
		return this.equationURL;
	}
}
