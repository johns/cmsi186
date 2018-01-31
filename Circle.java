public class Circle {
	double x;
	double y;
	double radius;

	public Circle (double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	public boolean isInside (double x2, double y2) {
		if (((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y)) < (radius * radius)) {
			return true;
		}
		else {
			return false;
		}
	}

	public Rectangle getBoundingBox () {
		return new Rectangle (x - radius, x + radius, y - radius, y + radius);
	}
}