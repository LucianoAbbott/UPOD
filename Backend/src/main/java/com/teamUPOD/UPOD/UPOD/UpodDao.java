package com.teamUPOD.UPOD.UPOD;

import java.io.FileInputStream;
import java.io.IOException;
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
	public static Page getPage(int pageId) { // working, needs more testing
		Page page = null;
		ArrayList<Section> sections;

		try {
			Statement pageStatement = createStatement();
			ResultSet pageResult;

			pageResult = pageStatement.executeQuery("SELECT * FROM PAGE WHERE pageId = " + pageId); // get page
			pageResult.next();
			page = new Page(pageResult);
			sections = getSections(pageId);
			page.setSections(sections);

			pageStatement.close();
			return page;

		} catch (SQLException e) {
			throw new IllegalStateException("Could not get page from database.", e);
		}
	}

	private static ArrayList<Section> getSections(int pageId) throws SQLException {
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

	private static ArrayList<Variable> getVariables(int pageId, int sectionId) throws SQLException {
		ArrayList<Variable> variables = new ArrayList<Variable>();
		Statement variableStatement, varIdListStatement;
		ResultSet variableResult, varIdListResult;

		varIdListStatement = createStatement();
		variableStatement = createStatement();

		varIdListResult = varIdListStatement.executeQuery("SELECT varId FROM SECVAR WHERE pageId = " + pageId + " AND sectionId = " + sectionId);

		while (varIdListResult.next()) {
			variableResult = variableStatement.executeQuery("SELECT * FROM VARIABLE WHERE varId=" + varIdListResult.getInt("varId"));
			while (variableResult.next()) {
				variables.add(new Variable(variableResult));
			}
		}

		varIdListStatement.close();
		variableStatement.close();
		return variables;
	}

	private static Graphic getGraphic(int graphicId) throws SQLException {
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
	 * @return 
	 * @Author Lauren Hepditch
	 * @author Ziyi Zhang
	 */
	public void setPage(Page page){ //STILL HASN'T BEEN TESTED........ JUST WANTED THIS OUT THERE SO I CAN UPDATE W/O HAVING TO COPY/PASTA BITS AND PIECES
		try{
			//update page information
			this.createStatement().executeUpdate("INSERT INTO PAGE (pageId, title, URL, editing) VALUES("+page.getId()+","+page.getTitle()+","+page.getURL()+",0) ON DUPLICATE KEY UPDATE title="+page.getTitle()+", URL="+page.getURL()+",editing=0;");
			//update section information
			ArrayList<Section> sections_list=page.getSections();
			for(int i=0;i<sections_list.size();i++){
				this.createStatement().executeUpdate("INSERT INTO SECTION (sectionId, pageId, sectionTitle, sectionText, equations, graphicId) VALUES("+sections_list.get(i).getSectionId()+","+page.getId()+",'"+sections_list.get(i).getTitle()+"','"+sections_list.get(i).getText()+"', '"+sections_list.get(i).getEquations()+"',"+sections_list.get(i).getGraphic().getGraphicId()+") ON DUPLICATE KEY UPDATE pageId="+page.getId()+",sectionTitle='"+sections_list.get(i).getTitle()+"',sectionText='"+sections_list.get(i).getText()+"',equations='"+sections_list.get(i).getEquations()+"',graphicId="+sections_list.get(i).getGraphic().getGraphicId()+";");
				//update graphic informations
				this.createStatement().executeUpdate("INSERT INTO GRAPHIC (graphicId, graphicURL, description) VALUES("+sections_list.get(i).getGraphic().getGraphicId()+",'"+sections_list.get(i).getGraphic().getGraphicURL()+"','"+sections_list.get(i).getGraphic().getDescription()+"') ON DUPLICATE KEY UPDATE graphicURL='"+sections_list.get(i).getGraphic().getGraphicURL()+"',description='"+sections_list.get(i).getGraphic().getDescription()+"';");
				//update variable information
				ArrayList<Variable> variables_list=getVariables(page.getId(), sections_list.get(i).getSectionId());
				for (int j=0;i<variables_list.size();j++){
					this.createStatement().executeUpdate("INSERT INTO VARIABLE (varId, symbol, name, description, URL) VALUES("+variables_list.get(i).getVarId()+",'"+variables_list.get(i).getSymbol()+"','"+variables_list.get(i).getName()+"','"+variables_list.get(i).getDescription()+"','"+variables_list.get(i).getURL()+"') ON DUPLICATE KEY UPDATE symbol='"+variables_list.get(i).getSymbol()+"', name='"+variables_list.get(i).getName()+"', description='"+variables_list.get(i).getDescription()+"', URL='"+variables_list.get(i).getURL()+"';");
				}
			}
		}catch(SQLException e){
			throw new IllegalStateException("Could not perform page update.", e);
		}
	}

	private Connection getConnection() {
		return this.connection;
	}

	private Statement createStatement() throws SQLException {
		return UpodDao.getInstance().getConnection().createStatement();
	}

	/** Checks for existance of page in database
	 * 
	 * @return  
	 * @author Travis Leyenaar-Misson
	 */
	public boolean pageExists(int pageId) {
		
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
			this.createStatement().execute("DELETE FROM Section WHERE PageID = " + pageId);
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
	* Gets a varId that will be deleted from the database
	* Deletes variable
	* @returns true if varId exist and returns false if the Id does not exist and therefore not deleted
	* @author Nathan Skof
	*/
	public static boolean deleteVariable(int varId){
		Statement stmt = null;
		 try {
			 	stmt = this.createStatement();
			 	if(idExists("VARIABLE", "varId", varId)){
			 		stmt.executeUpdate("DELETE FROM VARIABLE WHERE varId ="+varId);
			 		return true;
			 	}
		 }
		 catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
		
	}
}
