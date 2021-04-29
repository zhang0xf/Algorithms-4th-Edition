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

// mid-oder to postorder：中序表达式变后序表达式
// test args : ( ( ( 2 * 3 ) / ( 2 - 1 ) ) + ( 3 * ( 4 - 1 ) ) ) * need all brackets *
// preorder : (a + b) * (c + d) => *,+,a,b,+,c,d
// postorder : 2 * 3 / ( 2 - 1 ) + 3 * ( 4 - 1 ) => 2 3 * 2 1 - / 3 4 1 - * +
// 中序表达式适合人理解，但不适合机器识别。机器使用后序表达式计算表达式的值。在《编译原理》中编译器就使用后序表达式。

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex10_InfixToPostfix {

    public static void main(String[] args) {

        Stack<String> stack = new Stack<String>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("+") || s.equals("-") /* || s.equals("*") || s.equals("/") */) {
                stack.push(s);
            }
            else if (s.equals("(")) {
                StdOut.print("");
            }
            else if (s.equals(")")) {
                StdOut.print(stack.pop() + " ");    // 若括号不全，则栈中操作符无法及时弹出！
            }
            else {
                StdOut.print(s + " ");
            }
        }

        StdOut.println();
    }
}

