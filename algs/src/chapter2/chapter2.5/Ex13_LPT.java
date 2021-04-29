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

// 负载均衡（最长处理时间优先原则）
// use with MinPriorityQueueExtend.java and TaskForSPT.java and CPUState.java
// 注：处理器优先级高代表当前任务时间总和长

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex13_LPT {

    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);  // 处理器数量
        int N = Integer.parseInt(args[1]);  // 任务数量上限
        MinPriorityQueueExtend<CPUState> pq = new MinPriorityQueueExtend<CPUState>(
                M);  // 维护一个处理器优先队列
        TaskForSPT[] t = new TaskForSPT[N]; // 数组只存储引用，需要创建N个TaskForSPT对象！
        int i = 0;
        int k = 0;
        while (!StdIn.isEmpty()) {
            String name = StdIn.readString();
            Double time = StdIn.readDouble();
            t[i++] = new TaskForSPT(name, time); // 创建N个TaskForSPT对象
            k++;
        }

        for (int j = 0; j < M; j++) {
            CPUState c = new CPUState(10);
            c.insert(t[j]); // 绑定一个任务
            pq.insert(c);   // 插入优先队列
        }

        for (int j = M; j < k; j++) {
            // 优先级低的cpu出列
            CPUState temp = pq.delMin();
            // 添加任务
            temp.insert(t[j]);
            // 重新加入优先队列
            pq.insert(temp);
        }

        while (!pq.isEmpty()) {
            CPUState c = pq.delMin();
            StdOut.println("task num : " + c.size() + " total time : " + c.total());
        }
    }
}
