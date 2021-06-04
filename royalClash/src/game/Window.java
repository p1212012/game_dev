package game;

import javax.swing.JFrame;

public class Window extends JFrame{
	
	public static int offset = 2;
	
	public Window() {
		setTitle("royal clash");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new GamePanel(300*offset,600*offset));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}


}
