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

import java.util.Arrays;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class Ex15 {

    public static void printArr(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            StdOut.print(arr[i] + " ");
        }
        StdOut.println();
    }

    public static void initArr(int[] arr, int max, int min) {

        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(max - min);
        }
    }

    public static int sumEle(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static int[] histogram(int[] arr, int m) {
        int[] temp = new int[m];

        Arrays.sort(arr);
        StdOut.println("sorted array is :");
        printArr(arr);

        for (int i = 0; i < m; i++) {
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == i)
                    count++;
            }
            temp[i] = count;
        }

        int max = arr[arr.length - 1];

        StdOut.println(max);

        if (max > 0 && max <= m - 1) {
            String message = "all elements in range : 0 ~ " + (m - 1) + " arr.length = "
                    + arr.length + " sum of all elements is :" + sumEle(temp);
            StdOut.println(message);
        }

        return temp;
    }

    public static void main(String[] args) {

        int m = parseInt(args[0]);

        int[] testArr = new int[30];

        initArr(testArr, m, 0);

        printArr(testArr);

        int[] result = histogram(testArr, m);

        for (int i = 0; i < result.length; i++) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        printArr(result);
    }
}
