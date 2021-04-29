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

// 代码有错！
// 五取样切分
// 随机5个元素，并取中位数进行切分的快速排序！
// use with QuickSort.java

//注意：本代码为错误代码，为了让自己记住为什么会错！（调试这段代码花费了大量时间）

//最后结果：只有前几个元素（大概三个）元素的顺序有问题，后面的顺序都正确！尚不明确具体的错误在哪里？

// google查找解决方案：使用辅助数组存放5个中位数元素，但是排序结果测试之后依然不对，不知道他会什么上传？

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Ex19_QuickSort5Sampling {

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    //避免输入依赖
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    // 对于排序来说，不存在取偶数个数的中位数，因为平均值可能不存在于数组中！
    public static int median(Comparable[] a, int lo, int hi) {
        // 如果元素个数<5，返回lo
        // 不把这种情况放在函数partition中！
        if (lo + 5 > hi + 1)
            return lo;

        int m = lo;
        while (m < lo + 5)
            exch(a, StdRandom.uniform(lo, hi + 1), m++);
        // show(a);
        Arrays.sort(a, lo, hi + 1);
        // StdOut.println("after sort");
        // show(a);
        return 2;
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        exch(a, lo, median(a, lo, hi));
        Comparable v = a[lo];
        // StdOut.println("final array exch");
        // show(a);

        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
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
        // show(a);
    }
}
