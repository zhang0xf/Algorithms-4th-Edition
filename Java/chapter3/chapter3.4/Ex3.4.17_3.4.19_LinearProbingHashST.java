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

//线性探测法实现散列表

public class LinearProbingHashST<Key extends Comparable<Key>, Value> {


    private int N;  // 键值对的总数
    private int M;  // 线性探测表的大小
    private Key[] keys; // 平行数组：键
    private Value[] vals;   // 平行数组：值

    public LinearProbingHashST(int M) {
        this.M = M;
        keys = (Key[]) new Comparable[M];
        vals = (Value[]) new Object[M];
    }

    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M; // 去除符号位取余
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> st = new LinearProbingHashST<Key, Value>(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                st.put(keys[i], vals[i]);
            }
        }
        keys = st.keys; // 新的平行数组
        vals = st.vals;
        M = st.M;
    }

    public void put(Key key, Value val) {
        if (N >= M / 2) resize(2 * M);
        int i = 0;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) { // 如果到达数组尾部，需要从0开始。
            if (key.equals(keys[i])) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (key.equals(keys[i])) return vals[i];
        }
        return null;
    }

    public boolean contains(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (key.equals(keys[i])) return true;
        }
        return false;
    }

    public void delete(Key key) {
        if (!contains(key)) return;
        int i = hash(key);
        while (!key.equals(keys[i]))    // 键key经过contains判断之后一定存在！
            i = (i + 1) % M;
        keys[i] = null; // 删除键key
        vals[i] = null; // 删除值val

        i = (i + 1) % M;    // 所有后续键值对的起点
        // 将后续的所有键值对重新插入
        while (keys[i] != null) {
            Key keytodo = keys[i];
            Value valtodo = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keytodo, valtodo);  // N++
            i = (i + 1) % M;
        }
        N--;    //删除元素 ，数量减1
        if (N > 0 && N == M / 8) resize(M / 2); // 动态调整散列表大小
    }

    Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++) {
            if (keys[i] != null)
                queue.enqueue(keys[i]);
        }

        return queue;
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>(60);

        st.put("zhang", 1);
        st.put("fei", 2);
        st.put("java", 3);
        st.put("reading", 4);
        st.put("after", 5);

        for (String key : st.keys()) {
            StdOut.print("key :" + key + " " + "value :" + st.get(key));
            StdOut.println();
        }
        StdOut.println();

        st.delete("zhang");
        for (String key : st.keys()) {
            StdOut.print("key :" + key + " " + "value :" + st.get(key));
            StdOut.println();
        }
        StdOut.println();
    }
}
