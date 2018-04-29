package game;
public enum DifficultyLevel {

	EASY("Easy", 3),
	NORMAL("Normal", 4),
	HARD("Hard", 5),
	Nightmare("Nightmare", 6);
	private String description;
	private int level;
	private DifficultyLevel(String description, int level) {
		this.description = description;
		this.level = level;
	}
	public int level() {
		return level;
	}
}
