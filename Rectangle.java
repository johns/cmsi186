public class Rectangle {
	double up;
	double down;
	double left;
	double right;

	public Rectangle () {
	}

	public Rectangle (double left, double right, double down, double up) {
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
	}

	public double getArea () {
		return (right - left) * (up - down);
	}
}