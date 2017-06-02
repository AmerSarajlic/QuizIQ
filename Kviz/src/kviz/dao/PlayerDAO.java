package kviz.dao;

import kviz.data.Player;

public interface PlayerDAO {
	/**
	 * <p>
	 * This method is returning Player object from database based on player
	 * name.
	 * </p>
	 * 
	 * @param name
	 *            this parameter is used to create database query for selecting
	 *            player information from database
	 * @return new Player object from database
	 */
	public Player getPlayer(String name);

	/**
	 * <p>
	 * This method is used for adding new Player object to database
	 * </p>
	 * 
	 * @param player
	 *            object that was created when new player is registering
	 * @return true if new player is added or false if it is not added to
	 *         database
	 */
	public boolean registerNewPlayer(Player player);

	/**
	 * <p>
	 * This method is used for removing player from database based on player
	 * name
	 * </p>
	 * 
	 * @param name
	 *            this parameter is used to create database query for selecting
	 *            player that should be deleted
	 * @return true if player is removed or false if it is not
	 */
	public boolean deletePlayer(String name);

	/**
	 * <p>
	 * This method is used for displaying all player names and passwords from
	 * database
	 * </p>
	 *
	 */
	public void listAllPlayers();

}
