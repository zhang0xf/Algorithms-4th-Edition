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

// 快速归并:去掉检测某半边是否用尽的代码！

public class QuicklyMergeSort {

    private static Comparable[] aux;

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {

        for (int k = lo; k <= mid; k++) // 不能是0，因为这是递归接口，必须是lo ~ hi
            aux[k] = a[k];
        // for (int k = hi; k >= mid + 1; k--)  // 作者的思路比较统一，都是从前向后遍历！见下面~
        //     aux[k] = a[k];

        for (int k = mid + 1; k <= hi; k++) {
            aux[k] = a[hi - k + mid + 1];
        }

        int i = lo; // 左子数组，i递增
        int j = hi; // 右子数组，j递减

        // 如果i或者j过界，那么将会变为最大值，不会再移动，且一定是最后一个元素进入到数组，所以j--和i++均不会越界！
        for (int k = lo; k <= hi; k++) {    // bug: "int k = 0"。一定要从lo开始，这个bug找了好久！
            if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j--];
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

        QuicklyMergeSort.sort(a);
        show(a);
    }
}
