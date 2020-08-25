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

// 基于无序链表实现符号表
// keys接口尚未实现，原因：java迭代器未完全掌握

import java.util.Iterator;

public class SequentialSearchSTByList<Key, Value> implements Iterable<Key> {

    private Node first;
    private int N;

    public class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    // 使用for循环遍历链表
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                N++;
                return;
            }
        }
        // 巧妙地使用构造函数链接链表
        N++;
        first = new Node(key, val, first);
    }

    public void delete(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(first.key)) {
                first = first.next;
                N--;
            }
            else if (key.equals(x.next.key)) {
                x.next = x.next.next;
                N--;
            }
        }
    }

    public boolean contains(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    public int size() {
        return N;
    }

    @Override
    public Iterator<Key> iterator() {
        return new KeySetIterator();
    }

    private class KeySetIterator implements Iterator<Key> {

        private Node p;

        public KeySetIterator() {
            p = first;
        }

        @Override
        public boolean hasNext() {
            return p.next != null;
        }

        @Override
        public Key next() {
            if (p.key == null) throw new NullPointerException();
            return p.key;
        }

        @Override
        public void remove() {

        }
    }

    // // 表中所有键的集合
    // Iterable<Key> Keys() {
    //     // 暂时不知如何实现，作者直接调用treeMap的keySet()方法。
    //
    // }

    public void show() {
        for (Node x = first; x != null; x = x.next) {
            StdOut.print(x.key + " ");
            StdOut.print(x.val + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {

    }
}
