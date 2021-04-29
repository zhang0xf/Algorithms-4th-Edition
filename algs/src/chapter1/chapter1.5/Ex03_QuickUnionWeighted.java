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

// 加权quick-union算法！
// with file tinyUF.txt mediumUf.txt largeUF.txt

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex03_QuickUnionWeighted {

    private int[] sz;   // 权重：当前触点所在分量总和！
    private int[] id;
    private int count;  // 分量数量

    public Ex03_QuickUnionWeighted(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;  // 初始权重均为1表示自身
        }
        count = N;
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        // 低权重树连接到高权重树
        if (sz[pRoot] <= sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        count--;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        Ex03_QuickUnionWeighted QU = new Ex03_QuickUnionWeighted(N);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if (QU.connected(p, q)) {
                continue;
            }

            QU.union(p, q);
            StdOut.println(p + " -> " + q);
        }

        StdOut.println(QU.count() + " components ");
    }
}
