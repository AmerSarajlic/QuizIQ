package kviz.dao;

import kviz.data.Admin;

public interface AdminDAO {

	/**
	 * <p>
	 * This method is returning Admin object from database based on admin name.
	 * </p>
	 * 
	 * @param name
	 *            this parameter is used to create database query for selecting
	 *            admin information from database
	 * @return new Admin object from database
	 */
	public Admin getAdmin(String name);

	/**
	 * <p>
	 * This method is used for adding new Admin object to database
	 * </p>
	 * 
	 * @param admin
	 *            object that was created by moderator
	 * @return true if new admin is added or false if it is not added to
	 *         database
	 */
	public boolean addAdmin(Admin admin);

	/**
	 * <p>
	 * This method is used for removing admin from database based on admin name
	 * </p>
	 * 
	 * @param name
	 *            this parameter is used to create database query for selecting
	 *            admin that should be deleted
	 * @return true if admin is removed or false if it is not
	 */
	public boolean removeAdmin(String name);

	/**
	 * <p>
	 * This method is used for displaying all admin names and passwords from
	 * database
	 * </p>
	 * 
	 * @param mod
	 *            this parameter is checking does logged administrator have
	 *            permission to see all admins data
	 */
	public void listAllAdmins(int mod);

	/**
	 * <p>
	 * This method is used for promoting admin from normal admin to moderator
	 * </p>
	 * 
	 * @param name
	 *            this parameter is used to create database query for promoting
	 *            admin
	 * @return true if admin is promoted or false if it is not
	 */
	public boolean promoteAdminToMod(String name);
}
