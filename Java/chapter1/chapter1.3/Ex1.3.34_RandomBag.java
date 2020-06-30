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

// random bag : 随机背包

import java.util.Iterator;

public class RandomBag<Item> implements Iterable<Item> {

    private Item[] a;
    private int N;

    //构造函数时创建定容背包
    public RandomBag(int max) {
        a = (Item[]) new Object[max];
        N = 0;
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    public int size() {
        return N;
    }

    public void add(Item item) {
        a[N++] = item;  // ignore segfault
    }

    public Iterator<Item> iterator() {
        return new RandomBagIterator();
    }

    public class RandomBagIterator implements Iterator<Item> {

        public RandomBagIterator() {
            shuffle(a);
        }

        private void shuffle(Item[] bag) {

            for (int i = 0; i < bag.length; i++) {
                int j = StdRandom.uniform(i, bag.length); // between i and bag.length,not include !
                Item temp = bag[i];
                bag[i] = bag[j];
                bag[j] = temp;
            }
        }

        @Override
        public boolean hasNext() {
            return N != 0;
        }

        @Override
        public Item next() {
            return a[--N];
        }

        @Override
        public void remove() {

        }
    }

    public static void main(String[] args) {

        int testNum = StdRandom.uniform(3, 5);
        StdOut.printf("random num is : %d\n", testNum); // testNum is always 4!!!

        RandomBag<String> s = new RandomBag<String>(5);

        s.add("String 1");
        s.add("String 2");
        s.add("string 3");
        s.add("String 4");
        s.add("String 5");

        Iterator<String> it = s.iterator();
        while (it.hasNext()) {
            String str = it.next();
            StdOut.print(str + " ");
        }
        StdOut.println();

    }
}
