package gna;

public class Point {
	private final double x, y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
	  return y;
	}
	
	public double distanceTo(Point other) {
	  double dx = x - other.getX();
	  double dy = y - other.getY();
	  return Math.sqrt(dx*dx + dy*dy);
	}
	
	@Override
	public String toString() {
	  return "(" + x + ", " + y + ")";
	}
}
