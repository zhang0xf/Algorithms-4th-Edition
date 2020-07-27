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

// 两个计数位置有何区别？（见下文分析！）

public class ShellSortRatio {

    private static int count; // 递增序列每个元素带来的比较次数！

    private static boolean less(Comparable v, Comparable w) {
        count++;    // 这里才是正确的计数位置！
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3)
            h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h/*, count++*/) {
                    // count++; 这里统计的是交换的次数，而非比较的次数
                    // 条件j >= h若成立，但less比较不成立，不会进行计数，那么就遗漏了比较的次数！所以这种计数方式是错误的！
                    exch(a, j, j - h);
                }
            }
            StdOut.println("count = " + count);
            StdOut.println("Ratio = " + count / N);
            h = h / 3;
            count = 0;
        }
    }

    public static void main(String[] args) {
        // array size >= 10^2 and Increase in powers of 10
        int N = Integer.parseInt(args[0]);
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }

        sort(a);
        assert isSorted(a);
        // show(a);
    }
}
