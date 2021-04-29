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

// 优先队列的初级实现：有序数组

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex03_1_PQByOrderArray<Key extends Comparable<Key>/*, Value*/> {

    private Key[] a;
    private int N;

    public Ex03_1_PQByOrderArray() {
        a = (Key[]) new Comparable[1];
        // b = (Value[]) new Object[1];
    }

    public Ex03_1_PQByOrderArray(int max) {
        a = (Key[]) new Comparable[max];
        // b = (Value[]) new Object[max];
    }

    public boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    public void exch(int i, int j) {
        Key t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    public int size() {
        return N;
    }

    public void resize(int max) {
        if (max <= a.length)
            // throw exception
            return;
        Key[] temp = (Key[]) new Comparable[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    // 在插入新元素的时候确保数组是有序的：较大的元素右移
    public void insert(Key v) {
        if (N == a.length) resize(a.length * 2);
        a[N++] = v;
        int i = N - 1;
        for (int j = i; j >= 0 && less(a[j], a[j - 1]); j--) {
            exch(j, j - 1);
        }
    }

    // 返回最大元素
    public Key max() {
        return a[N - 1];
    }

    // 删除最大元素
    public Key delMax() {
        return a[--N];
    }

    public static void main(String[] args) {
        // 打印输入流中最大的M行
        int M = Integer.parseInt(args[0]);
        Ex03_1_PQByOrderArray<Transaction> pq
                = new Ex03_1_PQByOrderArray<Transaction>(M + 1);

        while (StdIn.hasNextLine()) {
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size() > M)
                pq.delMax();    // 最小的M个元素在优先队列中
        }
        Stack<Transaction> stack = new Stack<Transaction>();
        while (!pq.isEmpty()) {
            stack.push(pq.delMax());
        }
        // 打印
        for (Transaction t : stack)
            StdOut.println(t);
    }
}
