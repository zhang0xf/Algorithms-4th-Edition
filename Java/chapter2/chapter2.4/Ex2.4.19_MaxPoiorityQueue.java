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

// 使用二叉堆实现的优先队列：面向最大值的优先队列

public class MaxPoiorityQueue<Key extends Comparable<Key>> {

    private Key[] pq;
    private int N = 0;

    public MaxPoiorityQueue(int max) {
        pq = (Key[]) new Comparable[max + 1];   // pq[0]不使用
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    public int size() {
        return N;
    }

    // 在边界（底）添加新元素，并上浮到合适的位置
    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    // 根（最大值）与边界（底）元素交换，再下沉到合适的位置
    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);   // 计数减少
        pq[N + 1] = null;   // 杜绝游离
        sink(1);    // 下沉
        return max;
    }

    public boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    public void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    public void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    public void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;   // 2k + 1右孩子
            if (!less(k, j)) break;
            exch(k, j);
            k = j;  // 新的父节点下标（可能是曾经的左孩子，也可能是右孩子）
        }
    }

    public static void main(String[] args) {
        // 打印输入流中最大的M行
        int M = Integer.parseInt(args[0]);
        MaxPoiorityQueue<Transaction> pq
                = new MaxPoiorityQueue<Transaction>(M + 1);

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
