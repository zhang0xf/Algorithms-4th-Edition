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

// 动态查找中位数（用一个面向最大元素的堆，和一个面向最小元素的堆）

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Ex30_MedianDynamicallyFind {

    private enum KIND {
        MAX, MIN
    }

    private Double[] pq1;  // max优先队列
    private Double[] pq2;  // min优先队列
    private int N1; // max队列元素个数
    private int N2; // min队列元素个数
    private Double v;  // 中位数

    public Ex30_MedianDynamicallyFind(int N) {
        pq1 = new Double[N + 1];
        pq2 = new Double[N + 1];
    }

    public boolean less(int i, int j) {
        return pq1[i].compareTo(pq1[j]) < 0;
    }

    public boolean less(Double v, Double w) {
        return v.compareTo(w) < 0;
    }

    public boolean greater(int i, int j) {
        return pq2[i].compareTo(pq2[j]) > 0;
    }

    public void exch(int i, int j, KIND kind) {
        if (kind == KIND.MAX) {
            Double t = pq1[i];
            pq1[i] = pq1[j];
            pq1[j] = t;
        }
        else if (kind == KIND.MIN) {
            Double t = pq2[i];
            pq2[i] = pq2[j];
            pq2[j] = t;
        }
    }

    public void swim(int k, KIND kind) {
        if (kind == KIND.MAX)
            while (k > 1 && less(k / 2, k)) {
                exch(k / 2, k, KIND.MAX);
                k = k / 2;
            }
        else if (kind == KIND.MIN)
            while (k > 1 && greater(k / 2, k)) {
                exch(k / 2, k, KIND.MIN);
                k = k / 2;
            }
    }

    public void sink(int k, KIND kind) {
        if (kind == KIND.MAX) {
            while (2 * k <= N1) {
                int j = 2 * k;
                while (j < N1 && less(j, j + 1)) j++;
                if (!less(k, j)) break;
                exch(k, j, KIND.MAX);
                k = j;
            }
        }
        else if (kind == KIND.MIN) {
            while (2 * k <= N2) {
                int j = 2 * k;
                while (j < N2 && greater(j, j + 1)) j++;
                if (!greater(k, j)) break;
                exch(k, j, KIND.MAX);
                k = j;
            }
        }
    }

    public void insertMax(Double v) {
        pq1[++N1] = v;
        swim(N1, KIND.MAX);
    }

    public void insertMin(Double v) {
        pq2[++N2] = v;
        swim(N2, KIND.MIN);
    }

    public Double delMax() {
        Double max = pq1[1];
        exch(1, N1--, KIND.MAX);
        pq1[N1 + 1] = null;
        sink(1, KIND.MAX);
        return max;
    }

    public Double delMin() {
        Double min = pq2[1];
        exch(1, N2--, KIND.MIN);
        pq2[N2 + 1] = null;
        sink(1, KIND.MIN);
        return min;
    }

    public Double findMedian(Double[] a) {
        v = a[0];
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], v))
                insertMax(a[i]);
            else
                insertMin(a[i]);
        }

        while (Math.abs(N1 - N2) > 1) {
            if (N1 > N2) {
                insertMin(v);
                v = delMax();
            }
            else if (N2 > N1) {
                insertMax(v);
                v = delMin();
            }
        }

        if (N1 == N2) return v;
        else if (N1 > N2) return (v + delMax()) / 2;
        else return (v + delMin()) / 2;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }
        Ex30_MedianDynamicallyFind mdf = new Ex30_MedianDynamicallyFind(N);
        Double v = mdf.findMedian(a);
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
        StdOut.println("median = " + v);
    }
}
