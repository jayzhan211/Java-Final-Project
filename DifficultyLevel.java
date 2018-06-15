package game;
public enum DifficultyLevel {
	EASY(4),
	NORMAL(5),
	HARD(6),
	Nightmare(7);
	public int level;
	private DifficultyLevel(int level) {
		this.level = level;
	}
}
