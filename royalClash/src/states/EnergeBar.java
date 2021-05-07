package states;

import java.awt.Color;
import java.awt.Graphics2D;

import game.GamePanel;
import util.MouseHandle;
public class EnergeBar extends GameStates {

	private int updateCount;
	private int energy;
	
	public EnergeBar(GameStateManager gsm) {
		super(gsm);
		energy = 0;
		updateCount = 0;
		// TODO Auto-generated constructor stub
	}
	
	public int returnEnergy() {
		return energy;
	}
	
	public boolean useEnergy(int energy) {
		if(this.energy < energy) {
			return false;
		}else {
			this.energy -= energy;
			return true;
		}
	}

	@Override
	public void update() {		
		updateCount++;
		energy = updateCount/60;
	}

	@Override
	public void input(MouseHandle mosue) {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Graphics2D g) {

	    g.setColor(Color.black);
	    g.fillRect(0, GamePanel.height*28/30, GamePanel.width * energy/10, GamePanel.height/20);
	
	}

}
