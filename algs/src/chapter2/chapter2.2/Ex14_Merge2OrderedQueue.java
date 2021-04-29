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

// 归并两个有序的队列
// 尚不知道Java该如何抽象，暂时实现Integer类型

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Ex14_Merge2OrderedQueue {

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static Queue<Integer> mergeQueue(Queue<Integer> q1, Queue<Integer> q2) {
        Queue<Integer> temp = new Queue<Integer>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            if (q1.isEmpty()) temp.enqueue(q2.dequeue());
            else if (q2.isEmpty()) temp.enqueue(q1.dequeue());
            else if (less(q1.peek(), q2.peek())) temp.enqueue(q1.dequeue());
            else temp.enqueue(q2.dequeue());
        }
        return temp;
    }

    public static void main(String[] args) {

        Queue<Integer> q1 = new Queue<Integer>();
        Queue<Integer> q2 = new Queue<Integer>();

        // 参数队列必须有序
        q1.enqueue(1);
        q1.enqueue(3);
        q1.enqueue(7);

        q2.enqueue(2);
        q2.enqueue(4);
        q2.enqueue(8);

        Queue<Integer> sortedQueue = Ex14_Merge2OrderedQueue.mergeQueue(q1, q2);

        while (!sortedQueue.isEmpty()) {
            StdOut.print(sortedQueue.dequeue() + " ");
        }
        StdOut.println();
    }
}
