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

// 文本编辑器的缓冲区。 提示 : 使用两个栈！

import java.util.Iterator;

public class TextEditorBuffer implements Iterable {

    Stack<Character> front = new Stack<Character>();    // before cursor 光标之前
    Stack<Character> after = new Stack<Character>();    // after cursor 光标之后

    public TextEditorBuffer() {
        // 构造函数，创建两个空栈！
    }

    // insert before default
    public void insert(char ch) {
        front.push(ch);
    }

    public char delete() {
        return front.pop();
    }

    // cursor move left
    public void left(int k) {
        for (int i = 0; i < k; i++) {
            after.push(front.pop());
        }
    }

    // cursor move right
    public void right(int k) {
        for (int i = 0; i < k; i++) {
            front.push(after.pop());
        }
    }

    public Iterator iterator() {
        return new TextEditorBufferIterator();
    }

    // 打印文本缓冲区的内容，这反而成为这道理的烦点，需要使用迭代器遍历栈！
    // 来源于网络，新的写法和思维
    private class TextEditorBufferIterator implements Iterator {

        Iterator<Character> it;

        Stack<Character> stack = new Stack<Character>();
        Queue<Character> queue = new Queue<Character>();

        public TextEditorBufferIterator() {
            // 构造函数
            Stack<Character> temp = new Stack<Character>();
            // after
            while (!after.isEmpty()) {
                stack.push(after.pop());
            }
            while (!stack.isEmpty()) {
                queue.enqueue(stack.pop());
            }
            while (!queue.isEmpty()) {
                char ch = queue.dequeue();
                after.push(ch);
                temp.push(ch);
            }

            // front
            while (!front.isEmpty()) {
                char ch = front.pop();
                stack.push(ch);
                temp.push(ch);
            }
            while (!stack.isEmpty()) {
                queue.enqueue(stack.pop());
            }
            while (!queue.isEmpty()) {
                char ch = queue.dequeue();
                front.push(ch);
            }

            it = temp.iterator();
        }

        @Override

        public boolean hasNext() {
            return it.hasNext(); // 此迭代器依赖于合并好的Stack的迭代器，但是对外是隐藏的！两层调用！
        }

        @Override
        public Object next() {
            return it.next();
        }

        @Override
        public void remove() {
            // ...
        }
    }

    public int size() {
        return front.size() + after.size();
    }

    public static void main(String[] args) {
        TextEditorBuffer buffer = new TextEditorBuffer();
        String str = "hello world!";
        for (int i = 0; i < str.length(); i++) {
            buffer.insert(str.charAt(i));
        }

        for (Object ch : buffer) {
            // 可以不关心迭代器的类型直接使用for循环去遍历，避免类型错误！
            StdOut.print(ch);
        }
        StdOut.println();

        buffer.left(5);
        buffer.delete();

        for (Object ch : buffer) {
            StdOut.print(ch);
        }
        StdOut.println();

        buffer.right(1);
        buffer.insert('f');
        for (Object ch : buffer) {
            StdOut.print(ch);
        }
        StdOut.println();
    }
}
