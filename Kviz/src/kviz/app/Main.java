package kviz.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import kviz.util.ConnectionToDatabase;
import kviz.util.SystemMessages;

public class Main {

	/*
	 * Main app for starting our application
	 */
	public static void main(String[] args) throws SQLException {
		Scanner input = new Scanner(System.in);
		Connection conn = ConnectionToDatabase.getConected();
		try {

			Application.runApp(input, conn);

		} catch (Exception e) {
			System.out.println(SystemMessages.EXCEPTION);
			input.nextLine();
		}
		input.close();
		conn.close();
	}
}
