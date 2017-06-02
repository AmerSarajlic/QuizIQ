package kviz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kviz.data.Player;
import kviz.util.SystemMessages;

public class ScoreBoardDAOImplementation implements ScoreBoardDAO {

	Connection conn;
	PreparedStatement stmt;
	ResultSet rs = null;
	String sql = null;

	public ScoreBoardDAOImplementation(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean setScore(Player player, int score) {

		sql = "INSERT INTO ScoreBoard (score, Users_name) VALUES (?, ?)";

		try {

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, score);
			stmt.setString(2, player.getName());
			
			int done = stmt.executeUpdate();
			if(done == 1){
				return true;
			}
			
		} catch (Exception e) {
			System.err.println(SystemMessages.EXCEPTION);

		}

		return false;
	}

	@Override
	public void listAllScores() {

		sql = "SELECT * FROM ScoreBoard ORDER BY score DESC";

		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			int counter = 1;
			while (rs.next()) {
				System.out.println("No: " + counter + " -> Player name: " + rs.getString("Users_name")
						+ " -> Score: " + rs.getInt("score"));
				counter++;
			}

		} catch (Exception e) {
			System.err.println(SystemMessages.EXCEPTION);
		}

	}

	@Override
	public void top10() {

		sql = "SELECT Users_name, score FROM ScoreBoard ORDER BY score DESC LIMIT 10;";
		try {
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			System.out.println("TOP 10 All time scoreboard !\n");

			int counter = 1;
			while (rs.next()) {
				System.out.println("No: " + counter + " Name: " + rs.getString("Users_name") + " --> Score: "
						+ rs.getString("score"));
				counter++;
			}

		} catch (Exception e) {
			System.err.println(SystemMessages.EXCEPTION);
		}
	}

	@Override
	public void top100() {

		sql = "SELECT Users_name, score FROM ScoreBoard ORDER BY score DESC LIMIT 100";

		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			System.out.println("TOP 100 All time scoreboard !\n");

			int counter = 1;
			while (rs.next()) {
				System.out.println("No: " + counter + " Name: " + rs.getString("Users_name") + " --> Score: "
						+ rs.getString("score"));
				counter++;
			}

		} catch (Exception e) {
			System.err.println(SystemMessages.EXCEPTION);

		}

	}

	@Override
	public void weekTop10() {

		sql = "SELECT * FROM ScoreBoard WHERE dateAdded >= (curdate() - 7) ORDER BY score DESC LIMIT 10";

		try {

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			int counter = 1;
			while (rs.next()) {
				System.out.println(
						counter + ": Name: " + rs.getString("Users_name") + " --> Score: " + rs.getInt("score"));
				counter++;
			}

		} catch (Exception e) {
			System.err.println(SystemMessages.EXCEPTION);

		}

	}

	@Override
	public void monthTop10() {

		sql = "SELECT * FROM ScoreBoard WHERE dateAdded >= (curdate() - 30) ORDER BY score DESC LIMIT 10";

		try {

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			int counter = 1;
			while (rs.next()) {
				System.out.println(
						counter + ": Name: " + rs.getString("Users_name") + " --> Score: " + rs.getInt("score"));
				counter++;
			}

		} catch (Exception e) {
			System.err.println(SystemMessages.EXCEPTION);

		}

	}

	@Override
	public void playerTopScores(Player player) {

		sql = "SELECT * FROM ScoreBoard WHERE Users_name = ? ORDER BY score DESC;";

		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, player.getName());
			rs = stmt.executeQuery();

			int counter = 1;
			System.out.println("\nPlayer name: " + player.getName() + " scoreboard: \n");
			while (rs.next()) {
				System.out.println(
						counter + ". Score: " + rs.getInt("score") + " ---> Date played: " + rs.getDate("dateAdded"));
				counter++;
			}

		} catch (Exception e) {
			System.err.println(SystemMessages.EXCEPTION);

		}

	}

}
