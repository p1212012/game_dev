package util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import game.GamePanel;

public class MouseHandle implements MouseMotionListener, MouseListener {
	
	public static int mouseX = -1;
	public static int mouseY = -1;
	public static int mouseB = -1;
	public static boolean pressed = false;
	private static boolean entered = false;
	public static MouseEvent publicMouse;
	
	public MouseHandle(GamePanel game) {
		game.addMouseListener(this);
		game.addMouseMotionListener(this);
	}
	
	public int getX() {
		return mouseX;
	}
	
	public int getY() {
		return mouseY;
	}
	
	public int getButton() {
		return mouseB;
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) { // left:1 mid:2 right:3
		// TODO Auto-generated method stub
		mouseB = e.getButton();
		pressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseB = -1;
		pressed = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("entered");
		entered = true;
		/*while(entered == true) {
			mouseMoved(e);
		}*/
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("leave");
		entered = false;
	}
	
}
