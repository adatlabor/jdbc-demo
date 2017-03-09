package dal.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dal.ActionResult;
import dal.DataAccessLayer;
import dal.exceptions.CouldNotConnectException;
import dal.exceptions.EntityNotFoundException;
import dal.exceptions.NotConnectedException;
import model.Member;
import model.Person;
import model.Video;
import model.VideoHeader;

/**
 * Initial implementation for the DataAccessLayer for the 33-VIDEO exercise.
 */
public class VideoDal implements DataAccessLayer<VideoHeader, Video, Member> {
	private Connection connection;
	protected static final String driverName = "oracle.jdbc.driver.OracleDriver";
	protected static final String databaseUrl = "jdbc:oracle:thin:@rapid.eik.bme.hu:1521:szglab";

	private void checkConnected() throws NotConnectedException {
		if (connection == null) {
			throw new NotConnectedException();
		}
	}

	@Override
	public void connect(String username, String password) throws CouldNotConnectException, ClassNotFoundException {
		try {
			if (connection == null || !connection.isValid(30)) {
				if (connection == null) {
					// Load the specified database driver
					Class.forName(driverName);
				} else {
					connection.close();
				}

				// Create new connection and get metadata
				connection = DriverManager.getConnection(databaseUrl, username, password);
			}
		} catch (SQLException e) {
			throw new CouldNotConnectException();
		}
	}

	@Override
	public List<VideoHeader> search(String keyword) throws NotConnectedException {
		checkConnected();
		return null;
	}

	@Override
	public List<Member> getStatistics() throws NotConnectedException {
		checkConnected();
		return null;
	}

	@Override
	public boolean commit() throws NotConnectedException {
		checkConnected();
		return false;
	}

	@Override
	public boolean rollback() throws NotConnectedException {
		checkConnected();
		return false;
	}

	@Override
	public ActionResult insertOrUpdate(Video entity, Integer foreignKey)
			throws NotConnectedException, EntityNotFoundException {
		checkConnected();
		return null;
	}

	@Override
	public boolean setAutoCommit(boolean value) throws NotConnectedException {
		checkConnected();
		return false;
	}

	@Override
	public void disconnect() {
	}

	@Override
	public List<Person> sampleQuery() throws NotConnectedException {
		checkConnected();
		List<Person> result = new ArrayList<>();
		try (Statement stmt = connection.createStatement()) {
			try (ResultSet rset = stmt.executeQuery("SELECT nev, szemelyi_szam FROM OKTATAS.SZEMELYEK "
					+ "ORDER BY NEV "
					+ "OFFSET 0 ROWS FETCH NEXT 20 ROWS ONLY")){
				while (rset.next()) {
					Person p = new Person(rset.getString("nev"), rset.getString("szemelyi_szam"));
					result.add(p);
				}
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
