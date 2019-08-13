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

// BinarySearch find Equivalent key

import java.util.Arrays;

public class Ex29 {

    // return array elements(<key) nums
    public static int rank(int key, int[] arr) {
        int idx = Ex29.indexOf(arr, key);
        if (idx != -1) {
            while (idx > 0) {
                if (arr[idx] >= key)
                    idx--;
                else
                    return idx + 1;
            }
        }
        return -1;
    }

    // return array elements(=key) nums
    public static int count(int key, int[] arr) {
        int[] tempArray = new int[arr.length];
        System.arraycopy(arr, 0, tempArray, 0, arr.length);
        int count = 0;
        int idx = -1;
        while ((idx = Ex29.indexOf(tempArray, key)) != -1) {
            tempArray[idx] = key - 1;
            count++;
        }
        return count;
    }

    // binarysearch
    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {

        // read the integers from a file
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();

        // sort the array
        Arrays.sort(whitelist);

        // random key
        int key = whitelist[StdRandom.uniform(whitelist[whitelist.length - 1])];
        StdOut.printf("key = %d\n", key);

        // detection
        int lt = rank(key, whitelist);
        int eq = count(key, whitelist);

        int i = lt;
        StdOut.println("print all equal elements:");
        while (i <= lt + eq - 1) {
            StdOut.printf("%d ", whitelist[i]);
            i++;
        }
        StdOut.println();
        StdOut.printf("bound left: %d %d %d\n", whitelist[lt - 3], whitelist[lt - 2],
                      whitelist[lt - 1]);
        StdOut.printf("bound right: %d %d %d\n", whitelist[lt + eq], whitelist[lt + eq + 1],
                      whitelist[lt + eq + 2]);
    }
}
