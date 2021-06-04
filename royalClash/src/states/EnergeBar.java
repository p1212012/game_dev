package states;

import java.awt.Color;
import java.awt.Graphics2D;

import game.GamePanel;
import util.MouseHandle;
public class EnergeBar extends GameStates {

	private static int updateCount;
	private static int energy;
	private static int[] cost = new int[10];
	
	public EnergeBar(GameStateManager gsm) {
		super(gsm);
		energy = 0;
		updateCount = 0;
		cost[1] = 3;
		cost[2] = 4;
		cost[3] = 3;
		cost[4] = 5;
		cost[5] = 2;
		cost[6] = 3;
		cost[7] = 4;
		cost[8] = 5;
		// TODO Auto-generated constructor stub
	}
	
	public static int returnEnergy() {
		return energy;
	}
	
	public static boolean useEnergy(int id) {
		if(energy < cost[id]) {
			return false;
		}else {
			energy -= cost[id];
			return true;
		}
	}

	@Override
	public void update() {		
		updateCount++;
		if(updateCount%60 == 0 && energy < 10) energy++;
	}

	@Override
	public void input(MouseHandle mosue) {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Graphics2D g) {

	    g.setColor(Color.BLUE);
	    g.fillRect(0, GamePanel.height*28/30, GamePanel.width * energy/10, GamePanel.height/20);
	
	}

}
