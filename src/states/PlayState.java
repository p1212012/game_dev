package states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import game.GamePanel;
import util.MouseHandle;
import states.Card;


public class PlayState extends GameStates{
	private int x = 100, y = 100;
	public int card;
	private ArrayList<GameStates> Screen;
	
	
	public PlayState(GameStateManager gsm) { //why we need this? Arraylist<> is GameState so we need to put GameState inside
		super(gsm);//GameStates(gsm); 
		Screen = new ArrayList<GameStates>();
		Screen.add(new Card(gsm));
		Screen.add(new EnergeBar(gsm));
		Screen.add(new Main(gsm));
	}
	
	public void update() {
		for(int i = 0; i < 3; i++) {
			Screen.get(i).update();
		}
	}
	
	public void input(MouseHandle mouse) {
		if(mouse.pressed) {
			x = mouse.getX();
			if(mouse.getY()+100 < GamePanel.height*3/5) y = mouse.getY();
			else y = GamePanel.height*3/5-100;
			//summon(x,y,card);
		}
		for(int i = 0; i < 3; i++) {
			Screen.get(i).input(mouse);
		}

	}
	
	public void render(Graphics2D g) {
		for(int i = 0; i < 3; i++) {
			Screen.get(i).render(g);
		}
		g.setColor(Color.red);
		g.fillArc(x-100, y-100, 200, 200, 0, 360);
	}
}
