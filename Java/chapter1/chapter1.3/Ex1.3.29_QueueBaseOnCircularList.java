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

// queue by circluar list : 只能使用一个Node的实例变量（last）

public class QueueBaseOnCircularList<Item> {

    private Node last;
    private int N;

    // 链表的递归属性
    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return 0 == N; // last == null 是否可以判断环形链表为空，答：不可以，有悖论；链表为空需要手动last = null
    }

    public int size() {
        return N;
    }

    // add an item at tail
    public void enqueue(Item item) {
        Node Oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            last.next = last;   // 相当于第一次加入节点
        }
        else {
            last.next = Oldlast.next; // Oldlast.next 必须先进行，否则会丢失表头，无法形成环！
            Oldlast.next = last;    // 加入新节点
        }
        N++;
    }

    // delete an item at head
    public Item dequeue() {
        Item temp = last.next.item; // last.next 永远指向表头

        if (isEmpty()) {
            last = null;
        }
        else {
            last.next = last.next.next; // 重新成环
        }
        N--;
        return temp;
    }

    // test if exist a ring
    public boolean testRing() {
        // fast and slow
        Node faster = last;
        Node slower = last;
        int n = 0;

        while (!isEmpty()) {
            // 问题：成功比较两次之后陷入无限循环，queue size = 4
            //StdOut.printf("n = %d\n", n);
            //StdOut.printf("queue size = %d\n", size());
            faster = faster.next.next;
            slower = last.next;
            if (faster.item == slower.item) { // 需要虚函数重写比较方法
                return true;
            }
            n++;
        }
        return false;
    }

    public static void main(String[] args) {

        QueueBaseOnCircularList<String> s = new QueueBaseOnCircularList<String>();

        // test result:
        // while (!StdIn.isEmpty()) {
        //     String item = StdIn.readString();
        //     if (!item.equals("-")) {
        //         s.enqueue(item);
        //     }
        //     else if (!s.isEmpty()) {
        //         StdOut.print(s.dequeue() + " ");
        //     }
        // }
        // StdOut.println("( " + s.size() + "left on stack )");

        // test ring
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                s.enqueue(item);
            }
            else if (!s.isEmpty()) {
                // test if there is a ring in the queue;
                if (s.testRing()) {
                    StdOut.printf("there is a ring in the queue!\n");
                }
                StdOut.print(s.dequeue() + " ");
            }
        }
    }
}
