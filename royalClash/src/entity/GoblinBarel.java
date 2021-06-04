package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import states.Main;
import util.Calculate;
import util.Vector2f;

public class GoblinBarel {
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
	private boolean side;
	private int updateTimes = 0;
	
	public GoblinBarel(Vector2f Pos, Vector2f destination, int size,int speed, boolean side) {
		this.size = size;
		this.Pos = Pos;
		this.speed = speed;
		this.Dest = destination;
		animation.add(new ImageIcon("barrel1.png").getImage());
		animation.add(new ImageIcon("barrel2.png").getImage());
		animation.add(new ImageIcon("barrel3.png").getImage());
		animation.add(new ImageIcon("barrel2.png").getImage());
		kill = false;
		this.side = side;
		if(side) target = 0;
		else target = 1;
	}
	
	public void update() {
		updateTimes++;
		if(!kill && explode < 0) {
			Dir = Calculate.dirWithSpeed(Pos.x, Pos.y, Dest.x, Dest.y, speed);
			dis = (int) Math.sqrt(Calculate.dis(Pos, Dest));
			if(dis < 10) {
				Main.EMT.get(target).PushEntity(7, new Vector2f(Pos.x,Pos.y+10), side);
				Main.EMT.get(target).PushEntity(7, new Vector2f(Pos.x+8,Pos.y-10), side);
				Main.EMT.get(target).PushEntity(7, new Vector2f(Pos.x-8,Pos.y-10), side);
				kill = true;
				Pos.x = Dest.x;
				Pos.y = Dest.y;
				Dir.x = 0;
				Dir.y = 0;
			}
			Pos.x -= Dir.x;
			Pos.y -= Dir.y;
		}
	}
	public void render(Graphics2D g) {
		if(updateTimes%40 < 10) {
			g.drawImage(animation.get(0),(int)Pos.x-size/2, (int)Pos.y-size/2,size,size,null);
		}
		else if(updateTimes%40 < 20) {
			g.drawImage(animation.get(1),(int)Pos.x-size/2, (int)Pos.y-size/2,size,size,null);
		}
		else if(updateTimes%40 <30) {
			g.drawImage(animation.get(2),(int)Pos.x-size/2, (int)Pos.y-size/2,size,size,null);
		}
		else {
			g.drawImage(animation.get(3),(int)Pos.x-size/2, (int)Pos.y-size/2,size,size,null);
		}
	}
	
}
