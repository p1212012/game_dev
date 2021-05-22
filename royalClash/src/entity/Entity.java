package entity;

import java.awt.Graphics2D;

import states.Main;
import util.MouseHandle;
import util.Vector2f;

public abstract class Entity {
	
	private final int Up = 0;
	private final int Down = 1;
	private final int Right = 2;
	private final int Left = 3;
	
	private final int sky = 0;
	private final int ground = 1;
	private final int tower = 2;
	
	public Vector2f pos;
	protected int delayDamage;
	protected boolean attackReady;
	protected boolean side;
	protected boolean woodHit;
	protected int kind;
	protected int size;
	protected Vector2f dir;
	protected int attackCooldown;
	protected int health;
	protected int damage;
	protected int prepareToAttack;
	protected int updateTimes;
	protected int range;
	protected float speed;
	protected float deacc;
	protected float dirX;
	protected float dirY;
	protected final float UTBD = 30;// update times before detect
	
	public Entity(Vector2f position, int size, int health, int attackCooldown, int speed, int damage, boolean side, int kind, int range) {
		pos = position;
		this.range = range;
		this.size = size;
		this.damage = damage;
		this.health = health;
		this.attackCooldown = attackCooldown*60;
		this.speed = speed;
		this.kind = kind;
		this.side = side;
		woodHit = false;
		updateTimes = 0;
		prepareToAttack = 0;
		attackReady = false;
		setDir(new Vector2f(0,0));
	}
	
	public int gethurt(int damage) {
		health -= damage;
		return health;
	}
	
	protected void setDir(Vector2f vec) {
		dir = vec;
		dirX = vec.x;
		dirY = vec.y;
	}
	
	public int returnHealth() {
		return health;
	}
	
	public float getSize() {return size;}
	
	public abstract void update();
	public abstract void render(Graphics2D g) ;


















}
