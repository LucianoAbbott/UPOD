//package com.teamUPOD.UPOD.UPOD;

import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import datatypes.Page;
import datatypes.Equation;

/**
 * Database Access Object for the backend server
 * Is responsible for all interactions with the database
 * Should be completely localised
 * @author luciano abbott
 */
public class UpodDao {
	//TODO: Discussion on passing error codes instead of boolean success/fail
	
	
	private Connection connection = null;
    	private Statement stmt = null;
	
	private static UpodDao upodDao = null;

	public static final int INVALID_ID = -1;
	
	public static final String PAGE_TABLE_KEY = "Page";
	public static final String PAGE_TABLE_ID = "PageId";
	
	private static final HashMap<String, String> TABLE_ID_MAP;
	static {
		HashMap<String, String> map = new HashMap<String,String>();
		map.put(PAGE_TABLE_KEY, PAGE_TABLE_ID);
		TABLE_ID_MAP = (HashMap<String, String>) Collections.unmodifiableMap(map);
	}
	
	//TODO: UpodDao constructor
	private UpodDao() {
		String username = "";
		String password = "";
		String url = "";
		try{
			connection = DriverManager.getConnection(url, username, password);
			stmt = connection.createStatement();
		} 
		catch (SQLException e) {
	 		throw new IllegalStateException("Cannot connect to the database.", e);
		}
	}
	
	/**
	 * Access method for UpodDao - follows singleton pattern
	 * @return the UpodDao instance
	 */
	public static UpodDao getInstance() { 
		if (upodDao == null){
			upodDao = new UpodDao();
		}
		return upodDao;
	}
	
	private Connection getConnection(){
		return this.connection;
	}
	
	private Statement getStatement(){
		return this.stmt;
	}

	
	/**
	 * Get an Id that is not in use works for any table. 
	 * @return a valid Id with no attached page
	 * @Author Nathan Skof
	 */
	public int nextAvailableId(String table){
		 int MaxID = 0;
		 Statement stmt = null;
		 try {
			stmt = this.getConnection().createStatement();
			stmt.executeQuery("SELECT MAX("+ TABLE_ID_MAP.get(table) +") FROM "+ table);
			ResultSet rs2 = stmt.getResultSet();
			if(rs2.next()){
				MaxID = rs2.getInt(1);
			}
			MaxID +=1;
		} catch (SQLException e) {
			System.out.println("No connection");
		}
		 return MaxID;
	}
	
	/**
	 * Query the database with the given table, idType and id
	 * @param pageId
	 * @return true if there is a idType with the given Id that is not empty
	 * @Author Nathan Skof
	 */
	public static boolean idExists(String Table,String idType, int id) {
		String check = "SELECT * FROM "+Table+" WHERE "+ idType +" = " + id;
		Statement stmt = null;
		 try {
			stmt = upodDao.getInstance();
			ResultSet rs  = stmt.executeQuery(check);
			
			if(rs.absolute(1)){
				return true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		 
	}

	//TODO: updatePage
	/**
	 * Post a page to the database with the given page Id
	 * @param pageId
	 * @param page
	 * @return success or fail
	 */
	public boolean updatePage(int pageId, Page page) {
		return false;
	}
	
	//TODO: deletePage
	/**
	 * Delete the page with the given id from the database
	 * @param pageId
	 * @return success/fail
	 */
	public boolean deletePage(int pageId) {
		try {
			connection.createStatement().execute("DELETE FROM Page WHERE PageId = " + pageId);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Page getPage(int pageId) {
		return null;
	}
	//---------------------------------------------------------------------------------------------------------------------
	//Equations
	/**
	 * Creates Equation if none exists, updates otherwise. 
	 * @param Equation object
	 * @author Ziyi Zhang
	 */
	public void setEquationURL(Equation equation){ //THIS ONE'S NOT DONEEEEEEEE the others are tested and work
		UpodDao dao = getInstance();
		Statement stmt=dao.stmt;
		try {
			//update equation
			if(equationExists(equation.equationId)){
				stmt.executeUpdate("UPDATE EQUATION SET equationURL = '" +equation.equationURL+ "' WHERE equId="+equation.equationId);
				//update variables
				
			}else{ //create equation
				stmt.executeUpdate("INSERT INTO EQUATION VALUES ("+equation.equationId+",'"+equation.equationURL+"')");
				//create variables
			}
		}catch (SQLException e) {
				e.printStackTrace();
			}
		return;
	}
	
	/**
	 * Gets a list of variables that are in the equation. 
	 * @param Equation object
	 * @return ArrayList<ArrayList<String>> varResult - all values of variables except varId
	 * @author Ziyi Zhang
	 */
	public ArrayList<ArrayList<String>> getVariable(Equation equation){
		UpodDao dao = getInstance();
		Statement stmt=dao.stmt;
		ArrayList<ArrayList<String>> varResult = new ArrayList<ArrayList<String>>();
		try{
			ResultSet rs = stmt.executeQuery("SELECT symbol,name,category,description FROM VARIABLE WHERE varId IN (SELECT varId FROM EQUVAR WHERE equId = "+equation.equationId+")");
			//fill varResult
			while (rs.next()){
				ArrayList<String> varInfo = new ArrayList<String>();
				for (int i=1;i<=4;i++){
					varInfo.add(rs.getString(i));
				}
				varResult.add(varInfo);
			}
		}catch (SQLException e) {
				e.printStackTrace();
			}
		return varResult;
	}

	/**
	 * Gets the number of equations in the database.
	 * @return Integer count
	 * @author Ziyi Zhang
	 */
	public static int getEquationCount(){
		UpodDao dao = getInstance();
		Statement stmt=dao.stmt;
		int count = 0;
		try{
			//get count
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM EQUATION");
			rs.next();
			count = rs.getInt(1);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * Returns true if the equation exists, false otherwise. 
	 * @param int eId - equation id
	 * @return boolean 
	 * @author Ziyi Zhang
	 */
	public boolean equationExists(int eId) {
		UpodDao dao = getInstance();
		Statement stmt=dao.stmt;
		try{
			ResultSet rs=stmt.executeQuery("select * from EQUATION where equId =" + eId); 
			
			return rs.next();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	//---------------------------------------------------------------------------------------------------------------------
}
