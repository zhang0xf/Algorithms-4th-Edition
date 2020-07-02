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

// base on array

public class GeneralizedQueue<Item> {

    private Item[] a;
    private int N;

    public GeneralizedQueue(int max) {
        a = (Item[]) new Object[max];
    }


    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    public void insert(Item item) {
        if (a.length == N) {
            resize(a.length * 2);
        }
        a[N++] = item;
    }

    public Item delete(int k) {
        // assuming to start at 1
        if (k < 1)
            return null;
        Item temp = a[k - 1];
        for (int i = k - 1; i < N - 1; i++) {
            a[i] = a[i + 1];
        }
        a[--N] = null;
        return temp;
    }

    public void printQueue() {
        for (int i = 0; i < N; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {

        GeneralizedQueue<Integer> queue = new GeneralizedQueue<Integer>(10);
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);
        queue.insert(6);
        queue.insert(7);
        queue.insert(8);
        queue.printQueue();

        queue.delete(2);
        queue.printQueue();

        queue.delete(3);
        queue.printQueue();

    }
}
