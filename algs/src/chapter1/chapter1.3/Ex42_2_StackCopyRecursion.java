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

// Author's Recursion Method

import edu.princeton.cs.algs4.StdOut;

public class Ex42_2_StackCopyRecursion<Item> {

    private int N;
    private Node first;

    public class Node {

        Node() {
            //
        }

        // 添加拷贝构造函数，以拷贝节点！
        // 该方法可以避免抽象数据类型拷贝的问题（比如字符串的拷贝）
        // 该方法使用递归思想，且在创建头节点时就开始递归后面的节点！
        Node(Node x) {
            item = x.item;
            if (x.next != null) {
                next = new Node(x.next); // new 即拷贝操作，使新栈独立于原栈
            }
        }

        Item item;
        Node next;
    }

    // 默认构造函数
    public Ex42_2_StackCopyRecursion() {
        //
    }

    // 拷贝构造函数: 将Stack简化为一个first节点！
    public Ex42_2_StackCopyRecursion(Ex42_2_StackCopyRecursion<Item> q) {
        first = new Node(q.first);
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
        first.next = null;

        first.next = oldFirst;
        N++;
    }

    public Item pop() {
        Item temp = first.item;
        first = first.next;
        N--;

        return temp;
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

        Ex42_2_StackCopyRecursion<String> queue
                = new Ex42_2_StackCopyRecursion<String>();

        queue.push("zhangfei1");
        queue.push("zhangfei2");
        queue.push("zhangfei3");
        queue.push("zhangfei4");
        queue.push("zhangfei5");
        queue.printStack();

        Ex42_2_StackCopyRecursion<String> rqueue
                = new Ex42_2_StackCopyRecursion<String>(queue);

        rqueue.printStack();
        rqueue.pop();
        rqueue.printStack();
        queue.printStack();
    }
}
