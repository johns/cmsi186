public class Triangle {
	double x1;
	double x2;
	double x3;
	double y1;
	double y2;
	double y3;

	public Triangle (double x1, double y1, double x2, double y2, double x3, double y3) {
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
		this.y1 = y1;
		this.y2 = y2;
		this.y3 = y3;
	}

	public Rectangle getBoundingBox () {
		double left = x1;
		double right = x1;
		double down = y1;
		double up = y1;

		if (x2 < left) {
			left = x2;
		}
		if (x3 < left) {
			left = x3;
		}
		if (x2 > right) {
			right = x2;
		}
		if (x3 > right) {
			right = x3;
		}
		if (y2 < down) {
			down = y2;
		}
		if (y3 < down) {
			down = y3;
		}
		if (y2 > up) {
			up = y2;
		}
		if (y3 > up) {
			up = y3;
		}

		return new Rectangle (left, right, down, up);
	}

	public boolean isInside (double x, double y) {
		double alpha = ((y2 - y3)*(x - x3) + (x3 - x2)*(y - y3)) / ((y2 - y3)*(x1 - x3) + (x3 - x2)*(y1 - y3));
		double beta = ((y3 - y1)*(x - x3) + (x1 - x3)*(y - y3)) / ((y2 - y3)*(x1 - x3) + (x3 - x2)*(y1 - y3));
		double gamma = 1.0 - alpha - beta;

		return (0 <= alpha && alpha <= 1 && 0 <= beta && beta <= 1 && 0 <= gamma && gamma <= 1);
	}
}