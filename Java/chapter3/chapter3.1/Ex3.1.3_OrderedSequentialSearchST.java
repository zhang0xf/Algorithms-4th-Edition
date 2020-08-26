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

// 符号表：基于有序链表

public class OrderedSequentialSearchST<Key extends Comparable<Key>, Value> {    //“有序”需要Comparable类型的Key

    private Node first; // 作为链表地址，不存放节点，由此许多接口都得到简化，秒啊！
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

    public OrderedSequentialSearchST() {
        first = new Node(null, null, null);
    }

    public Value get(Key key) {
        for (Node x = first.next; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        Node pre = first;
        for (Node x = first.next; x != null; x = x.next) {
            if (key.compareTo(x.key) == 0) {
                x.val = val;
                return;
            }
            else if (key.compareTo(x.key) < 0) {
                pre.next = new Node(key, val, x);
                N++;
                return;
            }
            pre = pre.next;
        }
        // 循环结束，插入尾部
        N++;
        pre.next = new Node(key, val, null);
    }

    public void delete(Key key) {
        Node pre = first;
        for (Node x = first.next; x != null; x = x.next) {
            if (key.compareTo(x.key) == 0) {
                pre.next = x.next;
                N--;
            }
            pre = pre.next;
        }
    }

    public boolean contains(Key key) {
        for (Node x = first.next; x != null; x = x.next) {
            if (key.compareTo(x.key) == 0)
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

    public Key min() {
        return first.next.key;
    }

    public Key max() {
        Node pre = first;
        for (Node x = first.next; x != null; x = x.next) {
            pre = pre.next;
        }
        return pre.key;
    }

    // 向下取整
    public Key floor(Key key) {
        Node pre = first;
        for (Node x = first.next; x != null; x = x.next) {
            if (key.compareTo(x.key) < 0)
                break;
            pre = pre.next;
        }
        // 循环结束
        if (pre == first)
            return null;
        else
            return pre.key;
    }

    // 向上取整
    public Key ceiling(Key key) {
        for (Node x = first.next; x != null; x = x.next) {
            if (key.compareTo(x.key) <= 0)
                return x.key;
        }
        return null;    // 循环结束
    }

    // 小于key的键数量
    public int rank(Key key) {
        int k = 0;
        for (Node x = first.next; x != null; x = x.next) {
            if (key.compareTo(x.key) > 0)
                k++;
        }
        return k;
    }

    // 排名为key的键：从1开始
    public Key selec(int k) {
        Key temp = null;
        for (Node x = first.next; x != null && k > 0; x = x.next) {
            temp = x.key;
            k--;
        }
        return temp;
    }

    public void deleteMin() {
        first = first.next;
        first.key = null;
        first.val = null;
        N--;
    }

    public void deleteMax() {
        Node pre = first;
        for (Node x = first.next; x != null; x = x.next) {
            if (x.next == null) break;
            pre = pre.next;
        }
        pre.next = null;    // 删除最后节点
        N--;
    }

    public int size(Key lo, Key hi) {
        int m = 0;
        for (Node x = first.next; x != null; x = x.next) {
            if (lo.compareTo(x.key) <= 0 && hi.compareTo(x.key) >= 0)
                m++;
        }
        return m;
    }

    Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first.next; x != null; x = x.next) {
            queue.enqueue(x.key);
        }
        return queue;
    }

    Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first.next; x != null; x = x.next) {
            if (lo.compareTo(x.key) <= 0 && hi.compareTo(x.key) >= 0)
                queue.enqueue(x.key);
        }
        return queue;
    }

    public static void main(String[] args) {

        OrderedSequentialSearchST<String, Integer> st
                = new OrderedSequentialSearchST<String, Integer>();

        // stdin : zhang fei after reading java
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // 打印
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();

        // 输入中得包含键"after"和"fei"
        for (String s : st.keys("after", "fei"))
            StdOut.println(s + " " + st.get(s));
        StdOut.println();

        // 测试deleteMax和deleteMin
        StdOut.println("min :" + st.min());
        st.deleteMin();
        StdOut.println("max :" + st.max());
        st.deleteMax();
        st.delete("java");
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();

        StdOut.println("size :" + st.size());

        StdOut.println("ceiling :" + st.ceiling("fei"));
        StdOut.println("floor :" + st.floor("fei"));
    }
}
