package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import states.Main;
import util.Calculate;
import util.Vector2f;

public class Miner extends Entity{
	
	private int distance = 10000000;
	private int target;
	private ArrayList<Image> animation;
	private boolean attacking;
	private boolean stiff;
	private int lastStiffTime;
	private int stiffTime = 30;
	private boolean facing;
	private float faceY;
	
	public Miner(Vector2f position, int size, int health, int attackCooldown, int speed, int damage, boolean side, int kind, int range) {
		super(position, size, health, attackCooldown, speed, damage, side, kind, range);
		animation = new ArrayList<Image>();
		animation.add(new ImageIcon("minerRun1.png").getImage());
		animation.add(new ImageIcon("minerRun2.png").getImage()); 
		animation.add(new ImageIcon("minerRun3.png").getImage());
		animation.add(new ImageIcon("minerRun2.png").getImage());
		animation.add(new ImageIcon("minerAttack1.png").getImage()); 
		animation.add(new ImageIcon("minerAttack2.png").getImage());  
		animation.add(new ImageIcon("minerRun4.png").getImage());
		animation.add(new ImageIcon("minerRun5.png").getImage()); 
		animation.add(new ImageIcon("minerRun6.png").getImage());
		animation.add(new ImageIcon("minerRun5.png").getImage());
		animation.add(new ImageIcon("minerAttack3.png").getImage()); 
		animation.add(new ImageIcon("minerAttack4.png").getImage()); 
		
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
			attacking = false;
			for(int i = 0; i < Main.EMT.get(target).entityList.size(); i++) {
				if(Main.EMT.get(target).entityList.get(i).kind == 0) continue;
				int newDis = (int) Math.abs(Math.sqrt(Calculate.dis(Main.EMT.get(target).entityList.get(i).pos.x,Main.EMT.get(target).entityList.get(i).pos.y,pos.x,pos.y)));
				if(newDis < range) {
					distance = newDis;
					faceY = (Main.EMT.get(target).entityList.get(i).pos.y - pos.y);
					setDir(new Vector2f(0,0));
					attacking = true;
					if(attackReady && Main.EMT.get(target).entityList.get(i).returnHealth()-Main.EMT.get(target).entityList.get(i).delayDamage > 0) {
						Main.EMT.get(target).entityList.get(i).gethurt(damage);
						attackReady = false;
						lastStiffTime = updateTimes;
						stiff = true;
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
		if(!attacking) {
			prepareToAttack = updateTimes;
		}
		if(!stiff && prepareToAttack + attackCooldown < updateTimes) {
			attackReady = true;
		}
		if(stiff && lastStiffTime + stiffTime < updateTimes) {
			stiff = false;
			prepareToAttack = updateTimes;
		}
		pos.x += dirX;
		pos.y += dirY;
		
		updateTimes++;
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		if(facing) {
			if(stiff) {
				g.drawImage(animation.get(10),(int)pos.x-size*3/2, (int)pos.y-size*3/2,size*3,size*3,null);
			}
			else if(attacking) {
				g.drawImage(animation.get(11),(int)pos.x-size*3/2, (int)pos.y-size*3/2,size*3,size*3,null);
			}
			else {
				if(updateTimes%60 <= 15) {
					g.drawImage(animation.get(6),(int)pos.x-size*3/2, (int)pos.y-size*3/2,size*3,size*3,null);
				}
				else if(updateTimes%60 <= 30){
					g.drawImage(animation.get(7),(int)pos.x-size*3/2, (int)pos.y-size*3/2,size*3,size*3,null);
				}else if(updateTimes%60 <= 45){
					g.drawImage(animation.get(8),(int)pos.x-size*3/2, (int)pos.y-size*3/2,size*3,size*3,null);
				}else{
					g.drawImage(animation.get(9),(int)pos.x-size*3/2, (int)pos.y-size*3/2,size*3,size*3,null);
				}
			}
		}
		else {
			if(stiff) {
				g.drawImage(animation.get(5),(int)pos.x-size*3/2, (int)pos.y-size*3/2,size*3,size*3,null);
			}
			else if(attacking) {
				g.drawImage(animation.get(4),(int)pos.x-size*3/2, (int)pos.y-size*3/2,size*3,size*3,null);
			}
			else {
				if(updateTimes%60 <= 15) {
					g.drawImage(animation.get(0),(int)pos.x-size*3/2, (int)pos.y-size*3/2,size*3,size*3,null);
				}
				else if(updateTimes%60 <= 30){
					g.drawImage(animation.get(1),(int)pos.x-size*3/2, (int)pos.y-size*3/2,size*3,size*3,null);
				}else if(updateTimes%60 <= 45){
					g.drawImage(animation.get(2),(int)pos.x-size*3/2, (int)pos.y-size*3/2,size*3,size*3,null);
				}else{
					g.drawImage(animation.get(3),(int)pos.x-size*3/2, (int)pos.y-size*3/2,size*3,size*3,null);
				}
			}
		}
	}
	
	
}
