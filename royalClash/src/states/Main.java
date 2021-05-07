package states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import entity.EntityManager;
import entity.Tower;
import game.GamePanel;
import util.MouseHandle;
import util.Vector2f;

public class Main extends GameStates{
	
	private final int sky = 0;
	private final int ground = 1;
	private final int tower = 2;
	private final boolean ally = true;
	private final boolean enemy = false;
	public static EntityManager Ally;
	public static EntityManager Enemy;
	private int click = 0;
	public static int width = GamePanel.width;
	public static int height = GamePanel.height*2/3;
	public static ArrayList<EntityManager> EMT; //EntityManagerList (Emilia-tan Maji Tenshi)
	private ArrayList<Integer> AllyDeadList = new ArrayList<Integer>();
	private ArrayList<Integer> EnemyDeadList = new ArrayList<Integer>();

	public Main(GameStateManager gsm) {
		super(gsm);
		EMT = new ArrayList<EntityManager>();
		Ally = new EntityManager();
		Enemy = new EntityManager();
		
		EMT.add(Ally);
		EMT.add(Enemy);
		
		Ally.PushEntity(1,new Vector2f(width/5,height*3/4),ally,tower); //id, position, faction, kind
		Ally.PushEntity(1,new Vector2f(width*4/5,height*3/4),ally,tower);
		Ally.PushEntity(2,new Vector2f(width*1/2,height),ally,tower);
		
		Enemy.PushEntity(2,new Vector2f(width*1/2,0),enemy,tower);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		Ally.update();
		Enemy.update();
		if(GamePanel.updateTimes%60 == 0) {
			Enemy.PushEntity(0, new Vector2f(width*1/2, height/4),enemy,ground);
		}
		for(int i = 0; i < AllyDeadList.size(); i++) {
			Ally.entityList.remove(AllyDeadList.get(i)-i);
		}
		for(int i = 0; i < EnemyDeadList.size(); i++) {
			Enemy.entityList.remove(EnemyDeadList.get(i)-i);
		}
		AllyDeadList.clear();
		EnemyDeadList.clear();
		for(int i = 0; i < Ally.entityList.size(); i++) {
			if(Ally.entityList.get(i).returnHealth() <= 0) {
				AllyDeadList.add(i);
			}
		}
		for(int i = 0; i < Enemy.entityList.size(); i++) {
			if(Enemy.entityList.get(i).returnHealth() <= 0) {
				EnemyDeadList.add(i);
			}
		}
	}

	@Override
	public void input(MouseHandle mouse) {
		// TODO Auto-generated method stub
		if(mouse.clicked() > click) {
			click++;
			Ally.PushEntity(0, mouse.getMousePosition(),ally,ground);//PushEntity(new TestCube(30,10,10,10));
		}
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		Ally.render(g);
		Enemy.render(g);
	}

}
