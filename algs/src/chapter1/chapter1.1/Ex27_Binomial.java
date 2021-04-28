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

// binomial problem:
// <<Algorithm4>>'s function "binomial(100, 50, 0.25)" creates a lot of repeated calls and the repeated calls meets the "Yang Hui triangle" which indicates that repeated calls Exponential rises.
// solution: store the returns to reduce repeated calls and improve performance.

import edu.princeton.cs.algs4.StdOut;

public class Ex27_Binomial {

    private static int count = 0;
    private static int count2 = 0;

    private static double[][] result;

    public static double binomial(int n, int k, double p) {
        count++;
        StdOut.println(count);
        if (n == 0 && k == 0) return 1.0;
        if (n < 0 || k < 0) return 0.0;
        return (1 - p) * binomial(n - 1, k, p) + p * binomial(n - 1, k - 1, p);
    }

    public static double binomial2(int n, int k, double p) {

        count2++;
        StdOut.println(count2);

        if (n == 0 && k == 0) return 1.0;
        if (n < 0 || k < 0) return 0.0;

        if (result[n][k] == -1) {
            result[n][k] = (1 - p) * binomial2(n - 1, k, p) + p * binomial2(n - 1, k - 1, p);
        }

        return result[n][k];
    }

    public static double binomialprev(int n, int k, double p) {

        result = new double[n + 1][k + 1];

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < k + 1; j++) {
                result[i][j] = -1;
            }
        }

        return binomial2(n, k, p);
    }

    public static void main(String[] args) {

        // StdOut.println(binomial(50, 25, 0.25));
        StdOut.println(binomialprev(100, 50, 0.25));
    }
}
