/**
 * This skeleton is provided for the Software Laboratory 5 course. Its structure
 * should provide a general guideline for the students. Though we were trying to
 * create a good example application here, the code is probably not suitable for
 * a real life application.
 * 
 * Written by
 * 	Gergely J. Horváth
 * 	Richárd Milanovits
 * Based on the previous version by
 * 	Ádám Kollár
 * Revised by
 * 	Roland Kamaras
 */

import java.sql.*;
import java.util.Map;

// Model class
public class MyJwsApplicationModel {

	// As suggested by the Swing model, we'll have a model (this one) and a 
	// GUI + controllers class.
	protected MyJwsApplication gui;

	// DataBase driver and URL
	protected static final String driverName = "oracle.jdbc.driver.OracleDriver";
	protected static final String url = "jdbc:oracle:thin:@rapid.eik.bme.hu:1521:szglab";

	// Connection object
	protected Connection connection = null;

	// Enum structure for Exercise #2
	protected enum ModifyResult {
		InsertOccured,
		UpdateOccured
	}

	// Class constructor
	public MyJwsApplicationModel(MyJwsApplication application) {
		gui = application;
	}

	/**
	 * Tries to connect to the database.
	 * @param userName user who has access to the database
	 * @param password user's password
	 */
	public void connect(String userName, String password) throws SQLException, ClassNotFoundException {

		// If connection status is disconnected
		if (connection == null || !connection.isValid(30)) {

			if (connection == null) {

				// Load the specified database driver
				Class.forName(driverName);

				if (java.lang.System.getProperty("java.vendor").equals("Microsoft Corp.")) {
					DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
				}

			} else {

				connection.close();

			}

			// Create new connection
			connection = DriverManager.getConnection(url, userName, password);
			DatabaseMetaData dbmd = connection.getMetaData();
			gui.log("Connected to: '" + url + "'");
			gui.log(String.format("DBMS: %s, version: %s", dbmd.getDatabaseProductName(), dbmd.getDatabaseProductVersion()));

		}

	}

	/**
	 * Tests the database connection by submitting a query.
	 * @return true on success, false on fail
	 */
	public boolean testConnection() throws SQLException {

		// If user input has to be processed, use PreparedStatement instead
		Statement stmt = connection.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT count(*) FROM oktatas.igazolvanyok");

		while (rset.next()) {
			gui.log(String.format("Total number of rows in 'Igazolvanyok' table in 'Oktatas' schema: %s", rset.getString(1)));
		}

		stmt.close();

		return true;
	}

	/**
	 * Method for Exercise #1
	 * @return result of the query
	 */
	public ResultSet search(String keyword) {
		return null;
	}

	/**
	 * Method for Exercise #2
	 * @return type of action has been performed
	 */
	public ModifyResult modifyData(Map data) throws SQLException {
		return ModifyResult.UpdateOccured;
	}

	/**
	 * Method for Exercise #4
	 * @return true on success, false on fail
	 */
	public boolean commit() {
		return false;
	}

	/**
	 * Method for Exercise #5
	 * @return result of the query
	 */
	public ResultSet getStatistics() {
		return null;
	}

	// Finalize method
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}

}
