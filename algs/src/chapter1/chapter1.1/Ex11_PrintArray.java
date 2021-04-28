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

public class Ex11_PrintArray {
    public static void main(String[] args) {

        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);

        boolean[][] boolset = new boolean[m][n];

        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0) {
                    StdOut.print(j + "  ");
                }
                else if (j == 0 && i != 0) {
                    StdOut.print(i + "  ");
                }
                else if (boolset[i - 1][j - 1] == true) {
                    StdOut.print("*  ");
                }
                else if (boolset[i - 1][j - 1] == false) {
                    StdOut.print("   ");
                }
            }
            StdOut.println();
        }
    }
}
