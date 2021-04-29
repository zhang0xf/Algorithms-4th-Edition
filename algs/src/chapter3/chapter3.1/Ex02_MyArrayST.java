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

// 符号表：基于无序数组

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex02_MyArrayST<Key, Value> {

    private Key[] keys;
    private Value[] vals;    // 使用平行数组
    private int N;

    public Ex02_MyArrayST(int capacity) {
        keys = (Key[]) new Comparable[capacity];    // 无序的可以直接使用Object，有序的才需要使用Comparable
        vals = (Value[]) new Object[capacity];
    }

    public Value get(Key key) {
        for (int i = 0; i < N; i++) {
            if (key.equals(keys[i]))
                return vals[i];
        }
        return null;
    }

    // 省略resize
    public void put(Key key, Value val) {
        for (int i = 0; i < N; i++) {
            if (key.equals(keys[i])) {
                vals[i] = val;
                return;
            }
        }
        keys[N] = key;
        vals[N] = val;
        N++;
    }

    public void delete(Key key) {
        for (int i = 0; i < N; i++) {
            if (key.equals(keys[i])) {
                for (int j = i; j + 1 < N; j++) {
                    keys[j] = keys[j + 1];
                    vals[j] = vals[j + 1];
                }
                N--;
                return;
            }
        }
    }

    public boolean contains(Key key) {
        for (int i = 0; i < N; i++) {
            if (key.equals(keys[i]))
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

    // 可以使用自己实现了Iterator接口的泛型数据结构来作为返回类型！这里直接使用java的Queue
    Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < N; i++) {
            queue.enqueue(keys[i]);
        }
        return queue;
    }

    public static void main(String[] args) {
        Ex02_MyArrayST<String, Integer> st = new Ex02_MyArrayST<String, Integer>(100);
        // 秒啊
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));

        StdOut.println("size = " + st.size());
    }
}
