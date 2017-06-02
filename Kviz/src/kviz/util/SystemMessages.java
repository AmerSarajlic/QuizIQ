package kviz.util;

/**
 * <p>
 * Class that contains different messages that we can call in out application
 * </p>
 * 
 * @author amer
 *
 */
public class SystemMessages {

	public static final String EXCEPTION = "OOOPS, SOMETHING WENT WRONG :( !!! Try again: ";
	public static final String INVALID_INPUT = "Input is not valid, please try again: ";

	public static void displayHomeScreen() {

		System.out.println("          #   ###       #####  #####  #####  #####          ");
		System.out.println("     *    #  #   #        #    #      #        #     *      ");
		System.out.println(" ******   #  #   #        #    ###    #####    #    ******  ");
		System.out.println("     *    #  #   #        #    #          #    #     *      ");
		System.out.println("          #   ### #       #    #####  #####    #            \n\n\n");
		System.out.println("1. Log in\n2. Register new account\n3. Terms of uses");
	}

	public static final String ADMIN_MENU = "\n\n---> ADMIN MENU <---\n\n1. QUESTION MANAGEMENT\n2. PLAYER MANAGEMENT\n3. ADVANCED MODERATOR MENU\n4. Log off !";
	public static final String QUESTION_MANAGEMENT = "\n--- Qusetion management ---\n1. List all questions\n2. Delete question\n3. Add question"
			+ "\n4. Search questions by key word\n5. Back";
	public static final String PLAYER_MANAGEMENT = "\n--- Player management ---\n1. List all players\n2. Remove player\n3. Back";
	public static final String MODERATOR_MENU = "\n--- Moderator menu ---\n1. List all administrators and moderators\n2. Add new administrator"
			+ "\n3. Delete administrator\n4. Promote administrator to moderator\n5. Back!";
	public static final String PLAYER_MENU = "\n\n---> PLAYER MENU <---\n\n1. NEW GAME\n2. SCORE BOARD\n3. Rules\n4. Log off !";
	public static final String NEW_GAME = "\n--- New game ---\n1. Pro mode\n2. Sandbox mode\n3. Back";
	public static final String SCORE_BOARD = "\n--- Score board ---\n1. Top list all time\n2. Top 10 last month\n3. Top 10 last week\n4. My top list\n5. Back";
	public static final String RULES = "\n-----> RULES <-----\n\n---Pro mode---\n1. 15. Questions in 10 min\n2. Every question 1pt\n3. Every 10 sec over 10 min -1pt"
			+ "\n4. No info about mistakes\n\n---Sandbox mode---\n1. No time limit\n2. No scores\n3. Info after every answer\n\n!!! HAVE FUN !!!";
}
