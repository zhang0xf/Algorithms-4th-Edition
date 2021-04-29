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

// 使用二叉堆实现优先队列：面向最小元素的优先队列

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class MinPriorityQueueExtend<Key extends Comparable<Key>/*, Value*/> {

    private Key[] pq;
    private int N = 0;

    public MinPriorityQueueExtend(int max) {
        pq = (Key[]) new Comparable[max + 1];   // pq[0]不使用
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key delMin() {
        Key min = pq[1];
        exch(1, N--);       // 边界与最小值交换
        pq[N + 1] = null;   // 防止游离
        sink(1);            // 下沉
        return min;
    }

    public Key min() {
        return pq[1];
    }

    // 与面向最大值相反，只需要改动less函数即可
    public boolean greater(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    public void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    public void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    public void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && greater(j, j + 1)) j++;   // 2k + 1右孩子
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;  // 新的父节点下标（可能是曾经的左孩子，也可能是右孩子）
        }
    }

    public static void main(String[] args) {
        // 打印输入流中最大的M行
        int M = Integer.parseInt(args[0]);
        MinPriorityQueueExtend<Transaction> pq
                = new MinPriorityQueueExtend<Transaction>(M + 1);

        while (StdIn.hasNextLine()) {
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size() > M)
                pq.delMin();    // 最小的M个元素在优先队列中
        }
        Stack<Transaction> stack = new Stack<Transaction>();
        while (!pq.isEmpty()) {
            stack.push(pq.delMin());
        }
        // 打印
        for (Transaction t : stack)
            StdOut.println(t);
    }
}
