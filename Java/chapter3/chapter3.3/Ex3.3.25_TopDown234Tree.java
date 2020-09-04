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

// 自顶向下的平衡2-3-4树

public class TopDown234Tree<Key extends Comparable<Key>, Value> {

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
    }

    // 只有左连接是红链接
    // 允许4-节点存在
    public Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1, RED);

        // 由顶向下进行变换

        // 根节点

        // root为4-节点
        // isRED有非null判断
        if (x == root &&
                /* x.left != null && */isRED(x.left) &&  // 红链接
                /* x.left.left != null &&*/ isRED(x.left.left)   // 红链接
        ) {
            root = rotateRight(root);   // 右旋
            if (isRED(root.left) && isRED(root.right))
                flipColors(root);
        }

        // 非根节点

        // 1.父节点为2-节点的4-节点(2-节点的左连接)
        if (!isRED(x.left) &&
                isRED(x.left.left) &&
                isRED(x.left.left.left)
        ) {
            x.left = rotateRight(x.left);   // 右旋
            if (isRED(x.left.left) && isRED(x.left.right))
                flipColors(x.left);
        }

        // 2.父节点为2-节点的4-节点(2-节点的右连接)
        if (!isRED(x.right) &&
                isRED(x.right.left) &&
                isRED(x.right.left.left)
        ) {
            x.left = rotateRight(x.left);   // 右旋
            if (isRED(x.left.left) && isRED(x.left.right))
                flipColors(x.left);
        }

        // 3.父节点为3-节点的4-节点（3-节点的左连接）
        // isRED有非null判断
        if (isRED(x.left) &&
                isRED(x.left.left) &&    // 确定父节点为3-节点
                !isRED(x.left.left.left) && // 普通黑链接
                isRED(x.left.left.left.left) &&
                isRED(x.left.left.left.left.left) // 要处理的节点为4-节点
        ) {
            x.left.left.left = rotateRight(x.left.left.left);
            if (isRED(x.left.left.left.left) && isRED(x.left.left.left.right))
                flipColors(x.left.left.left);
        }

        // 4. 父节点为3-节点的4-节点（3-节点的中连接）
        if (isRED(x.left) &&
                isRED(x.left.left) &&    // 确定父节点为3-节点
                !isRED(x.left.right) && // 普通黑链接
                isRED(x.left.right.left) &&
                isRED(x.left.right.left.left) // 要处理的节点为4-节点
        ) {
            x.left.right.left = rotateRight(x.left.right.left);
            if (isRED(x.left.right.left.left) && isRED(x.left.right.left.right))
                flipColors(x.left.right.left);
        }

        // 5.父节点为3-节点的4-节点（3-节点的右连接）
        if (isRED(x.left) &&
                isRED(x.left.left) &&    // 确定父节点为3-节点
                !isRED(x.right.left) && // 普通黑链接
                isRED(x.right.left.left) &&
                isRED(x.right.left.left.left) // 要处理的节点为4-节点
        ) {
            x.right.left = rotateRight(x.right.left);
            if (isRED(x.right.left.left) && isRED(x.right.left.right))
                flipColors(x.right.left);
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;

        // 自下而上旋转并将4-节点配平
        if (!isRED(x.left) && isRED(x.right)) x = rotateLeft(x);
        if (isRED(x.left) && isRED(x.left.left)) x = rotateRight(x);
        if (isRED(x.left) && isRED(x.right)) flipColors(x);

        return x;
    }

    public static void main(String[] args) {

    }
}
