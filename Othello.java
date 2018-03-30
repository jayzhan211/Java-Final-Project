package game;
import java.util.*;
import javax.swing.JFrame;

public class Othello extends JFrame{
	public static void main(String[] args) {
		Othello othello=new Othello();
		State state=new State();
		state.ff();
	}
}
