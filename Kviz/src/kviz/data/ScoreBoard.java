package kviz.data;

public class ScoreBoard {

	private int id;
	private Player player;
	private int score;

	public ScoreBoard() {
	}
	
	public ScoreBoard(Player player, int score) {
		this.player = player;
		this.score = score;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ScoreBoard [player=" + player + ", score=" + score + "]";
	}

}
