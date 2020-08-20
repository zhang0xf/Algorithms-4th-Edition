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

// 调度（最短优先原则）
// use with MinPriorityQueue.java and TaskForSPT.java
public class SPT {

    // 创建一个面向最下元素的优先队列
    private MinPriorityQueue<TaskForSPT> pq;

    public SPT(int max) {
        pq = new MinPriorityQueue<TaskForSPT>(max);
    }

    public MinPriorityQueue<TaskForSPT> getQueue() {
        return pq;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        TaskForSPT[] t = new TaskForSPT[N]; // 数组只存储引用，需要创建N个TaskForSPT对象！
        int i = 0;
        while (!StdIn.isEmpty()) {
            TaskForSPT temp = new TaskForSPT(); // 创建N个TaskForSPT对象
            temp.setTask_name(StdIn.readString());
            temp.setTask_time(StdIn.readDouble());
            t[i++] = temp;
        }

        SPT sp = new SPT(N);
        for (int j = 0; j < i; j++) {
            sp.getQueue().insert(t[j]);
        }

        StdOut.println("pq size = " + sp.getQueue().size());

        while (!sp.getQueue().isEmpty()) {
            TaskForSPT temp = sp.getQueue().delMin();
            StdOut.print(temp.getTask_name() + " ");
            StdOut.print(temp.getTask_time());
        }
        StdOut.println();
    }
}
