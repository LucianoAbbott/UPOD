package com.teamUPOD.UPOD.UPOD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import datatypes.Equation;
import datatypes.Page;

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

	public static final String PAGE_TABLE_KEY = "Page";
	public static final String PAGE_TABLE_ID = "PageId";

	public static final String EQUATION_TABLE_KEY = "EQUATION";
	public static final String EQUATION_TABLE_ID = "equId";

	public static final String VARIABLE_TABLE_KEY = "VARIABLE";
	public static final String VARIABLE_TABLE_ID = "varId";

	public static final String SECTION_TABLE_KEY = "SECTION";
	public static final String SECTION_TABLE_ID = "sectionId";

	public static final String GRAPHIC_TABLE_KEY = "GRAPHIC";
	public static final String GRAPHIC_TABLE_ID = "graphicId";

	private static final HashMap<String, String> TABLE_ID_MAP;
	static {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(PAGE_TABLE_KEY, PAGE_TABLE_ID);
		map.put(EQUATION_TABLE_KEY, EQUATION_TABLE_ID);
		map.put(VARIABLE_TABLE_KEY, VARIABLE_TABLE_ID);
		map.put(SECTION_TABLE_KEY, SECTION_TABLE_ID);
		map.put(GRAPHIC_TABLE_KEY, GRAPHIC_TABLE_ID);

		TABLE_ID_MAP = (HashMap<String, String>) Collections.unmodifiableMap(map);
	}

	private UpodDao() {
		String username = "";
		String password = "";
		String url = "";
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
	public Page getPage(int pageId) {
		Page page = null;

		try {

			Statement stmt = this.createStatement();
			stmt.executeQuery("SELECT * FROM PAGE WHERE pageId = " + pageId);

		} catch (SQLException e) {
			throw new IllegalStateException("Could not get page from database.", e);
		}
		return page;
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
	public int nextAvailableId(String table) {
		int MaxID = 0;
		Statement stmt = null;
		try {
			stmt = this.createStatement();
			stmt.executeQuery("SELECT MAX(" + TABLE_ID_MAP.get(table) + ") FROM " + table);
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

	// ---------------------------------------------------------------------------------------------------------------------
	// Equations
	/**
	 * Creates Equation if none exists, updates otherwise.
	 * 
	 * @param Equation
	 *            object
	 * @author Ziyi Zhang
	 */
	public void setEquationURL(Equation equation) { // THIS ONE'S NOT DONEEEEEEEE the others are tested and work
		Statement stmt;
		try {
			stmt = this.createStatement();
			// update equation
			if (equationExists(equation.getId())) {
				stmt.executeUpdate("UPDATE EQUATION SET equationURL = '" + equation.getUrl() + "' WHERE equId="
						+ equation.getId());
				// update variables

			} else { // create equation
				stmt.executeUpdate(
						"INSERT INTO EQUATION VALUES (" + equation.getId() + ",'" + equation.getUrl() + "')");
				// create variables
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	public void deleteEquation(Equation equation) {
		Statement stmt;
		try {
			stmt = this.createStatement();
			stmt.executeQuery("delete from EQUVAR where equId=" + equation.getId() + ";");
			stmt.executeQuery("delete from EQUATION where equId=" + equation.getId() + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * Gets a list of variables that are in the equation.
	 * 
	 * @param Equation
	 *            object
	 * @return ArrayList<ArrayList<String>> varResult - all values of variables
	 *         except varId
	 * @author Ziyi Zhang
	 */
	public ArrayList<ArrayList<String>> getVariable(Equation equation) {
		ArrayList<ArrayList<String>> varResult = new ArrayList<ArrayList<String>>();
		try {
			ResultSet rs = this.createStatement().executeQuery(
					"SELECT symbol,name,category,description FROM VARIABLE WHERE varId IN (SELECT varId FROM EQUVAR WHERE equId = "
							+ equation.getId() + ")");
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

	/**
	 * Gets the number of equations in the database.
	 * 
	 * @return Integer count
	 * @author Ziyi Zhang
	 */
	public int getEquationCount() {
		int count = 0;
		try {
			// get count
			ResultSet rs = this.createStatement().executeQuery("SELECT COUNT(*) FROM EQUATION");
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * Returns true if the equation exists, false otherwise.
	 * 
	 * @param int
	 *            eId - equation id
	 * @return boolean
	 * @author Ziyi Zhang
	 */
	public boolean equationExists(int eId) {
		try {
			ResultSet rs = this.createStatement().executeQuery("select * from EQUATION where equId =" + eId);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	// ---------------------------------------------------------------------------------------------------------------------
}
