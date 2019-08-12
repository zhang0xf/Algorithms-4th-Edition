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

// remove repeat elements

import java.util.Arrays;

public class Ex28 {

    // sorted array detection
    public static boolean detectArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int idx = Ex28.indexof(arr, arr[i], 0, arr.length - 1);
            if (idx != -1 && idx != i)
                return false;
        }
        return true;
    }

    public static int indexof(int[] arr, int key, int low, int high) {
        int lo = low;
        int hi = high;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > arr[mid])
                lo = mid + 1;
            else if (key < arr[mid])
                hi = mid - 1;
            else
                return mid;
        }
        return -1;
    }

    public static int[] removeRepeatElements(int[] arr) {
        int[] tempArray = new int[arr.length];
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            int idx = Ex28.indexof(arr, arr[i], 0, arr.length - 1);
            if (idx != -1 && idx != i)
                continue;
            else
                tempArray[j] = arr[i];
            j++;
        }

        int[] newArray = new int[j];
        System.arraycopy(tempArray, 0, newArray, 0, j);
        StdOut.printf("newArray size = %d\n", j);
        return newArray;
    }

    public static void main(String[] args) {
        // read the integers from a file
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();

        // printf size
        StdOut.printf("whitelist's size = %d\n", whitelist.length);

        // sort the array
        Arrays.sort(whitelist);

        // detetion result
        if (Ex28.detectArray(Ex28.removeRepeatElements(whitelist)))
            StdOut.println("remove success ...");
        else
            StdOut.println("remove failed ...");
    }
}
