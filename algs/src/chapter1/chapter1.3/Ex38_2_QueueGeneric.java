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

// base on Linklist

import edu.princeton.cs.algs4.StdOut;

public class Ex38_2_QueueGeneric<Item> {

    private int N;
    private Node first;
    private Node last;

    public class Node {
        Item item;
        Node next;
    }

    public Ex38_2_QueueGeneric(int max) {
        // first.item = null;
        // first.next = null;
        // last.item = null;
        // last.next = null;
        first = null;
        last = null;
        N = 0;
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    public void insert(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldlast.next = last;
        }
        N++;
    }

    public Item delete(int k) {
        // assuming start at 0
        Node curr = first;
        int cnt = k - 1 - 1;
        while (curr != null && cnt > 0) {
            curr = curr.next;
            cnt--;
        }
        if (curr.next == null) {
            return null;
        }
        Item temp = curr.next.item;
        curr.next = curr.next.next;
        N--;
        return temp;
    }

    public void printQueue() {
        Node pCurr = first;
        while (pCurr != null) {
            StdOut.print(pCurr.item + " ");
            pCurr = pCurr.next;
        }
        StdOut.println();
    }

    public static void main(String[] args) {

        Ex38_2_QueueGeneric<Integer> queue
                = new Ex38_2_QueueGeneric<Integer>(10);

        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);
        queue.insert(6);
        queue.printQueue();

        StdOut.println(queue.delete(2) + " be deleted ");
        queue.printQueue();

        StdOut.println(queue.delete(3) + " be deleted ");
        queue.printQueue();
    }
}
