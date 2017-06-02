package kviz.app;

import java.sql.Connection;
import java.util.Scanner;

import kviz.dao.AdminDAOImplementation;
import kviz.dao.PlayerDAOImplementation;
import kviz.data.Admin;
import kviz.data.Player;
import kviz.util.SystemMessages;
import kviz.validation.AdminValidation;
import kviz.validation.PlayerValidation;

public class Application {

	/*
	 * Method for choosing options on wellcome screen
	 */
	public static void runApp(Scanner input, Connection conn) {

		SystemMessages.displayHomeScreen();
		int option = 0;
		boolean on = true;
		while (on) {
			try {

				System.out.print("\nInsert your option: ");
				option = input.nextInt();
				switch (option) {
				case 1:
					logIn(input, conn);
					on = false;
					break;

				case 2:
					PlayerApplication.register(input, conn);
					on = false;
					break;

				case 3:
					System.out.println("-- TERMS OF USES -- \n--Not yet presented--\n ");
					input.nextLine();

					break;
				default:
					System.out.println("Invalid input, please chose 1, 2 or 3: ");
					break;
				}

			} catch (Exception e) {
				System.out.println(SystemMessages.EXCEPTION);
				input.nextLine();
			}
		}
	}

	/*
	 * Method for login into application. In this method we are calling
	 * validation methods which are checking are inserted data correct. Also in
	 * this method app is making decision, based on inserted data, to runn admin
	 * or player part of app.
	 */
	public static void logIn(Scanner input, Connection conn) {
		AdminDAOImplementation adminDAOImplementation = new AdminDAOImplementation(conn);
		PlayerDAOImplementation playerDAOImplementation = new PlayerDAOImplementation(conn);
		String name;
		String password;

		try {

			System.out.print("\nInsert user name: ");
			name = input.next();
			System.out.print("Insert password: ");
			password = input.next();

			if (AdminValidation.isValidAdministrator(name, password)) {
				Admin admin = adminDAOImplementation.getAdmin(name);
				System.out.println("\nYou are successfully loged in as administrator !");
				AdminApplication.runAdminApp(input, conn, admin);

			} else if (PlayerValidation.isValidPlayer(name, password)) {
				Player player = playerDAOImplementation.getPlayer(name);
				System.out.println("\nYou are successfully loged in as player !");
				PlayerApplication.runPlayerApp(input, conn, player);

			} else {
				System.out.println("\nInvalid username or password !!!\n\n");
				runApp(input, conn);
				input.nextLine();
			}

		} catch (Exception e) {
			System.out.println(SystemMessages.EXCEPTION);
			input.nextLine();

		}

	}
}
