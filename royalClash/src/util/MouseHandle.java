package util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import game.GamePanel;

public class MouseHandle implements MouseMotionListener, MouseListener {
	
	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseB = -1;
	private static boolean press = false;
	private static int click = 0;
	private static boolean enter = false;
	
	public MouseHandle(GamePanel game) {
		game.addMouseListener(this);
		game.addMouseMotionListener(this);
	}
	
	public Vector2f getMousePosition() {
		return new Vector2f((int)mouseX,(int)mouseY);
	}
	
	public boolean pressed() {
		if(press) return true;
		else return false;
	}
	
	public int clicked() {
		return click;
	}
	
	public boolean entered() {
		if(enter) return true;
		else return false;
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
		press = true;
		click++;
		System.out.println(click);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseB = -1;
		press = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("entered");
		enter = true;
		/*while(entered == true) {
			mouseMoved(e);
		}*/
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("leave");
		enter = false;
	}
	
}
