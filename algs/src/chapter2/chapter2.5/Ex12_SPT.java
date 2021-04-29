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

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// 调度（最短优先原则）
// use with MinPriorityQueueExtend.java and TaskForSPT.java

public class Ex12_SPT {

    // 创建一个面向最下元素的优先队列
    private MinPriorityQueueExtend<TaskForSPT> pq;

    public Ex12_SPT(int max) {
        pq = new MinPriorityQueueExtend<TaskForSPT>(max);
    }

    public MinPriorityQueueExtend<TaskForSPT> getQueue() {
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

        Ex12_SPT sp = new Ex12_SPT(N);
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
