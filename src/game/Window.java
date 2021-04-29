package game;

import javax.swing.JFrame;

public class Window extends JFrame{
	
	public Window() {
		setTitle("royal clash");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new GamePanel(600,1200));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}


}
