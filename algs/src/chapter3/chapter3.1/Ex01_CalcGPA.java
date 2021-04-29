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

// 计算GPA
// SequentialSearchST.java

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex01_CalcGPA {

    public static void main(String[] args) {

        SequentialSearchST<String, Double> st
                = new SequentialSearchST<String, Double>();

        // ctrl + d 结束标准输入
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            Double d = StdIn.readDouble();
            st.put(s, d);
        }

        for (String key : st.keys()) {
            StdOut.print(key + " " + st.get(key) + " ");
        }
        StdOut.println();

        // calculate GPA
        Double GPA = 0.0;
        int N = 0;
        for (String key : st.keys()) {
            GPA += st.get(key);
            N++;
        }
        StdOut.println("GPA = " + GPA / N);
    }
}
