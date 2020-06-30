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

// steque : a stack queue by linklist
// API : push,pop,enqueue

public class StequeByLinkList<Item> {

    private Node first;
    private Node last;

    int N = 0;

    private class Node {
        Item item;
        Node next;
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

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.next = last;
        }
        N++;
    }

    public void printSteque() {
        Node current = first;
        while (current != null) {
            StdOut.print(current.item + " ");
            current = current.next;
        }
        StdOut.println();
    }

    public static void main(String[] args) {

        StequeByLinkList<String> s = new StequeByLinkList<String>();

        String str1 = "zhangfei1";
        String str2 = "zhangfei2";
        String str3 = "zhangfei3";
        String str4 = "zhangfei4";

        s.push(str1);
        s.push(str2);
        s.push(str3);
        s.push(str4);

        s.printSteque();

        StdOut.printf("pop one item : %s\n", s.pop());

        s.printSteque();

        s.enqueue("new node");
        s.printSteque();

    }
}
