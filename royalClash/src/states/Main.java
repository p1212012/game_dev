package states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import entity.Bullet;
import entity.EntityManager;
import entity.FireBall;
import entity.MinerGround;
import entity.Tower;
import entity.Wood;
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
	private static ArrayList<Bullet> BulletList = new ArrayList<Bullet>();
	private static ArrayList<FireBall> FireBallList = new ArrayList<FireBall>();
	private static ArrayList<Wood> WoodList = new ArrayList<Wood>(); 
	private static ArrayList<MinerGround> GroundList = new ArrayList<MinerGround>();
	private ArrayList<Image> animation;
	private int updateTimes = 0;

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
		
		animation = new ArrayList<Image>();
		animation.add(new ImageIcon("firemanRun1.jpg").getImage());
		animation.add(new ImageIcon("firemanRun2.jpg").getImage()); 
		animation.add(new ImageIcon("firemanRun3.jpg").getImage()); 
		animation.add(new ImageIcon("firemanRun2.jpg").getImage()); 
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		Ally.update();
		Enemy.update();
		for(int i = 0 ; i < GroundList.size();i++){
			if(GroundList.get(i).kill) {
				GroundList.remove(i);
			}
		}
		for(int i = 0; i < WoodList.size(); i++) {
			if(WoodList.get(i).kill) {
				WoodList.remove(i);
			}
		}
		for(int i = 0; i < FireBallList.size(); i++) {
			if(FireBallList.get(i).kill) {
				FireBallList.remove(i);
			}
		}
		for(int i = 0; i < BulletList.size(); i++) {
			if(BulletList.get(i).kill) {
				BulletList.remove(i);
			}
		}
		
		for(int i = 0 ; i < GroundList.size();i++){
			GroundList.get(i).update();
		}
		for(int i = 0; i < BulletList.size(); i++) {
			BulletList.get(i).update();
		}
		for(int i = 0; i < FireBallList.size(); i++) {
			FireBallList.get(i).update();
		}
		for(int i = 0; i < WoodList.size(); i++) {
			WoodList.get(i).update();
		}
		
		
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
	public static void addBullet(Bullet bullet) {
		BulletList.add(bullet);
	}
	
	

	@Override
	public void input(MouseHandle mouse) {
		// TODO Auto-generated method stub
		if(mouse.clicked() > click) {
			click++;
			//Ally.PushEntity(4, mouse.getMousePosition(),ally,sky);
			GroundList.add(new MinerGround(new Vector2f(GamePanel.width/2, GamePanel.height), mouse.getMousePosition(), 100, 10, ally));
			//WoodList.add(new Wood(mouse.getMousePosition(), 150, 80, 10, 3, ally));//pos, height,width,damage,speed,side
			//FireBallList.add(new FireBall(new Vector2f(GamePanel.width/2, GamePanel.height), mouse.getMousePosition(), 100, 100, 10, ally, 30)); //Pos,destination,size,damage,speed,side,radius
			//Ally.PushEntity(0, mouse.getMousePosition(),ally,ground);//PushEntity(new TestCube(30,10,10,10));
		}
		
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		Enemy.render(g);
		Ally.render(g);
		for(int i = 0; i < GroundList.size(); i++) {
			GroundList.get(i).render(g);
		}
		for(int i = 0; i < BulletList.size(); i++) {
			BulletList.get(i).render(g);
		}
		for(int i = 0; i < FireBallList.size(); i++) {
			FireBallList.get(i).render(g);
		}
		for(int i = 0; i < WoodList.size(); i++) {
			WoodList.get(i).render(g);
		}
		updateTimes++;
		/*int posX = GamePanel.width/2;
		int posY = GamePanel.height/2;
		int size = 25;
		if(updateTimes%80 < 20) {
			g.drawImage(animation.get(0),(int)posX-size*3/2, (int)posY-size*3/2,size*3,size*3,null);
		}else if(updateTimes%80 < 40) {
			g.drawImage(animation.get(1),(int)posX-size*3/2, (int)posY-size*3/2,size*3,size*3,null);
		}else if(updateTimes%80 < 60){
			g.drawImage(animation.get(2),(int)posX-size*3/2, (int)posY-size*3/2,size*3,size*3,null);
		}else {
			g.drawImage(animation.get(3),(int)posX-size*3/2, (int)posY-size*3/2,size*3,size*3,null);
		}*/
	}

}
