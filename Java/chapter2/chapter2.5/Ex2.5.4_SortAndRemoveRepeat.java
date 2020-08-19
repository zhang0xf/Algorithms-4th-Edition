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

// 排序String数组，并且移除重复元素
public class SortAndRemoveRepeat {

    public static String[] dedup(String[] s) {
        int N = s.length;
        sort(s);

        // 删除重复元素
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                if (s[j].equals(s[i])) {
                    s[j] = null;    // 不移动元素
                }

        return s;
    }

    // 按照首字母排序
    private static boolean less(String v, String w) {
        return v.charAt(0) < w.charAt(0);
    }

    private static void exch(String[] s, int i, int j) {
        String t = s[i];
        s[i] = s[j];
        s[j] = t;
    }

    // 插入排序（稳定的排序：插入、归并）
    public static void sort(String[] s) {
        int N = s.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(s[j], s[j - 1]); j--) {
                exch(s, j, j - 1);
            }
        }
    }

    public static void show(String[] s) {
        for (int i = 0; i < s.length; i++) {
            StdOut.print(s[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        String[] s = new String[N];
        int i = 0;
        while (!StdIn.isEmpty() && i < N) {
            s[i++] = StdIn.readString();
        }
        show(s);
        SortAndRemoveRepeat.dedup(s);
        show(s);
    }
}
