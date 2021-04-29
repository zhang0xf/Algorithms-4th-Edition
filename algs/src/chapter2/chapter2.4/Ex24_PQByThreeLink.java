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

// 优先队列：使用链表代替数组，每一个节点都需要三个链接。
// 本题需要位置指示器，以指示该往何处插入新元素。可以将父节点依次存储到双向链表中！
// 注意：队列看似是一个很好的选择，但是一端只有一种操作（入队/出队），而代码需要在两端入队和出队！
// use with DoubleNodeListLink.java

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Ex24_PQByThreeLink<Key extends Comparable<Key>> {

    private Node first;
    private Node last;
    private DoubleNodeListLink<Node> dl;    // 使用双向链表而非队列Queue
    private int N = 0;

    public class Node {
        Key v;
        Node root;
        Node left;
        Node right;

        public boolean hasLeft() {
            return left != null;
        }

        public boolean hasRight() {
            return right != null;
        }

        public boolean hasParent() {
            return root != null;
        }
    }

    public Ex24_PQByThreeLink() {
        first = null;
        last = null;
        dl = new DoubleNodeListLink<Node>();
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        Node OldLast = last;
        last = new Node();
        last.v = v;
        if (isEmpty()) {
            first = last;   // 根节点的 parent = null
            dl.insertTail(last);
        }
        else {
            Node parent = dl.head();
            // 如果没有有左孩子，就在左孩子下插入节点，并且新加入的节点入队列（可以作为父节点的队列）
            if (!parent.hasLeft()) {
                parent.left = last;
                last.root = parent;
                dl.insertTail(last);
            }
            else if (!parent.hasRight()) {
                parent.right = last;
                last.root = parent;
                dl.insertTail(last);
                // 此时该parent节点以不能作为父节点，出队
                dl.deleteHead();
            }
        }

        // 加入节点之后，还需要上浮操作（只交换键值，不改变节点的链接）
        swim(last);
        N++;
    }

    public Key delMax() {
        if (isEmpty()) return null;
        Key max = first.v;
        exch(first, last);  // 与last节点交换
        if (size() == 1) {
            first = null;
            last = null;
        }
        else if (last.root.right == last) {
            last.root.right = null; // 删除右节点
            dl.deleteTail();
            dl.insertHead(last.root); // 删除右子节点的父节点又可以插入元素，且优先级最高
            last = dl.tail();   // 重新定位last
        }
        else if (last.root.left == last) {
            last.root.left = null;  // 删除左节点
            dl.deleteTail();
            last = dl.tail();   // 重新定位last位置（队列中至少有一个元素）
        }

        // 删除节点之后，还需要下沉操作（只交换键值，不改变节点的链接）
        sink(first);
        N--;
        return max;
    }

    // 上浮
    public void swim(Node last) {
        if (last == null) return;
        Node p = last;
        while (p.hasParent() && less(p.root.v, p.v)) {
            exch(p, p.root);
            p = p.root;
        }
    }

    // 下沉
    public void sink(Node first) {
        if (null == first) return;
        Node p = first;
        while (p.hasLeft()) {   // 如果有子节点，那么一定是左节点优先！
            if (p.hasRight() && less(p.left.v, p.right.v)) {
                if (!less(p.v, p.right.v)) break;
                exch(p, p.right);
                p = p.right;
            }
            else {
                if (!less(p.v, p.left.v)) break;
                exch(p, p.left);
                p = p.left;
            }
        }
    }

    public boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    public void exch(Node v, Node w) {
        Key temp = v.v;
        v.v = w.v;
        w.v = temp;
    }

    public void printPQ() {
        StdOut.print();
    }

    public static void main(String[] args) {
        Ex24_PQByThreeLink<Integer> pq
                = new Ex24_PQByThreeLink<Integer>();

        pq.insert(10);
        pq.insert(20);
        pq.insert(6);
        pq.insert(15);
        pq.insert(13);
        pq.insert(19);
        pq.insert(18);
        for (int i = 0; i < 10; i++) {
            pq.insert(StdRandom.uniform(0, 100));
        }

        // 检查优先队列是否有问题
        // StdOut.println(
        //         "right :" + pq.first.v + " " + pq.first.right.v + " " + pq.first.right.left.v);
        // StdOut.println(
        //         "left 1 :" + pq.first.v + " " + pq.first.left.v + " " + pq.first.left.left.v);

        // 检查队列是否有问题
        // StdOut.println("dl size = " + pq.dl.size());
        // while (!pq.dl.isEmpty()) {
        //     StdOut.print(pq.dl.deleteHead().v + " ");
        // }
        // StdOut.println();

        while (!pq.isEmpty()) {
            StdOut.println(pq.delMax());
        }
    }
}
