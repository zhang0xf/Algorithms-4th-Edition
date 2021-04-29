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

// LIFO : 下压栈（能够动态调整数组大小的实现）,添加泛型和迭代器

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ResizingArrayStackGeneric<Item> implements Iterable<Item> {

    private Item[] a = (Item[]) new Object[1];  // stack elements
    private int N = 0;  // elements size

    public boolean isEmpty() {
        return 0 == N;
    }

    public int size() {
        return N;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void push(Item item) {
        if (a.length == N) {
            resize(2 * a.length);
        }
        a[N++] = item;
    }

    public Item pop() {
        Item item = a[--N];
        a[N] = null;    // avoid dissociate : can not find this memory
        if (N > 0 && a.length / 4 == N) {
            resize(a.length / 2);
        }
        return item;
    }

    // error next section
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    // public Iterable<Item> iterator() {
    //     return new ReverseArrayIterator();
    // }
    // compile error：Iterable接口里定义了返回iterator的方法，iterator是迭代器

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        // reverse iterator : 反向迭代器
        public Item next() {
            return a[--i];
        }

        // null
        public void remove() {
        }
    }

    public static void main(String[] args) {
        ResizingArrayStackGeneric<String> s;
        s = new ResizingArrayStackGeneric<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                s.push(item);

                // 1 way to use iterator
                // Iterator<String> it = s.iterator();
                // while (it.hasNext()) {
                //     String str = it.next();
                //     StdOut.print(str + " ");
                // }

                // 2 way to use iterator
                for (String str : s) {
                    StdOut.print(str + " ");
                }
                StdOut.println();
            }
            else if (!s.isEmpty()) {
                // StdOut.print(s.pop() + " ");
            }
        }

    }
}
