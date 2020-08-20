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

// 处理器当前任务状态
// use with
public class CPUState implements Comparable<CPUState> {

    private TaskForSPT[] tasks; // 任务集合
    private int N;  //当前任务数量

    public CPUState(int max) {
        tasks = new TaskForSPT[max];
        N = 0;
    }

    public int size() {
        return N;
    }

    public Double total() {
        Double time = 0.0;
        for (int i = 0; i < N; i++) {
            time += tasks[i].getTask_time();
        }
        return time;
    }

    // 添加任务
    public void insert(TaskForSPT t) {
        tasks[N++] = t;
    }

    @Override

    public int compareTo(CPUState that) {
        return this.total().compareTo(that.total());
    }

    public static void main(String[] args) {

    }
}
