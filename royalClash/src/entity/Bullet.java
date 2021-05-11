package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import util.Calculate;
import util.Vector2f;

public class Bullet {
	private float posX;
	private float posY;
	private Vector2f Pos;
	private Vector2f Dir;
	private Entity target;
	private int size;
	private int dis;
	public boolean kill;
	
	public Bullet(Entity target, float posX, float posY, int size) {
		this.target = target;
		this.size = size;
		this.posX = posX;
		this.posY = posY;
		kill = false;
	}
	
	public void update() {
		if(target.health <= 0) kill = true;
		if(!kill) {
			Dir = Calculate.dirWithSpeed(posX, posX, target.posX, target.posY, size);
			dis = Calculate.dis(posX, posY, target.posY, target.posX);
			if(dis < 10) {
				target.gethurt(100);
				kill = true;
			}
		}
	}
	public void render(Graphics2D g) {
		g.setColor(Color.black);
		g.fillArc((int)posX-size/2, (int)posY-size/2, size, size, 0, 360);
	}
	
}
