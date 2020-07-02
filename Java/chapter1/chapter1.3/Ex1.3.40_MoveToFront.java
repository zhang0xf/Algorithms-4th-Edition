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

// 前移编码策略：假设最近访问过的数据很可能再次访问

public class MoveToFront<Item> {

    private Node first;
    private Node last;
    private int N = 0;

    public class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    public void insert(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = null;
        if (isEmpty()) {
            last = first;
        }
        else {
            first.next = oldFirst;
            // insert firstly and delete secondly
            Node p = first;
            while (p.next != null) {
                if (p.next.item == item) {  // 如果是String类型，则比较的是引用地址，所以if为false。
                    p.next = p.next.next;
                    break;
                }
                p = p.next;
            }
        }
        N++;
    }

    public void printQueue() {
        Node curr = first;
        while (curr != null) {
            StdOut.print(curr.item + " ");
            curr = curr.next;
        }
        StdOut.println();
    }


    public static void main(String[] args) {

        MoveToFront<Integer> queue = new MoveToFront<Integer>();
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.printQueue();

        queue.insert(1);
        queue.printQueue();

        queue.insert(3);
        queue.printQueue();

    }
}
