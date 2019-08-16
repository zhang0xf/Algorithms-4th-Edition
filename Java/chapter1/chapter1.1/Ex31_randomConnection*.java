/* *****************************************************************************
 *  Name:    Alan Turing
 *  NetID:   aturing
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

// random connection

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Ex31 {

    static class Point {
        private double x;
        private double y;

        public Point(double x, double y) {
            super();
            this.x = x;
            this.y = y;
        }
    }

    // test StdDraw point
    public static void drawPointTest() {
        // set pencil color
        StdDraw.setPenColor(StdDraw.BLACK);
        // set pencil size
        StdDraw.setPenRadius(0.5);
        // set point location(middle)
        StdDraw.point(0.5, 0.5);
    }

    // test StdDraw circle
    public static void drawCircleTest() {
        // set circle center and radius
        StdDraw.circle(0.5, 0.5, 0.4);
    }

    // test draw multipoint
    public static void drawMultPointTest(int n) {
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.BLACK);
        double angel = 360.0 / n;
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(0.5 + 0.5 * Math.cos(angel * i * Math.PI / 180),
                                  0.5 + 0.5 * Math.sin(angel * i * Math.PI / 180));
            StdDraw.point(points[i].x, points[i].y);
        }
    }

    public static void main(String[] args) {
        //Ex31.drawPointTest();
        //Ex31.drawCircleTest();
        int n = parseInt(args[0]);
        double p = parseDouble(args[1]);
        //Ex31.drawMultPointTest(n);
        double angel = 360.0 / n;
        StdDraw.setPenRadius(0.05);
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(0.5 + 0.5 * Math.cos(angel * i * Math.PI / 180),
                                  0.5 + 0.5 * Math.sin(angel * i * Math.PI / 180));
            StdDraw.point(points[i].x, points[i].y);
        }
        StdDraw.setPenColor(StdDraw.GRAY);
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (StdRandom.bernoulli(p)) {
                    StdDraw.line(points[i].x, points[i].y, points[j].x, points[j].y);
                }
            }
        }
    }
}
