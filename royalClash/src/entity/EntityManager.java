package entity;

import java.util.ArrayList;

import game.Window;
import util.Vector2f;
import states.PlayState;

import java.awt.Graphics2D;

public class EntityManager {
	
	public ArrayList<Entity> entityList;
	
	private static final int base = 0;
	private static final int Tower = 1;
	private static final int skeleton = 2; 
	private static final int bomber = 3;
	private static final int dragon = 4;
	private static final int musketeer = 5;
	private static final int miner = 6;
	private static final int Goblin = 7;
	private final int sky = 0;
	private final int ground = 1;
	private final int tower = 2;
	
	public EntityManager() {
		entityList = new ArrayList<Entity>();
	}
	
	public void PushEntity(int id, Vector2f P, boolean side) {//position, size, health, attackCooldown, speed, damage, side , kind, range     
		if(id == skeleton) {
			entityList.add(new TestCube(P,12*Window.offset,50,1,5*Window.offset,10,side,ground,15*Window.offset));
		}else if(id == Tower) {
			entityList.add(new Tower(P,12*Window.offset,200,2,5*Window.offset,60,side,tower,200*Window.offset));
		}else if(id == base) {
			entityList.add(new Base(P,25*Window.offset,300,2,5*Window.offset,60,side,tower,250*Window.offset));
		}else if(id == miner) {
			entityList.add(new Miner(P,20*Window.offset,100,1,5*Window.offset,50,side,ground,15*Window.offset));
		}else if(id == dragon) {
			entityList.add(new Dragon(P,20*Window.offset,100,1,5*Window.offset,50,side,sky,100*Window.offset));
		}else if(id == musketeer) {
			entityList.add(new Musketeer(P,20*Window.offset,100,1,5*Window.offset,50,side,ground,100*Window.offset));
		}else if(id == bomber) {
			entityList.add(new Boomer(P,10*Window.offset,100,1,5*Window.offset,50,side,ground,10,50*Window.offset));
		}else if(id == Goblin) {
			entityList.add(new TestCube(P,12*Window.offset,50,1,5*Window.offset,10,side,ground,15*Window.offset));
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
