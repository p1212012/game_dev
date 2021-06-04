package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import states.Main;
import util.Calculate;
import util.Vector2f;

public class MinerGround {
	private Vector2f Pos;
	private Vector2f Dir;
	private Vector2f Dest;
	private ArrayList<Image> animation = new ArrayList<Image>();;
	private int target;
	private int size;
	private int dis;
	private int speed;
	private int explode = -1;
	public boolean kill;
	private boolean side;
	
	public MinerGround(Vector2f Pos, Vector2f destination, int size,int speed, boolean side) {
		this.size = size;
		this.Pos = Pos;
		this.speed = speed;
		this.Dest = destination;
		animation.add(new ImageIcon("ground.png").getImage());
		kill = false;
		this.side = side;
		if(side) target = 0;
		else target = 1;
	}
	
	public void update() {
		if(!kill && explode < 0) {
			Dir = Calculate.dirWithSpeed(Pos.x, Pos.y, Dest.x, Dest.y, speed);
			dis = (int) Math.sqrt(Calculate.dis(Pos, Dest));
			if(dis < 10) {
				Main.EMT.get(target).PushEntity(6, Pos, side);
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
		g.drawImage(animation.get(0),(int)Pos.x-size/2, (int)Pos.y-size/2,size,size,null);
	}
	
}
