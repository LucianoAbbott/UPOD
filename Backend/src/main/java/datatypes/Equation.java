package datatypes;

import java.sql.*;
import java.util.ArrayList;
/*
import com.teamUPOD.UPOD.UPOD.UpodDao;
import datatypes.Section;
*/

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
	/**
	 * Creates Equation if none exists, updates otherwise. 
	 * @param Equation object
	 */
	public void setEquationURL(Equation equation){ //THIS ONE'S NOT DONEEEEEEEE the others are tested and work
		Statement stmt=UpodDao.getStmt();
		
		//update equation
		if(equationExists(equation.equationId)){
			stmt.executeUpdate("UPDATE EQUATION SET equationURL = '" +equation.equationURL+ "' WHERE equId="+equation.equationId);
			//update variables
			
		}else{ //create equation
			stmt.executeUpdate("INSERT INTO EQUATION VALUES ("+equation.equationId+",'"+equation.equationURL+"')");
			//create variables
		}
		return;
	}
	
	/**
	 * Gets a list of variables that are in the equation. 
	 * @param Equation object
	 * @return ArrayList<ArrayList<String>> varResult - all values of variables except varId
	 */
	public ArrayList<ArrayList<String>> getVariable(Equation equation){
		Statement stmt = UpodDao.getStmt();
		ResultSet rs = stmt.executeQuery("SELECT symbol,name,category,description FROM VARIABLE WHERE varId IN (SELECT varId FROM EQUVAR WHERE equId = "+equation.equationId+")");
		ArrayList<ArrayList<String>> varResult = new ArrayList<ArrayList<String>>();
		//fill varResult
		while (rs.next()){
			ArrayList<String> varInfo = new ArrayList<String>();
			for (int i=1;i<=4;i++){
				varInfo.add(rs.getString(i));
			}
			varResult.add(varInfo);
		}
		return varResult;
	}

	/**
	 * Gets the number of equations in the database.
	 * @return Integer count
	 */
	public static int getEquationCount(){
		Statement stmt=UpodDao.getStmt();
		//get count
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM EQUATION");
		int count = 0;
		rs.next();
		count = rs.getInt(1);
		
		return count;
	}

	/**
	 * Returns true if the equation exists, false otherwise. 
	 * @param int eId - equation id
	 * @return boolean 
	 */
	public boolean equationExists(int eId) {
		Statement stmt=UpodDao.getStmt();
		try{
			ResultSet rs=stmt.executeQuery("select * from EQUATION where equId =" + eId); 
			
			return rs.next();
		}catch(SQLException e){System.out.println("equationExists failed");}
		
		return false;
	}

}
