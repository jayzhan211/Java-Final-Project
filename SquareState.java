package game;

public enum SquareState {

	BLACK('¡´'),
	WHITE('¡³'),
	PSSBL('.'),
	EMPTY(' ');
	private final char symbol;

	SquareState(char symbol) {
		this.symbol = symbol;
	}

	public char symbol() {
		return this.symbol;
	}

	public SquareState opposite() {
		return this == BLACK ? WHITE : BLACK;
	}

	@Override
	public String toString() {
		return String.valueOf(symbol);
	}
}
