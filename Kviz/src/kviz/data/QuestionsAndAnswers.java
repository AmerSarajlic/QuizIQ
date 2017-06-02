package kviz.data;

public class QuestionsAndAnswers {

	private int id;
	private String question;
	private String a;
	private String b;
	private String c;
	private String d;
	private String correctAnswer;
	private String explanation;

	public QuestionsAndAnswers(String question, String a, String b, String c, String d, String ca, String explanation) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.question = question;
		this.correctAnswer = ca;
		this.explanation = explanation;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	@Override
	public String toString() {
		return "QuestionsAndAnswers [question=" + question + ", a=" + a + ", b=" + b + ", c=" + c + ", d=" + d
				+ ", correctAnswer=" + correctAnswer + ", explanation=" + explanation + "]";
	}

}
