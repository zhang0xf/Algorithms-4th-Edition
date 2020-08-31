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
// 使用非递归实现

public class BSTNoRecursion<Key extends Comparable<Key>, Value> {

    private Node root;  // 二叉查找树根节点

    public class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int N;  // 节点计数器

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return root.N;
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        Node z = new Node(key, val, 1);
        if (root == null) {
            root = z;
            return;
        }
        Node parent = null;
        Node x = root;
        while (x != null) {
            parent = x;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {
                x.val = val;
                return; // 替换不需要改变计数器
            }
        }
        int cmp = key.compareTo(parent.key);
        if (cmp < 0) parent.left = z;
        else /* if (cmp > 0) */ parent.right = z;

        // 再次沿着路径遍历，更新计数器。路径一定是固定的！
        x = root;
        while (x != null) {
            x.N += 1;
            cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {
                x.N -= 1;
                return;
            }
        }
    }

    public Key min() {
        // 默认root不为null
        Node x = root;
        while (x.left != null) {
            x = x.left;
        }
        return x.key;
    }

    public Key max() {
        Node x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    // 向上取整（小规模测试暂无问题，留待大规模测试）
    public Key ceiling(Key key) {
        Node x = root;
        Node le = null;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.key;
            if (cmp > 0) {
                x = x.right;    // 向右子树查找
                continue;   // 这里必须有continue，否则le会改变为错误的值
            }
            le = x; // 此时，x可能是最后的结果，要看左子树的结果
            x = x.left; // 向左子树查找
        }
        if (le != null) return le.key;
        return null;    // 所有节点均找不到大于等于key的值
    }

    // 向下取整（小规模测试暂无问题，留待大规模测试）
    public Key floor(Key key) {
        Node x = root;
        Node ri = null;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.key;
            if (cmp < 0) {
                x = x.left;    // key < x,key 向左子树查找
                continue;
            }
            ri = x;
            x = x.right;    // 向右子树查找较大值，如果没有就返回key > x.key时的key
        }
        if (ri != null) return ri.key;
        return null;    // 所有节点均找不到小于等于key的值
    }

    // 选择操作
    // 小规模测试暂无问题，留待大规模测试
    public int rank(Key key) {
        // 返回小于key的键的数量，需要使用节点计数器
        Node x = root;
        int tmp = 0;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;    // key < x.key 向左子树深入。
            else if (cmp > 0) {
                if (x.left == null) tmp += 0;   // 需要手动判断left是否为null
                else tmp += x.left.N + 1;
                x = x.right;    // 左子树和根满足条件，向右子树深入
            }
            else {
                if (x.left == null) tmp += 0;
                else tmp += x.left.N;   // 左子树的键个数即为答案。
                return tmp;
            }
        }
        return tmp;
    }

    // 小规模测试暂无问题，留待大规模测试
    public Key select(int k) {
        Node x = root;
        while (x != null) {
            int t = 0;
            if (x.left == null) t = 0;  // 不能少，递归接口在size()中封装了null情况，非递归需要判断一下
            else t = x.left.N;
            if (k < t) x = x.left;   // 左子树的计数器作为标准，如果k == x.left.N，则x.key就是排名为k的键
            else if (k > t) {
                x = x.right;
                // 此时，寻找的排名需要变化，左子树和根已经抵消一部分排名。
                k = k - t - 1;
            }
            else return x.key;
        }
        return null;    // 如果k超出了范围，x = null跳出循环，即没找到！
    }

    // 照抄递归思路是有问题的，不能遍历所有节点
    // 下面的方法借鉴作者，简直不是人能想到的！
    Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        Stack<Node> stack = new Stack<Node>();
        Node x = root;
        while (x != null || !stack.isEmpty()) {
            if (x != null) {
                stack.push(x);
                x = x.left;
            }
            else {  // x为null：遇到了空节点
                x = stack
                        .pop();    // 首先弹出空节点的父节点，根和左节点在栈中总是相邻的，所以在弹出根节点的时候，需要将右子树中的节点以根->左顺序加入栈中
                queue.enqueue(x.key);
                x = x.right;
            }
        }
        return queue;
    }

    // delete系列接口暂时不实现（比较难，也没有答案）

    public static void main(String[] args) {

        BSTNoRecursion<String, Integer> st = new BSTNoRecursion<String, Integer>();

        // stdin : zhang fei after reading java
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // StdOut.println("height :" + st.height());
        // StdOut.println("height recursion :" + st.heightRecursion());
        // StdOut.println("avgCompares :" + st.avgComaparesRecursion());

        // 打印
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();

        // 输入中得包含键"after"和"fei"
        // for (String s : st.keys("after", "fei"))
        //     StdOut.println(s + " " + st.get(s));
        // StdOut.println();

        // 测试deleteMax和deleteMin
        StdOut.println("min :" + st.min());
        // st.deleteMin();
        StdOut.println("max :" + st.max());
        // st.deleteMax();
        // st.delete("java");
        // for (String s : st.keys())
        //     StdOut.println(s + " " + st.get(s));
        // StdOut.println();

        StdOut.println("size :" + st.size());

        StdOut.println("ceiling :" + st.ceiling("before"));
        StdOut.println("ceiling :" + st.ceiling("wow"));
        StdOut.println("floor :" + st.floor("fei"));
        StdOut.println("floor :" + st.floor("error"));  // 测试null
        StdOut.println("select 2:" + st.select(2));
        StdOut.println("zhang rank :" + st.rank("zhang"));
        StdOut.println("java rank:" + st.rank("java"));
        StdOut.println("after rank :" + st.rank("after"));
        // if (st.contains("fei")) StdOut.println("fei true");
        // else StdOut.println("fei false");
    }
}
