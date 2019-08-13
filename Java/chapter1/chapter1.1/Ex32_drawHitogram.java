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

// Draw Histogram

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Ex32 {

    // Test Stddraw
    public static void testStddraw() {
        int n = 50;
        double[] arr = new double[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform();
        }
        for (int i = 0; i < arr.length; i++) {
            double x = 1.0 * i / n;      //直方图x轴起点
            double y = arr[i] / 2.0;     //直方图y轴起点
            double rw = 0.5 / n;         //直方图x轴的宽度
            double rh = arr[i] / 2.0;    //直方图y轴的高度
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }

    // calculate Hitogram
    public static double[] calculateHitogram(int n, double left, double right, double[] arr) {
        double[] resultArray = new double[n];
        double[] max = new double[n];
        double[] min = new double[n];
        for (int i = 0; i < n; i++) {
            min[i] = left + (right - left) / n * i;
            max[i] = left + (right - left) / n * (i + 1);
        }
        for (int i = 0; i < arr.length; i++) {
            int lo = 0;
            int hi = n - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (arr[i] >= max[mid])
                    lo = mid + 1;
                else if (arr[i] < min[mid])
                    hi = mid - 1;
                else if (arr[i] >= min[mid] && arr[i] < max[mid]) {
                    resultArray[mid]++;
                    break;
                }
            }
        }
        return resultArray;
    }

    // Draw Hitogram
    public static void drawHitogram(int n, double[] arr) {
        for (int i = 0; i < n; i++) {
            double x = 1.0 * i / n;
            double y = arr[i] / 50.0;
            double rw = 0.5 / n;
            double rh = arr[i] / 50.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }

    public static void main(String[] args) {

        //Ex32.testStddraw();

        int n = parseInt(args[0]);
        double left = parseDouble(args[1]);
        double right = parseDouble(args[2]);

        // initial data
        double[] arr = new double[200];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform(left, right);  // 产生double范围随机数
        }

        // calculate Hitogram
        double[] rArray = Ex32.calculateHitogram(n, left, right, arr);

        // draw Hitogram
        Ex32.drawHitogram(n, rArray);
    }
}
