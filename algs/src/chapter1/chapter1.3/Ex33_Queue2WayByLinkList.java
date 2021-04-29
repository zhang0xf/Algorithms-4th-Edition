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

// two way deque by linklist

public class Ex33_Queue2WayByLinkList<Item> {

    private Node first;
    private Node last;
    int N = 0;

    public boolean isEmpty() {
        return 0 == N;
    }

    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    public int size() {
        return N;
    }

    public void pushLeft(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        first.next = null;

        if (isEmpty()) {
            last = first;
        }
        else {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        N++;
    }

    public void pushRight(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = null;
        last.next = null;

        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.next = last;
            last.prev = oldLast;
        }
        N++;
    }

    public Item popLeft() {
        Item temp = first.item;

        first = first.next;

        if (isEmpty()) {
            last = null;
        }
        N--;

        return temp;

    }

    public Item popRight() {

        Item temp = last.item;

        last = last.prev;

        if (isEmpty()) {
            first = null;
        }
        N--;

        return temp;
    }

    public static void main(String[] args) {
        // 略
    }
}
