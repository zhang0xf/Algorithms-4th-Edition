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
// 简单多边形
// 更多代码细节见作者写的《Point2D.java》

import java.util.Arrays;
import java.util.Comparator;

public class SimplePolygon implements Comparable<SimplePolygon> {

    public static final Comparator<SimplePolygon> X_ORDER = new XOrder();
    public static final Comparator<SimplePolygon> Y_ORDER = new YOrder();
    public static final Comparator<SimplePolygon> ANGLE_ORDER = new Atan2Order();

    private static SimplePolygon p = new SimplePolygon(0.0, 0.0); // 最小点p
    private double x;
    private double y;

    public SimplePolygon(double x, double y) {
        if (Double.isNaN(x) || Double.isNaN(y)) throw new RuntimeException();   // 检测x和y是否为非数字值
        if (x == 0.0) this.x = 0.0; // 将-0.0转换为+0.0（？）
        else this.x = x;
        if (y == 0.0) this.y = 0.0;
        else this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    private double angleTo(SimplePolygon that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.atan2(dy, dx);
    }

    @Override

    public int compareTo(SimplePolygon that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }

    private static class XOrder implements Comparator<SimplePolygon> {
        @Override
        public int compare(SimplePolygon p, SimplePolygon q) {
            if (p.x < q.x) return -1;
            if (p.x > q.x) return +1;
            return 0;
        }
    }

    private static class YOrder implements Comparator<SimplePolygon> {
        @Override
        public int compare(SimplePolygon p, SimplePolygon q) {
            if (p.y < q.y) return -1;
            if (p.y > q.y) return +1;
            return 0;
        }
    }

    private static class Atan2Order implements Comparator<SimplePolygon> {
        @Override
        public int compare(SimplePolygon q1, SimplePolygon q2) {
            double angle1 = q1.angleTo(p);
            double angle2 = q2.angleTo(p);
            if (angle1 < angle2) return -1;
            else if (angle1 > angle2) return +1;
            else return 0;
        }
    }

    // 绘画点
    public void draw() {
        StdDraw.point(x, y);
    }

    // 绘画直线
    public void drawTo(SimplePolygon that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // 找到最小点
    public static void setMin(SimplePolygon point) {
        p.x = point.x;
        p.y = point.y;
    }

    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);
        StdDraw.setCanvasSize(800, 800);    // 画布大小
        StdDraw.setXscale(0, 100);  // x轴范围
        StdDraw.setYscale(0, 100);  // y轴范围
        StdDraw.setPenRadius(0.005);    // 笔半径
        StdDraw.enableDoubleBuffering();    // 双缓冲区，屏幕不闪烁
        StdDraw.setPenColor(StdDraw.BLACK);   // 笔颜色

        SimplePolygon[] points = new SimplePolygon[N];
        for (int i = 0; i < N; i++) {
            int x = StdRandom.uniform(100);
            int y = StdRandom.uniform(100);
            points[i] = new SimplePolygon(x, y);
            points[i].draw();
        }

        StdDraw.setPenColor(StdDraw.RED);
        Arrays.sort(points, Y_ORDER);   // 按照y轴排序
        double yMin = points[0].y();
        Arrays.sort(points, X_ORDER);   // 按照x轴排序
        double xMin = 0.0;
        for (int i = 0; i < N; i++) {
            if (points[i].y() == yMin) {
                xMin = points[i].x();
                break;
            }
        }
        setMin(new SimplePolygon(xMin, yMin));

        Arrays.sort(points, ANGLE_ORDER);
        for (int i = 0; i + 1 < N; i++) {
            points[i].drawTo(points[i + 1]);
        }
        points[N - 1].drawTo(points[0]);
        StdDraw.show();
    }
}
