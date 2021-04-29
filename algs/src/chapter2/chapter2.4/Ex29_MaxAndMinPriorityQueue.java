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

// 同时面向最大和最小元素的优先队列（使用两个堆）
// use with Transaction.java and Date.java

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex29_MaxAndMinPriorityQueue<Key extends Comparable<Key>> {

    public enum Heap {
        MAXHEAP, MINHEAP
    }

    private Key[] a;    // 面向最大元素的数组
    private Key[] b;    // 面向最小元素的数组
    private int M;
    private int N;
    private Key max;    // 最大值
    private Key min;    // 最小值

    public Ex29_MaxAndMinPriorityQueue(int max) {
        a = (Key[]) new Comparable[max + 1];
        b = (Key[]) new Comparable[max + 1];
    }

    public int sizeMax() {
        return M;
    }

    public int sizeMin() {
        return N;
    }

    public boolean isEmpty() {
        return 0 == M || 0 == N;
    }

    public boolean less(int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    public boolean greater(int i, int j) {
        return b[i].compareTo(b[j]) > 0;
    }

    public void exch(Key[] e, int i, int j) {
        Key t = e[i];
        e[i] = e[j];
        e[j] = t;
    }

    public void resize(int max, Heap kind) {
        Key[] temp = (Key[]) new Comparable[max + 1];
        if (kind == Heap.MAXHEAP) {
            for (int i = 0; i <= M; i++)
                temp[i] = a[i];
            a = temp;
        }
        else if (kind == Heap.MINHEAP) {
            for (int i = 0; i <= N; i++)
                temp[i] = b[i];
            b = temp;
        }
    }

    public void swim(int k, Heap kind) {
        if (kind == Heap.MAXHEAP)
            while (k > 1 && less(k / 2, k)) {
                exch(a, k / 2, k);
                k = k / 2;
            }
        else if (kind == Heap.MINHEAP)
            while (k > 1 && greater(k / 2, k)) {
                exch(b, k / 2, k);
                k = k / 2;
            }
    }

    public void sink(int k, Heap kind) {
        if (kind == Heap.MAXHEAP) {
            while (2 * k <= M) {
                int j = 2 * k;
                if (j < M && less(j, j + 1)) j++;
                if (!less(k, j)) break;
                exch(a, k, j);
                k = j;
            }
        }
        else if (kind == Heap.MINHEAP) {
            while (2 * k <= N) {
                int j = 2 * k;
                if (j < N && greater(j, j + 1)) j++;
                if (!greater(k, j)) break;
                exch(b, k, j);
                k = j;
            }
        }
    }

    public void insert(Key v) {
        if (isEmpty()) {
            max = v;
            min = v;
        }
        else if (v.compareTo(min) < 0)
            min = v;
        else if (!(v.compareTo(max) < 0))
            max = v;

        if (M + 1 == a.length) resize(2 * a.length, Heap.MAXHEAP);
        if (N + 1 == b.length) resize(2 * b.length, Heap.MINHEAP);
        a[++M] = v;
        swim(M, Heap.MAXHEAP);
        b[++N] = v;
        swim(N, Heap.MINHEAP);
    }

    public Key delMax() {
        Key max = a[1];
        exch(a, 1, M--);
        a[M + 1] = null;
        sink(1, Heap.MAXHEAP);
        if (M >= 0 && M < a.length / 4) resize(a.length / 2, Heap.MAXHEAP);
        return max;
    }

    public Key delMin() {
        Key min = b[1];
        exch(b, 1, N--);
        b[N + 1] = null;
        sink(1, Heap.MINHEAP);
        if (N >= 0 && N < b.length / 4) resize(b.length / 2, Heap.MINHEAP);
        return min;
    }

    public Key max() {
        return max;
    }

    public Key min() {
        return min;
    }

    public static void main(String[] args) {
        // 打印输入流中最大的M行
        int M = Integer.parseInt(args[0]);
        Ex29_MaxAndMinPriorityQueue<Transaction> pq
                = new Ex29_MaxAndMinPriorityQueue<Transaction>(M);

        while (StdIn.hasNextLine()) {
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.sizeMax() > M)
                pq.delMax();    // 最小的M个元素在优先队列中
            if (pq.sizeMin() > M)
                pq.delMin();    // 最大的M个元素在优先队列中
        }

        StdOut.println("max = " + pq.max());
        StdOut.println("min = " + pq.min());

        Stack<Transaction> stack1 = new Stack<Transaction>();
        Stack<Transaction> stack2 = new Stack<Transaction>();
        while (!pq.isEmpty()) {
            stack1.push(pq.delMax());
            stack2.push(pq.delMin());
        }

        // 打印
        StdOut.println("min to max:");
        for (Transaction t : stack1)
            StdOut.println(t);

        StdOut.println("max to min:");
        for (Transaction t : stack2)
            StdOut.println(t);
    }
}
