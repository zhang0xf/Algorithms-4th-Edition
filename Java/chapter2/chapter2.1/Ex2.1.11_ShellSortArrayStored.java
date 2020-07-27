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

// 递增序列预先计算并存储在一个数组中
// use with Date.java file

public class ShellSortArrayStored {

    private static int[] s;
    private static int count;

    private static void createArray(int N) {
        s = new int[N];
        int h = 1;
        for (int i = 0; i < N && (h < N / 3); i++) {
            s[i] = h;
            h = 3 * h + 1;
            count++;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
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
        createArray(N);
        for (int m = count - 1; m >= 0; m--) {
            int h = s[m];
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
        }
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Date[] d = new Date[N];
        for (int i = 0; i < N; i++) {
            int year = StdRandom.uniform(2000, 2100);
            int month = StdRandom.uniform(0, 13);
            int day = StdRandom.uniform(0, 31);
            d[i] = new Date(day, month, year);
        }
        show(d);
        sort(d);
        assert isSorted(d);
        show(d);

    }
}
