package com.teamUPOD.UPOD.UPOD;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import datatypes.Graphic;
import datatypes.Page;
import datatypes.Section;
import datatypes.Table;
import datatypes.Variable;
import utils.TableIdMap;

/**
 * Database Access Object for the backend server Is responsible for all
 * interactions with the database Should be completely localised
 * 
 * @author luciano abbott
 */
public class UpodDao {

	private Connection connection = null;
	private static UpodDao upodDao = null;

	public static final int INVALID_ID = -1;

	private UpodDao() {
		String username = "";
		String password = "";
		String url = "";

		InputStream input = null;
		Properties prop = new Properties();

		try {
			input = this.getClass().getResourceAsStream("/properties/db.properties");

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
			throw new IllegalStateException("Cannot connect to the database with information " + url, e);
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
	public Page getPage(int pageId) throws SQLException { // working, needs more testing
		Page page = null;
		ArrayList<Section> sections;

		Statement pageStatement = createStatement();
		ResultSet pageResult;

		pageResult = pageStatement.executeQuery("SELECT * FROM PAGE WHERE pageId = " + pageId); // get page
		pageResult.next();
		page = new Page(pageResult);
		sections = getSections(pageId);
		page.setSections(sections);

		pageStatement.close();
		return page;
	}

	private ArrayList<Section> getSections(int pageId) throws SQLException {
		ArrayList<Section> sections = new ArrayList<Section>();
		Section currentSection;
		Statement statement = createStatement();
		ResultSet sectionResult = statement.executeQuery("SELECT * FROM SECTION WHERE pageId = " + pageId); // get
																											// sections
		while (sectionResult.next()) {
			currentSection = new Section(sectionResult);

			currentSection.setGraphic(getGraphic(sectionResult.getInt("graphicId")));
			currentSection.addVariables(getVariables(pageId, currentSection.getSectionId()));

			sections.add(currentSection);
		}
		statement.close();
		return sections;
	}

	private ArrayList<Variable> getVariables(int pageId, int sectionId) throws SQLException {
		ArrayList<Variable> variables = new ArrayList<Variable>();
		Statement variableStatement, varIdListStatement;
		ResultSet variableResult, varIdListResult;

		varIdListStatement = createStatement();
		variableStatement = createStatement();

		varIdListResult = varIdListStatement
				.executeQuery("SELECT varId FROM SECVAR WHERE pageId = " + pageId + " AND sectionId = " + sectionId);

		while (varIdListResult.next()) {
			variableResult = variableStatement
					.executeQuery("SELECT * FROM VARIABLE WHERE varId=" + varIdListResult.getInt("varId"));
			while (variableResult.next()) {
				variables.add(new Variable(variableResult));
			}
		}

		varIdListStatement.close();
		variableStatement.close();
		return variables;
	}

	/**
	 * Pull all pages with page title, section title, or section text containing
	 * str.
	 * 
	 * @return an arraylist of relevant page objects.
	 * @Author Lauren Hepditch
	 */
	public ArrayList<Page> searchPages(String str) {
		ArrayList<Page> pages = new ArrayList<Page>();
		int id, size, i;
		Boolean inList = false;
		try {
			Statement stmt = createStatement();
			ResultSet rs;

			rs = stmt.executeQuery("SELECT PageId from PAGE WHERE title LIKE '%" + str + "%';");

			while (rs.next()) {
				id = rs.getInt("pageId");
				pages.add(getPage(id));
			}

			rs = stmt.executeQuery("SELECT DISTINCT PageId from SECTION WHERE sectionTitle LIKE '%" + str
					+ "%' or sectionText LIKE '%" + str + "%';");

			while (rs.next()) {

				id = rs.getInt("pageId");
				size = pages.size();// number of items is the arraylist

				for (i = 0; i < size; i++) {

					if (pages.get(i).getId() == id) { // if id already in list do not add again
						inList = true;
						break;
					}
				}
				if (inList == false) {
					pages.add(getPage(id));
				}
			}

			return pages;

		} catch (SQLException e) {
			throw new IllegalStateException("Could not retrieve pages.", e);
		}
	}

	private Graphic getGraphic(int graphicId) throws SQLException {
		Statement statement;
		ResultSet result;
		Graphic graphic = null;

		statement = createStatement();
		result = statement.executeQuery("SELECT * FROM GRAPHIC WHERE graphicId = " + graphicId); // get graphic

		if (result.next()) {
			graphic = new Graphic(result);
		}

		statement.close();
		return graphic;
	}

	/**
	 * Changes or creates a new page in the database.
	 * 
	 * @return
	 * @author Ziyi Zhang
	 */
	public void setPage(Page page) {
		try {
			// update page information
			createStatement().executeUpdate("INSERT INTO PAGE (pageId, title, URL,editing) VALUES(" + page.getId()
					+ ",'" + page.getTitle() + "','" + page.getUrl() + "',0) ON DUPLICATE KEY UPDATE title='"
					+ page.getTitle() + "', URL='" + page.getUrl() + "',editing=0;");
			// update section information
			ArrayList<Section> sections_list = page.getSections();
			for (int i = 0; i < sections_list.size(); i++) {
				createStatement().executeUpdate(
						"INSERT INTO SECTION (sectionId, pageId, sectionTitle, sectionText, equations, graphicId) VALUES("
								+ sections_list.get(i).getSectionId() + "," + page.getId() + ",'"
								+ sections_list.get(i).getTitle() + "','" + sections_list.get(i).getText() + "', '"
								+ sections_list.get(i).getEquations() + "',"
								+ sections_list.get(i).getGraphic().getGraphicId() + ") ON DUPLICATE KEY UPDATE pageId="
								+ page.getId() + ",sectionTitle='" + sections_list.get(i).getTitle() + "',sectionText='"
								+ sections_list.get(i).getText() + "',equations='" + sections_list.get(i).getEquations()
								+ "',graphicId=" + sections_list.get(i).getGraphic().getGraphicId() + ";");
				// update graphic informations
				createStatement().executeUpdate("INSERT INTO GRAPHIC (graphicId, graphicURL, description) VALUES("
						+ sections_list.get(i).getGraphic().getGraphicId() + ",'"
						+ sections_list.get(i).getGraphic().getGraphicURL() + "','"
						+ sections_list.get(i).getGraphic().getDescription() + "') ON DUPLICATE KEY UPDATE graphicURL='"
						+ sections_list.get(i).getGraphic().getGraphicURL() + "',description='"
						+ sections_list.get(i).getGraphic().getDescription() + "';");
				// update variable information
				ArrayList<Variable> variables_list = getVariables(page.getId(), sections_list.get(i).getSectionId());
				for (int j = 0; j < variables_list.size(); j++) {
					createStatement()
							.executeUpdate("INSERT INTO VARIABLE (varId, symbol, name, description, URL) VALUES("
									+ variables_list.get(j).getId() + ",'" + variables_list.get(j).getSymbol() + "','"
									+ variables_list.get(j).getName() + "','" + variables_list.get(j).getDescription()
									+ "','" + variables_list.get(j).getURL() + "') ON DUPLICATE KEY UPDATE symbol='"
									+ variables_list.get(j).getSymbol() + "', name='" + variables_list.get(j).getName()
									+ "', description='" + variables_list.get(j).getDescription() + "', URL='"
									+ variables_list.get(j).getURL() + "';");
					createStatement()
							.executeUpdate("INSERT INTO SECVAR (pageId, sectionId, varId) VALUES(" + page.getId() + ","
									+ sections_list.get(i).getSectionId() + "," + variables_list.get(j).getId()
									+ ") ON DUPLICATE KEY UPDATE varId=" + variables_list.get(j).getId() + ";");
				}
			}
		} catch (SQLException e) {
			throw new IllegalStateException("Could not perform page update.", e);
		}
		return;
	}

	/**
	 * Gets all variables & their data and returns an arraylist of these variable
	 * objects.
	 * 
	 * @return ArrayList<Variable> var_list
	 * @author Ziyi Zhang
	 */
	public ArrayList<Variable> getAllVariables() {
		ArrayList<Variable> var_list = new ArrayList<Variable>();
		try {
			ResultSet rs = createStatement().executeQuery("SELECT * FROM VARIABLE");
			while (rs.next()) {
				Variable temp_var = new Variable(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(1));
				var_list.add(temp_var);
			}
		} catch (SQLException e) {
			throw new IllegalStateException("Could not perform page update.", e);
		}
		return var_list;
	}

	private Connection getConnection() {
		return this.connection;
	}

	private Statement createStatement() throws SQLException {
		return UpodDao.getInstance().getConnection().createStatement();
	}

	/**
	 * Gets all graphics stored in the database.
	 * 
	 * @return ArrayList<Graphic>
	 * @author Lauren Hepditch
	 */
	public ArrayList<Graphic> getAllGraphics() {
		try {
			ArrayList<Graphic> graphics = new ArrayList<Graphic>();
			Statement stmt = createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM GRAPHIC"); // get all graphics from the database

			while (rs.next()) { // add all graphics to the graphics arraylist
				graphics.add(
						new Graphic(rs.getInt("graphicId"), rs.getString("graphicURL"), rs.getString("description")));
			}

			return graphics;

		} catch (SQLException e) {
			throw new IllegalStateException("Could not get graphics.", e);
		}

	}

	/**
	 * Return true if there is already a page with the id pageId in the database
	 * 
	 * @param pageId
	 * @return
	 * @throws SQLException
	 * @author Travis Leyenaar-Misson
	 */
	public boolean pageExists(int pageId) throws SQLException {
		Statement stmt = null;
		try {
			stmt = this.createStatement();
			stmt.executeQuery("Select * FROM Page where PageId = " + pageId);
			ResultSet rs1 = stmt.getResultSet();

			return rs1.next();

		} catch (SQLException e) {
			System.out.println("No connection");
		}
		stmt.close();
		return false;
	}

	/**
	 * Get the edit status of a page.
	 * 
	 * @author Lauren Hepditch
	 * @return true if page is currently being edited, false if not.
	 * 
	 */
	public Boolean getEditStatus(int pageId) {

		Boolean status = false;

		try {

			Statement stmt = createStatement();
			ResultSet rs;

			rs = stmt.executeQuery("SELECT editing FROM PAGE WHERE pageId=" + pageId);
			rs.next();

			status = rs.getBoolean("editing");

			return status;

		} catch (SQLException e) {
			throw new IllegalStateException("Could not get edit status.", e);
		}
	}

	/**
	 * Set the edit status of a page, true if page is currently being edited, false
	 * otherwise.
	 * 
	 * @author Lauren Hepditch
	 */
	public void setEditStatus(int pageId, Boolean status) {

		try {
			Statement stmt = createStatement();
			stmt.executeUpdate("UPDATE PAGE SET editing = " + status + " WHERE pageId = " + status);

		} catch (SQLException e) {
			throw new IllegalStateException("Could not set edit status.", e);
		}

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

	// TODO: deletePage
	/**
	 * Delete the page with the given id from the database
	 * 
	 * @param pageId
	 * @return success/fail
	 */
	public boolean deletePage(int pageId) throws SQLException {
		try {
			Statement pageStatement = createStatement();
			Statement sectionStatement = createStatement();

			pageStatement.execute("DELETE FROM Page WHERE PageId = " + pageId);
			sectionStatement.execute("DELETE FROM Section WHERE PageID = " + pageId);

			pageStatement.close();
			sectionStatement.close();
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

	/*
	 * Gets a varId that will be deleted from the database Deletes variable
	 * 
	 * @returns true if varId exist and returns false if the Id does not exist and
	 * therefore not deleted
	 * 
	 * @author Nathan Skof
	 */
	public boolean deleteVariable(int varId) {
		Statement stmt = null;
		try {
			stmt = this.createStatement();
			if (idExists("VARIABLE", "varId", varId)) {
				stmt.executeUpdate("DELETE FROM VARIABLE WHERE varId =" + varId);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
}
