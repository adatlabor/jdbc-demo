/**
 * This JavaFX skeleton is provided for the Software Laboratory 5 course. Its structure
 * should provide a general guideline for the students.
 * As suggested by the JavaFX model, we'll have a GUI (view), 
 * a controller class and a model (this one).
 */

package application;

import java.sql.*;
import java.util.Map;

// Model class
public class Model {

	// Database driver and URL
	protected static final String driverName = "oracle.jdbc.driver.OracleDriver";
	protected static final String databaseUrl = "jdbc:oracle:thin:@rapid.eik.bme.hu:1521:szglab";

	// Product name and product version of the database
	protected String databaseProductName = null;
	protected String databaseProductVersion = null;

	// Connection object
	protected Connection connection = null;

	// Enum structure for Exercise #2
	protected enum ModifyResult {
		InsertOccured, UpdateOccured
	}

	// String containing last error message
	protected String lastError = "";

	/**
	 * Model constructor
	 */
	public Model() {
	}

	/**
	 * Give product name of the database
	 * 
	 * @return Product name of the database
	 */
	public String getDatabaseProductName() {

		return databaseProductName;

	}

	/**
	 * Give product version of the database
	 * 
	 * @return Product version of the database
	 */
	public String getDatabaseProductVersion() {

		return databaseProductVersion;

	}

	/**
	 * Give database URL
	 * 
	 * @return Database URL
	 */
	public String getDatabaseUrl() {

		return databaseUrl;

	}

	/**
	 * Give the message of last error
	 * 
	 * @return Message of last error
	 */
	public String getLastError() {
		
		return lastError;
		
	}

	/**
	 * Tries to connect to the database
	 * 
	 * @param userName
	 *            User who has access to the database
	 * @param password
	 *            User's password
	 */
	public boolean connect(String userName, String password) {

		try {

			// If connection status is disconnected
			if (connection == null || !connection.isValid(30)) {

				if (connection == null) {

					// Load the specified database driver
					Class.forName(driverName);

					// Driver is for Oracle 12cR1 (certified with JDK 7 and JDK
					// 8)
					if (java.lang.System.getProperty("java.vendor").equals("Microsoft Corp.")) {
						DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
					}
				} else {

					connection.close();

				}

				// Create new connection and get metadata
				connection = DriverManager.getConnection(databaseUrl, userName, password);
				DatabaseMetaData dbmd = connection.getMetaData();

				databaseProductName = dbmd.getDatabaseProductName();
				databaseProductVersion = dbmd.getDatabaseProductVersion();

			}

			return true;

		} catch (SQLException e) {

			// !TODO: More user friendly error handling
			lastError = e.toString();
			return false;

		} catch (ClassNotFoundException e) {

			// !TODO: More user friendly error handling
			lastError = e.toString();
			return false;

		}

	}

	/**
	 * Tests the database connection by submitting a query
	 * 
	 * @return True on success, false on fail
	 */
	public String testConnection() {

		try {

			// Create SQL query and execute it
			// If user input has to be processed, use PreparedStatement instead!
			Statement stmt = connection.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT count(*) FROM oktatas.igazolvanyok");

			// Process the results
			String result = null;
			while (rset.next()) {
				result = String.format("Total number of rows in 'Igazolvanyok' table in 'Oktatas' schema: %s",
						rset.getString(1));
			}

			// Close statement
			stmt.close();

			return result;

		} catch (SQLException e) {

			// !TODO: More user friendly error handling
			lastError = e.toString();
			
			return null;

		}
	}

	/**
	 * Method for Exercise #1
	 * 
	 * @return Result of the query
	 */
	public ResultSet search(String keyword) {

		return null;

	}

	/**
	 * Method for Exercise #2
	 * 
	 * @param data
	 *            New or modified data
	 * @return Type of action has been performed
	 */
	public ModifyResult modifyData(Map data) {

		return ModifyResult.UpdateOccured;

	}

	/**
	 * Method for Exercise #4
	 * 
	 * @return True on success, false on fail
	 */
	public boolean commit() {

		return false;

	}

	/**
	 * Method for Exercise #5
	 * 
	 * @return Result of the query
	 */
	public ResultSet getStatistics() {

		return null;

	}

}
