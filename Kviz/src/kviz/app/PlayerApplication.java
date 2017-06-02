package kviz.app;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import kviz.dao.PlayerDAOImplementation;
import kviz.dao.QuestionAndAnswersDAOImplementation;
import kviz.dao.ScoreBoardDAOImplementation;
import kviz.data.Player;
import kviz.data.QuestionsAndAnswers;
import kviz.util.InputHelper;
import kviz.util.SystemMessages;
import kviz.validation.PlayerValidation;
import kviz.validation.QuestionsAnswersValidation;

/**
 * Player part of application is responsibile for all player operations based on
 * PlayerDAOImplementation and ScoreBoardDAOImplementation
 * 
 * @author amer
 *
 */
public class PlayerApplication {

	public static void runPlayerApp(Scanner input, Connection conn, Player player) {

		int option = 0;
		boolean on = true;

		while (on) {
			System.out.println(SystemMessages.PLAYER_MENU);
			try {
				System.out.print("\nInser your option: ");
				option = input.nextInt();

				switch (option) {
				case 1:
					newGame(input, conn, player);
					break;

				case 2:
					scoreBoard(input, conn, player);
					break;
				case 3:
					rules();
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
	 * This part of app is used for new game. Here players can choose between
	 * two game type, pro mode and sandbox mode
	 */
	private static void newGame(Scanner input, Connection conn, Player player) {

		int option = 0;
		boolean on = true;

		while (on) {
			System.out.println(SystemMessages.NEW_GAME);

			try {
				System.out.print("\nInser your option: ");
				option = input.nextInt();

				switch (option) {
				case 1:
					newGameProMode(input, conn, player);
					break;

				case 2:
					newGameSandbox(input, conn, player);
					break;

				case 3:
					on = false;
					runPlayerApp(input, conn, player);
					break;
				default:
					System.out.println("Insert valid option (1, 2 or 3): ");
					input.nextLine();
					break;
				}

			} catch (Exception e) {
				System.out.println(SystemMessages.EXCEPTION);
				input.nextLine();
			}
		}
	}

	/*
	 * Method that display pro mode and sandbox mode rules
	 */
	private static void rules() {
		System.out.println(SystemMessages.RULES);

	}

	/*
	 * Part of player app used for displaying different data from scoreboard
	 */
	private static void scoreBoard(Scanner input, Connection conn, Player player) {

		ScoreBoardDAOImplementation sbDao = new ScoreBoardDAOImplementation(conn);

		System.out.println(SystemMessages.SCORE_BOARD);
		int option = 0;
		boolean on = true;

		while (on) {

			try {
				System.out.print("\nInser your option: ");
				option = input.nextInt();

				switch (option) {
				case 1:
					topListAllTime(conn, sbDao);
					break;
				case 2:
					topListLastMonth(conn, sbDao);
					break;
				case 3:
					topListLastWeek(conn, sbDao);
					break;
				case 4:
					myTopList(conn, player, sbDao);
					break;
				case 5:
					on = false;
					runPlayerApp(input, conn, player);
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

	private static void myTopList(Connection conn, Player player, ScoreBoardDAOImplementation sbDao) {

		sbDao.playerTopScores(player);

	}

	private static void topListLastWeek(Connection conn, ScoreBoardDAOImplementation sbDao) {
		System.out.println("\n--- TOP SCORES LAST 7 DAYS --- ");
		sbDao.weekTop10();

	}

	private static void topListLastMonth(Connection conn, ScoreBoardDAOImplementation sbDao) {
		System.out.println("\n--- TOP SCORES LAST MONTH --- ");
		sbDao.monthTop10();
	}

	private static void topListAllTime(Connection conn, ScoreBoardDAOImplementation sbDao) {
		System.out.println("\n--- ALL TIME SCORE LIST ---");
		sbDao.listAllScores();

	}

	/*
	 * Method that run sandbox mode game. It ask from player to answer on 15
	 * questions but it does not count points or time and it canot be added to
	 * scoreboard
	 */
	private static void newGameSandbox(Scanner input, Connection conn, Player player) {

		QuestionAndAnswersDAOImplementation questionDao = new QuestionAndAnswersDAOImplementation(conn);
		ArrayList<QuestionsAndAnswers> questions = questionDao.getRandomQuestionsList(input);

		String answer = "";
		for (int i = 0; i < 15; i++) {
			QuestionsAndAnswers question = questions.get(i);

			System.out.print((i + 1) + ".) Q:   " + question.getQuestion() + "\nA.) " + question.getA() + "\nB.) "
					+ question.getB() + "\nC.) " + question.getC() + "\nD.) " + question.getD());

			System.out.print("\n\nType your answer (A, B, C or D): ");
			boolean valid = true;
			while (valid) {
				answer = input.next();
				if (QuestionsAnswersValidation.isValidAnswer(answer)) {
					valid = false;
				} else {
					System.out.print("Invalid input. Please type (A, B, C or D): ");
				}
			}
			if (answer.equalsIgnoreCase(question.getCorrectAnswer())) {
				System.out.println("\nCorrect !!!\n");
			} else {
				System.out.println("\nNot correct !!! Correct answer is: " + question.getCorrectAnswer()
						+ " Explanation: " + question.getExplanation() + "\n");
			}
		}

	}

	/*
	 * Method for running pro mode game. It counts points for every of 15
	 * questions. Stopwatch is also runing and on the end score is added to
	 * scoreboard
	 */
	private static void newGameProMode(Scanner input, Connection conn, Player player) {

		QuestionAndAnswersDAOImplementation questionDao = new QuestionAndAnswersDAOImplementation(conn);
		ArrayList<QuestionsAndAnswers> questions = questionDao.getRandomQuestionsList(input);

		long startTime = System.currentTimeMillis();
		long elapsedTime = 0;
		long endTime = 0;
		String answer = "";
		int points = 0;
		for (int i = 0; i < 15; i++) {

			QuestionsAndAnswers question = questions.get(i);
			System.out.print("\n\n" + (i + 1) + ".) Q:   " + question.getQuestion() + "\n\nA.) " + question.getA()
					+ "\nB.) " + question.getB() + "\nC.) " + question.getC() + "\nD.) " + question.getD());
			System.out.print("\n\nType your answer (A, B, C or D): ");

			boolean valid = true;
			while (valid) {
				answer = input.next();
				if (QuestionsAnswersValidation.isValidAnswer(answer)) {
					valid = false;
				} else {
					System.out.print("Invalid input. Please type (A, B, C or D): ");
				}
			}
			elapsedTime = System.currentTimeMillis();

			if (answer.equalsIgnoreCase(question.getCorrectAnswer())) {
				points += 1;
			}

			System.out.print("\nTime: " + timePassed(startTime, elapsedTime));

		}
		endTime = System.currentTimeMillis();
		int score = finalScore(points, endTime, startTime);
		System.out.println("\n\n\nYour score is: " + score + "pt.");
		resultMessage(score);
		ScoreBoardDAOImplementation scoreDao = new ScoreBoardDAOImplementation(conn);
		scoreDao.setScore(player, score);
	}

	/*
	 * Method for counting final score based on points from answers and
	 * potentialy negative points from time penality
	 */
	private static int finalScore(int points, long endTime, long startTime) {

		long time = (endTime - startTime) / 1000;

		int penalityTime = 0;
		int penalityPoints = 0;
		int finalScore = 0;

		if (time <= 600) {
			return points;
		} else {
			penalityTime = (int) time - 600;
			penalityPoints = penalityTime / 10;
			finalScore = points - penalityPoints;
			return finalScore;
		}
	}

	/*
	 * Method that display final message after game based on player score.
	 */
	private static void resultMessage(int score) {

		if (score <= 10) {
			System.out.println("\nHMMMM, You are not so smart !\n");
		} else if (score > 10 && score <= 14) {
			System.out.println("\nHMMMM, You are average smart !\n");
		} else {
			System.out.println("\n!!! YOU ARE GENIOUS !!!\n\n");
		}
	}

	/*
	 * Stopwatch method, which shows how much time is player spending in game
	 * after every question
	 */
	private static String timePassed(long startTime, long elapsedTime) {

		long time = (elapsedTime - startTime) / 1000;
		int minutes = (int) time / 60;
		int seconds = (int) time % 60;
		return minutes + "min : " + seconds + "sec";

	}

	/*
	 * Method for registering new player
	 */
	public static void register(Scanner input, Connection conn) {

		PlayerDAOImplementation playerDao = new PlayerDAOImplementation(conn);

		String name = "";
		String password = "";

		try {
			System.out.print("\nInsert User name: ");
			name = InputHelper.getInput(name);
			if (!PlayerValidation.isValidPlayerName(name)) {
				password = "" + (int) (999 + Math.random() * 9000);
				playerDao.registerNewPlayer(new Player(name, password));
				System.out.println("\n\nName: " + name + " --> Password: " + password);
				runPlayerApp(input, conn, new Player(name, password));

			} else {
				System.out.println("\nUser name: " + name + " already in use, try new one !");
				System.out.print("\nSuggested names: ");
				nameSuggestion(name);
				System.out.println("\n");
				register(input, conn);
			}

		} catch (Exception e) {
			System.out.println(SystemMessages.EXCEPTION);
			input.nextLine();
		}
	}

	/*
	 * This method is suggesting potential user names if current user name is
	 * already in use
	 */
	private static void nameSuggestion(String name) {

		String newName = "";
		int number = 0;
		int suggested = 0;

		while (number < 3) {
			suggested = (int) (999 + Math.random() * 9000);
			newName = name + "" + suggested;
			number++;
			System.out.print(newName + ", ");
		}
	}
}
