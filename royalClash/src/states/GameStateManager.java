package states;

import java.awt.Graphics2D;
import java.util.ArrayList;

import entity.EntityManager;
import game.GamePanel;
import util.MouseHandle;
import util.Vector2f;
import states.PlayState;

public class GameStateManager { // controll the state 
	
	private ArrayList<GameStates> states;
	
	public static Vector2f map;
	
	public static final int Play = 0;
	public static final int Test = 1;
	public GameStateManager() {
		map = new Vector2f(GamePanel.width, GamePanel.height);
		
		states = new ArrayList<GameStates>();
		states.add(new TestState(this));
		states.add(new PlayState(this));//insert the "PlayState" to the StateList
		
	}
	
	public void popState(int state) {
		states.remove(state);
	}
	
	public void addState(int state) {
		if(state == Play) {
			states.add(new PlayState(this));
		}
	}
	
	public void update() {
		for(int i = 0;i < states.size(); i++) {
			states.get(i).update();
		}
	}
	
	public void input(MouseHandle mouse) {
		for(int i = 0; i < states.size(); i++) {
			states.get(i).input(mouse);
		}
	}
	
	public void render(Graphics2D g) {
		for(int i = 0; i < states.size(); i++) {
			states.get(i).render(g);
		}
	}
}
