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

// compelete brackets ： （通过栈）补全算术表达式的左括号
// before : 1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
// after : （（ 1 + 2） * （ （ 3 - 4 ）* （ 5 - 6 ） ） ）
// 我的思路：得到整个String再输出   (no)
// 作者思路：增量输出    (yes)

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Ex09_CompelteBrackets {

    private static final char LEFT_PAREN = '(';
    private static final char RIGHT_PAREN = ')';

    public static String CompleteBrackets(String s) {

        Stack<String> exp = new Stack<String>();
        Stack<Character> ops = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == ' ')
                continue;

            if (s.charAt(i) == RIGHT_PAREN) {   // bug : 这个条件一开始进不来，检查发现是中文的右括号和英文的右括号这个BUG
                if (exp.isEmpty() || ops.isEmpty())
                    return null;
                String v = exp.pop();
                String str = LEFT_PAREN + " " + exp.pop() + " " + ops.pop() + " " + v + " "
                        + RIGHT_PAREN;
                exp.push(str);
            }
            else if (s.charAt(i) == '+') {
                ops.push(s.charAt(i));
            }
            else if (s.charAt(i) == '-') {
                ops.push(s.charAt(i));
            }
            else if (s.charAt(i) == '*') {
                ops.push(s.charAt(i));
            }
            else if (s.charAt(i) == '/') {
                ops.push(s.charAt(i));
            }
            else {
                exp.push(s.charAt(i) + ""); // change char to String
            }
        }

        if (exp.isEmpty())
            return null;
        else
            return exp.pop();
    }

    public static void main(String[] args) {
        In in = new In();
        // trim() : remove space before head / after tail
        String s = in.readAll().trim();
        String result = CompleteBrackets(s);
        StdOut.println(result);
    }
}
