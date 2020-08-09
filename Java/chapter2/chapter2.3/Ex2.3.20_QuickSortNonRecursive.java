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

// 快速排序的非递归解决方案：使用栈和循环实现！
// 编译器使用栈实现递归，我们也可以直接使用栈去模拟递归的过程

public class QuickSortNonRecursive {

    private static Stack<Integer> stack;

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void sort(Comparable[] a) {
        stack = new Stack<Integer>();
        int lo = 0;
        int hi = a.length - 1;

        // 栈记录数组范围，在弹出的时候相当于得到子数组！
        stack.push(lo); // 左边界
        stack.push(hi);  // 右边界

        while (!stack.isEmpty()) {
            hi = stack.pop();
            lo = stack.pop();
            if (lo >= hi) continue;    // only one elements! we need go to next sub array.

            int i = lo;
            int j = hi + 1;
            Comparable pivot = a[lo];

            while (true) {
                while (less(a[++i], pivot)) if (i == hi) break;
                while (less(pivot, a[--j])) if (j == lo) break;
                if (i >= j) break;
                exch(a, i, j);
            }
            exch(a, lo, j);

            // 此时j的位置即切分元素

            // 左子数组
            if (lo < j) {
                stack.push(lo);
                stack.push(j - 1);
            }

            // 右子数组
            if (j < hi) {
                stack.push(j + 1);
                stack.push(hi);
            }
        }
    }

    public static void show(Comparable[] a) {
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
