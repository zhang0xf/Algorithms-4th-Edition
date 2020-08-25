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

// 符号表：基于无序链表

public class SequentialSearchST<Key, Value> {

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
                x.val = val;	// 数量没有增加
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

    // 表中所有键的集合：Queue实现了Iterable，直接返回Queue<Key>类型
    Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next) {
            queue.enqueue(x.key);
        }
        return queue;
    }

    public static void main(String[] args) {

    }
}
