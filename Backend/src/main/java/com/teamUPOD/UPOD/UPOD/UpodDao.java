package com.teamUPOD.UPOD.UPOD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;

import datatypes.Page;

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
	
	//TODO: pageExists
	/**
	 * Query the database with the given page Id
	 * @param pageId
	 * @return true if there is a page with the given Id that is not empty
	 */
	public boolean pageExists(int pageId) {
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
}
