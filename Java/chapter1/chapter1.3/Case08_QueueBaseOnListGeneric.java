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

// FIFO : 队列（基于链表，实现泛型、迭代器）

import java.util.Iterator;

public class QueueBaseOnListGeneric<Item> implements Iterable<Item> {

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

    public Iterator<Item> iterator() {
        return new QueueBaseOnListIterator();
    }

    private class QueueBaseOnListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item temp = current.item;
            current = current.next;
            return temp;
        }

        @Override
        public void remove() {
            //
        }
    }

    public static void main(String[] args) {

        QueueBaseOnListGeneric<String> s = new QueueBaseOnListGeneric<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                s.enqueue(item);

                // use iterator
                // way 1
                Iterator<String> it = s.iterator();
                while (it.hasNext()) {
                    String str = it.next();
                    StdOut.print(str + " ");
                }
                StdOut.println();

                // way 2
                // for (String str : s) {
                //     StdOut.print(str + " ");
                // }
                // StdOut.println();

            }
            else if (!s.isEmpty()) {
                //StdOut.print(s.dequeue() + " ");
            }
        }
        //StdOut.println("( " + s.size() + "left on stack )");
    }
}
