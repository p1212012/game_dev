package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import states.Main;
import util.Calculate;
import util.Vector2f;


public class Musketeer extends Entity{
	
	private int distance = 10000000;
	private int target;
	private ArrayList<Image> animation;
	private boolean attacking;
	private boolean stiff;
	private int lastStiffTime;
	private int stiffTime = 30;
	private boolean facing;
	private float faceY;
	private Vector2f bulletPosition = pos;
	
	
	public Musketeer(Vector2f position, int size, int health, int attackCooldown, int speed, int damage, boolean side, int kind, int range) {
		super(position, size, health, attackCooldown, speed, damage, side, kind, range);
		animation = new ArrayList<Image>();
		animation.add(new ImageIcon("MusketeerRun1.png").getImage());
		animation.add(new ImageIcon("MusketeerRun2.png").getImage()); 
		animation.add(new ImageIcon("MusketeerAttack1.png").getImage()); 
		animation.add(new ImageIcon("MusketeerAttack2.png").getImage()); 
		animation.add(new ImageIcon("MusketeerRun3.png").getImage()); 
		animation.add(new ImageIcon("MusketeerRun4.png").getImage()); 
		animation.add(new ImageIcon("MusketeerAttack3.png").getImage()); 
		animation.add(new ImageIcon("MusketeerAttack4.png").getImage()); 
		
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
				int newDis = (int) Math.abs(Math.sqrt(Calculate.dis(Main.EMT.get(target).entityList.get(i).posX,Main.EMT.get(target).entityList.get(i).posY,posX,posY)));
				if(newDis < range) {
					
					distance = newDis;
					faceY = (Main.EMT.get(target).entityList.get(i).posY - posY);
					setDir(new Vector2f(0,0));
					
					attacking = true;
					if(attackReady && Main.EMT.get(target).entityList.get(i).returnHealth()-Main.EMT.get(target).entityList.get(i).delayDamage > 0) {
						Main.EMT.get(target).entityList.get(i).delayDamage += damage;
						Main.addBullet(new Bullet(Main.EMT.get(target).entityList.get(i),bulletPosition,10,damage));
						attackReady = false;
						prepareToAttack = updateTimes;
						break;
					}
					
				}
				else if(distance > newDis) {
					faceY = (Main.EMT.get(target).entityList.get(i).posY - posY);
					distance = newDis;
					setDir(Calculate.dir(Main.EMT.get(target).entityList.get(i).posX,Main.EMT.get(target).entityList.get(i).posY,posX,posY));
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
		posX += dirX;
		posY += dirY;
		
		bulletPosition.x = posX;
		bulletPosition.y = posY;
		
		updateTimes++;
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		if(facing) {
			
			if(stiff) {
				g.drawImage(animation.get(7),(int)posX-size*3/2, (int)posY-size*3/2,size*5,size*5,null);
			}
			else if(attacking) {
				g.drawImage(animation.get(6),(int)posX-size*3/2, (int)posY-size*3/2,size*5,size*5,null);
			}
			else {
				if(updateTimes%20 <= 10) {
					g.drawImage(animation.get(4),(int)posX-size*3/2, (int)posY-size*3/2,size*5,size*5,null);
				}
				else {
					g.drawImage(animation.get(5),(int)posX-size*3/2, (int)posY-size*3/2,size*5,size*5,null);
				}
			}
			
		}
		else {
			if(stiff) {
				g.drawImage(animation.get(3),(int)posX-size*3/2, (int)posY-size*3/2,size*5,size*5,null);
			}
			else if(attacking) {
				g.drawImage(animation.get(2),(int)posX-size*3/2, (int)posY-size*3/2,size*5,size*5,null);
				
			}
			else {
				if(updateTimes%20 <= 10) {
					g.drawImage(animation.get(0),(int)posX-size*3/2, (int)posY-size*3/2,size*5,size*5,null);
				}
				else {
					g.drawImage(animation.get(1),(int)posX-size*3/2, (int)posY-size*3/2,size*5,size*5,null);
				}
			}
		} 
	}
	
	
}

