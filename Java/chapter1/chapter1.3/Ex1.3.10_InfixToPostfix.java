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

// mid-oder to postorder : 中序表达式转换为后序表达式算法
// preorder : 不含括号的算术表达式，而且将运算符写在前面，操作数卸载后面的表达式，又称“波兰式“。举例：(a + b) * (c + d) 转换为 *,+,a,b,+,c,d
// mid-order : 正常的表达式
// postorder : 与前序表达式相反；举例：2 * 3 / ( 2 - 1 ) + 3 * ( 4 - 1 ) 转换为 2 3 * 2 1 - / 3 4 1 - * +
// complete brackets: 补全括号：( ( ( 2 * 3 ) / ( 2 - 1 ) ) + ( 3 * ( 4 - 1 ) ) )
// notice : 作者的解法是建立在括号写全的基础上，但是如上式“2 * 3 / ( 2 - 1 ) + 3 * ( 4 - 1 )”则无法得到正确结果，因为没有足够的右括号！
// PS : 如果转换前序表达式，则相反；遇到操作符直接输出，遇到数字入栈，遇到右括号数字出栈！

public class InfixToPostfix {

    public static void main(String[] args) {

        Stack<String> stack = new Stack<String>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
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
