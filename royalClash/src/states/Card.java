package states;

import java.awt.Color;
import java.awt.Graphics2D;

import game.GamePanel;
import util.MouseHandle;

public class Card extends GameStates {

	private int width;
	private int height;
	
	public Card(GameStateManager gsm) {
		super(gsm);
		width = GamePanel.width;
		height = GamePanel.height;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void input(MouseHandle mosue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
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
