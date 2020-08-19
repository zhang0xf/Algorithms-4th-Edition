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

// 搜索第k小的元素
public class SearchKthSmall {

    public static void show(Comparable[] s) {
        for (int i = 0; i < s.length; i++) {
            StdOut.print(s[i] + " ");
        }
        StdOut.println();
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // 插入排序
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];   // 切分元素
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    // 使用递归实现select(需要a有序)
    public static Comparable select(Comparable[] a, int k, int lo, int hi) {
        if (lo < 0 || hi > a.length || k < 0 || k > a.length)
            return null;
        sort(a);
        int j = partition(a, lo, hi);
        if (j == k) return a[k];
        else if (j > k) select(a, k, lo, j - 1);
        else select(a, k, j + 1, hi);
        return a[k];
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }

        StdOut.println(" result ：" + SearchKthSmall.select(a, 2, 0, N - 1));
        show(a);
    }
}
