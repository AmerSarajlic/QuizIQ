package kviz.dao;

import kviz.data.Player;

public interface ScoreBoardDAO {

	/**
	 * This method is used for creating query for inserting score to database
	 * 
	 * @param player
	 *            object from player which score is geting inserted to database
	 * @param score
	 *            number of points
	 * @return true if score is added or false if it is not added
	 */
	public boolean setScore(Player player, int score);

	/**
	 * This method is used for creating query for displaying all time
	 * socoreboard from database
	 */
	public void listAllScores();

	/**
	 * This method is used for creating query for displaying all time top 10
	 * scores from database
	 */
	public void top10();

	/**
	 * This method is used for creating query for displaying all time top 100
	 * scores from database
	 */
	public void top100();

	/**
	 * This method is used for creating query for displaying last 7 days top 10
	 * scores from database
	 */
	public void weekTop10();

	/**
	 * This method is used for creating query for displaying last 30 days top 10
	 * scores from database
	 */
	public void monthTop10();

	/**
	 * This method is used creating query which is showing to current Player his
	 * scoreboard
	 * 
	 * @param player
	 *            object that is currently logged in
	 */
	public void playerTopScores(Player player);

}
