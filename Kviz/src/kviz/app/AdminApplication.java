package kviz.app;

import java.sql.Connection;
import java.util.Scanner;

import kviz.dao.AdminDAOImplementation;
import kviz.dao.PlayerDAOImplementation;
import kviz.dao.QuestionAndAnswersDAOImplementation;
import kviz.data.Admin;
import kviz.data.QuestionsAndAnswers;
import kviz.util.InputHelper;
import kviz.util.SystemMessages;
import kviz.validation.AdminValidation;
import kviz.validation.PlayerValidation;
import kviz.validation.QuestionsAnswersValidation;

/**
 * Admin part of application is responsibile for all admin operations based on
 * AdminDAOImplementation and QuestionsAndAnswersDAOImplementation
 * 
 * @author amer
 *
 */
public class AdminApplication {

	public static void runAdminApp(Scanner input, Connection conn, Admin admin) {
		System.out.println(SystemMessages.ADMIN_MENU);
		int option = 0;
		boolean on = true;

		while (on) {

			try {
				System.out.print("\nInser your option: ");
				option = input.nextInt();

				switch (option) {
				case 1:
					questionManagement(input, conn, admin);
					break;

				case 2:
					playerManagement(input, conn, admin);
					break;

				case 3:
					moderatorManagement(input, conn, admin);
					break;
				case 4:
					on = false;
					System.out.println("Loging of....\n\n");
					Application.runApp(input, conn);
					break;
				default:
					break;
				}

			} catch (Exception e) {
				System.out.println(SystemMessages.EXCEPTION);
				input.nextLine();
			}
		}
	}

	/*
	 * This is part of app for moderators. They are higher tier administrators
	 * and they can add admins, see all admins data, promote admins or remove
	 * them
	 */
	private static void moderatorManagement(Scanner input, Connection conn, Admin admin) {
		AdminDAOImplementation adminDao = new AdminDAOImplementation(conn);
		if (AdminValidation.isModerator(admin.getName(), admin.getPassword())) {

			int option = 0;
			boolean on = true;

			while (on) {
				System.out.println(SystemMessages.MODERATOR_MENU);
				try {
					System.out.print("\nInsert option: ");
					option = input.nextInt();
					switch (option) {
					case 1:
						listAllAdministrators(conn, adminDao);
						break;
					case 2:
						addNewAdministrator(conn, input, adminDao);
						break;
					case 3:
						deleteAdministrator(conn, input, adminDao);
						break;
					case 4:
						promoteAdministratorToModerator(conn, input, adminDao);
						break;
					case 5:
						on = false;
						runAdminApp(input, conn, admin);
						break;
					default:
						System.out.println("Please insert valid option: ");
						break;
					}

				} catch (Exception e) {
					System.out.println(SystemMessages.EXCEPTION);
					input.nextLine();
				}
			}

		} else {
			System.out.println("\nRestricted permission ! Only moderators area !");
		}

	}

	private static void promoteAdministratorToModerator(Connection conn, Scanner input,
			AdminDAOImplementation adminDao) {

		String adminName = "";
		try {
			listAllAdministrators(conn, adminDao);
			System.out.print("\nInsert name of administrator that you want to promote: ");
			adminName = input.next();

			if (AdminValidation.isValidAdminName(adminName)) {

				adminDao.promoteAdminToMod(adminName);
				System.out.println("Admin: " + adminName + " promoted !");
			} else {
				System.out.println("Admin: " + adminName + " is not in admin base !");
			}

		} catch (Exception e) {
			System.out.println(SystemMessages.EXCEPTION);
			input.nextLine();
		}

	}

	private static void deleteAdministrator(Connection conn, Scanner input, AdminDAOImplementation adminDao) {

		String adminName = "";
		try {

			listAllAdministrators(conn, adminDao);
			System.out.print("\nInsert name of administrator that you want to delete: ");
			adminName = input.next();
			if (AdminValidation.isValidAdminName(adminName)) {
				adminDao.removeAdmin(adminName);
				System.out.println("Admin: " + adminName + " deleted !");
			} else {
				System.out.println("Admin: " + adminName + " is not in admin base !");
			}

		} catch (Exception e) {
			System.out.println(SystemMessages.EXCEPTION);
			input.nextLine();
		}

	}

	private static void addNewAdministrator(Connection conn, Scanner input, AdminDAOImplementation adminDao) {

		String name = "";
		String password = "";

		try {
			System.out.print("Insert new admin name: ");
			name = InputHelper.getInput(name);
			if (!AdminValidation.isValidAdminName(name)) {
				password = "" + (int) (999 + Math.random() * 9000);
				adminDao.addAdmin(new Admin(name, password));
				System.out.println("Name: " + name + " --> Password: " + password);

			} else {
				System.out.println("\nAdmin: " + name + " already in use, try new one !");
			}

		} catch (Exception e) {
			System.out.println(SystemMessages.EXCEPTION);
			input.nextLine();
		}

	}

	private static void listAllAdministrators(Connection conn, AdminDAOImplementation adminDao) {
		System.out.println("\n");
		adminDao.listAllAdmins(1);
		System.out.println("\n");
		adminDao.listAllAdmins(2);
	}

