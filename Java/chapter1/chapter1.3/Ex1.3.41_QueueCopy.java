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

// prompt : 从q中取出所有元素，再分别插入q和r
// notice : Java中字符串直接赋值为引用的传递，拷贝应使用 String S = new String(name)

public class QueueCopy<Item> {

    private Item[] a;
    private int N;

    public QueueCopy(int max) {
        a = (Item[]) new Object[max];
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public int capcity() {
        return a.length;
    }

    public int size() {
        return N;
    }

    public Item getItemByIndex(int idx) {
        return a[idx];
    }

    public QueueCopy(QueueCopy<Item> q) {
        a = (Item[]) new Object[q.capcity()];
        for (int i = 0; i < q.size(); i++) {
            insert(q.getItemByIndex(i)); // bug : lost object "q" and result is null
        }
    }

    public void insert(Item item) {
        if (a.length == N) {
            resize(a.length * 2);
        }
        a[N++] = item;
    }

    public Item delete() {
        Item temp = a[0];
        for (int i = 1; i < N; i++) {
            a[i - 1] = a[i];
        }
        N--;
        return temp;
    }

    public void printQueue() {
        for (int i = 0; i < N; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {

        QueueCopy<Integer> queue = new QueueCopy<Integer>(10);

        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.printQueue();

        QueueCopy<Integer> rqueue = new QueueCopy<Integer>(queue);
        rqueue.printQueue();
        rqueue.delete();
        rqueue.printQueue();
        queue.printQueue();
    }
}
