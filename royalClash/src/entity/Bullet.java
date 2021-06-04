package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import util.Calculate;
import util.Vector2f;

public class Bullet {
	private Vector2f pos;
	private Vector2f Dir;
	private Entity target;
	private int size;
	private int dis;
	private int damage;
	public boolean kill;
	
	public Bullet(Entity target, Vector2f Pos, int size, int damage) {
		this.target = target;
		this.size = size;
		this.pos = new Vector2f(Pos.x, Pos.y);
		this.damage = damage;
		kill = false;
	}
	
	public void update() {
		if(target.health <= 0) kill = true;
		if(!kill) {
			Dir = Calculate.dirWithSpeed(target.pos.x, target.pos.y, pos.x,pos.y, size);
			dis = Calculate.dis(pos.x, pos.y, target.pos.x, target.pos.y);
			if(dis < 200) {
				target.gethurt(damage);
				target.delayDamage -= damage;
				kill = true;
			}
			pos.x += Dir.x;
			pos.y += Dir.y;
		}
	}
	public void render(Graphics2D g) {
		g.setColor(Color.black);
		g.fillArc((int)pos.x-size/2, (int)pos.y-size/2, size, size, 0, 360);
	}
	
}