	/*
	 * This is part of app used for managing players. Administrators can list
	 * all players and they can delete them if they are braking some rules
	 */
	private static void playerManagement(Scanner input, Connection conn, Admin admin) {
		PlayerDAOImplementation pDao = new PlayerDAOImplementation(conn);
		int option = 0;
		boolean on = true;

		while (on) {
			System.out.println(SystemMessages.PLAYER_MANAGEMENT);
			try {
				System.out.print("\nInsert option: ");
				option = input.nextInt();
				switch (option) {
				case 1:
					listAllPlayers(conn, pDao);
					break;
				case 2:
					removePlayer(conn, input, pDao);
					break;
				case 3:
					on = false;
					runAdminApp(input, conn, admin);
					break;
				default:
					System.out.println("Please insert valid option: ");
					break;
				}

			} catch (Exception e) {
				System.out.println(SystemMessages.EXCEPTION);
				input.nextLine();
			}
		}

	}

	private static void removePlayer(Connection conn, Scanner input, PlayerDAOImplementation pDao) {

		String playerName = "";

		try {
			listAllPlayers(conn, pDao);
			System.out.print("Insert player User name: ");
			playerName = input.next();

			if (PlayerValidation.isValidPlayerName(playerName)) {
				pDao.deletePlayer(playerName);
			} else {
				System.out.println("No such player in database !");
			}

		} catch (Exception e) {
			System.out.println(SystemMessages.EXCEPTION);
			input.nextLine();
		}

	}

	private static void listAllPlayers(Connection conn, PlayerDAOImplementation pDao) {
		pDao.listAllPlayers();
	}

	/*
	 * This part of app is for managing questions. Administrators can see all
	 * questions, delete them, add new ones or search questions by keyword
	 */
	public static void questionManagement(Scanner input, Connection conn, Admin admin) {

		QuestionAndAnswersDAOImplementation questionManagement = new QuestionAndAnswersDAOImplementation(conn);

		int option = 0;
		boolean on = true;

		while (on) {
			System.out.println(SystemMessages.QUESTION_MANAGEMENT);
			try {
				System.out.print("\nInsert option: ");
				option = input.nextInt();
				switch (option) {
				case 1:
					listAllQuestionAndAnswer(input, conn, questionManagement);
					break;
				case 2:
					deleteQuestion(input, conn, questionManagement);
					break;
				case 3:
					addQuestion(input, conn, admin, questionManagement);
					break;

				case 4:
					searchQuestionByKeyWord(input, questionManagement);
					break;
				case 5:
					on = false;
					runAdminApp(input, conn, admin);
					break;

				default:
					System.out.println("Please insert valid option: ");
					break;
				}

			} catch (Exception e) {
				System.out.println(SystemMessages.EXCEPTION);
				input.nextLine();
			}
		}

	}

	private static void searchQuestionByKeyWord(Scanner input, QuestionAndAnswersDAOImplementation questionManagement) {

		try {
			System.out.print("Insert key word: ");
			String word = input.next();
			questionManagement.searchQuestionAndAnswerByKeyWord(word);
		} catch (Exception e) {
			System.out.println(SystemMessages.EXCEPTION);
			input.nextLine();
		}

	}

	private static void addQuestion(Scanner input, Connection conn, Admin admin,
			QuestionAndAnswersDAOImplementation questionManagement) {

		String question = "";
		String a = "";
		String b = "";
		String c = "";
		String d = "";
		String answer = "";
		String explanation = "";

		try {

			System.out.print("Insert question (Only question text): ");
			question = InputHelper.getInput(question);

			System.out.print("Insert answer a): ");
			a = InputHelper.getInput(a);

			System.out.print("Insert answer b): ");
			b = InputHelper.getInput(b);

			System.out.print("Insert answer c): ");
			c = InputHelper.getInput(c);

			System.out.print("Insert answer d): ");
			d = InputHelper.getInput(d);

			System.out.print("Insert letter of correct answer (a, b, c or d): ");
			answer = InputHelper.getInput(answer);

			System.out.print("Insert explanation for correct answer: ");
			explanation = InputHelper.getInput(explanation);

			questionManagement.addQuestionAndAnswer(new QuestionsAndAnswers(question, a, b, c, d, answer, explanation),
					admin);

		} catch (Exception e) {
			System.out.println(SystemMessages.EXCEPTION);
			input.nextLine();
		}

	}

	public static void listAllQuestionAndAnswer(Scanner input, Connection conn,
			QuestionAndAnswersDAOImplementation questionManagement) {
		questionManagement.listAllQuestionAndAnswer();

	}

	public static void deleteQuestion(Scanner input, Connection conn,
			QuestionAndAnswersDAOImplementation questionManagement) {

		listAllQuestionAndAnswer(input, conn, questionManagement);

		int id;

		try {
			System.out.println("Inser ID of question that you want to delete: ");
			id = input.nextInt();
			if (QuestionsAnswersValidation.isValidQuestionId(id)) {
				questionManagement.deleteQuestionAndAnswer(id);
			} else {
				System.out.print("Inserted ID is not valid, please try again: ");
				input.nextLine();
			}

		} catch (Exception e) {
			System.out.println(SystemMessages.EXCEPTION);
			input.nextLine();
		}

	}

}
