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
	
	public Section(int sectionId, String title, String bodyText, String equations, Graphic graphic){
		this.sectionId = sectionId;
		this.title = title;
		this.bodyText = bodyText;
		this.equations = equations;
		this.graphic = graphic;
		this.variables = new ArrayList<Variable>();
	} 
	
	public Section(ResultSet sectionResult) throws SQLException{
		this(sectionResult.getInt("sectionId"), 
				sectionResult.getString("sectionTitle"), 
				sectionResult.getString("sectionText"), 
				sectionResult.getString("equations"),
				null);
	}

	public int getSectionId(){
		return this.sectionId;
	}
	
	public void setSectionId(int sectionId){
		this.sectionId= sectionId;
	}
	
	public String getEquations() {
		return equations;
	}

	public void setEquations(String equation) {
		this.equations = equation;
	}

	public Graphic getGraphic() {
		return graphic;
	}
	
	public void setGraphic(Graphic graphic) {
		this.graphic = graphic;
	}

	public String getTitle(){
		return this.title;
	}
	
	public String getText(){
		return this.bodyText;
	}
		
	public void setTitle(String sTitle){
		this.title = sTitle;
	}
	
	public void setText(String sText){
		this.bodyText = sText;
	}
	
	public void addVariables(ArrayList<Variable> variables) {
		for (Variable v : variables) {
			this.variables.add(v);
		}
	}
	
	public ArrayList<Variable> getVariables () {
		return this.variables;
	}
}
