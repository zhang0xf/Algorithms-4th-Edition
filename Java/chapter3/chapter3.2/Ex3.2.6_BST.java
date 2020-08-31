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

// Binary Search Tree 二叉查找树
// 使用递归实现

public class BST<Key extends Comparable<Key>, Value> {

    private Node root;  // 二叉树的根节点

    // 定义二叉树节点
    public class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int N;  // 节点计数器
        private int H;  // 节点高度

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    // 为何不使用size(x.left) + size(x.right) + 1计算x的计数器？
    // 将值保存在变量N中，避免每次使用都需要递归，方便rank()和select()接口实现。提高性能。
    public int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    // 使用类似添加N的方法添加H求高度（空间线性级别，查询耗时常数级别）
    public int height() {
        return height(root);
    }

    public int height(Node x) {
        if (x == null) return 0;
        else return x.H;
    }

    // 使用递归求高度（用时线性级别，所需空间和树的高度成正比）
    public int heightRecursion() {
        return heightRecursion(root);
    }

    public int heightRecursion(Node x) {
        if (x == null) return 0;    // 这里不能只使用x.left = null和x.right = null中的一个来判断！
        return (heightRecursion(x.left) >= heightRecursion(x.right) ? heightRecursion(x.left) :
                heightRecursion(x.right)) + 1;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public Value get(Node x, Key key) {
        if (x == null) return null; // 空节点，说明二叉查找树中没有该key
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public boolean contains(Key key) {
        return contains(root, key);
    }

    public boolean contains(Node x, Key key) {
        if (x == null) return false;    // 已经递归说明上一层没有找到，如果x为null，则找不到key了！
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return contains(x.left, key);
        else if (cmp > 0) return contains(x.right, key);
        else return true;
        // 可以直接调用get()接口，来判断是否存在key！
    }

    public void put(Key key, Value val) {
        // 查找key，如果找到就更新值；否则添加一个新的节点。
        root = put(root, key, val);
    }

    public Node put(Node x, Key key, Value val) {
        // 如果key存在于以x为根节点的子树中，则更新它的值，否则创建一个新的节点加入到该子树中
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        // 更新计数器的值
        x.N = size(x.left) + size(x.right) + 1; // 以x为根节点的子树所有计数器的值，沿着路径向上更新
        // 更新树高
        x.H = (height(x.left) >= size(x.right) ? height(x.left) : size(x.right)) + 1;
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    // 最小值一定在左子树中
    public Node min(Node x) {
        if (x.left == null) return x;   // 左子树不能为空
        return min(x.left);
        // if (x == null) return null;  // min()接口会有问题，所以应该先判断左子树不为null。
        // return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    // 最大值一定在右子树中
    public Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    // 向上取整
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    public Node ceiling(Node x, Key key) {
        if (x == null) return null; // 这里也分两种情况：左子树和右子树中返回null
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);  // 向上取整，首先去右子树中寻找节点
        Node t = ceiling(x.left, key);  // 当左子数中存在大于等于key的键值时，向上取整才会出现在左子数中。
        if (t != null)
            return t;   // 左子数中找到的t一定比x小，但是大于等于key
        else
            return x;   // 如果没有找到，退而求其次，选择x作为向上取整的结果返回。
    }

    // 向下取整
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    public Node floor(Node x, Key key) {
        if (x == null) return null;
        // 如果进入右子树中得到null，说明整个二叉树有问题，不然左子数中应该有结果！
        // 如果进入左子数中得到null，说明没有节点小于等于key，这样的向下取整不存在！
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x; // 判断根节点是否=优先
        if (cmp < 0) return floor(x.left, key);
        // 只要去了右边，就说明x小于key。
        Node t = floor(x.right, key);   // 当右子树中存在小于等于key的键值时，向下取整才会出现在右子树中。
        if (t != null)
            return t;   // 找到的这个t比x大，但是小于等于key
        else
            return x;   // 如果没有找到，退而求其次，使用x返回作为向下取整的结果。

    }

    // 选择操作
    public int rank(Key key) {
        int k = rank(root, key);    // 小于k键的数量
        return k;
    }

    // 小于x.key的键的数量有k个(0 ~ k - 1)，则x.key排名为k
    public int rank(Node x, Key key) {
        // 返回以x为根节点的子树中，小于x.key键的数量
        if (x == null) return 0;    // 递归结束：向上return！
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(x.left, key);
        else if (cmp > 0)
            return rank(x.right, key) + size(x.left) + 1; // 左子数和根节点全部小于x.key //递归性！
        else return size(x.left);
    }

    public Key select(int k) {
        Node x = select(root, k);
        if (x == null) return null;
        return x.key;
    }

    // 下标从0开始！排名第k为第k+1个元素
    public Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);   // 以左子数计数器为标准！
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1); // 右子树中查找"剩余"排名
        else return x;  // 先按递归寻找路径，最后再比较根节点！
    }

