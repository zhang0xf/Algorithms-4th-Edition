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

// 优先队列：使用无序数组实现
// use with Transaction,java and Date.java
// Key extends Comparable<Key>:表示Key是一个泛型，且实现了Comparable接口（Transaction类），有点回调函数的意思！
// Value：和之前学习数据结构时的Item一样，属于一个键值，没有实现Comparable。
public class PriorityQueueByUnOrderArray<Key extends Comparable<Key>/*, Value*/> {

    private Key[] a;
    /* private Value[] b;*/
    private int N;

    // 构造函数
    public PriorityQueueByUnOrderArray() {
        a = (Key[]) new Comparable[1];  // key 与 value 的区别！
        // b = (Value[]) new Object[1];
    }

    public PriorityQueueByUnOrderArray(int max) {
        a = (Key[]) new Comparable[max];
        // b = (Value[]) new Object[max];
    }

    // 插入一个元素
    public void insert(Key v) {
        if (N == a.length)
            resize(a.length * 2);
        a[N++] = v;
    }

    // 返回最大的元素
    public Key max() {
        Key max = a[0];
        for (int i = 1; i < N; i++) {
            if (less(max, a[i]))
                max = a[i];
        }
        return max;
    }

    // 删除最大元素
    public Key delMax() {
        int max = 0;
        for (int i = 1; i < N; i++) {
            if (less(a[max], a[i]))
                max = i;
        }
        exch(max, N - 1);
        return a[--N];
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

    public static void main(String[] args) {

        // 打印输入流中最大的M行
        int M = Integer.parseInt(args[0]);
        PriorityQueueByUnOrderArray<Transaction> pq
                = new PriorityQueueByUnOrderArray<Transaction>();

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
