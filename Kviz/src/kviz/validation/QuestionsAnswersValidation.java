package kviz.validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kviz.util.ConnectionToDatabase;
import kviz.util.SystemMessages;

public class QuestionsAnswersValidation {

	/**
	 * This method is checking is question id in database. This is used for
	 * removing question by inserting its id.
	 * 
	 * @param id
	 *            parameter inserted from moderator for question that he want to
	 *            remove
	 * @return true if question id is already in base or false if it is not in
	 *         base
	 */

	public static boolean isValidQuestionId(int id) {

		String sql = "SELECT idQuestions FROM Questions WHERE idQuestions = ?";

		try (Connection conn = ConnectionToDatabase.getConected();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				return false;
			}
			return true;

		} catch (Exception e) {
			System.err.println(SystemMessages.EXCEPTION);
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * This method is checking is player answer valid. This is used to inform
	 * player if he mada a mistake by inserting non existing answer
	 * 
	 * @param answer
	 *            parameter inserted from player
	 * @return true if answer is valid or false if it is not
	 */
	public static boolean isValidAnswer(String answer) {

		if (answer.equalsIgnoreCase("A") || answer.equalsIgnoreCase("B") || answer.equalsIgnoreCase("C")
				|| answer.equalsIgnoreCase("D")) {
			return true;
		}
		return false;
	}

}
