package states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

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
	private Image map = new ImageIcon("map.png").getImage();
	
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
		g.drawImage(map,0,0,GamePanel.width,GamePanel.height*2/3,null);
		g.setColor(new Color(23, 100, 244));
		g.fillRect(0, GamePanel.height*2/3, GamePanel.width, GamePanel.height);
		g.setColor(new Color(200, 100, 50));
		g.fillRect(0, GamePanel.height*11/12, GamePanel.width, GamePanel.height);
		for(int i = 0; i < 3; i++) {
			Screen.get(i).render(g);
		} 
	}
}
