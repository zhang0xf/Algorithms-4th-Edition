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

// 优先队列的初级实现：无序链表
// use with Transaction.java and Date.java

public class PriorityQueueByUnorderList<Key extends Comparable<Key>> {

    private Node first;
    private int N;

    public class Node {
        Key key;
        Node next;
    }

    public boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        Node oldfirst = first;
        first = new Node();
        first.key = v;
        first.next = null;
        first.next = oldfirst;
        N++;
    }

    // 返回最大的元素(遍历链表)
    public Key max() {
        Key max = first.key;
        Node p = first;
        while (p != null) {
            if (less(max, p.key))
                max = p.key;
            p = p.next;
        }
        return max;
    }

    // 删除最大元素(遍历两次链表，第一次找到最大值，第二次删除最大值)
    public Key delMax() {
        Node p = first;
        Key max = null;
        while (p != null) {
            if (p.key.equals(max())) {
                max = p.key;
                p.next = p.next.next;
                break;
            }
            p = p.next;
        }
        N--;
        return max;
    }


    public static void main(String[] args) {
        // 打印输入流中最大的M行
        int M = Integer.parseInt(args[0]);
        PriorityQueueByUnOrderArray<Transaction> pq
                = new PriorityQueueByUnOrderArray<Transaction>(M + 1);

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
