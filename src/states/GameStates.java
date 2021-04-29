package states;

import java.awt.Graphics2D;

import util.MouseHandle;

public abstract class GameStates { //to create different state(screen)
	
	private GameStateManager gsm;
	
	public GameStates(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public abstract void update();
	public abstract void input(MouseHandle mosue);
	public abstract void render(Graphics2D g);
}
