package com.teamUPOD.UPOD.UPOD;

import datatypes.Page;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UpodDao {
	
	Connection connection = null;
    	Statement stmt = null;
	
	//TODO: pageExists
	public boolean pageExists(int pageId) {
		try
		{
		    Class.forName(""); // not sure wht goes in there
		    connection = DriverManager.getConnection(""); // not sure wht goes in there

		    stmt = connection.createStatement();
		    stmt.execute("SELECT FROM Equation WHERE equationID = " + pageID);

		    return true;
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

	//TODO: addPage
	public boolean addPage(String url, Page page) {
		return false;
	}
	
	//TODO: deletePage
	public void deletePage(int pageId) {
		try
		{
		    Class.forName(""); // not sure wht goes in there
		    connection = DriverManager.getConnection(""); // not sure wht goes in there

		    stmt = connection.createStatement();
		    stmt.execute("DELETE FROM Equation WHERE equationID = " + pageId);
		    return;
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
			return;
	}
}
