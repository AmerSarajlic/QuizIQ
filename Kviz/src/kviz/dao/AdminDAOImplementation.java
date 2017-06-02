package kviz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kviz.data.Admin;
import kviz.util.SystemMessages;

public class AdminDAOImplementation implements AdminDAO {

	ResultSet rs = null;
	String sql = null;
	Connection conn;
	PreparedStatement stmt;

	public AdminDAOImplementation(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Admin getAdmin(String name) {

		sql = "SELECT * FROM Admins WHERE name = ?";

		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, name);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return new Admin(rs.getString("name"), rs.getString("password"));
			}

		} catch (Exception e) {
			System.err.println(SystemMessages.EXCEPTION);
		}

		return null;
	}

	@Override
	public boolean addAdmin(Admin admin) {

		sql = "INSERT INTO Admins (name, password) VALUES (?, ?)";
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, admin.getName());
			stmt.setString(2, admin.getPassword());
			int done = stmt.executeUpdate();

			if (done == 1) {
				System.out.println("New admin added to database !");
				return true;
			}

		} catch (Exception e) {
			System.err.println(SystemMessages.EXCEPTION);
		}
		return false;
	}

	@Override
	public boolean removeAdmin(String name) {

		sql = "DELETE FROM Admins WHERE name = ?";

		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, name);

			int done = stmt.executeUpdate();

			if (done == 1) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.err.println(SystemMessages.EXCEPTION);
		}

		return false;
	}

	@Override
	public void listAllAdmins(int mod) {

		sql = "SELECT * FROM Admins WHERE moderator = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mod);
			
			rs = stmt.executeQuery();
			if(mod == 1){
				System.out.println("List of all admins: ");
			}
			else{
				System.out.println("Lost of all moderators: ");
			}
			System.out.println("-------------------");
			while (rs.next()) {
				System.out.println("Name: " + rs.getString("name") + " ---> Pass: " + rs.getString("password"));
			}

		} catch (Exception e) {
			System.out.println(SystemMessages.EXCEPTION);
		}

	}

	
	@Override
	public boolean promoteAdminToMod(String name) {
		
		sql = "UPDATE Admins SET moderator = ? WHERE name = ?";
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, 2);
			stmt.setString(2, name);
			int done = stmt.executeUpdate();

			if (done == 1) {
				System.out.println("Admin: " + name + " promoted to moderator !");
				return true;
			}

		} catch (Exception e) {
			System.err.println(SystemMessages.EXCEPTION);
		}
		return false;
	}

}
