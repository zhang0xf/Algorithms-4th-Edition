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

// notice : base on array is a better way.
// 作者有更好的答案(Ex42_2 和 Ex42_3)

import edu.princeton.cs.algs4.StdOut;

public class Ex42_StackCopy<Item> {

    private int N;
    private Node first;
    private Node last;

    private class Node {
        Item item;
        Node next;
    }

    public Ex42_StackCopy() {
        N = 0;
        first = null;
        last = null;
    }

    public Ex42_StackCopy(Ex42_StackCopy<Item> q) {

        // get all elements in q
        Item[] a = (Item[]) new Object[q.size()]; // need protect original stack
        int idx = 0;
        while (!q.isEmpty()) {
            a[idx] = q.pop();
            idx++;
        }

        // push into two stack
        for (int i = a.length - 1; i >= 0; i--) {
            q.push(a[i]);
            push(a[i]);
        }
    }


    public boolean isEmpty() {
        return 0 == N;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = null;
        if (isEmpty()) {
            last = first;
        }
        else {
            first.next = oldFirst;
        }
        N++;
    }

    public Item pop() {
        Item temp = first.item;

        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        N--;
        return temp;
    }

    public int size() {
        return N;
    }

    public void printStack() {
        Node p = first;
        while (p != null) {
            StdOut.print(p.item + " ");
            p = p.next;
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        Ex42_StackCopy<Integer> queue = new Ex42_StackCopy<Integer>();

        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.printStack();

        Ex42_StackCopy<Integer> rqueue = new Ex42_StackCopy<Integer>(queue);
        rqueue.printStack();
        rqueue.pop();
        rqueue.printStack();

        queue.printStack();
    }
}
