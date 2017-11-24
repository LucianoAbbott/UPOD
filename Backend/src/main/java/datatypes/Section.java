package datatypes;

/**
 * Data type representing a subsection of a page in the wiki
 * @author luciano abbott
 */
public class Section {
  
  private int sectionId;
	private String title;
	private String text;
	private Equation equ;
	private Graphic graphic;
	
	public Section(int sId, String sTitle, String sText, Equation e,Graphic g){
		
		sectionId = sId;
		title = sTitle;
		text = sText;
		equ = e;
		graphic = g;
		
	} 
	
	public Section(){
		
		sectionId = 0;
		pageId = 0;
		title = null;
		text = null;
		equ = null;
		graphic = null;
		
	} 
  
  public int getSecId(){
		return this.sectionId;
	}
	
	public String getSecTitle(){
		return this.title;
	}
	
	public String getSecText(){
		return this.text;
	}
	
	public int setSecId(int id){
		this.sectionId = id;
	}
	
	public String setSecTitle(String sTitle){
		this.title = sTitle;
	}
	
	public String setSecText(String sText){
		this.text = sText;
	}
  
  //still need setters and getters for equations and graphics
  
}
