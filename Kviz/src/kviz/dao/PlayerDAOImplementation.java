package kviz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kviz.data.Player;
import kviz.util.SystemMessages;

public class PlayerDAOImplementation implements PlayerDAO {

	ResultSet rs = null;
	String sql = null;
	Connection conn;
	PreparedStatement stmt;

	public PlayerDAOImplementation(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Player getPlayer(String name) {

		sql = "SELECT * FROM Users WHERE name = ?";

		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, name);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return new Player(rs.getString("name"), rs.getString("password"));
			}

		} catch (Exception e) {
			System.err.println(SystemMessages.EXCEPTION);
		}
		return null;
	}

	@Override
	public boolean registerNewPlayer(Player player) {

		sql = "INSERT INTO Users (name, password) VALUES (?, ?)";

		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, player.getName());
			stmt.setString(2, player.getPassword());

			int done = stmt.executeUpdate();
			if (done == 1) {
				System.out.println("New player registered !");
				return true;
			}

		} catch (Exception e) {
			System.err.println(SystemMessages.EXCEPTION);
		}
		return false;
	}

	@Override
	public boolean deletePlayer(String name) {

		sql = "DELETE FROM Users WHERE name = ?";

		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			int deleted = stmt.executeUpdate();
			if (deleted == 1) {
				System.out.println("Player removed !");
				return true;
			}

		} catch (Exception e) {
			System.err.println(SystemMessages.EXCEPTION);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void listAllPlayers() {

		sql = "SELECT * FROM Users";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				System.out
						.println("User name: " + rs.getString("name") + " ---> Password: " + rs.getString("password"));
			}

		} catch (Exception e) {
			System.err.println(SystemMessages.EXCEPTION);
			e.getMessage();
		}

	}

}
