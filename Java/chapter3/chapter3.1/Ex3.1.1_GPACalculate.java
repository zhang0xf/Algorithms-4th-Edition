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
// 依赖：SequentialSearchSTByList.java

public class GPACalculate {

    public static void main(String[] args) {

        SequentialSearchSTByList<String, Double> st
                = new SequentialSearchSTByList<String, Double>();

        // ctrl + d 结束标准输入
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            Double d = StdIn.readDouble();
            st.put(s, d);
        }

        st.show();
    }
}
