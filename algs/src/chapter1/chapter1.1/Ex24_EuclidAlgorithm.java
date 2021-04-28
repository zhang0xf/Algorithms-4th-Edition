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

// Euclid Algorithm : 辗转相除法求最大公约数

import edu.princeton.cs.algs4.StdOut;

// import static java.lang.Integer.parseInt;

public class Ex24_EuclidAlgorithm {

    public static int EuclidMaxComDivisor(int m, int n) {

        int max = 0;
        int min = 0;
        if (m < n) {
            max = n;
            min = m;
        }
        else {
            max = m;
            min = n;
        }

        if (max % min == 0)
            return min;

        return EuclidMaxComDivisor(min, max % min);
    }

    public static void main(String[] args) {

        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);

        int result = EuclidMaxComDivisor(m, n);

        String message = "max common divisor is :" + result;
        StdOut.println(message);
    }
}
