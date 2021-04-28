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

// simulate throwing dice（模拟掷色子）

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Ex35_SimulateDice {

    private static int sides = 6;
    private static double[] distS = new double[2 * sides + 1];

    public static int throwDice() {
        int d1 = StdRandom.uniform(1, 6);
        int d2 = StdRandom.uniform(1, 6);
        return d1 + d2;
    }

    public static void accurateProbability() {
        for (int i = 1; i <= sides; i++) {
            for (int j = 1; j <= sides; j++) {
                distS[i + j] += 1.0;
            }
        }
        for (int i = 2; i <= 2 * sides; i++) {
            distS[i] /= 36.0;
        }
    }

    public static void main(String[] args) {

        // initial accurate probability
        Ex35_SimulateDice.accurateProbability();

        double[] distP = new double[2 * sides + 1];
        for (int i = 2; i <= 2 * sides; i++) {
            distP[i] = 0.0;
        }
        double n = 1.0;
        boolean flag = true;
        while (flag) {
            StdOut.printf("n = %f\n", n);
            for (double i = 0; i < n; i++) {
                int idx = Ex35_SimulateDice.throwDice();
                distP[idx] += 1.0;
            }
            for (int i = 2; i <= 2 * sides; i++) {
                distP[i] /= n;
                if (Math.abs(distP[i] - distS[i]) >= 0.001) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                break;

            flag = true;
            n *= 2;
        }

        for (int i = 2; i <= 2 * sides; i++)
            StdOut.printf("myRandom probability[%d] = %f\n", i, distP[i]);
        StdOut.println();
        for (int i = 2; i <= 2 * sides; i++)
            StdOut.printf("theory probability[%d] = %f\n", i, distS[i]);
        StdOut.println();
    }
}
