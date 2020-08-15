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

// 索引优先队列：允许引用已经进入优先队列的元素
// 将元素的索引放入优先队列，并通过key大小将索引存放在优先队列中合适位置

public class IndexMinPriorityQueue<Key extends Comparable<Key>> {

    private int N;  // pq中元素的数量
    private int[] pq;   // 存放索引的二叉堆
    private int[] qp;   // 下标作为索引，值为-1说明索引不存在
    private Key[] keys;  // 存放元素的数组（下标为索引）

    public IndexMinPriorityQueue(int max) {
        keys = (Key[]) new Comparable[max + 1];
        pq = new int[max + 1];
        qp = new int[max + 1];
        for (int i = 0; i < qp.length; i++)
            qp[i] = -1;
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    // 检测该索引是否存在
    public boolean contains(int k) {
        return qp[k] != -1;
    }

    public boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    public void exch(int i, int j) {
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
        qp[pq[i]] = j;  // 实时更新qp对应值
        qp[pq[j]] = i;
    }

    public void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {   // k > 1 可以保证 k / 2 >= 1
            exch(k, k / 2);
            k = k / 2;
        }
    }

    public void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public void insert(int k, Key key) {
        N++;    // 元素个数+1，数组下标+1
        qp[k] = N;  // qp随着元素（索引）交换而交换，保持对应关系
        pq[N] = k;  // pq中存放索引
        keys[k] = key;  // 数组下标即为索引
        swim(N);
    }

    public Key min() {
        return keys[pq[1]];
    }

    public Key delMin() {
        Key indexOfMin = keys[pq[1]];
        exch(1, N--);
        sink(1);
        keys[pq[N + 1]] = null; // 优先队列 -> 得到索引 -> 删除keys数组中对应的元素
        qp[pq[N + 1]] = -1; // 优先队列 -> 得到索引 -> 删除索引
        return indexOfMin;
    }

    public int minIndex() {
        return pq[1];
    }

    public void change(int k, Key key) {
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }

    public void delete(int k) {
        int index = qp[k];
        exch(index, N--);
        swim(index);
        sink(index);
        keys[k] = null;
        qp[k] = -1;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        IndexMinPriorityQueue<Double> Impq = new IndexMinPriorityQueue<Double>(N);
        Impq.insert(4, 18.0);
        Impq.insert(3, 14.0);
        Impq.insert(5, 7.0);
        Impq.insert(6, 25.0);
        Impq.insert(9, 108.0);
        Impq.insert(11, 70.0);
        Impq.insert(21, 205.0);
        Impq.insert(7, 16.0);
        Impq.insert(1, 10.0);
        Impq.insert(2, 12.0);

        Double min = Impq.min();
        StdOut.println("min :" + min);

        Impq.delMin();
        StdOut.println("min update :" + Impq.min());

        Impq.change(1, 9.0);
        StdOut.println("1 :" + Impq.min());

        Impq.delete(1);
        StdOut.println("min index :" + Impq.minIndex());
        StdOut.println("min after delete :" + Impq.min());
    }
}
