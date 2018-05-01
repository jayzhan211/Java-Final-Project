package game;
public enum DifficultyLevel {
	EASY(3),
	NORMAL(4),
	HARD(5),
	Nightmare(6);
	public int level;
	private DifficultyLevel(int level) {
		this.level = level;
	}
}
