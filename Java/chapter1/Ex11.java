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

public class Ex11 {
    public static void main(String[] args) {

        int m = parseInt(args[0]);
        int n = parseInt(args[1]);

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
