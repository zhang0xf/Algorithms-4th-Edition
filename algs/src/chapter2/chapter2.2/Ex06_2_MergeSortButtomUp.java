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

// 自底向上的归并排序访问数组的次数，并画出随数组元素个数变化的曲线。

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Ex06_2_MergeSortButtomUp {

    private static Comparable[] aux;
    private static int count;

    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz += sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(N, lo + sz + sz - 1));
            }
        }
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;

        for (int k = 0; k <= hi; k++) {
            count++;
            aux[k] = a[k];
        }

        // 归并到a[] 即 a[lo] ~ a[hi]
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], aux[j])) {
                count += 2;
                a[k] = aux[i++];
            }
            else a[k] = a[j++];
            count += 2;
        }
    }

    public static int getCount() {
        return count;
    }

    // 参考网页:https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/StdDraw.html#setXscale--
    // public static void setXscale()
    // Sets the x-scale to be the default (between 0.0 and 1.0).

    // public static void setYscale()
    // Sets the y-scale to be the default (between 0.0 and 1.0).

    // public static void setPenRadius(double radius)
    // Sets the radius of the pen to the specified size. The pen is circular, so that lines have rounded ends, and when you set the pen radius and draw a point, you get a circle of the specified radius. The pen radius is not affected by coordinate scaling.
    //         Parameters:
    // radius - the radius of the pen
    // Throws:
    // IllegalArgumentException - if radius is negative, NaN, or infinite

    // public static void point(double x,
    //                          double y)
    // Draws a point centered at (x, y). The point is a filled circle whose radius is equal to the pen radius. To draw a single-pixel point, first set the pen radius to 0.
    // Parameters:
    // x - the x-coordinate of the point
    // y - the y-coordinate of the point
    // Throws:
    // IllegalArgumentException - if either x or y is either NaN or infinite

    public static void main(String[] args) {
        int p = 0;
        StdDraw.setXscale(0, 10);
        StdDraw.setYscale(0, Math.pow(2, 11));  // the range can not to be too small or too big!
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.01);
        while (Math.pow(2, p) <= 512) {
            Double[] a = new Double[(int) Math.pow(2, p)];
            for (int i = 0; i < Math.pow(2, p); i++) {
                a[i] = StdRandom.uniform();
            }
            sort(a);
            StdOut.println("p = " + p + ", " + "count = " + getCount());
            StdDraw.point(p, getCount());
            p++;
        }
    }
}
