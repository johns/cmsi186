public class RandomizedIntegrator {
    double points = 1000000;
    double insideCurvePositive = 0;
    double insideCurveNegative = 0;

    public static void main(String[] args) {
        MathFunctions functions = new MathFunctions();
        MathOperation func = functions.polynomial;
        Rectangle box = new Rectangle();
        RandomizedIntegrator integrator = new RandomizedIntegrator();
        double[] coefficients = {};
        double[] bounds = new double[2];
        bounds[0] = Double.parseDouble(args[args.length-2]);
        bounds[1] = Double.parseDouble(args[args.length-1]);

        if (args[0].equals("poly")) {
            if (args.length != 6) {
                throw new ArrayIndexOutOfBoundsException("Please input 6 arguments ('poly', the coefficients of the function, and the left and right bounds.");
            }
            coefficients = new double[args.length-3];
            for (int i = 0; i < args.length-3; i++) {
                coefficients[i] = Double.parseDouble(args[i+1]);
            }
            func = functions.polynomial;
            box = integrator.getBoundingBox(func, bounds, coefficients);
        }
        else if (args[0].equals("sin")) {
            if (args.length != 3) {
                throw new ArrayIndexOutOfBoundsException("Please input 3 arguments ('sin' and the left and right bounds.");
            }
            func = functions.sin;
            box = new Rectangle(bounds[0], bounds[1], -1.0, 1.0);
        }
        else if (args[0].equals("cos")) {
            if (args.length != 3) {
                throw new ArrayIndexOutOfBoundsException("Please input 3 arguments ('cos' and the left and right bounds.");
            }
            func = functions.cos;
            box = new Rectangle(bounds[0], bounds[1], -1.0, 1.0);
        }
        else if (args[0].equals("log")) {
            if (args.length != 3) {
                throw new ArrayIndexOutOfBoundsException("Please input 3 arguments ('log' and the left and right bounds.");
            }
            func = functions.log;
            box = integrator.getBoundingBox(func, bounds, coefficients);
        }
        else if (args[0].equals("exp")) {
            if (args.length != 3) {
                throw new ArrayIndexOutOfBoundsException("Please input 3 arguments ('exp' and the left and right bounds.");
            }
            func = functions.exp;
            box = integrator.getBoundingBox(func, bounds, coefficients);
        }
        else if (args[0].equals("sqrt")) {
            if (args.length != 3) {
                throw new ArrayIndexOutOfBoundsException("Please input 3 arguments ('sqrt' and the left and right bounds.");
            }
            func = functions.sqrt;
            box = integrator.getBoundingBox(func, bounds, coefficients);
        }
        System.out.println(integrator.generatePoints(func, box, coefficients));
    }

    public Rectangle getBoundingBox (MathOperation func, double[] bounds, double[] coefficients) {
        double left = bounds[0];
        double right = bounds[1];
        double down = 0;
        double up = 0;

        for (double i = left; i < right; i += 0.00001) {
            if (func.eval(i, coefficients) < down) {
                down = func.eval(i, coefficients);
            }

            if (func.eval(i, coefficients) > up) {
                up = func.eval(i, coefficients);
            }
        }
        return new Rectangle(left, right, down, up);
    }

    public double generatePoints (MathOperation func, Rectangle box, double[] coefficients) {
        for (int i = 0; i < points; i++) {
            Point p = new Point(box);
            if (p.y >= 0) {
                if (p.y <= func.eval(p.x, coefficients)) {
                    insideCurvePositive++;
                }
            }

            if (p.y < 0) {
                if (p.y >= func.eval(p.x, coefficients)) {
                    insideCurveNegative++;
                }
            }
        }
        return ((insideCurvePositive - insideCurveNegative) * box.getArea())/ points;
    }
}