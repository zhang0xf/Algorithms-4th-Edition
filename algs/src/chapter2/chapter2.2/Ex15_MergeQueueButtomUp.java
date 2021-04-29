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

// 自底向上的有序队列的归并排序，以Ex14为基础！

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Ex15_MergeQueueButtomUp {

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // 创建N个队列组成的队列
    public static Queue<Queue> createQueue(Comparable[] a) {
        Queue<Queue> queue = new Queue();
        for (int i = 0; i < a.length; i++) {
            Queue q = new Queue();
            q.enqueue(a[i]);
            queue.enqueue(q);
        }
        return queue;
    }

    public static Queue<Double> mergeQueue(Queue<Double> q1, Queue<Double> q2) {
        Queue<Double> temp = new Queue<Double>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            if (q1.isEmpty()) temp.enqueue(q2.dequeue());
            else if (q2.isEmpty()) temp.enqueue(q1.dequeue());
            else if (less(q1.peek(), q2.peek())) temp.enqueue(q1.dequeue());
            else temp.enqueue(q2.dequeue());
        }
        return temp;
    }

    public static void sort(Queue<Queue> q) {
        while (q.size() > 1) {
            Queue q1 = q.dequeue(); // q1和q2为队列，且长度随着归并的进行而加长
            Queue q2 = q.dequeue();
            Queue orderQueue = mergeQueue(q1, q2);
            q.enqueue(orderQueue);  // 每次归并，队列q的长度会减少1
        }
    }

    public static void printQueue(Queue queue) {
        while (!queue.isEmpty()) {
            StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }

        Queue queue = createQueue(a);
        Ex15_MergeQueueButtomUp.sort(queue);
        printQueue(queue);
    }
}