    // 删除最大最小键
    // 不断深入左子数，寻找最小节点
    public void deleteMin() {
        root = deleteMin(root);
    }

    public Node deleteMin(Node x) {
        // if (x.left == null) return null; // 这里没有考虑右子树，根节点被删除后，就找不到右子树了！
        if (x.left == null)
            return x.right; // 这里检查的其实是:x.left.left == null；所以在删除左子数的时候把x.left.right右子树（所有节点小于x根节点）链接到x.left保证大小关系。
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        x.H = (height(x.left) >= size(x.right) ? height(x.left) : size(x.right)) + 1;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    // 不断深入右子树寻找最大值
    public Node deleteMax(Node x) {
        if (x.right == null)
            return x.left; // 当x.right.right = null时，删除x.right需要把x.right.left加入到x.right；否则将找不到这个左子数。
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        x.H = (height(x.left) >= size(x.right) ? height(x.left) : size(x.right)) + 1;
        return x;
    }

    // 删除任意键
    public void delete(Key key) {
        root = delete(root, key);
    }

    public Node delete(Node x, Key key) {
        if (x == null) return null; // 没有找到要删除的键
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);  // 深入左子树；"x.left ="是因为需要按路径向上更新以x.left为根节点的子树！
        else if (cmp > 0) x.right = delete(x.right, key);   // 深入右子树
        else {
            // 需要从右子树（后继节点）中找到最小键节点作为新的根节点
            if (x.right == null) return x.left;
            if (x.left == null)
                return x.right; // 只有一个子树（而非两个节点）的情况也需要考虑到！// x.left = x.left.right 或者 x.right = x.right.right。
            Node t = x; // 保存当前待删除节点
            // x = min(x.right);
            x = min(x.right);   // 后继节点中寻找新的根节点
            x.right = deleteMin(t.right);   // 删除最小键节点，并链接到x根节点
            x.left = t.left;    // 左子树链接到x根节点
        }
        x.N = size(x.left) + size(x.right) + 1; // 沿路径向上更新计数器
        x.H = (height(x.left) >= size(x.right) ? height(x.left) : size(x.right)) + 1;   // 更新树高
        return x;
    }

    Iterable<Key> keys() {
        return keys(min(), max());
    }

    Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    // 使用中序遍历（左子树->根->右子树）
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;  // x =  null除了参数非空判断，还说明下界远比最小元素小或者上界远比最大元素大，且已经找完了所有节点，直接返回即可！
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        // 中序遍历：保证queue中的key是有序的！
        if (cmplo < 0) keys(x.left, queue, lo, hi);     // lo < x.key 下界还在左子树中
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key); // 范围内的key!
        if (cmphi > 0) keys(x.right, queue, lo, hi);    // hi > x.key 上界还在右子树中
    }

    public static void main(String[] args) {

        BST<String, Integer> st = new BST<String, Integer>();

        // stdin : zhang fei after reading java
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        StdOut.println("height :" + st.height());
        StdOut.println("height recursion :" + st.heightRecursion());

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
        StdOut.println("floor :" + st.floor("error"));  // 测试null
        if (st.contains("fei")) StdOut.println("fei true");
        else StdOut.println("fei false");
    }
}
