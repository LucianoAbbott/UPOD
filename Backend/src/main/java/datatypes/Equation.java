package datatypes;

import java.sql.*;
import java.util.ArrayList;
import com.teamUPOD.UPOD.UPOD.UpodDao;
import datatypes.Section;

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
}
