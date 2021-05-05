package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import states.Main;
import util.Calculate;
import util.Vector2f;

public class TestCube extends Entity{
	
	private int distance = 10000000;
	private int target;
	private ArrayList<Image> animation;
	
	public TestCube(Vector2f position, int size, int health, int attackCooldown, int speed, int damage, boolean side, int kind, int range) {
		super(position, size, health, attackCooldown, speed, damage, side, kind, range);
		animation = new ArrayList<Image>();
		animation.add(new ImageIcon("skeletonRun1.png").getImage());
		animation.add(new ImageIcon("skeletonRun2.png").getImage()); 
		if(side) target = 1;
		else target = 0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(updateTimes >= 0) {
			setDir(new Vector2f(0,0));
			distance = 100000000;
			for(int i = 0; i < Main.EMT.get(target).entityList.size(); i++) {

				int newDis = (int) Math.abs(Math.sqrt(Calculate.dis(Main.EMT.get(target).entityList.get(i).posX,Main.EMT.get(target).entityList.get(i).posY,posX,posY)));
				if(newDis < range) {
					setDir(new Vector2f(0,0));
					if(attackReady) {
						if(Main.EMT.get(target).entityList.get(i).returnHealth() <= 0) {
							updateTimes--;
							break;
						}
						else Main.EMT.get(target).entityList.get(i).gethurt(damage);
						attackReady = false;
						lastAttackTimes = updateTimes;
					}
					break;
				}
				else if(distance > newDis) {
					distance = newDis;
					setDir(Calculate.dir(Main.EMT.get(target).entityList.get(i).posX,Main.EMT.get(target).entityList.get(i).posY,posX,posY));
				}
			}
		}
		if(!attackReady && lastAttackTimes + attackCooldown < updateTimes) {
			attackReady = true;
		}
		posX += dirX;
		posY += dirY;
		updateTimes++;
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		if(target == 0) {
			g.setColor(new Color(100, 100, 100));
			g.fillRect((int)posX-size/2, (int)posY-size/2, size, size);
		}
		else {
			if(updateTimes%20 <= 10) {
				g.drawImage(animation.get(0),(int)posX-size*3/2, (int)posY-size*3/2,size*3,size*3,null);
			}
			else {
				g.drawImage(animation.get(1),(int)posX-size*3/2, (int)posY-size*3/2,size*3,size*3,null);
			}
		}
	}
	
	
}
