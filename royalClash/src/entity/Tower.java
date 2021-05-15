package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import states.Main;
import util.Calculate;
import util.Vector2f;

public class Tower extends Entity{
	
	private int target;
	private boolean attacking;
	private Image img1, img2 ;

	public Tower(Vector2f pos, int size, int health, int attackCooldown, int speed, int damage, boolean side, int kind, int range) {
		super(pos, size, health, attackCooldown, speed, damage, side, kind, range);
		if(side) target = 1;
		else target = 0;
		delayDamage = 0;
		img1 = new ImageIcon("tower2.png").getImage();
		img2 = new ImageIcon("tower3.png").getImage();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(updateTimes >= 0) {
			setDir(new Vector2f(0,0));
			attacking = false;
			for(int i = 0; i < Main.EMT.get(target).entityList.size(); i++) {
				int newDis = (int) Math.abs(Math.sqrt(Calculate.dis(Main.EMT.get(target).entityList.get(i).posX,Main.EMT.get(target).entityList.get(i).posY,posX,posY)));
				if(newDis < range) {
					attacking = true;
					if(attackReady && Main.EMT.get(target).entityList.get(i).returnHealth()-Main.EMT.get(target).entityList.get(i).delayDamage > 0) {
						Main.EMT.get(target).entityList.get(i).delayDamage += damage;
						Main.addBullet(new Bullet(Main.EMT.get(target).entityList.get(i),pos,10,damage));
						attackReady = false;
						prepareToAttack = updateTimes;
						break;
					}
				}
			}
		}
		if(!attacking) {
			prepareToAttack = updateTimes;
		}
		if(prepareToAttack+attackCooldown < updateTimes) {
			attackReady = true;
		}
		updateTimes++;
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		if(target == 0) {
			g.drawImage(img2,(int)posX-size*5/2, (int)posY,size*5,size*5,null);
		}
		else{
			g.drawImage(img1,(int)posX-size*5/2, (int)posY-size*5/2,size*5,size*5,null);
		}
	}
	
}
