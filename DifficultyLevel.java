package game;
public enum DifficultyLevel {
	EASY(3),
	NORMAL(5),
	HARD(7),
	Nightmare(9);
	public int level;
	private DifficultyLevel(int level) {
		this.level = level;
	}
}
