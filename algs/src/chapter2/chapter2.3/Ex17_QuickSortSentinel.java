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

// 使用哨兵优化快速排序
// 具体分析见书本，内循环边界判定可以使用哨兵省去，且a[lo]天然时哨兵！只需再添加一个最大的哨兵！
// 哨兵元素到达哨兵时，是不满足while循环条件的，从而退出循环不会越界！
// 哨兵元素需要比较操作才能发挥作用，而while的条件就是在比较，所以不需要额外的比较操作了！

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Ex17_QuickSortSentinel {

    //避免输入依赖
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        // 找到最大值
        int largest = largest(a);
        exch(a, largest, a.length - 1);
        sort(a, 0, a.length - 1);
    }

    public static int largest(Comparable[] a) {
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            if (less(a[max], a[i]))
                max = i;
        }
        return max;
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        Comparable v = a[lo];   // 切分元素

        while (true) {
            while (less(a[++i], v)) ;
            while (less(v, a[--j])) ;
            
            // StdOut.println("i = " + i + "and j = " + j);

            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }
        show(a);
        sort(a);
        show(a);
    }
}
