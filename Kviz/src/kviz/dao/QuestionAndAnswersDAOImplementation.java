package kviz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import kviz.data.Admin;
import kviz.data.QuestionsAndAnswers;
import kviz.util.ConnectionToDatabase;
import kviz.util.SystemMessages;

public class QuestionAndAnswersDAOImplementation implements QuestionAndAnswersDAO {

	ResultSet rs = null;
	ResultSet keys = null;
	String sql = null;
	Connection conn;
	PreparedStatement stmt;

	public QuestionAndAnswersDAOImplementation(Connection conn) {
		this.conn = conn;
	}

	@Override
	public QuestionsAndAnswers getQuestionAndAnswer(int id) {

		sql = "SELECT Questions.question, Answers.a, Answers.b, Answers.c, Answers.d, Answers.correctAnswer, Answers.explanation "
				+ "FROM Questions INNER JOIN Answers ON Questions.idQuestions = Answers.Questions_idQuestions "
				+ "WHERE Questions.idQuestions = ?";

		try {

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new QuestionsAndAnswers(rs.getString("question"), rs.getString("a"), rs.getString("b"),
						rs.getString("c"), rs.getString("d"), rs.getString("correctAnswer"),
						rs.getString("explanation"));
			}

		} catch (Exception e) {
			System.out.println(SystemMessages.EXCEPTION);
		}
		return null;
	}

	@Override
	public boolean addQuestionAndAnswer(QuestionsAndAnswers qa, Admin admin) throws SQLException {

		try {
			conn.setAutoCommit(false);
			sql = "INSERT INTO Questions (question, Admins_name) VALUES (?, ?)";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, qa.getQuestion());
			stmt.setString(2, admin.getName());

			int affected = stmt.executeUpdate();
			int newKey;
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				newKey = keys.getInt(1);
				qa.setId(newKey);
			}

			sql = "INSERT INTO Answers (a, b, c, d, correctAnswer, Questions_idQuestions, explanation) VALUES (?, ?, ?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, qa.getA());
			stmt.setString(2, qa.getB());
			stmt.setString(3, qa.getC());
			stmt.setString(4, qa.getD());
			stmt.setString(5, qa.getCorrectAnswer());
			stmt.setInt(6, qa.getId());
			stmt.setString(7, qa.getExplanation());
			stmt.executeUpdate();
			conn.commit();
			return true;
		} catch (Exception e) {
			System.out.println(SystemMessages.EXCEPTION);
			conn.rollback();

		}

		return false;
	}

	@Override
	public void listAllQuestionAndAnswer() {

		sql = "SELECT Questions.idQuestions, Questions.question, Answers.a, Answers.b, Answers.c, Answers.d, Answers.correctAnswer, Answers.explanation "
				+ "FROM Questions INNER JOIN Answers ON Questions.idQuestions = Answers.Questions_idQuestions";

		try {

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				System.out.println("ID: " + rs.getString("idQuestions") + "\n\nQ: " + rs.getString("question")
						+ "\n\nA: \n\na). " + rs.getString("a") + "\nb). " + rs.getString("b") + "\nc). "
						+ rs.getString("c") + "\nd). " + rs.getString("d") + "\n\nCorrect answer is: "
						+ rs.getString("correctAnswer") + " --> Explanation: " + rs.getString("explanation") + "\n");
				System.out.println("------------------------------------");
			}

		} catch (Exception e) {
			System.out.println(SystemMessages.EXCEPTION);
			e.printStackTrace();
		}

	}

	@Override
	public void searchQuestionAndAnswerByKeyWord(String str) {

		sql = "SELECT Questions.question, Answers.a, Answers.b, Answers.c, Answers.d, Answers.correctAnswer, Answers.explanation "
				+ "FROM Questions INNER JOIN Answers ON Questions.idQuestions = Answers.Questions_idQuestions "
				+ "WHERE Questions.question LIKE ?";

		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + str + "%");
			rs = stmt.executeQuery();
			int counter = 1;
			while (rs.next()) {
				System.out.println(counter + ". QUESTION: " + rs.getString("question") + "\nANSWERS: \na). "
						+ rs.getString("a") + "\nb). " + rs.getString("b") + "\nc). " + rs.getString("c") + "\nd). "
						+ rs.getString("d") + "\nCorrect answer is: " + rs.getString("correctAnswer")
						+ " --> Explanation: " + rs.getString("explanation") + "\n");
				counter++;
			}

		} catch (Exception e) {
			System.out.println(SystemMessages.EXCEPTION);
		}
	}

	@Override
	public boolean deleteQuestionAndAnswer(int id) {

		String sql = "DELETE FROM Questions WHERE idQuestions = ?";

		try (Connection conn = ConnectionToDatabase.getConected();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			conn.prepareStatement(sql);
			stmt.setInt(1, id);
			int done = stmt.executeUpdate();
			if (done == 1) {
				System.out.println("Question removed !");
				return true;
			}

		} catch (Exception e) {
			System.err.println(SystemMessages.EXCEPTION);
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public ArrayList<QuestionsAndAnswers> getRandomQuestionsList(Scanner input) {

		ArrayList<QuestionsAndAnswers> questionList = new ArrayList<>();

		String sql = "SELECT Questions.question, Answers.a, Answers.b, Answers.c, Answers.d, Answers.correctAnswer, Answers.explanation "
				+ "FROM Questions INNER JOIN Answers ON Questions.idQuestions = Answers.Questions_idQuestions";

		try (Connection conn = ConnectionToDatabase.getConected();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			ResultSet rs = stmt.executeQuery();
			rs.last();

			int totalQuestions = rs.getRow();

			if (totalQuestions >= 15) {

				ArrayList<Integer> randomNumbers = new ArrayList<>();

				while (randomNumbers.size() < 15) {

					int number = (int) (1 + Math.random() * totalQuestions);
					if (!randomNumbers.contains(number)) {
						randomNumbers.add(number);
					} else {
						number = (int) (1 + Math.random() * totalQuestions);
					}
				}

				for (int i = 0; i < 15; i++) {
					rs.absolute(randomNumbers.get(i));

					questionList.add(new QuestionsAndAnswers(rs.getString("question"), rs.getString("a"),
							rs.getString("b"), rs.getString("c"), rs.getString("d"), rs.getString("correctAnswer"),
							rs.getString("explanation")));
				}

				return questionList;
			} else {
				System.out.println("There is no enough questions in database !");
				input.nextLine();
			}

		} catch (Exception e) {
			System.out.println(SystemMessages.EXCEPTION);
			input.nextLine();
		}
		return null;
	}

}
