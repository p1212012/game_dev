package states;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import util.MouseHandle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class CardDisplay extends GameStates{
 
 int height,width;
 
 
 
 public CardDisplay(GameStateManager gsm) {
  super(gsm);
  
 }

 @Override
 public void update() {
  
  
 }

 @Override
 public void input(MouseHandle mosue) {
  
  
 }

 @Override
 public void render(Graphics2D g) {
  
	  g.setColor(Color.BLUE);
	  g.fillRect((width)*1/5,(height)*2/3,(width)*4/5,(height)*1/4);
	  
	  g.setColor(Color.RED);
	  g.fillRect(0,(height)*2/3, (width)*1/5,(height)*1/4);
	  
	  g.setColor(Color.BLACK);
	  g.drawLine(0,(height)*2/3,width,(height)*2/3);
	  g.drawLine(0,(height)*11/12,width,(height)*11/12);
	  
	  g.drawLine(0,(height)*2/3,0,(height)*11/12);
	  g.drawLine((width)*1/5,(height)*2/3,(width)*1/5,(height)*11/12);
	  g.drawLine((width)*2/5,(height)*2/3,(width)*2/5,(height)*11/12);
	  g.drawLine((width)*3/5,(height)*2/3,(width)*3/5,(height)*11/12);
	  g.drawLine((width)*4/5,(height)*2/3,(width)*4/5,(height)*11/12);
	  g.drawLine(width,(height)*2/3,width,(height)*11/12);
	 
 }
 
 
 
 
}