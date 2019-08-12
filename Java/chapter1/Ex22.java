/******************************************************************************
 *  Compilation:  javac BinarySearch.java
 *  Execution:    java BinarySearch whitelist.txt < input.txt
 *  Dependencies: In.java StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/11model/tinyW.txt
 *                https://algs4.cs.princeton.edu/11model/tinyT.txt
 *                https://algs4.cs.princeton.edu/11model/largeW.txt
 *                https://algs4.cs.princeton.edu/11model/largeT.txt
 *
 *  % java BinarySearch tinyW.txt < tinyT.txt
 *  50
 *  99
 *  13
 *
 *  % java BinarySearch largeW.txt < largeT.txt | more
 *  499569
 *  984875
 *  295754
 *  207807
 *  140925
 *  161828
 *  [367,966 total values]
 *
 ******************************************************************************/

// show depth.

import java.util.Arrays;

public class Ex22 {

    /**
     * This class should not be instantiated.
     */
    private Ex22() {
    }

    public static int indexOf(int[] a, int key, int depth) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // print depth
            String message = "";
            for (int i = 0; i < depth * 2; i++) {
                message += " ";
            }
            message += " current depth : " + depth;
            message += " lo = " + lo;
            message += " hi = " + hi;
            StdOut.println(message);

            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
            depth++;
        }
        return -1;
    }

    public static void main(String[] args) {

        // read the integers from a file
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();

        // sort the array
        Arrays.sort(whitelist);

        // calculate depth by add arguments in indexOf() function
        int depth = 0;

        // read integer key from standard input; print if not in whitelist
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (Ex22.indexOf(whitelist, key, depth) == -1)
                StdOut.println(key);
        }
    }
}

