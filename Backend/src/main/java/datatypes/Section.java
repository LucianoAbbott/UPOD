package datatypes;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data type representing a subsection of a page in the wiki
 * @author luciano abbott
 * @author Lauren Hepditch
 */
public class Section {
  
	private int sectionId;
	private String title;
	private String bodyText;
	private String equation;
	private Graphic graphic;
	
	public Section(int sectionId, String title, String bodyText, String equation, Graphic graphic){
		this.sectionId = sectionId;
		this.title = title;
		this.bodyText = bodyText;
		this.equation = equation;
		this.graphic = graphic;
	} 
	
	public Section(ResultSet sectionResult) throws SQLException{
		this(sectionResult.getInt("sectionId"), 
				sectionResult.getString("sectionTitle"), 
				sectionResult.getString("sectionText"), 
				sectionResult.getString("equation"),
				null);
	}
	
	public Section(){
		
		this.sectionId = 0;
		this.title = null;
		this.bodyText = null;
		this.equation = null;
		this.graphic = null;
		
	} 
  
	public int getSectionId(){
		return this.sectionId;
	}
	
	public void setSectionId(int sectionId){
		this.sectionId= sectionId;
	}
	
	public String getEquation() {
		return equation;
	}

	public void setEquation(String equation) {
		this.equation = equation;
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
  
}
