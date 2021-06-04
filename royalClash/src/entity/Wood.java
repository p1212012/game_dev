package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import states.Main;
import util.Calculate;
import util.Vector2f;

public class Wood {
	private Vector2f Pos;
	private ArrayList<Image> animation = new ArrayList<Image>();;
	private int target;
	private int size;
	private int damage;
	private int speed;
	private int width;
	private int height;
	private int updateTimes;
	public boolean kill;
	
	public Wood(Vector2f Pos, int height, int width, int damage,int speed, boolean side) {
		this.width = height;
		this.height = height;
		this.Pos = Pos;
		this.damage = damage;
		animation.add(new ImageIcon("wood1.png").getImage());
		animation.add(new ImageIcon("wood2.png").getImage());
		kill = false;
		if(side) {
			target = 1;
			this.speed = speed;
		}else {
			target = 0;
			this.speed = -speed;
		}
		updateTimes = 0;
	}
	
	public void update() {
		for(int i = 0; i < Main.EMT.get(target).entityList.size(); i++) {
			if(Main.EMT.get(target).entityList.get(i).woodHit == false && Calculate.inside(width, height, Pos, Main.EMT.get(target).entityList.get(i).pos, Main.EMT.get(target).entityList.get(i).size)) {
				Main.EMT.get(target).entityList.get(i).woodHit = true;
				if(Main.EMT.get(target).entityList.get(i).kind != 2) {
					Main.EMT.get(target).entityList.get(i).pos.y -= 20;
				}
				Main.EMT.get(target).entityList.get(i).gethurt(damage);
			}
		}
		updateTimes++;
		if(updateTimes > 60*4) kill = true;
		Pos.y -= speed;
	}
	public void render(Graphics2D g) {
		if(updateTimes % 20 <= 10) {
			g.drawImage(animation.get(0),(int)Pos.x-width/2, (int)Pos.y-height,width,height*2,null);
		}else {
			g.drawImage(animation.get(1),(int)Pos.x-width/2, (int)Pos.y-height,width,height*2,null);
		}
	}
	
}
