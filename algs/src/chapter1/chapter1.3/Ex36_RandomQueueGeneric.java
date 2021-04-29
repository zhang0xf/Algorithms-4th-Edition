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

// add random iterator base on RandomQueue.java
// notice : use with Card.java

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class Ex36_RandomQueueGeneric<Item> implements Iterable<Item> {

    private Item[] a;
    private int N;

    // 构造函数
    public Ex36_RandomQueueGeneric(int max) {
        a = (Item[]) new Object[max];
        N = 0;
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void enqueue(Item item) {
        if (a.length == N) {
            resize(a.length * 2);
        }
        a[N++] = item;
    }

    // 随机返回一个元素，取样且放回
    public Item sample() {
        int idx = StdRandom.uniform(N);
        return a[idx];
    }

    // 随机返回一个元素，取样且不放回
    public Item dequeue() {

        int idx = StdRandom.uniform(N);  // result always : 0 ~ N - 1
        Item temp = a[idx];
        a[idx] = a[N - 1];
        a[N - 1] = temp;

        temp = a[--N]; // cnt --
        a[N] = null;

        if (N > 0 && a.length / 4 == N) {
            resize(a.length / 2);
        }

        return temp;
    }

    public Iterator<Item> iterator() {
        return new RandomQueueGenericIterator();
    }

    private class RandomQueueGenericIterator implements Iterator<Item> {

        private int n = N;

        // 迭代器的构造函数中，对队列进行随机，就会使得迭代器看上去是随机的
        public RandomQueueGenericIterator() {
            shuffle(a);
        }

        private void shuffle(Item[] q) {
            for (int i = 0; i < n; i++) {
                int j = StdRandom.uniform(i, n);
                Item temp = q[i];
                q[i] = q[j];
                q[j] = temp;
            }
        }

        @Override
        public boolean hasNext() {
            return n > 0;
        }

        @Override
        public Item next() {
            return a[--n]; // no exception but we can not go wrong, so wo don't need i.
        }

        @Override
        public void remove() {
            //
        }
    }

    public static void main(String[] args) {

        Ex36_RandomQueueGeneric<Card> r = new Ex36_RandomQueueGeneric<Card>(60);

        for (int i = 0; i < 52; i++) {
            Card card = new Card();
            card.setCardValue(i);
            card.setCardName("");
            r.enqueue(card);
        }

        // test sample
        StdOut.println(r.sample().getCardValue());

        Iterator<Card> it = r.iterator();
        while (it.hasNext()) {
            StdOut.print(it.next().getCardValue() + " ");
        }
        StdOut.println();
    }
}
