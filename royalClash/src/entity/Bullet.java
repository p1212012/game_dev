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
	private int damage;
	public boolean kill;
	
	public Bullet(Entity target, Vector2f Pos, int size, int damage) {
		this.target = target;
		this.size = size;
		this.Pos = Pos;
		this.posX = Pos.x;
		this.posY = Pos.y;
		this.damage = damage;
		kill = false;
	}
	
	public void update() {
		if(target.health <= 0) kill = true;
		if(!kill) {
			Dir = Calculate.dirWithSpeed(target.posX, target.posY, posX,posY, size);
			dis = Calculate.dis(posX, posY, target.posX, target.posY);
			if(dis < 200) {
				target.gethurt(damage);
				target.delayDamage -= damage;
				kill = true;
			}
			posX += Dir.x;
			posY += Dir.y;
		}
	}
	public void render(Graphics2D g) {
		g.setColor(Color.black);
		g.fillArc((int)posX-size/2, (int)posY-size/2, size, size, 0, 360);
	}
	
}
