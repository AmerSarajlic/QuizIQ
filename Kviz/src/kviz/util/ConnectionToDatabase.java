package kviz.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDatabase {

	private static final String USER_NAME = "root";
	private static final String PASSWORD = "ameramer";
	private static final String URL = "jdbc:mysql://localhost/iqtest";

	public static Connection getConected() throws SQLException {
		
		return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
	}

}
