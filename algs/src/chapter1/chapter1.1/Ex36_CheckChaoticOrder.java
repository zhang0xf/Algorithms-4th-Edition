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

// check chaotic order

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// import static java.lang.Integer.parseInt;

public class Ex36_CheckChaoticOrder {

    public static void printArray(double[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.printf("%d  ", (int) a[i]);
        }
        StdOut.println();
    }

    public static void printArray(double[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                StdOut.printf("%d  ", (int) a[i][j]);
            }
            StdOut.println();
        }
    }

    public static void initialArray(double[] a) {
        for (int j = 0; j < a.length; j++) {
            a[j] = j;
        }
    }

    public static void shuffle(double[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + StdRandom.uniform(n - i);   //*
            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static double[][] shuffleTest(int m, int n) {
        double[] a = new double[m];
        double[][] temp = new double[m][m];
        for (int i = 0; i < n; i++) {
            Ex36_CheckChaoticOrder.initialArray(a);
            Ex36_CheckChaoticOrder.shuffle(a);
            Ex36_CheckChaoticOrder.printArray(a);
            for (int j = 0; j < a.length; j++) {
                temp[(int) a[j]][j]++;
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        double[][] r = Ex36_CheckChaoticOrder.shuffleTest(m, n);
        StdOut.println();
        Ex36_CheckChaoticOrder.printArray(r);
    }
}
