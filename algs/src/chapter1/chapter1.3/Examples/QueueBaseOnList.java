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

// FIFO : 队列基于链表实现（泛型）

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QueueBaseOnList<Item> {

    private Node first; // head
    private Node last;  // tail
    private int N;


    // 链表的递归属性
    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null; // or 0 == N
    }

    public int size() {
        return N;
    }

    // add item at tail
    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node(); // last need memory
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.next = last;
        }
        N++; // counter add
    }

    // delete item at head
    public Item dequeue() {
        Item temp = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        N--; // counter sub
        return temp;
    }

    public static void main(String[] args) {

        QueueBaseOnList<String> s = new QueueBaseOnList<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                s.enqueue(item);
            }
            else if (!s.isEmpty()) {
                StdOut.print(s.dequeue() + " ");
            }
        }
        StdOut.println("( " + s.size() + "left on stack )");
    }
}
