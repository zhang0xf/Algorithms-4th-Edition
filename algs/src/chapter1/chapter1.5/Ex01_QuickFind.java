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

// with file tinyUf.txt

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex01_QuickFind {

    private int times;
    private int[] id;
    private int count;

    public Ex01_QuickFind(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        count = N;
        times = 0;
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        times += 1;
        return id[p];
    }

    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) return;
        for (int i = 0; i < id.length; i++) {
            if (pID == id[i]) {
                times += 1;
                id[i] = qID;
                times += 1;
            }
        }
        count--;
    }

    public int times() {
        return times;
    }

    public void printArray() {
        for (int i = 0; i < id.length; i++) {
            StdOut.print(id[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        Ex01_QuickFind QF = new Ex01_QuickFind(N);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if (QF.connected(p, q)) {
                continue;
            }

            QF.union(p, q);
            StdOut.println(p + " -> " + q);
            QF.printArray();
            StdOut.println("access to array times total : " + QF.times());
        }

        StdOut.println(QF.count() + " components ");
    }
}
