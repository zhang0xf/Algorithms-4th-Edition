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

// array exercise

import edu.princeton.cs.algs4.StdOut;

// import static java.lang.Integer.parseInt;

public class Ex30_ArrayExercise {

    public static void printArray(boolean[][] arr) {
        for (int i = 0; i < arr.length + 1; i++) {
            if (i == 0) {
                StdOut.printf("*   ");
                for (int m = 1; m < arr.length + 1; m++)
                    StdOut.printf("%d     ", m - 1);
                StdOut.println();
            }
            else {
                for (int j = 0; j < arr[i - 1].length + 1; j++) {
                    if (j == 0)
                        StdOut.printf("%d   ", i - 1);
                    else if (arr[i - 1][j - 1])
                        StdOut.printf("%s  ", "true");
                    else if (!arr[i - 1][j - 1])
                        StdOut.printf("%s ", "false");
                }
                StdOut.println();
            }
        }
    }

    // 辗转相除法求最大公约数，如果最大公约数为1即互质;
    public static int maximumCommonDivisor(int max, int min) {
        if (max == 0 || min == 0)
            return -1;
        if (max == min)
            return max;
        if (max < min) {
            max = max ^ min;
            min = max ^ min;
            max = max ^ min;
        }
        if (max % min == 0)
            return min;
        return maximumCommonDivisor(min, max % min);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        boolean[][] arr = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (Ex30_ArrayExercise.maximumCommonDivisor(i, j) == 1)
                    arr[i][j] = true;
                else
                    arr[i][j] = false;
            }
        }
        Ex30_ArrayExercise.printArray(arr);
    }
}
