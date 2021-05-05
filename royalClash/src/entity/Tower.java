package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import states.Main;
import util.Calculate;
import util.Vector2f;

public class Tower extends Entity{
	
	private int distance;
	private int target;

	public Tower(Vector2f position, int size, int health, int attackCooldown, int speed, int damage, boolean side, int kind, int range) {
		super(position, size, health, attackCooldown, speed, damage, side, kind, range);
		if(side) target = 1;
		else target = 0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(updateTimes%UTBD == 0) {
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
			}
		}
		if(!attackReady && lastAttackTimes + attackCooldown < updateTimes) {
			attackReady = true;
		}
		updateTimes++;
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(new Color(200, 100, 100));
		g.fillRect((int)posX-size/2, (int)posY-size/2, size, size);
	}
	
}
