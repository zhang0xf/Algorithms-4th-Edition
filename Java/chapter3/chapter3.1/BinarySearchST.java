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

// 符号表：有序平行数组

public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);  // 返回小于等于key的键的数量
        if (i < N && key.compareTo(keys[i]) == 0) return vals[i];
        else return null;
    }

    public void put(Key key, Value val) {
        // 省略resize
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public void delete(Key key) {
        if (isEmpty()) return;
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) {
            for (int j = i; j + 1 < N; j++) {
                keys[j] = keys[j + 1];
                vals[j] = vals[j + 1];
            }
            N--;
        }
    }

    public boolean contains(Key key) {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0)
            return true;
        return false;
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    public int size() {
        return N;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    // 向下取整
    public Key floor(Key key) {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0)
            return keys[i];
        else if (i > 0)
            return keys[i - 1];
        else
            return null;
    }

    // 向上取整
    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    // 小于key的键数量（此结构的核心）
    public int rank(Key key) {
        int lo = 0;
        int hi = N - 1;
        while (lo <= hi) {  // lo > hi循环结束
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp > 0) lo = mid + 1;
            else if (cmp < 0) hi = mid - 1;
            else return mid;
        }
        return lo;  // 可以证明，循环结束的时候，lo的值正好是小于等于key的键的数量（为什么不是hi？因为hi不是。）
    }

    // 排名为k的键:从0开始简化接口
    public Key selec(int k) {
        return keys[k];
    }

    public void deleteMin() {
        for (int i = 0; i + 1 < N; i++) {
            keys[i] = keys[i + 1];
            vals[i] = vals[i + 1];
        }
        N--;
    }

    public void deleteMax() {
        keys[N - 1] = null;
        vals[N - 1] = null;
        N--;
    }

    public int size(Key lo, Key hi) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (lo.compareTo(keys[i]) <= 0 && hi.compareTo(keys[i]) >= 0)
                count++;
        }
        return count;
    }

    Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < N; i++)
            queue.enqueue(keys[i]);
        return queue;
    }

    Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++) // rank()返回小于等于key的键的数量，与下标的关系
            queue.enqueue(keys[i]);
        if (contains(hi))
            queue.enqueue(keys[rank(hi)]);
        return queue;
    }

    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(100);

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
        StdOut.println("floor :" + st.floor("error"));  // 测试null

        if (st.contains("fei")) StdOut.println("fei true");
        else StdOut.println("fei false");
    }
}
