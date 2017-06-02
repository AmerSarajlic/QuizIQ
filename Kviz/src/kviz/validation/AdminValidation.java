package kviz.validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kviz.util.ConnectionToDatabase;
import kviz.util.SystemMessages;

public class AdminValidation {
	/**
	 * This method is used for checking inserted admin data validation in
	 * database
	 * 
	 * @param name
	 *            inserted name for admin
	 * @param password
	 *            inserted password for admin
	 * @return true if data are valid or false if they are not valid
	 */
	public static boolean isValidAdministrator(String name, String password) {

		String sql = "SELECT name, password FROM Admins WHERE name = ? AND password = ?";

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
	 * This method is checking is admin name already in database. This is used
	 * for registering new admin.
	 * 
	 * @param name
	 *            parameter inserted from moderator
	 * @return true if admin name is already in base or false if it is not in
	 *         base
	 */
	public static boolean isValidAdminName(String name) {

		String sql = "SELECT name FROM Admins WHERE name = ?";

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

	/**
	 * This method is checking does loged administrator has moderator
	 * permissions
	 * 
	 * @param name
	 *            inserted admin name
	 * @param password
	 *            inserted admin password
	 * @return true if is moderator or false if is not
	 */
	public static boolean isModerator(String name, String password) {

		String sql = "SELECT name, password FROM Admins WHERE name = ? AND password = ? AND moderator = ?";

		try (Connection conn = ConnectionToDatabase.getConected();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, name);
			stmt.setString(2, password);
			stmt.setInt(3, 2);

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

}
