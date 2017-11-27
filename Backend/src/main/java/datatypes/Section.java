package datatypes;

/**
 * Data type representing a subsection of a page in the wiki
 * @author luciano abbott
 */
public class Section {
  
	private int sectionId;
	private int pageId;
	private String title;
	private String bodyText;
	private Equation equation;
	private Graphic graphic;
	
	public Section(int sectionId, int pageId, String title, String bodyText, Equation equation, Graphic graphic){
		this.sectionId = sectionId;
		this.pageId = pageId;
		this.title = title;
		this.bodyText = bodyText;
		this.equation = equation;
		this.graphic = graphic;
	} 
	
	public Section(){
		
		this.sectionId = 0;
		this.pageId = 0;
		this.title = null;
		this.bodyText = null;
		this.equation = null;
		this.graphic = null;
		
	} 
  
	public int getSectionId(){
		return this.sectionId;
	}
	
	public Equation getEquation() {
		return equation;
	}

	public void setEquation(Equation equation) {
		this.equation = equation;
	}

	public Graphic getGraphic() {
		return graphic;
	}

	public void setGraphic(Graphic graphic) {
		this.graphic = graphic;
	}

	public int getPageId() {
		return pageId;
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
