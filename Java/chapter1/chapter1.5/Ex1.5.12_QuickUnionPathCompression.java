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

// 路径压缩的quick-union算法
// 更好的解法见作者解法！

public class QuickUnionPathCompression {

    private int[] id;
    private int count;
    private int[] pc;

    public QuickUnionPathCompression(int N) {
        id = new int[N];
        pc = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        count = N;
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // 思想：记录路径上的所有成员，最后一起连接
    // 作者思想：先找出root，然后重来一遍，逐个连接到root，该过程不产生额外的空间和更多的数组访问！
    public int find(int p) {
        int cnt = 0;
        while (p != id[p]) {
            pc[cnt] = p;
            p = id[p];
            cnt++;
        }

        for (int i = 0; i < cnt; i++) {
            id[pc[i]] = p;
        }

        return p;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count--;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickUnion QU = new QuickUnion(N);

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
