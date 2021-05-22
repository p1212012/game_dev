package entity;

import java.util.ArrayList;

import util.Vector2f;
import states.PlayState;

import java.awt.Graphics2D;

public class EntityManager {
	
	public ArrayList<Entity> entityList;
	
	private static final int skeleton = 0; 
	private static final int tower = 1;
	private static final int base = 2;
	private static final int miner = 3;
	private static final int dragon = 4;
	private static final int fireMan = 5;
	private static final int bomber = 6;
	private static final int Goblin = 7;
	
	public EntityManager() {
		entityList = new ArrayList<Entity>();
	}
	
	public void PushEntity(int id, Vector2f P, boolean side, int faction) {//position, size, health, attackCooldown, speed, damage, side , kind, range     
		if(id == skeleton) {
			entityList.add(new TestCube(P,25,50,1,10,10,side,faction,30));
		}else if(id == tower) {
			entityList.add(new Tower(P,25,20,2,10,60,side,faction,400));
		}else if(id == base) {
			entityList.add(new Tower(P,50,50,2,10,60,side,faction,500));
		}else if(id == miner) {
			entityList.add(new Miner(P,40,100,1,10,50,side,faction,30));
		}else if(id == dragon) {
			entityList.add(new Dragon(P,40,100,1,10,50,side,faction,200));
		}else if(id == fireMan) {
			
		}else if(id == bomber) {
			
		}else if(id == Goblin) {
			
		}
	}
	
	public void PopEntity(int id) {
		entityList.remove(id);
	}
	
	public void pop(Entity en) {
		entityList.remove(en);
	}
	
	public void update() {
		for(int i = 0; i < entityList.size(); i++) {
			entityList.get(i).update();
		}
	}
	
	public void input() {
		
	}
	
	public void render(Graphics2D g) {
		for(int i = 0; i < entityList.size(); i++) {
			entityList.get(i).render(g);
		}
	}
}
