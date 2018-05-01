package game;

public enum SquareState {
	BLACK,
	WHITE,
	PSSBL,
	EMPTY;
	SquareState() {}
	public SquareState opposite() {
		return this == BLACK ? WHITE : BLACK;
	}
}
