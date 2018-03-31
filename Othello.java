package game;
import javax.swing.*;
import java.awt.*;
import java.net.*;

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
		
		
		ImageIcon aIcon=new ImageIcon("images/TK.jpg");
		ImageIcon bIcon=new ImageIcon("images/TK.jpg");
		JButton button=new JButton(aIcon);
		button.setRolloverIcon(bIcon);
		button.setPressedIcon(bIcon);
		Dimension sz=new Dimension(1000, 1000);
		button.setPreferredSize(sz);
		//New start
		ImageIcon img=new ImageIcon("images/TK.jpg");
		JButton startbutton=new JButton(img);
		startbutton.setPreferredSize(new Dimension(100, 400));
		buttonjp.add(button);
		buttonjp.add(startbutton);
		
		this.setLayout(new GridLayout(1,2,100, 0));  
		this.add(gamejp);
		this.add(buttonjp);
		this.setTitle("Othello");
		this.setResizable(true);
		this.setSize(1000,650);
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(3);  
		this.setVisible(true);
		
		
		
	}
}
