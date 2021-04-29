package states;

import java.awt.Color;
import java.awt.Graphics2D;

import util.MouseHandle;

public class Main extends GameStates{

	public Main(GameStateManager gsm) {
		super(gsm);
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
		g.setColor(Color.white);
		g.fillArc(0, 100, 200, 200, 0, 360);
		
	}

}
