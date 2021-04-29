package states;

import java.awt.Color;
import java.awt.Graphics2D;

import util.MouseHandle;

public class TestState extends GameStates{ //(test state)
	public TestState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void update() {}
	public void input(MouseHandle mouse) {
		
	}
	public void render(Graphics2D g) {
		g.setColor(Color.blue);
		g.drawRect(200,200,200,200);
	}
}