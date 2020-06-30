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

import java.util.Iterator;

public class ResizingArrayDeque<Item> implements Iterable<Item> {

    private Item[] a = (Item[]) new Object[1];
    private int N = 0;

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

    public void pushLeft(Item item) {
        if (a.length == N) {
            resize(2 * a.length);
        }

        for (int i = N - 1; i >= 0; i--) {
            a[i + 1] = a[i];
        }
        a[0] = item;
        N++;
    }

    public void pushRight(Item item) {
        if (a.length == N) {
            resize(2 * a.length);
        }

        a[N++] = item;
    }

    public Item popLeft() {
        Item temp = a[0];

        if (N > 0 && a.length / 4 == N) {
            resize(a.length / 2);
        }

        for (int i = 1; i < N; i++) {
            a[i - 1] = a[i];    //String类型"="传递的是引用！
        }

        N--;    // BUG：少了N--会导致数据混乱
        return temp;
    }

    public Item popRight() {
        Item temp = a[--N];
        a[N] = null;

        if (N > 0 && a.length / 4 == N) {
            resize(a.length / 2);
        }

        N--;
        return temp;
    }

    public Iterator<Item> iterator() {
        return new ResizingArrayDequeIterator();
    }

    private class ResizingArrayDequeIterator implements Iterator<Item> {

        private int i = 0;

        @Override
        public boolean hasNext() {  // 正向迭代器
            return i < N;
        }

        @Override
        public Item next() {
            return a[i++];
        }

        @Override
        public void remove() {
            //
        }
    }

    public static void main(String[] args) {

        ResizingArrayDeque<String> s = new ResizingArrayDeque<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                s.pushLeft(item);
            }
        }

        StdOut.printf("pop one item from left : %s\n", s.popLeft());

        StdOut.printf("pop one item from right : %s\n", s.popRight());

        Iterator<String> it = s.iterator();
        while (it.hasNext()) {
            String str = it.next();
            StdOut.print(str + " ");
        }
        StdOut.println();

    }
}
