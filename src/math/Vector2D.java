package math;

public class Vector2D {
	public float x;
	public float y;
	
	private float maxMag = (float) Double.POSITIVE_INFINITY;
	
	public Vector2D() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D(Vector2D vec) {
		this.x = vec.x;
		this.y = vec.y;
	}
	
	public void mult(float multiplier) {
		if(mag() < maxMag) {
			this.x *= multiplier;
			this.y *= multiplier;
		}
		
	}
	
	public Vector2D multReturn(float multiplier) {
		return new Vector2D(this.x * multiplier, this.y * multiplier);
	}
	
	public void div(float multiplier) {
		if(multiplier != 0) {
			if(mag() < maxMag) {
				this.x /= multiplier;
				this.y /= multiplier;
			}
		} else {
			System.out.println("zort");
		}
		
	}
	public Vector2D divReturn(float multiplier) {
		return new Vector2D(this.x / multiplier, this.y / multiplier);
	}
	
	public void add(Vector2D vec) {
		if(mag() < maxMag) {
			this.x += vec.x;
			this.y += vec.y;
		}
		else {
			this.setMag(maxMag);
		}
	}
	public static Vector2D add(Vector2D vec1, Vector2D vec2) {
		return new Vector2D(vec1.x + vec2.x, vec1.y + vec2.y);
	}
	
	public void sub(Vector2D vec) {
		if(mag() < maxMag) {
			this.x -= vec.x;
			this.y -= vec.y;
		}
		else {
			this.setMag(maxMag);
		}
	}
	public static Vector2D sub(Vector2D vec1, Vector2D vec2) {
		return new Vector2D(vec1.x - vec2.x, vec1.y - vec2.y);
	}
	
	public float mag() {
		return (float) Math.sqrt(x * x + y * y);
	}
	
	public void normalize() {
		float m = mag();
		div(m);
	}
	
	public void limit(float maxMag) {
		this.maxMag = maxMag;
	}
	
	public void resetLimit() {
		this.maxMag = (float) Double.POSITIVE_INFINITY;
	}
	
	public void setMag(float multiplier) {
		normalize();
		mult(multiplier);
	}
	
	public static double distance(Vector2D vec1, Vector2D vec2) {
		return vec1.distance(vec2);
	}
	
	public double distance(Vector2D vec) {
		double xD = vec.x - this.x;
		double yD = vec.y - this.y;
		return Math.sqrt(xD * xD + yD * yD);
	}
	
	public Vector2D copy() {
		return new Vector2D(this.x , this.y);
	}
	public void setVector2D(Vector2D newVec) {
		this.x = newVec.x;
		this.y = newVec.y;
	}
}
