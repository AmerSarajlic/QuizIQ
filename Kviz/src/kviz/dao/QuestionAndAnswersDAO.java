package kviz.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import kviz.data.Admin;
import kviz.data.QuestionsAndAnswers;

public interface QuestionAndAnswersDAO {

	/**
	 * <p>
	 * This method is used for creating query that return QuestionsAndAnswers object from database based
	 * on its id.
	 * </p>
	 * 
	 * @param id
	 *            this parameter is used to create database query for selecting
	 *            QuestionsAndAnswers information from database
	 * @return new QuestionsAndAnswers object from database
	 */

	public QuestionsAndAnswers getQuestionAndAnswer(int id);

	/**
	 * <p>
	 * This method is used for creating query that add new QuestionsAndAnswers object to database
	 * </p>
	 * 
	 * @param qa
	 *            QuestionsAndAnswers object created after admin insert all data
	 * @param admin
	 *            Admin object that tells us which admin is adding question in
	 *            database
	 * @return true if QuestionAndAnswer is added to database or false if it is
	 *         not added
	 * @throws SQLException
	 */
	public boolean addQuestionAndAnswer(QuestionsAndAnswers qa, Admin admin) throws SQLException;

	/**
	 * <p>
	 * This method is used for creating query that display all QuestionsAndAnswers from database
	 * </p>
	 * 
	 */
	public void listAllQuestionAndAnswer();

	/**
	 * 
	 * <p>
	 * This method is used for creating query that display QuestionsAndAnswers from database
	 * based on keyword that admin type
	 * </p>
	 * 
	 * @param str
	 *            is keyword for searching QuestionsAndAnswers
	 */
	public void searchQuestionAndAnswerByKeyWord(String str);

	/**
	 *
	 * <p>
	 * This method is used for creating query for removing QuestionsAndAnswers from database based
	 * on QuestionsAndAnswers id
	 * </p>
	 *
	 * @param id
	 *            is parameter taken from admin input
	 * @return true if question is deleted or false if it is not
	 * @throws SQLException
	 */
	public boolean deleteQuestionAndAnswer(int id) throws SQLException;

	/**
	 * <p>
	 * This method is used for creating query that for making 15 random questions for player iq test
	 * </p>
	 * 
	 * @return ArrayList with 15 random questions from database
	 */
	public ArrayList<QuestionsAndAnswers> getRandomQuestionsList(Scanner input);
}
