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

// FixedCapcotyStack : 定容栈

public class FixedCapcityStackOfStrings {

    private String[] a; // stack entries
    private int N;  // size

    public FixedCapcityStackOfStrings(int cap) {

        a = new String[cap];
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    public int size() {
        return N;
    }

    //bug : index from 0 to N -1,just simplify
    public void push(String item) {
        a[N++] = item;
    }

    public String pop() {
        return a[--N];
    }

    public static void main(String[] args) {

        FixedCapcityStackOfStrings s;
        s = new FixedCapcityStackOfStrings(100);
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
