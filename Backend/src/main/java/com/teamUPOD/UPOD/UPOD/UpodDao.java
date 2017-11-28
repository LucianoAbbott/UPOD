package com.teamUPOD.UPOD.UPOD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import datatypes.Page;
import datatypes.Table;
import utils.TableIdMap;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Database Access Object for the backend server Is responsible for all
 * interactions with the database Should be completely localised
 * 
 * @author luciano abbott
 */
public class UpodDao {
	// TODO: Discussion on passing error codes instead of boolean success/fail

	private Connection connection = null;
	private static UpodDao upodDao = null;

	public static final int INVALID_ID = -1;

	
	private UpodDao() {
		String username = "";
		String password = "";
		String url = "";
		
		Properties prop = new Properties();
		FileInputStream input = null;
		
		try {

			input = new FileInputStream("db.properties");
			prop.load(input);
			
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			
		} catch (IOException ex) {
			ex.printStackTrace();
			
		} finally {
			if (input != null) {
				try {
					input.close();
		
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect to the database.", e);
		}
	}

	/**
	 * Access method for UpodDao - follows singleton pattern
	 * 
	 * @return the UpodDao instance
	 */
	public static UpodDao getInstance() {
		if (upodDao == null) {
			upodDao = new UpodDao();
		}
		return upodDao;
	}

	/**
	 * Pull a page object containing all page data from the database.
	 * 
	 * @return a complete page object.
	 * @Author Lauren Hepditch
	 */
	public static Page getPage(int pageId) { //working , needs more testing
		Page page = null;
		Variable var = null;

		try {
			UpodDao dao = UpodDao.getInstance();
			Statement stmt = dao.connection.createStatement();
		    Statement stmtG = dao.connection.createStatement();
		    Statement stmtV = dao.connection.createStatement();
		    Statement stmtSV = dao.connection.createStatement();
			ResultSet rs,rsG,rsV,rsSV;
			
			rs = stmt.executeQuery("SELECT * FROM PAGE WHERE pageId = "+pageId); //get page infomation
		   	rs.next();
			
			page = new Page(rs.getInt("pageId"),rs.getString("title"),rs.getString("URL"),false);
			
			rs = stmt.executeQuery("SELECT * FROM SECTION WHERE pageId = " + pageId); //get sections
			
		   	while(rs.next()){ // fill sections with information and add to page
			   Section s = new Section();
			   s.setSectionId(rs.getInt("sectionId"));
			   s.setTitle(rs.getString("sectionTitle"));
			   s.setText(rs.getString("sectionText"));
			   s.setEquations(rs.getString("equations"));
			   
			   rsG = stmtG.executeQuery("SELECT * FROM GRAPHIC WHERE graphicId = "+rs.getInt("graphicId"));
				
			   if(rsG.next()){   
			   	s.setGraphic(new Graphic(rsG.getInt("graphicId"),rsG.getString("graphicURL"),rsG.getString("description")));   
			   }
			   else{
				s.setGraphic(null);
			   }
			   
			   rsSV = stmtSV.executeQuery("SELECT varId FROM SECVAR WHERE pageId = "+page.getId()+" AND sectionId = "+s.getSectionId());
			   
			   while(rsSV.next()){
				   rsV = stmtV.executeQuery("SELECT * FROM VARIABLE WHERE varId="+rsSV.getInt("varId"));
				   while(rsV.next()){
				   
				   var = new Variable(rsV.getString("symbol"),rsV.getString("name"),rsV.getString("description"),rsV.getString("URL"));
				   
				   s.getVariables().add(var);
				   }
			   }
			   
			   page.getSections().add(s);
		   	   }

			return page;
			
		} catch (SQLException e) {
			throw new IllegalStateException("Could not get page from database.", e);
		}

	/**
	 * Changes or creates a new page in the database. 
	 * @return 
	 * @Author Lauren Hepditch
	 */
	public void setPage(Page page){
		try{
			//update page information
			this.createStatement().executeUpdate("");
			//update section information
			//update graphic and equation information
			//update equvar relationships
			//update variable information
				
		}catch(SQLException e){
			throw new IllegalStateException("Could not perform page update.", e);
		}
	}
		
	private Connection getConnection() {
		return this.connection;
	}

	private Statement createStatement() throws SQLException {
		return this.getConnection().createStatement();
	}

	/**
	 * 
	 */
	public boolean pageExists(int pageId) {
		return false;
	}

	/**
	 * Get an Id that is not in use works for any table.
	 * 
	 * @return a valid Id with no attached page
	 * @Author Nathan Skof
	 */
	public int nextAvailableId(Table table) {
		int MaxID = 0;
		Statement stmt = null;
		try {
			stmt = this.createStatement();
			stmt.executeQuery("SELECT MAX(" + TableIdMap.getId(table) + ") FROM " + table.getName());
			ResultSet rs2 = stmt.getResultSet();
			if (rs2.next()) {
				MaxID = rs2.getInt(1);
			}
			MaxID += 1;
		} catch (SQLException e) {
			System.out.println("No connection");
		}
		return MaxID;
	}

	/**
	 * Query the database with the given table, idType and id
	 * 
	 * @param pageId
	 * @return true if there is a idType with the given Id that is not empty
	 * @Author Nathan Skof
	 */
	public boolean idExists(String Table, String idType, int id) {
		String check = "SELECT * FROM " + Table + " WHERE " + idType + " = " + id;
		Statement stmt = null;
		try {
			stmt = this.createStatement();
			ResultSet rs = stmt.executeQuery(check);

			if (rs.absolute(1)) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	// TODO: updatePage
	// TODO: When we update page, we will receive a list of variables
	/**
	 * Post a page to the database with the given page Id
	 * 
	 * @param pageId
	 * @param page
	 * @return success or fail
	 */
	public boolean updatePage(int pageId, Page page) {
		return false;
	}

	// TODO: deletePage
	/**
	 * Delete the page with the given id from the database
	 * 
	 * @param pageId
	 * @return success/fail
	 */
	public boolean deletePage(int pageId) {
		try {
			this.createStatement().execute("DELETE FROM Page WHERE PageId = " + pageId);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Gets a list of variables that are in the equation.
	 * 
	 * @param Section
	 *            object
	 * @return ArrayList<ArrayList<String>> varResult - all values of variables
	 *         except varId
	 * @author Ziyi Zhang
	 */
	public ArrayList<ArrayList<String>> getVariable(Section section) {
		ArrayList<ArrayList<String>> varResult = new ArrayList<ArrayList<String>>();
		try {
			ResultSet rs = this.createStatement().executeQuery(
					"SELECT symbol,name,description,URL FROM VARIABLE WHERE varId IN (SELECT varId FROM SECVAR WHERE sectionId = "
							+ section.getSectionId() + ")");
			// fill varResult
			while (rs.next()) {
				ArrayList<String> varInfo = new ArrayList<String>();
				for (int i = 1; i <= 4; i++) {
					varInfo.add(rs.getString(i));
				}
				varResult.add(varInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return varResult;
	}
}
