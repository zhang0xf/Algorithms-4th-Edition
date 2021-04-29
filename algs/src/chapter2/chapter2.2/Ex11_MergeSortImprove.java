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

// 改进归并排序：
// 1.加快小数组的排序速度
// 2.检测数组是否已经有序
// 3.通过在递归中交换参数来避免数组复制（在之前的练习中也有完成）

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Ex11_MergeSortImprove {

    private static final int CUTOFF = 7;  // cutoff to insertion sort

    private static int count = 0;

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void sort(Comparable[] a) {
        // Comparable[] aux = new Comparable[a.length];
        Comparable[] aux = a.clone();   // 作者写法！关于clone()函数需要Google一下！
        sort(a, aux, 0, a.length - 1);
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void insertsort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j >= 1 && less(a[j], a[j - 1]); j -= 1) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        if (lo + CUTOFF >= hi) {
            insertsort(a);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);  // 递归接口出来，a一定是有序的，aux有序吗？
        sort(a, aux, mid + 1, hi);

        if (less(a[mid], a[mid + 1])) { // 作者这里的处理方法及其不一样，且尚未看懂！
            count++;
            return;
        }

        merge(a, aux, lo, mid, hi);
    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {    // 这里不是复制整个数组
            aux[k] = a[k];
        }

        // 归并到a[] 即 a[lo] ~ a[hi]
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = a[j++];
        }
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
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform();
        }

        show(a);
        Ex11_MergeSortImprove.sort(a);
        show(a);
        StdOut.println("count = " + count);
    }
}
