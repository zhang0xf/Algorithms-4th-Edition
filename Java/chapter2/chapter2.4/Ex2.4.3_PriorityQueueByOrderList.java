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

// 优先队列的初级实现：有序的链表
// use with Transaction.java and Date.java

public class PriorityQueueByOrderList<Key extends Comparable<Key>> {

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

    // 插入元素：将较小的元素向后移动，表头总是存放最大的元素
    public void insert(Key v) {
        Node oldFirst = first;
        first = new Node();
        first.key = v;
        first.next = null;
        first.next = oldFirst;
        Node p = first;
        while (p.next != null) {
            if (less(p.key, p.next.key)) {
                // 交换两个节点
                Node pNext = p.next;
                p.next = p.next.next;
                pNext.next = p;
            }
        }
        N++;
    }

    public Key max() {
        return first.key;
    }

    public Key delMax() {
        Key max = first.key;
        first = first.next;
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
