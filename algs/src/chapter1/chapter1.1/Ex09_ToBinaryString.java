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

import edu.princeton.cs.algs4.StdOut;

// import static java.lang.Integer.parseInt;    (no)
// Integer.parseInt(args);  (yes)

public class Ex09_ToBinaryString {
    public static void main(String[] args) {

        String s = "";

        if (args.length == 1) {
            //StdOut.println("enter");
            for (int n = Integer.parseInt(args[0]); n > 0; n = n / 2) {
                s = n % 2 + s;
            }
            StdOut.println(s);
        }
    }
}
