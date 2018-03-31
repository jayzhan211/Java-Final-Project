package game;
import javax.swing.*;
import java.awt.*;

public class Othello extends JFrame{
	public static void main(String[] args) {
		Othello othello=new Othello();
		othello.showframe();
	}
	public void showframe() {
		//game interface
		JPanel gamejp=new JPanel();
		//game button
		JPanel buttonjp=new JPanel();
		
		//Button
		ImageIcon aIcon=new ImageIcon("https://imgur.com/c8pZXKt");
		
		ImageIcon bIcon=new ImageIcon("https://imgur.com/2hjmbJk");
		
		JButton button=new JButton(aIcon);
		button.setRolloverIcon(bIcon);
		button.setPressedIcon(bIcon);
		Dimension sz=new Dimension(1000, 1000);
		button.setPreferredSize(sz);
		this.add(gamejp);
		this.setTitle("Othello");
		this.setResizable(true);
		this.setSize(1000, 800);
		this.setVisible(true);
	}
}
