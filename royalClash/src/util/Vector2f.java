package util;

public class Vector2f {
	public float x;
	public float y;
	
	public Vector2f() {
		x = 0;
		y = 0;
		
	}
	
	public Vector2f add(Vector2f vec) {
		return new Vector2f(x+vec.x, y+vec.y);
	}
	
	public Vector2f(Vector2f pos) {
		new Vector2f(pos.x, pos.y);
	}
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void addX(float i) {x += i;}
	public void addY(float i) {y += i;}
	
	public void setX(float i) {x = i;}
	public void setY(float i) {y = i;}
	
	
	public void setVector(Vector2f vec) {
		this.x = vec.x;
		this.y = vec.y;
	}
	
	public void setVector(float x, float y) {
		this.x = x;
		this.y = y;
	}
		
	@Override
	public String toString() {
		return x + ", " + y;
	}
	
	
	
	
	
}
