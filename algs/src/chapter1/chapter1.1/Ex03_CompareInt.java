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

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex03_CompareInt {
    public static void main(String[] args) {

        int[] arr = new int[3];

        int i = 0;

        while (!StdIn.isEmpty()) {
            arr[i] = StdIn.readInt();
            i++;
            if (i >= arr.length)
                break;
        }

        if (arr[0] == arr[1] && arr[0] == arr[2])
            StdOut.println("equal");
        else
            StdOut.println("not equal");
    }
}
