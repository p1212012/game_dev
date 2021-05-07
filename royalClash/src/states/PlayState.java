package states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import entity.Entity;
import entity.EntityManager;
import entity.TestCube;
import entity.Tower;
import game.GamePanel;
import util.MouseHandle;
import util.Vector2f;
import states.Card;


public class PlayState extends GameStates{
	private int x = 100, y = 100;
	public int card;
	private ArrayList<GameStates> Screen;
	
	public PlayState(GameStateManager gsm) { //why we need this? Arraylist<> is GameState so we need to put GameState inside
		super(gsm);//GameStates(gsm); 
		Screen = new ArrayList<GameStates>();
		Screen.add(new Card(gsm));
		Screen.add(new Main(gsm));
		Screen.add(new EnergeBar(gsm));
	}
	
	public void update() {
		for(int i = 0; i < 3; i++) {
			Screen.get(i).update();
		}
	}
	
	public void input(MouseHandle mouse) {
		for(int i = 0; i < 3; i++) {
			Screen.get(i).input(mouse);
		}

	}
	
	public void render(Graphics2D g) {
		g.setColor(new Color(66, 134, 244));
		g.fillRect(0, 0, GamePanel.width, GamePanel.height*2/3);

		g.setColor(new Color(23, 100, 244));
		g.fillRect(0, GamePanel.height*2/3, GamePanel.width, GamePanel.height);
		g.setColor(new Color(200, 100, 50));
		g.fillRect(0, GamePanel.height*11/12, GamePanel.width, GamePanel.height);
		for(int i = 0; i < 3; i++) {
			Screen.get(i).render(g);
		} 
	}
}
