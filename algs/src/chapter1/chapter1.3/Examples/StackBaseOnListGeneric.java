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

// LIFO : 下压堆栈（链表实现、泛型、迭代器）

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class StackBaseOnListGeneric<Item> implements Iterable<Item> {

    private Node first;

    // stack size
    private int N;

    // define node
    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Item pop() {
        Item temp = first.item;
        first = first.next;
        N--;
        return temp;
    }

    // using interface to return a iterator
    // usage: Iterator<String> it = s.iterator();
    public Iterator<Item> iterator() {
        return new StackBaseOnListIterator();
    }

    private class StackBaseOnListIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
        }

        // exception is required, but we don't need it
        public Item next() {
            Item temp = current.item;
            current = current.next;
            return temp;
        }
    }

    public static void main(String[] args) {

        StackBaseOnListGeneric<String> s = new StackBaseOnListGeneric<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                s.push(item);

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
                // StdOut.print(s.pop() + " ");
            }
        }
        // StdOut.println("( " + s.size() + "left on stack )");
    }
}
