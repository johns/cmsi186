public class RandomizedAreaEstimator {
	
	public static void main(String[] args) {
		double points = 1000000;
    	double insidePoints = 0;
		int numberOfCircles = 0;
		int numberOfTriangles = 0;
		int circlesIndex = 0;
		int trianglesIndex = 0;

		for (int i = 0; i < args.length - 1; i++) {
			if (args[i].equals("circle")) {
				numberOfCircles++;
			}
			else if (args[i].equals("triangle")) {
				numberOfTriangles++;
			}
		}

		Circle[] circles = new Circle[numberOfCircles];
		Triangle[] triangles = new Triangle[numberOfTriangles];

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("circle")) {
				circles[circlesIndex] = new Circle(Double.parseDouble(args[i+1]), Double.parseDouble(args[i+2]), Double.parseDouble(args[i+3]));
				circlesIndex++;
			}
			else if (args[i].equals("triangle")) {
				triangles[trianglesIndex] = new Triangle(Double.parseDouble(args[i+1]), Double.parseDouble(args[i+2]), Double.parseDouble(args[i+3]), Double.parseDouble(args[i+4]), Double.parseDouble(args[i+5]), Double.parseDouble(args[i+6]));
				trianglesIndex++;
			}
		}
		Rectangle box = getBoundingBox(circles, triangles);
		eachShapeArea(box, circles, triangles);
		unionShapeArea(box, circles, triangles);
		intersectShapeArea(box, circles, triangles);
		notIntersectShapeArea(box, circles, triangles);
	}

	public static Rectangle getBoundingBox(Circle[] circles, Triangle[] triangles) {
		double left = 0;
        double right = 0;
        double down = 0;
        double up = 0;

        for (int i = 0; i < circles.length; i++) {
        	Rectangle rectCirc = circles[i].getBoundingBox();
        	if (rectCirc.left < left) {
        		left = rectCirc.left;
        	}
        	if (rectCirc.right > right) {
        		right = rectCirc.right;
        	}
        	if (rectCirc.down < down) {
        		down = rectCirc.down;
        	}
        	if (rectCirc.up > up) {
        		up = rectCirc.up;
        	}
        }
        for (int i = 0; i < triangles.length; i++) {
        	Rectangle rectTria = triangles[i].getBoundingBox();
        	if (rectTria.left < left) {
        		left = rectTria.left;
        	}
        	if (rectTria.right > right) {
        		right = rectTria.right;
        	}
        	if (rectTria.down < down) {
        		down = rectTria.down;
        	}
        	if (rectTria.up > up) {
        		up = rectTria.up;
        	}
        }
        return new Rectangle(left, right, down, up);
	}

	public static void eachShapeArea (Rectangle box, Circle[] circles, Triangle[] triangles) {
		for (int i = 0; i < circles.length; i++) {
			double points = 1000000;
			double insidePoints = 0;
			for (int j = 0; j < points; j++) {
				Point p = new Point(box);
				if (circles[i].isInside(p.x, p.y)) {
					insidePoints++;
				}
			}
			System.out.println("Area of cicle " + (i+1) + " is " + ((insidePoints * box.getArea()) / points));
		}

		for (int i = 0; i < triangles.length; i++) {
			double points = 1000000;
			double insidePoints = 0;
			for (int j = 0; j < points; j++) {
				Point p = new Point(box);
				if (triangles[i].isInside(p.x, p.y)) {
					insidePoints++;
				}
			}
			System.out.println("Area of triangle " + (i+1) + " is " + ((insidePoints * box.getArea()) / points));
		}
	}

	public static void unionShapeArea (Rectangle box, Circle[] circles, Triangle[] triangles) {
		double points = 1000000;
		double insidePoints = 0;
		boolean insideAShape = false;
		for (int i = 0; i < points; i ++) {
			Point p = new Point(box);
			insideAShape = false;
			for (int j = 0; j < circles.length; j++) {
				if (circles[j].isInside(p.x, p.y)) {
					insideAShape = true;
				}
			}

			for (int k = 0; k < triangles.length; k++) {
				if (triangles[k].isInside(p.x, p.y)) {
					insideAShape = true;
				}
			}

			if (insideAShape) {
				insidePoints++;
			}
		}
		System.out.println("The area of the union of all shapes is " + ((insidePoints * box.getArea()) / points));
	}

	public static void intersectShapeArea (Rectangle box, Circle[] circles, Triangle[] triangles) {
		double points = 1000000;
		double insidePoints = 0;
		boolean insideAllShapes = true;
		for (int i = 0; i < points; i ++) {
			Point p = new Point(box);
			insideAllShapes = true;
			for (int j = 0; j < circles.length; j++) {
				if (!(circles[j].isInside(p.x, p.y))) {
					insideAllShapes = false;
				}
			}

			for (int k = 0; k < triangles.length; k++) {
				if (!(triangles[k].isInside(p.x, p.y))) {
					insideAllShapes = false;
				}
			}

			if (insideAllShapes) {
				insidePoints++;
			}
		}

		if (insidePoints == 0) {
			System.out.println("There is no area of the intersection of all shapes because not all shapes intersect.");
		}
		else {
			System.out.println("The area of the intersection of all shapes is " + ((insidePoints * box.getArea()) / points));
		}
	}

	public static void notIntersectShapeArea (Rectangle box, Circle[] circles, Triangle[] triangles) {
		double points = 1000000;
		double insidePoints = 0;
		int insideShapes = 0;
		for (int i = 0; i < points; i ++) {
			Point p = new Point(box);
			insideShapes = 0;
			for (int j = 0; j < circles.length; j++) {
				if (circles[j].isInside(p.x, p.y)) {
					insideShapes++;
				}
			}

			for (int k = 0; k < triangles.length; k++) {
				if (triangles[k].isInside(p.x, p.y)) {
					insideShapes++;
				}
			}

			if (insideShapes == 1) {
				insidePoints++;
			}
		}

		if (insidePoints == 0) {
			System.out.println("There is no area of the individual shapes that do not intersect any other shape because all shapes intersect.");
		}
		else {
			System.out.println("The area of the individual shapes that do not intersect any other shape is " + ((insidePoints * box.getArea()) / points));
		}
	}
}