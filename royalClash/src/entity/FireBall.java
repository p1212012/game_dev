package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import states.Main;
import util.Calculate;
import util.Vector2f;

public class FireBall {
	private Vector2f Pos;
	private Vector2f Dir;
	private Vector2f Dest;
	private ArrayList<Image> animation = new ArrayList<Image>();;
	private int target;
	private int size;
	private int dis;
	private int damage;
	private int speed;
	private int explode = -1;
	public boolean kill;
	
	public FireBall(Vector2f Pos, Vector2f destination, int size, int damage,int speed, boolean side, int radius) {
		this.size = size;
		this.Pos = Pos;
		this.damage = damage;
		this.speed = speed;
		this.Dest = destination;
		animation.add(new ImageIcon("fireBallFly.png").getImage());
		animation.add(new ImageIcon("fireBallExplode.png").getImage());
		kill = false;
		if(side) target = 1;
		else target = 0;
	}
	
	public void update() {
		if(!kill && explode < 0) {
			Dir = Calculate.dirWithSpeed(Pos.x, Pos.y, Dest.x, Dest.y, speed);
			dis = (int) Math.sqrt(Calculate.dis(Pos, Dest));
			if(dis < 10) {
				explode = 10;
				for(int i = 0; i < Main.EMT.get(target).entityList.size(); i++) {
					System.out.println((int) Math.sqrt(Calculate.dis(Main.EMT.get(target).entityList.get(i).pos,Dest)));
					if((int) Math.sqrt(Calculate.dis(Main.EMT.get(target).entityList.get(i).pos,Dest)) <= size/2) {
						System.out.println("hit");
						Main.EMT.get(target).entityList.get(i).gethurt(damage);
					}
				}
				Pos.x = Dest.x;
				Pos.y = Dest.y;
				Dir.x = 0;
				Dir.y = 0;
			}
			Pos.x -= Dir.x;
			Pos.y -= Dir.y;
		}
		if(explode-- == 0) {
			explode = 10000;
			kill = true;
		}
	}
	public void render(Graphics2D g) {
		if(explode < 0) {
			g.drawImage(animation.get(0),(int)Pos.x-size/2, (int)Pos.y-size/2,size,size,null);
		}else {
			g.drawImage(animation.get(1),(int)Pos.x-size/2, (int)Pos.y-size/2,size,size,null);
		}
	}
	
}
