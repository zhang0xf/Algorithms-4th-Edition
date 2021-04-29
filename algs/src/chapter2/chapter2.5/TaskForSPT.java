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

// 调度任务类，与SPT一起使用
public class TaskForSPT implements Comparable<TaskForSPT> {

    private String task_name;
    private Double task_time;

    public TaskForSPT() {
        task_name = null;
        task_time = 0.0;
    }

    public TaskForSPT(String name, Double time) {
        task_name = name;
        task_time = time;
    }

    @Override
    public int compareTo(TaskForSPT that) {
        return Double.compare(this.task_time, that.task_time);
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public void setTask_time(Double task_time) {
        this.task_time = task_time;
    }

    public String getTask_name() {
        return task_name;
    }

    public Double getTask_time() {
        return task_time;
    }

    public static void main(String[] args) {

    }
}
