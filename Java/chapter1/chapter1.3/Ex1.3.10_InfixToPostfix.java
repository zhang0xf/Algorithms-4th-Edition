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

// mid-oder to postorder
// test args : ( ( ( 2 * 3 ) / ( 2 - 1 ) ) + ( 3 * ( 4 - 1 ) ) ) * need all brackets *
// preorder : (a + b) * (c + d) => *,+,a,b,+,c,d
// postorder : 2 * 3 / ( 2 - 1 ) + 3 * ( 4 - 1 ) => 2 3 * 2 1 - / 3 4 1 - * +


public class InfixToPostfix {

    public static void main(String[] args) {

        Stack<String> stack = new Stack<String>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("+") || s.equals("-") /*|| s.equals("*") || s.equals("/")*/) {
                stack.push(s);
            }
            else if (s.equals("(")) {
                StdOut.print("");
            }
            else if (s.equals(")")) {
                StdOut.print(stack.pop() + " ");    //若括号不全，则栈中操作符无法及时弹出！
            }
            else {
                StdOut.print(s + " ");
            }
        }

        StdOut.println();
    }
}

