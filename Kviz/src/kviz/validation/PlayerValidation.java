package kviz.validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kviz.util.ConnectionToDatabase;
import kviz.util.SystemMessages;

public class PlayerValidation {
	/**
	 * This method is used for checking inserted player data validation in
	 * database
	 * 
	 * @param name
	 *            inserted name for player
	 * @param password
	 *            inserted password for player
	 * @return true if data are valid or false if they are not valid
	 */
	public static boolean isValidPlayer(String name, String password) {

		String sql = "SELECT name, password FROM Users WHERE name = ? AND password = ?";

		try (Connection conn = ConnectionToDatabase.getConected();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, name);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				return false;
			}
			return true;

		} catch (Exception e) {
			System.err.println(SystemMessages.EXCEPTION);

		}
		return false;
	}

	/**
	 * This method is checking is player name already in database. This is used
	 * for registering new players.
	 * 
	 * @param name
	 *            parameter inserted from new player
	 * @return true if player name is already in base or false if it is not in
	 *         base
	 */

	public static boolean isValidPlayerName(String name) {

		String sql = "SELECT name FROM Users WHERE name = ?";

		try (Connection conn = ConnectionToDatabase.getConected();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, name);

			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				return false;
			}
			return true;

		} catch (Exception e) {
			System.err.println(SystemMessages.EXCEPTION);
			e.printStackTrace();
		}
		return false;
	}

}
