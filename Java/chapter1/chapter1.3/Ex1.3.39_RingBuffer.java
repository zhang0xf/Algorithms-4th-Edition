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

// ring buffer base on fixed capcity array!

public class RingBuffer<Item> {

    private Item[] a;
    private int N;  // current nums
    private int head;   // pointer head
    private int tail;   // pointer tail

    public RingBuffer(int max) {
        a = (Item[]) new Object[max];
        head = 0;
        tail = 0;
        N = 0;
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    public int size() {
        return N;
    }

    public boolean isFull() {
        return N == a.length;   // "head == tail"can not be the condition,it could be empty!
    }

    public void enqueue(Item item) {
        if (isFull()) {
            StdOut.println("ring buffer is full, please wait !");
            return;
        }
        a[tail++] = item;
        if (tail == a.length)
            tail = 0;
        N++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            StdOut.println("ring buffer is empty, please wait !");
            return null;
        }
        Item temp = a[head];
        a[head++] = null;   // *
        if (head == a.length) {
            head = 0;
        }
        N--;
        return temp;
    }

    public void printQueue() {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    public static void main(String[] args) {
        RingBuffer<Integer> r = new RingBuffer<Integer>(5);
        r.enqueue(1);
        r.enqueue(2);
        r.enqueue(3);
        r.enqueue(4);
        r.enqueue(5);
        r.printQueue();


        r.dequeue();
        r.dequeue();
        r.dequeue();
        StdOut.println(" dequeue num is : " + r.dequeue());

        r.dequeue();
        StdOut.println(" head is : " + r.getHead());
        StdOut.println(" tail is : " + r.getTail());
        StdOut.println(" dequeue is : " + r.dequeue());
        r.dequeue();
        r.dequeue();
        StdOut.println(" head is : " + r.getHead());
        StdOut.println(" tail is : " + r.getTail());
        r.enqueue(6);
        StdOut.println(" head is : " + r.getHead());
        StdOut.println(" tail is : " + r.getTail());

        r.printQueue();

    }
}
