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

// double link list

public class DoubleNodeListLink<Item> {

    private Node first;
    private Node last;  // 不是循环的双向队列需要一个头和一个尾
    private int N;

    private class Node {
        Item item;
        Node Prev;
        Node Next;
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    public int size() {
        return N;
    }

    public void insertHead(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.Prev = null;
        first.Next = null;

        if (isEmpty()) {
            last = first;
        }
        else {
            first.Next = oldFirst;
            oldFirst.Prev = first;
        }

        N++;
    }

    public void insertTail(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.Prev = null;
        last.Next = null;

        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.Next = last;
            last.Prev = oldLast;
        }

        N++;
    }

    public Item deleteHead() {
        Item temp = first.item;
        first.Next.Prev = null;
        first = first.Next;

        if (isEmpty()) {
            last = null;
        }

        N--;
        return temp;
    }

    public Item deleteTail() {
        Item temp = last.item;
        last.Prev.Next = null;
        last = last.Prev;

        if (isEmpty()) {
            first = null;
        }

        N--;
        return temp;
    }

    public boolean insertByNodeBefore(Item item, Item comp) {

        Node insert = new Node();
        insert.item = item;
        insert.Prev = null;
        insert.Next = null;

        Node current = first;

        if (isEmpty()) {
            first = insert;
            last = insert;
            return true;
        }

        while (current != null) {
            //假设Item为int类型
            if (comp == current.item) {
                if (size() == 1) {
                    insert.Next = current;
                    current.Prev = insert;
                }
                else {
                    Node currPrev = current.Prev;
                    currPrev.Next = insert;
                    insert.Prev = currPrev;
                    insert.Next = current;
                    current.Prev = insert;
                }
                return true;
            }
            current = current.Next;
        }
        return false;
    }

    public boolean insertByNodeAfter(Item item, Item comp) {

        Node insert = new Node();
        insert.item = item;
        insert.Prev = null;
        insert.Next = null;

        Node current = first;

        if (isEmpty()) {
            first = insert;
            last = insert;
            return true;
        }

        while (current != null) {
            //假设Item为int类型
            if (item == current.item) {
                if (size() == 1) {
                    current.Next = insert;
                    insert.Prev = current;
                }
                else {
                    Node currNext = current.Next;
                    currNext.Prev = insert;
                    insert.Next = currNext;
                    insert.Prev = current;
                    current.Next = insert;
                }
                return true;
            }
            current = current.Next;
        }
        return false;
    }

    public void printNodeByPreOrder() {
        Node current = first;
        while (current != null) {
            StdOut.print(current.item + " ");
            current = current.Next;
        }
        StdOut.println();
    }

    public void printNodeByPostOrder() {
        Node current = last;
        while (current != null) {
            StdOut.print(current.item + " ");
            current = current.Prev;
        }
        StdOut.println();
    }

    public static void main(String[] args) {

        DoubleNodeListLink<String> q = new DoubleNodeListLink<String>();

        String str1 = "zhangfei1";
        String str2 = "zhangfei2";
        String str3 = "zhangfei3";
        String str4 = "zhangfei4";
        String str5 = "zhangfei5";
        String str6 = "zhangfei6";

        q.insertHead(str1);
        q.insertHead(str2);
        q.insertHead(str3);
        q.printNodeByPreOrder();

        q.insertTail(str4);
        q.insertTail(str5);
        q.insertTail(str6);
        q.printNodeByPreOrder();

        q.printNodeByPostOrder();

        q.insertByNodeBefore("new node 1", str1);
        q.printNodeByPreOrder();

        q.insertByNodeAfter("new node 2", str5);
        q.printNodeByPreOrder();
    }
}
