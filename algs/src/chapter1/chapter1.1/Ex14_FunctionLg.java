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

// import static java.lang.Integer.parseInt;

// math function : lg()

public class Ex14_FunctionLg {

    public static int lg(int N) {

        int count = 0;
        for (int n = N; n > 0; n /= 2) {
            count++;
            if (n / 2 < 2)
                break;
        }
        return count;
    }

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);

        int maxInt = lg(n);

        String message = "max int less than log2^" + n + "is :";
        StdOut.println(message);
        StdOut.println(maxInt);
    }
}
