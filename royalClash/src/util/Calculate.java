package util;

public class Calculate {
	public static int dis(float x1, float y1, float x2, float y2) {
		return (int) ((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
	}
	
	public static int dis(Vector2f p1, Vector2f p2) {
		return (int) ((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y));
	}

	public static Vector2f dir(float x1, float y1, float x2, float y2) {
		float offset = (float) Math.sqrt(dis(x1,y1,x2,y2));
		return new Vector2f((x1-x2)/offset,(y1-y2)/offset);
	}
	
	public static Vector2f dirWithSpeed(float x1, float y1, float x2, float y2, int vel) {
		float offset = (float) Math.sqrt(dis(x1,y1,x2,y2));
		return new Vector2f((x1-x2)/offset*vel,(y1-y2)/offset*vel);
	}
	
	public static Boolean inside(int width, int height, Vector2f box, Vector2f target, int size) {
		if((target.x <= box.x+width/2+size && target.x >= box.x-width/2-size) && (target.y <= box.y+height/2+size && target.y >= box.y-height/2-size)) {
			return true;
		}else return false;
	}
	
}
