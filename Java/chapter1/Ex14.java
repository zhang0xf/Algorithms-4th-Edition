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

import static java.lang.Integer.parseInt;

public class Ex14 {

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

        int n = parseInt(args[0]);

        int maxInt = lg(n);

        String message = "max int less than log2^" + n + "is :";
        StdOut.println(message);
        StdOut.println(maxInt);
    }
}
