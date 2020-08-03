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

// 对静态数组aux[]（辅助数组）进行优化，作为sort的局部变量和merge函数的参数

public class MergeSortTopDownArrayOptimization {

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        sort(a, aux, 0, N - 1);
    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;   // 代码应该具有鲁棒性！细节很重要不能忘记
        int N = a.length;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, 0, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;

        for (int k = 0; k <= hi; k++) {
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
        // 类的静态方法，不需要创建对象！和Math.pow(a,b)是一样的！这是很重要的设计思想！
        // 相当于设计成一个库函数，这也是需要对原先的静态数组aux优化的原因！
        MergeSortTopDownArrayOptimization.sort(a);
        show(a);
    }
}
