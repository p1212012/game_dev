package util;

public class Calculate {
	public static int dis(float x1, float y1, float x2, float y2) {
		return (int) ((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
	}

	public static Vector2f dir(float x1, float y1, float x2, float y2) {
		float offset = (float) Math.sqrt(dis(x1,y1,x2,y2));
		return new Vector2f((x1-x2)/offset,(y1-y2)/offset);
	}
}
