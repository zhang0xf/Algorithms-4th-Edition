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

// Josephus survival problem
// notice : 不需要使用环形队列，只需要把安全的人接着放在队列尾部继续进行即可。

public class Josephus {

    public static void main(String[] args) {

        // Queue<int> killCircle = new Queue<int>(); // error

        Queue<Integer> queue = new Queue<Integer>();

        int m = Integer.parseInt(args[0]); // 总人数
        int n = Integer.parseInt(args[1]); // 被杀号码

        for (int i = 0; i < m; i++) {
            queue.enqueue(i);
        }

        while (!queue.isEmpty()) {
            for (int j = 0; j < n - 1; j++) {   // n -1 will be safe and add them behand the queue
                queue.enqueue(queue.dequeue());
            }
            StdOut.print(queue.dequeue() + " "); // kill the poor guy
        }
        StdOut.println();
    }
}
