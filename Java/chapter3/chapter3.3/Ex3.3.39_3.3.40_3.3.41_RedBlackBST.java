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

// 符号表的实现：基于红黑树

import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    public class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;  // 节点计数器
        private boolean color;  // 链接颜色

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
        if (h == null) throw new NullPointerException();
        if (!isRED(h.right)) return h;   // 左旋要求右链接为红链接
        // 调整链接
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        // 调整颜色
        x.color = h.color;
        h.color = RED;
        // 调整计数器
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;   // 新根节点
    }

    // 右旋转：左连接
    public Node rotateRight(Node h) {
        if (h == null) throw new NullPointerException();
        if (!isRED(h.left)) return h;   // 右旋要求左链接为红链接
        // 调整链接
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        // 调整颜色
        x.color = h.color;
        h.color = RED;
        // 调整计数器
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;   // 新根节点
    }

    // 调整颜色（颜色取反，与书中代码不同）
    public void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
        // h.color = RED;
        // h.left.color = BLACK;
        // h.right.color = BLACK;
    }

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public boolean isEmpty() {
        return root == null;
    }

    // 查找
    public Value get(Key key) {
        return get(root, key);
    }

    public Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    // 插入
    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    public Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1, RED);

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;

        // 旋转至重新平衡
        if (isRED(x.right) && !isRED(x.left)) x = rotateLeft(x);
        if (isRED(x.left) && isRED(x.left.left)) x = rotateRight(x);
        if (isRED(x.left) && isRED(x.right)) flipColors(x);

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // 最小值
    public Key min() {
        return min(root).key;
    }

    public Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    // 最大值
    public Key max() {
        return max(root).key;
    }

    public Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    // 删除最小值
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException();  // 如果空树，抛出异常！
        if (!isRED(root.left) && !isRED(root.right))    // root适用deleteMin()接口代码需要更改颜色
            root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK; // 恢复root颜色
    }

    public Node deleteMin(Node x) {
        if (x == null) throw new NullPointerException();
        if (x.left == null) return null;    // 执行删除操作
        if (!isRED(x.left) && !isRED(x.left.left))  // 若x.left为2-节点，向右兄弟节点借红链接。
            x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return balance(x);  // 向上配平
    }

    public Node moveRedLeft(Node x) {
        // 假设x为红链接，x.left和x.right均为黑链接
        // 将x.left或者x.left的子节点之一变为红色

        if (x == null) throw new NullPointerException();
        flipColors(x); // x变换为4-节点

        if (isRED(x.right.left)) {  // 从右兄弟节点借一个红链接
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);  // 恢复颜色
        }
        return x;
    }

    public Node balance(Node x) {
        if (isRED(x.right)) x = rotateLeft(x);  // 替换put接口中的第一行
        if (isRED(x.left) && isRED(x.left.left)) x = rotateRight(x);
        if (isRED(x.left) && isRED(x.right)) flipColors(x);

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    // 删除最大值
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException();  // 如果空树，抛出异常！
        if (!isRED(root.left) && !isRED(root.right))
            root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    public Node deleteMax(Node x) {
        if (x == null) throw new NullPointerException();
        if (isRED(x.left)) // 将x.right变为红色的优先级较高
            x = rotateRight(x);
        if (x.right == null) return null;   // 执行删除操作
        if (!isRED(x.right) && !isRED(x.right.left)) // 右子节点为2-节点，从左兄弟借一个红链接
            x = moveRedRight(x);

        x.right = deleteMax(x.right);
        return balance(x);  // 向上配平
    }

    public Node moveRedRight(Node x) {
        // 假设x为红链接，x.right和x.right.left均为黑链接
        // 将x.right或者x.right的子节点之一变为红色

        if (x == null) throw new NullPointerException();
        flipColors(x);

        if (isRED(x.left.left)) {
            x = rotateRight(x); // 与删除最小键不同，直接右旋即可。
            flipColors(x);  // 恢复颜色
        }
        return x;
    }

    // 删除
    public void delete(Key key) {
        if (isEmpty()) throw new NoSuchElementException();  // 如果空树，抛出异常！
        if (!isRED(root.left) && !isRED(root.right))
            root.color = RED;
        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    public Node delete(Node x, Key key) {
        if (x == null) throw new NullPointerException();

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            if (!isRED(x.left) && !isRED(x.left.left))  // 向下变换，保证删除节点后树的平衡！
                x = moveRedLeft(x);
            x.left = delete(x.left, key);  // 深入左子树
        }
        else /* if (cmp > 0) */ {
            if (!isRED(x.left)) x = rotateRight(x); // 将x.right变红的优先级高于x.right的子节点
            if (cmp == 0 && x.right == null) return null;   // 到达树底，直接删除！（null递归向上）
            if (!isRED(x.right) && !isRED(x.right.left))    // x.right为2-节点，向下变换，保证删除节点后树的平衡！
                x = moveRedRight(x);
            if (cmp == 0) { // 不在树底
                // 因为此时节点的属性值多了color，所以不能简单使用min()接口拷贝节点
                x.val = get(x.right, min(x.right).key); // 获得最小值
                x.key = min(x.right).key;   // 获得最小key
                x.right = deleteMin(x.right);   // 删除左子树最小节点
            }
            else x.right = delete(x.right, key);    // 向右子树深入
        }

        return balance(x);
    }

    public static void main(String[] args) {

        RedBlackBST<String, Integer> st = new RedBlackBST<String, Integer>();

        // stdin : zhang fei after reading java
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // 最小值和最大值
        StdOut.println("min :" + st.min());
        st.deleteMin();
        StdOut.println("max :" + st.max());
        st.deleteMax();

        st.delete("fei");
        // 暂时未实现其他接口，使用debug模式验证结果！
    }
}
