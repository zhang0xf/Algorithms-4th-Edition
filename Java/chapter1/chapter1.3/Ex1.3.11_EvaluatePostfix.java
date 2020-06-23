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

// postorder value : 2 3 * 2 1 - / 3 4 1 - * +

public class EvaluatePostfix {

    public static void main(String[] args) {
        Stack<Double> stack = new Stack<Double>();

        while (!StdIn.isEmpty()) {

            String s = StdIn.readString();

            if (s.equals("+")) {
                double val = stack.pop();
                stack.push(stack.pop() + val);
            }
            else if (s.equals("-")) {
                double val = stack.pop();
                stack.push(stack.pop() - val);
            }
            else if (s.equals("*")) {
                double val = stack.pop();
                stack.push(stack.pop() * val);
            }
            else if (s.equals("/")) {
                double val = stack.pop();
                stack.push(stack.pop() / val);
            }
            else {
                stack.push(Double.parseDouble(s));
            }
        }
        if (!stack.isEmpty())
            StdOut.println(stack.pop());

    }
}
