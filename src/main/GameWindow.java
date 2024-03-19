package main;

import javax.swing.JFrame;

public class GameWindow {
	private JFrame frame;
	
	public GameWindow(GamePanel gamepanel) {
		frame = new JFrame("KADWIROSSURDU");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.add(gamepanel);
		frame.setLocation(0, 0);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

	}
}
