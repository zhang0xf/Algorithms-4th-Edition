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

// 自底向上的平衡2-3-4树：只需要分解查找路径底部的4-节点

public class ButtomUp234Tree<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    public class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;  // 节点计数器
        boolean color;  // 链接颜色

        public Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    // 判断链接是否为红链接（指向该节点的链接）
    public boolean isRED(Node x) {
        if (x == null) return false;
        else return x.color == RED;
    }

    // 左旋转:右连接
    public Node rotateLeft(Node h) {
        // 调整链接
        Node x = h.right;   // 右连接为RED
        h.right = x.left;
        x.left = h;
        // 调整颜色
        x.color = h.color;
        h.color = RED;
        // 调整计数器
        x.N = h.N;
        // h.N = size(h.left) + size(h.right) + 1;
        return x;   // 新根节点
    }

    // 右旋转：左连接
    public Node rotateRight(Node h) {
        // 调整链接
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        // 调整颜色
        x.color = h.color;
        h.color = RED;
        // 调整计数器
        x.N = h.N;
        // h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    // 调整颜色
    public void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK; // 重要！root的值不能变为红色。
    }

    public Node put(Node x, Key key, Value val) {
        if (x == null) new Node(key, val, 1, RED);

        //  路径途中遇到的4-节点该如何处理？

        return x;
    }

    public static void main(String[] args) {

    }
}
