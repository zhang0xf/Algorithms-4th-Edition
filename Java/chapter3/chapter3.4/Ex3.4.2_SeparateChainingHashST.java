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

// 拉链法实现散列表
// 依赖：SequentialSearchST.java（无序链表）

public class SeparateChainingHashST<Key extends Comparable<Key>, Value> {

    //  private int N;  // 键值对数量
    private int M;  // 散列表大小
    private SequentialSearchST<Key, Value>[] st;    // 数组的对象是链表

    public SeparateChainingHashST() {
        this(997);  // 使用997条链表
    }

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        st[hash(key)].put(key, val);
    }

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>();

        StdOut.println("zhangfei hash =" + st.hash("zhangfei"));
        st.put("zhangfei", 1);
        StdOut.println(
                "st[" + st.hash("zhangfei") + "]" + " size = " + st.st[st.hash("zhangfei")].size());
        st.put("zhangfei", 2);
        StdOut.println(
                "st[" + st.hash("zhangfei") + "]" + " size = " + st.st[st.hash("zhangfei")].size());

        for (String key : st.st[st.hash("zhangfei")].keys()) {
            StdOut.println("value : " + st.st[st.hash("zhangfei")].get(key));
        }

        StdOut.println("java hash = " + st.hash("java"));
        st.put("java", 2);

        StdOut.println("reading hash = " + st.hash("reading"));
        st.put("reading", 4);
    }
}
