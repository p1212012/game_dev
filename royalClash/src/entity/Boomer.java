package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import states.Main;
import util.Calculate;
import util.Vector2f;

public class Boomer extends Entity{
	
	private int distance = 10000000;
	private int target;
	private ArrayList<Image> animation;
	private boolean attacking;
	private boolean stiff;
	private int explodeRange;
	private boolean facing;
	private float faceY;
	
	public Boomer(Vector2f position, int size, int health, int attackCooldown, int speed, int damage, boolean side, int kind, int range, int explodeRange) {
		super(position, size, health, attackCooldown, speed, damage, side, kind, range);
		animation = new ArrayList<Image>();
		animation.add(new ImageIcon("skeletonRun1.png").getImage());
		animation.add(new ImageIcon("skeletonRun2.png").getImage()); 
		animation.add(new ImageIcon("skeletonRun3.png").getImage()); 
		animation.add(new ImageIcon("skeletonRun4.png").getImage()); 
		this.explodeRange = explodeRange;
		if(side) target = 1;
		else target = 0;
		attacking = false;
		stiff = false;
		delayDamage = 0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() { 
		// TODO Auto-generated method stub
		if(updateTimes >= 0 && !stiff) {
			setDir(new Vector2f(0,0));
			distance = 100000000;
			for(int i = 0; i < Main.EMT.get(target).entityList.size(); i++) {
				if(Main.EMT.get(target).entityList.get(i).kind == 0) continue;
				int newDis = (int) Math.abs(Math.sqrt(Calculate.dis(Main.EMT.get(target).entityList.get(i).pos.x,Main.EMT.get(target).entityList.get(i).pos.y,pos.x,pos.y)));
				if(newDis < range) {
					health = 0;
					distance = newDis;
					faceY = (Main.EMT.get(target).entityList.get(i).pos.y - pos.y);
					setDir(new Vector2f(0,0));
					if(Main.EMT.get(target).entityList.get(i).returnHealth()-Main.EMT.get(target).entityList.get(i).delayDamage > 0) {
						for(int j = 0; j < Main.EMT.get(target).entityList.size(); j++) {
							newDis = (int) Math.abs(Math.sqrt(Calculate.dis(Main.EMT.get(target).entityList.get(j).pos.x,Main.EMT.get(target).entityList.get(j).pos.y,pos.x,pos.y)));
							if(newDis < explodeRange) {
								Main.EMT.get(target).entityList.get(j).gethurt(damage);
							}
						}
						break;
					}
				}
				else if(distance > newDis) {
					faceY = (Main.EMT.get(target).entityList.get(i).pos.y - pos.y);
					distance = newDis;
					setDir(Calculate.dir(Main.EMT.get(target).entityList.get(i).pos.x,Main.EMT.get(target).entityList.get(i).pos.y,pos.x,pos.y));
				}
			}
			if(faceY > 0) facing = true;
			else if(faceY < 0) facing = false;
		}
		pos.x += dirX;
		pos.y += dirY;
		
		updateTimes++;
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		if(facing) {
			if(updateTimes%20 <= 10) {
				g.drawImage(animation.get(2),(int)pos.x-size*3/2, (int)pos.y-size*3/2,size*3,size*3,null);
			}
			else {
					g.drawImage(animation.get(3),(int)pos.x-size*3/2, (int)pos.y-size*3/2,size*3,size*3,null);
			}
		}
		else {
			if(updateTimes%20 <= 10) {
				g.drawImage(animation.get(0),(int)pos.x-size*3/2, (int)pos.y-size*3/2,size*3,size*3,null);
			}
			else {
				g.drawImage(animation.get(1),(int)pos.x-size*3/2, (int)pos.y-size*3/2,size*3,size*3,null);
			}
		}
	}
	
	
}
