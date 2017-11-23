package com.teamUPOD.UPOD.UPOD;

import datatypes.Page;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database Access Object for the backend server
 * Is responsible for all interactions with the database
 * Should be completely localised
 * @author luciano abbott
 */
public class UpodDao {
	//TODO: Discussion on passing error codes instead of boolean success/fail
	
	
	Connection connection = null;
    	Statement stmt = null;
	
	private static UpodDao upodDao = null;

	public static int INVALID_ID = -1;
	
	//TODO: UpodDao constructor
	private UpodDao () {
		
	}
	
	/**
	 * Access method for UpodDao - follows singleton pattern
	 * @return the UpodDao instance
	 */
	public static UpodDao getInstance() { 
		if (upodDao == null) {
			upodDao = new UpodDao();
		}
		return upodDao;
	}

	//TODO: nextAvailableId
	/**
	 * Get an Id that is not in use works for any table. 
	 * @return a valid Id with no attached page
	 * @Author Nathan Skof
	 */
	public int nextAvailableId( String Table, String id ){
		 int MaxID = 0;
		 try {
			stmt = connection.createStatement();
			stmt.executeQuery("SELECT MAX("+ id +") FROM "+ Table);
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
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try
		{
		    Class.forName(""); // not sure wht goes in there
		    connection = DriverManager.getConnection(""); // not sure wht goes in there

		    stmt = connection.createStatement();
		    stmt.execute("DELETE FROM Page WHERE PageId = " + pageId);
		}
		catch (Exception e) {
		    e.printStackTrace();
		}finally {
		    try {  
			stmt.close();
			connection.close();
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
		return false;
	}
	
	public Page getPage(int pageId) {
		return null;
	}
}
