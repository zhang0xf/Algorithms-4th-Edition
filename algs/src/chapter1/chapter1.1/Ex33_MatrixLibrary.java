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

// Matrix（矩阵） library

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Ex33_MatrixLibrary {

    // vector dot
    public static double dotVector(double[] x, double[] y) {
        if (x.length != y.length)
            return 0.0;
        double dotPro = 0.0;
        for (int i = 0; i < x.length; i++) {
            dotPro += x[i] * y[i];
        }
        return dotPro;
    }

    // matrix mult matrix
    // a(m, p) AND b(p, n)
    public static double[][] multMatrix(double[][] a, double[][] b) {
        double[][] multMatr = new double[a.length][b[0].length];
        if (a[0].length != b.length)
            return multMatr;
        double[][] transMatrix = Ex33_MatrixLibrary.transposeMatrix(b);
        StdOut.println("transpose Matrix:");
        Ex33_MatrixLibrary.printMatrix(transMatrix);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < transMatrix.length; j++) {
                for (int m = 0; m < a[i].length; m++) {
                    multMatr[i][j] += a[i][m] * transMatrix[j][m];
                }
            }
        }
        return multMatr;
    }

    // transpose matrix
    public static double[][] transposeMatrix(double[][] a) {
        double[][] transMatrix = new double[a[0].length][a.length];
        for (int i = 0; i < transMatrix.length; i++) {
            for (int j = 0; j < transMatrix[0].length; j++) {
                transMatrix[i][j] = a[j][i];
            }
        }
        return transMatrix;
    }

    // matrix mult vector
    public static double[] multMatrix(double[][] a, double[] x) {
        double[] multMatr = new double[a.length];
        if (a[0].length != x.length)
            return multMatr;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < x.length; j++) {
                multMatr[i] += a[i][j] * x[j];
            }
        }
        return multMatr;
    }

    // vector mult matrix
    public static double[] multMatrix(double[] y, double[][] a) {
        double[] multMatr = new double[a[0].length];
        if (y.length != a.length)
            return multMatr;
        double[][] transMatrix = Ex33_MatrixLibrary.transposeMatrix(a);
        for (int i = 0; i < transMatrix.length; i++) {
            for (int j = 0; j < y.length; j++) {
                multMatr[i] += y[j] * transMatrix[i][j];
            }
        }
        return multMatr;
    }

    // print matrix
    public static void printMatrix(double[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                StdOut.printf(" %f ", a[i][j]);
            }
            StdOut.println();
        }
    }

    // print vector
    public static void printVector(double[] v) {
        for (int i = 0; i < v.length; i++) {
            StdOut.printf(" %f ", v[i]);
        }
        StdOut.println();
    }

    // initial matrix
    public static void initialMatrix(double[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                a[i][j] = StdRandom.uniform(1.0, 10.0);
            }
        }
    }

    // test
    public static void main(String[] args) {
        // Matrix A
        double[][] a = new double[3][2];
        Ex33_MatrixLibrary.initialMatrix(a);
        // Matrix B
        double[][] b = new double[2][4];
        Ex33_MatrixLibrary.initialMatrix(b);
        // Vector M
        double[] m = new double[2];
        m[0] = 1.0;
        m[1] = 2.0;
        // Vector N
        double[] n = new double[2];
        n[0] = 1.0;
        n[1] = 2.0;

        StdOut.println("vector dot test:");
        double result = Ex33_MatrixLibrary.dotVector(m, n);
        StdOut.printf("vector dot = %f\n", result);
        StdOut.println();

        StdOut.println("Matrix mult Matrix test:");
        Ex33_MatrixLibrary.printMatrix(a);
        Ex33_MatrixLibrary.printMatrix(b);
        Ex33_MatrixLibrary.printMatrix(Ex33_MatrixLibrary.multMatrix(a, b));
        StdOut.println();

        StdOut.println("Matrix mult vector test:");
        Ex33_MatrixLibrary.printMatrix(a);
        Ex33_MatrixLibrary.printVector(m);
        Ex33_MatrixLibrary.printVector(Ex33_MatrixLibrary.multMatrix(a, m));
        StdOut.println();

        StdOut.println("Vector mult matrix test:");
        Ex33_MatrixLibrary.printVector(m);
        Ex33_MatrixLibrary.printMatrix(b);
        Ex33_MatrixLibrary.printVector(Ex33_MatrixLibrary.multMatrix(m, b));
        StdOut.println();
    }
}
