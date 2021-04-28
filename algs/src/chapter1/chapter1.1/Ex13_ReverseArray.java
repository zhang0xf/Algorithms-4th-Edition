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

public class Ex13_ReverseArray {

    public static void initArr(int[][] arr, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = i + j;
            }
        }
    }

    public static void printArr(int[][] arr, int m, int n) {

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                StdOut.print(arr[i][j] + "  ");
            }
            StdOut.println();
        }
    }

    public static int[][] reverseArr(int[][] arr, int m, int n) {
        int[][] temp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp[i][j] = arr[j][i];
            }
        }
        return temp;
    }

    public static void main(String[] args) {

        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        int[][] arr = new int[m][n];

        initArr(arr, m, n);
        StdOut.println("Original array is :");
        printArr(arr, m, n);

        int[][] reversedArr = reverseArr(arr, m, n);
        StdOut.println("Reversed array is :");
        printArr(reversedArr, n, m);
    }
}
