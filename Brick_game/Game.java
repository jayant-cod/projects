package Brick_game;

import javax.swing.JFrame;

public class Game {
	public static void main(String[] args) {
		JFrame obj=new JFrame();
		Working working = new Working();
		
		obj.setBounds(10, 10, 700, 600);
		obj.setTitle("Break_brick");		
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(working);
                obj.setVisible(true);
	}

}
