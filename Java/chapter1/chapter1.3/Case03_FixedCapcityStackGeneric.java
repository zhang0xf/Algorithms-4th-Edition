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

//FixedCapcityStackGeneric : 定容栈的泛型

public class FixedCapcityStackGeneric<Item> {

    private Item[] a;
    private int N;

    public FixedCapcityStackGeneric(int cap) {
        // ignore warning!
        a = (Item[]) new Object[cap]; // "a = new Item[];" is wrong in java!
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        a[N++] = item;
    }

    public Item pop() {
        return a[--N];
    }

    // test case:
    public static void main(String[] args) {
        FixedCapcityStackGeneric<String> s;
        s = new FixedCapcityStackGeneric<String>(100);

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                s.push(item);
            }
            else if (!s.isEmpty()) {
                StdOut.print(s.pop() + " ");
            }
        }
        StdOut.println("( " + s.size() + "left on stack )");
    }
}
