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

// 没有平衡限制的2-3树，实现符号表部分API

public class NoBalance23Tree<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;    // 红链接
    private static final boolean BLACK = false; // 黑链接

    private Node root;  // 根

    // 不修改Node节点的结构，使用红色链接和多个Node来表示3-节点
    public class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        boolean color;  // 链接颜色
        private int N;  // 节点计数器

        public Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    public Value get(Key key) {
        return get(root, key);
    }

    // 忽略红链接
    public Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) return get(x.right, key);
        else if (cmp < 0) return get(x.left, key);
        else return x.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    public Node put(Node x, Key key, Value val) {
        //  暂未实现
        return x;
    }

    public static void main(String[] args) {

    }
}
