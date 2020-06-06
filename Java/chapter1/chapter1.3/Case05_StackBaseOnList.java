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

// LIFO : 下压堆栈（链表实现）

public class StackBaseOnList<Item> {

    private Node first;

    //stack size
    private int N;

    //define node
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

    public static void main(String[] args) {

        StackBaseOnList<String> s;
        s = new StackBaseOnList<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                s.push(item);
            }
            else if (!s.isEmpty()) {
                StdOut.print(s.pop() + " ");
            }
        }
        StdOut.println("( " + s.size() + "left on stack )");
    }
}
