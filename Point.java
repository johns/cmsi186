public class Point {
	double up;
	double down;
	double left;
	double right;
	double x;
	double y;

	public Point (Rectangle rectangle) {
		this.up = rectangle.up;
		this.down = rectangle.down;
		this.left = rectangle.left;
		this.right = rectangle.right;
		this.x = Math.random() * (right - left) + left;
		this.y = Math.random() * (up - down) + down;
	}
}