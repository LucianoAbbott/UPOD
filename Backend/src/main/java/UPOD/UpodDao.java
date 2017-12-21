package UPOD;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
		InputStream input;
		Properties prop = new Properties();

		try {
			input = this.getClass().getResourceAsStream("/properties/db.properties");

			prop.load(input);
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			input.close();
			
			connection = DriverManager.getConnection(url, username, password);
		} catch (IOException e) {
			throw new IllegalStateException("Cannot load database credentials", e);
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect to the database at " + url, e);
		}
	}
	
	// exists for the mock upod dao, still useful don't remove
	public UpodDao(UpodDao dao) {}

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
	public Page getPage(int pageId) throws SQLException {
		Page page = null;
		Section[] sections;

		PreparedStatement pageStatement = null;
		String selectPage = "SELECT * FROM PAGE WHERE pageId = ?";
		connection.setAutoCommit(false);
		pageStatement = connection.prepareStatement(selectPage);
		pageStatement.setInt(1, pageId);
		ResultSet pageResult = pageStatement.executeQuery();
		connection.commit();
		
		
		pageResult.next();
		page = new Page(pageResult);
		sections = getSections(pageId);
		page.setSections(sections);
		
		pageStatement.close();
		connection.setAutoCommit(true);
		return page;
	}
	
	/**
	 * Pull a page object containing all page data from the database.
	 * 
	 * @return a complete page object.
	 * @Author Lauren Hepditch
	 */
	public static Page getPage(String title) throws SQLException {
		Page page = null;
		Section[] sections;

		PreparedStatement pageStatement = null;
		String selectPage = "SELECT * FROM PAGE WHERE title = ?";
		connection.setAutoCommit(false);
		pageStatement = connection.prepareStatement(selectPage);
		pageStatement.setString(1, title);
		ResultSet pageResult = pageStatement.executeQuery();
		connection.commit();
		
		
		pageResult.next();
		page = new Page(pageResult);
		sections = getSections(page.getId());
		page.setSections(sections);
		
		pageStatement.close();
		connection.setAutoCommit(true);
		return page;
	}
	private Section[] getSections(int pageId) throws SQLException {
		Section[] sections = new Section[Page.MAX_SECTION_COUNT];
		Section currentSection;
		//Statement statement = createStatement();
		//ResultSet sectionResult = statement.executeQuery("SELECT * FROM SECTION WHERE pageId = " + pageId); // get
		
		
		PreparedStatement statement = null;
		String selectSection = "SELECT * FROM SECTION WHERE pageId = ?"; 
		connection.setAutoCommit(false);
		statement = connection.prepareStatement(selectSection);
		statement.setInt(1, pageId);
		ResultSet sectionResult = statement.executeQuery();
		connection.commit();
		
		
		
		int index = 0;																						// sections
		while (sectionResult.next()) {
			currentSection = new Section(sectionResult);

			currentSection.setGraphic(getGraphic(sectionResult.getInt("graphicId")));
			currentSection.addVariables(getVariables(pageId, currentSection.getSectionId()));

			sections[index] = currentSection;
			index++;
		}
		statement.close();
		connection.setAutoCommit(true);
		return sections;
	}

	private ArrayList<Variable> getVariables(int pageId, int sectionId) throws SQLException {
		ArrayList<Variable> variables = new ArrayList<Variable>();
		//Statement variableStatement, varIdListStatement;
		//ResultSet variableResult, varIdListResult;

		//varIdListStatement = createStatement();
		//variableStatement = createStatement();

		//varIdListResult = varIdListStatement.executeQuery("SELECT varId FROM SECVAR WHERE pageId = " + pageId + " AND sectionId = " + sectionId);

		PreparedStatement varIdListStatement = null;
		PreparedStatement variableStatement = null;
		String selectVarId = "SELECT varId FROM SECVAR WHERE pageId = ? AND sectionId = ?"; 
		String selectVar = "SELECT * FROM VARIABLE WHERE varId = ?";
		connection.setAutoCommit(false);
		varIdListStatement = connection.prepareStatement(selectVarId);
		varIdListStatement.setInt(1, pageId);
		varIdListStatement.setInt(2, sectionId);
		ResultSet varIdListResult = varIdListStatement.executeQuery(); 
		variableStatement = connection.prepareStatement(selectVar);
		connection.commit();
		
		
		while (varIdListResult.next()) {
			//variableResult = variableStatement.executeQuery("SELECT * FROM VARIABLE WHERE varId=" + varIdListResult.getInt("varId"));
			variableStatement.setInt(1, varIdListResult.getInt("varId"));
			ResultSet variableResult = variableStatement.executeQuery();
			connection.commit();
			
			
			while (variableResult.next()) {
				variables.add(new Variable(variableResult));
			}
		}

		varIdListStatement.close();
		variableStatement.close();
		connection.setAutoCommit(true);
		return variables;
	}

	/**
	 * Pull all pages with page title, section title, or section text containing
	 * str.
	 * 
	 * @return an arraylist of relevant page objects.
	 * @Author Lauren Hepditch
	 */
	public ArrayList<Page> searchPages(String str) throws SQLException {
		ArrayList<Page> pages = new ArrayList<Page>();
		int id, size, i;
		Boolean inList = false;
		//Statement stmt = createStatement();
		//ResultSet rs;

		//rs = stmt.executeQuery("SELECT PageId from PAGE WHERE title LIKE '%" + str + "%';");
		
		PreparedStatement stmt = null;
		String selectPageId = "SELECT PageId from PAGE WHERE title LIKE ?;"; 
		connection.setAutoCommit(false);
		stmt = connection.prepareStatement(selectPageId);
		stmt.setString(1, str);
		ResultSet rs = stmt.executeQuery();
		connection.commit();
		
		while (rs.next()) {
			id = rs.getInt("pageId");
			pages.add(getPage(id));
		}

		//rs = stmt.executeQuery("SELECT DISTINCT PageId from SECTION WHERE sectionTitle LIKE '%" + str
				//+ "%' or sectionText LIKE '%" + str + "%';");
		
		selectPageId = "SELECT DISTINCT PageId from SECTION WHERE sectionTitle LIKE ? or sectionText LIKE ?;";
		stmt = connection.prepareStatement(selectPageId);
		stmt.setString(1, str);
		stmt.setString(1, str);
		rs = stmt.executeQuery();
		connection.commit();
		
		
		
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
		
		
		stmt.close();
		connection.setAutoCommit(true);
		return pages;
	}

	private Graphic getGraphic(int graphicId) throws SQLException {
		//Statement statement;
		//ResultSet result;
		Graphic graphic = null;

		//statement = createStatement();
		//result = statement.executeQuery("SELECT * FROM GRAPHIC WHERE graphicId = " + graphicId); // get graphic

		PreparedStatement statement = null;
		String selectGraphic = "SELECT * FROM GRAPHIC WHERE graphicId = ?"; 
		connection.setAutoCommit(false);
		statement = connection.prepareStatement(selectGraphic);
		statement.setInt(1, graphicId);
		ResultSet result = statement.executeQuery();
		connection.commit();
		
		if (result.next()) {
			graphic = new Graphic(result);
		}

		statement.close();
		connection.setAutoCommit(true);
		return graphic;
	}

	/**
	 * Changes or creates a new page in the database.
	 * 
	 * @return
	 * @author Ziyi Zhang
	 */
	public void setPage(Page page) throws SQLException {
			int graphicId;
			
//			createStatement().executeUpdate("INSERT INTO PAGE (pageId, title, URL,editing) VALUES(" + page.getId()
//					+ ",'" + page.getTitle() + "','" + page.getUrl() + "',0) ON DUPLICATE KEY UPDATE title='"
//					+ page.getTitle() + "', URL='" + page.getUrl() + "',editing=0;");
			
			PreparedStatement stmt = null;
			String insert = "INSERT INTO PAGE (pageId, title, URL,editing) VALUES(? , ? , ? , 0) ON DUPLICATE KEY UPDATE title= ?, URL= ?,editing=0;";
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(insert);
			stmt.setInt(1, page.getId());
			stmt.setString(2, page.getTitle());
			stmt.setString(3, page.getUrl());
			stmt.setString(4, page.getTitle());
			stmt.setString(5, page.getUrl());
			stmt.executeUpdate();
			connection.commit();
			

			Section[] sections_list = page.getSections();
			for (Section section : sections_list) {
				if (section.getGraphic() != null) {
					graphicId = section.getGraphic().getGraphicId();
				} else {
					graphicId = 0;
				}
//				createStatement().executeUpdate(
//						"INSERT INTO SECTION (sectionId, pageId, sectionTitle, sectionText, equations, graphicId) VALUES("
//								+ section.getSectionId() + "," + page.getId() + ",'" + section.getTitle() + "','"
//								+ section.getText() + "', '" + section.getEquations() + "',"
//								+ graphicId + ") ON DUPLICATE KEY UPDATE pageId="
//								+ page.getId() + ",sectionTitle='" + section.getTitle() + "',sectionText='"
//								+ section.getText() + "',equations='" + section.getEquations() + "',graphicId="
//								+ graphicId + ";");
				
				insert = "INSERT INTO SECTION (sectionId, pageId, sectionTitle, sectionText, equations, graphicId) VALUES( ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE pageId= ?,sectionTitle= ?,sectionText= ?,equations= ?,graphicId= ?;";
				
				connection.setAutoCommit(false);
				stmt = connection.prepareStatement(insert);
				stmt.setInt(1, section.getSectionId());
				stmt.setInt(2, page.getId());
				stmt.setString(3, section.getTitle());
				stmt.setString(4, section.getText());
				stmt.setString(5, section.getEquations());
				stmt.setInt(6, graphicId);
				stmt.setInt(7, page.getId());
				stmt.setString(8, section.getTitle());
				stmt.setString(9, section.getText());
				stmt.setString(10, section.getEquations());
				stmt.setInt(11, graphicId);
				stmt.executeUpdate();
				connection.commit();
				
//				createStatement().executeUpdate("INSERT INTO GRAPHIC (graphicId, graphicURL, description) VALUES("
//						+ section.getGraphic().getGraphicId() + ",'"
//						+ section.getGraphic().getGraphicURL() + "','"
//						+ section.getGraphic().getDescription() + "') ON DUPLICATE KEY UPDATE graphicURL='"
//						+ section.getGraphic().getGraphicURL() + "',description='"
//						+ section.getGraphic().getDescription() + "';");
				
				
				insert = "INSERT INTO GRAPHIC (graphicId, graphicURL, description) VALUES( ?, ?, ?) ON DUPLICATE KEY UPDATE graphicURL= ?,description= ?;";
				
				connection.setAutoCommit(false);
				stmt = connection.prepareStatement(insert);
				stmt.setInt(1, section.getGraphic().getGraphicId());
				stmt.setString(2, section.getGraphic().getGraphicURL());
				stmt.setString(3, section.getGraphic().getDescription());
				stmt.setString(4, section.getGraphic().getGraphicURL());
				stmt.setString(5, section.getGraphic().getDescription());
				stmt.executeUpdate();
				connection.commit();

				
				ArrayList<Variable> variables_list = getVariables(page.getId(), section.getSectionId());
				for (Variable variable : variables_list) {
//					createStatement()
//					.executeUpdate("INSERT INTO VARIABLE (varId, symbol, name, description, URL) VALUES("
//							+ variable.getId() + ",'" + variable.getSymbol() + "','"
//							+ variable.getName() + "','" + variable.getDescription()
//							+ "','" + variable.getURL() + "') ON DUPLICATE KEY UPDATE symbol='"
//							+ variable.getSymbol() + "', name='" + variable.getName()
//							+ "', description='" + variable.getDescription() + "', URL='"
//							+ variable.getURL() + "';");
					
					
					
					insert = "INSERT INTO VARIABLE (varId, symbol, name, description, URL) VALUES( ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE symbol= ?,name= ?,description= ?,URL= ?;";
					
					connection.setAutoCommit(false);
					stmt = connection.prepareStatement(insert);
					stmt.setInt(1, variable.getId());
					stmt.setString(2, variable.getSymbol());
					stmt.setString(3, variable.getName());
					stmt.setString(4, variable.getDescription());
					stmt.setString(5, variable.getURL());
					stmt.setString(6, variable.getSymbol());
					stmt.setString(7, variable.getName());
					stmt.setString(8, variable.getDescription());
					stmt.setString(9, variable.getURL());
					stmt.executeUpdate();
					connection.commit();

					
					
//					createStatement()
//					.executeUpdate("INSERT INTO SECVAR (pageId, sectionId, varId) VALUES(" + page.getId() + ","
//							+ section.getSectionId() + "," + variable.getId()
//							+ ") ON DUPLICATE KEY UPDATE varId=" + variable.getId() + ";");	
					
					
					insert = "INSERT INTO SECVAR (pageId, sectionId, varId) VALUES( ?, ?, ?) ON DUPLICATE KEY UPDATE varId= ?;";
					
					connection.setAutoCommit(false);
					stmt = connection.prepareStatement(insert);
					stmt.setInt(1, page.getId());
					stmt.setInt(2, section.getSectionId());
					stmt.setInt(3, variable.getId());
					stmt.setInt(4, variable.getId());
					stmt.executeUpdate();
					connection.commit();
					
					}
			}
			
		stmt.close();
		connection.setAutoCommit(true);
		return;
	}

	/**
	 * Gets all variables & their data and returns an arraylist of these variable
	 * objects.
	 * 
	 * @return ArrayList<Variable> var_list
	 * @author Ziyi Zhang
	 */
	public ArrayList<Variable> getAllVariables() throws SQLException {
		ArrayList<Variable> var_list = new ArrayList<Variable>();
		//Statement statement = createStatement();
		//ResultSet rs = statement.executeQuery("SELECT * FROM VARIABLE");
		
		
		PreparedStatement statement = null;
		String selectVar = "SELECT * FROM VARIABLE"; 
		connection.setAutoCommit(false);
		statement = connection.prepareStatement(selectVar);
		ResultSet rs = statement.executeQuery();
		connection.commit();
		
		
		while (rs.next()) {
			Variable temp_var = new Variable(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getInt(1));
			var_list.add(temp_var);
		}
		statement.close();
		connection.setAutoCommit(true);
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
	public ArrayList<Graphic> getAllGraphics() throws SQLException {
		ArrayList<Graphic> graphics = new ArrayList<Graphic>();
		//Statement stmt = createStatement();
		//ResultSet rs = stmt.executeQuery("SELECT * FROM GRAPHIC"); // get all graphics from the database

		
		PreparedStatement stmt = null;
		String selectGraphic = "SELECT * FROM GRAPHIC"; 
		connection.setAutoCommit(false);
		stmt = connection.prepareStatement(selectGraphic);
		ResultSet rs = stmt.executeQuery();
		connection.commit();
		
		
		while (rs.next()) { // add all graphics to the graphics arraylist
			graphics.add(
					new Graphic(rs.getInt("graphicId"), rs.getString("graphicURL"), rs.getString("description")));
		}
		stmt.close();
		connection.setAutoCommit(true);
		return graphics;
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
		//Statement stmt = null;
		boolean result;

		//stmt = createStatement();
		//stmt.executeQuery("Select * FROM PAGE where PageId = " + pageId);
		//ResultSet rs1 = stmt.getResultSet();
		
		
		PreparedStatement stmt = null;
		String selectPage = "Select * FROM PAGE where PageId = ?"; 
		connection.setAutoCommit(false);
		stmt = connection.prepareStatement(selectPage);
		stmt.setInt(1, pageId);
		ResultSet rs1 = stmt.executeQuery();
		connection.commit();
		
		
		result = rs1.next();

		stmt.close();
		connection.setAutoCommit(true);
		return result;
	}

	/**
	 * Get the edit status of a page.
	 * 
	 * @author Lauren Hepditch
	 * @return true if page is currently being edited, false if not.
	 * 
	 */
	public Boolean getEditStatus(int pageId) throws SQLException{

		Boolean status = false;
		
		PreparedStatement stmt = null;
		String selectPage = "SELECT editing FROM PAGE WHERE pageId = ?"; 
		
		try {

			//Statement stmt = createStatement();
			//ResultSet rs;

			//rs = stmt.executeQuery("SELECT editing FROM PAGE WHERE pageId=" + pageId);
			
			
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(selectPage);
			stmt.setInt(1, pageId);
			ResultSet rs = stmt.executeQuery();
			connection.commit();
			
			
			
			rs.next();

			status = rs.getBoolean("editing");

			return status;

		} catch (SQLException e) {
			throw new IllegalStateException("Could not get edit status.", e);
		}finally{
			stmt.close();
			connection.setAutoCommit(true);
		}
	}

	/**
	 * Set the edit status of a page, true if page is currently being edited, false
	 * otherwise.
	 * 
	 * @author Lauren Hepditch
	 */
	public void setEditStatus(int pageId, Boolean status) throws SQLException{
		PreparedStatement stmt = null;
		String selectPage = "UPDATE PAGE SET editing = ? WHERE pageId = ?;"; 
		try {
			//Statement stmt = createStatement();
			//stmt.executeUpdate("UPDATE PAGE SET editing = " + status + " WHERE pageId = " + status);
			
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(selectPage);
			stmt.setBoolean(1, status);
			stmt.setBoolean(2, status);
			stmt.executeUpdate();
			connection.commit();
			
		} catch (SQLException e) {
			throw new IllegalStateException("Could not set edit status.", e);
		}finally{
			stmt.close();
			connection.setAutoCommit(true);
		}

	}

	/**
	 * Get an Id that is not in use works for any table.
	 * 
	 * @return a valid Id with no attached page
	 * @Author Nathan Skof
	 */
	public int nextAvailableId(Table table) throws SQLException {
		int MaxID = 0;
		//Statement stmt = null;
		//stmt = createStatement();
		//stmt.executeQuery("SELECT MAX(" + TableIdMap.getId(table) + ") FROM " + table.getName());
		//ResultSet rs2 = stmt.getResultSet();
		
		
		PreparedStatement stmt = null;
		String selectTable = "SELECT MAX( ? ) FROM ?"; 
		connection.setAutoCommit(false);
		stmt = connection.prepareStatement(selectTable);
		stmt.setString(1, TableIdMap.getId(table));
		stmt.setString(2, table.getName());
		ResultSet rs2 = stmt.executeQuery();
		connection.commit();
		
		
		if (rs2.next()) {
			MaxID = rs2.getInt(1);
		}
		MaxID += 1;
		
		stmt.close();
		connection.setAutoCommit(true);
		return MaxID;
	}

	/**
	 * Query the database with the given table, idType and id
	 * 
	 * @param pageId
	 * @return true if there is a idType with the given Id that is not empty
	 * @Author Nathan Skof
	 */
	public boolean idExists(String Table, String idType, int id) throws SQLException{
		//String check = "SELECT * FROM " + Table + " WHERE " + idType + " = " + id;
		//Statement stmt = null;
		//stmt = createStatement();
		//ResultSet rs = stmt.executeQuery(check);
		
		
		PreparedStatement stmt = null;
		String selectSection = "SELECT * FROM ? WHERE ? = ?"; 
		connection.setAutoCommit(false);
		stmt = connection.prepareStatement(selectSection);
		stmt.setString(1, Table);
		stmt.setString(2, idType);
		stmt.setInt(3, id);
		ResultSet rs = stmt.executeQuery();
		connection.commit();
		
	
		if (rs.absolute(1)) {
			return true;
		}
		
		connection.setAutoCommit(true);
		return false;
	}

	// TODO: deletePage
	/**
	 * Delete the page with the given id from the database
	 * 
	 * @param pageId
	 * @return success/fail
	 */
	public void deletePage(int pageId) throws SQLException {
		//Statement pageStatement = createStatement();
		//Statement sectionStatement = createStatement();

		//pageStatement.execute("DELETE FROM PAGE WHERE PageId = " + pageId);
		//sectionStatement.execute("DELETE FROM SECTION WHERE PageID = " + pageId);
		
		
		PreparedStatement pageStatement = null;
		PreparedStatement sectionStatement = null;
		String selectPage = "DELETE FROM PAGE WHERE PageId = ?"; 
		String selectSection = "DELETE FROM SECTION WHERE PageID = ?"; 
		connection.setAutoCommit(false);
		pageStatement = connection.prepareStatement(selectPage);
		pageStatement.setInt(1, pageId);
		sectionStatement = connection.prepareStatement(selectSection);
		sectionStatement.setInt(1, pageId);
		pageStatement.execute();
		sectionStatement.execute();
		connection.commit();
		
		
		pageStatement.close();
		sectionStatement.close();
		connection.setAutoCommit(true);
	}
/*
	/**
	 * Gets a list of variables that are in the equation.
	 * 
	 * @param Section
	 *            object
	 * @return ArrayList<ArrayList<String>> varResult - all values of variables
	 *         except varId
	 * @author Ziyi Zhang
	 */
/*	public ArrayList<ArrayList<String>> getVariable(Section section) throws SQLException {
		ArrayList<ArrayList<String>> varResult = new ArrayList<ArrayList<String>>();
		Statement statement = createStatement();
		ResultSet rs = statement.executeQuery(
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
		statement.close();
		return varResult;
	}
*/
	/*
	 * Gets a varId that will be deleted from the database Deletes variable
	 * 
	 * @returns true if varId exist and returns false if the Id does not exist and
	 * therefore not deleted
	 * 
	 * @author Nathan Skof
	 */
	public boolean deleteVariable(int varId) throws SQLException {
		//Statement stmt = null;
		//stmt = createStatement();
		
		PreparedStatement stmt = null;
		String deleteVar = "DELETE FROM VARIABLE WHERE varId = ?"; 
		
		
		if (idExists("VARIABLE", "varId", varId)) {
			//stmt.executeUpdate("DELETE FROM VARIABLE WHERE varId =" + varId);
			
			
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(deleteVar);
			stmt.setInt(1, varId);
			stmt.execute();
			connection.commit();
			
			
			stmt.close();
			connection.setAutoCommit(true);
			return true;
		}
		
		connection.setAutoCommit(true);
		return false;

	}
}
