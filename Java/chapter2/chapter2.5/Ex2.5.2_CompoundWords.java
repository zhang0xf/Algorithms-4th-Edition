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

// 组合词
public class CompoundWords {

    private static boolean less(String v, String w) {
        return v.length() < w.length();
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

    public static String[] findCompound(String[] s) {
        String[] temp = new String[s.length];
        int m = 0;
        sort(s);
        for (int i = 2; i < s.length; i++)
            for (int j = i - 1; j >= 0; j--)
                for (int k = j - 1; k >= 0; k--)
                    if ((s[i].equals(s[k] + s[j]) || s[i].equals(s[j] + s[k])) && m < s.length)
                        temp[m++] = s[i];

        return temp;
    }

    public static void show(String[] s) {
        for (int i = 0; i < s.length; i++) {
            StdOut.print(s[i] + " ");
        }
        StdOut.println();
    }

    // 测试时要求数组大小与字符串数量一致
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        String[] s = new String[N];
        int i = 0;
        while (!StdIn.isEmpty() && i < N) {
            s[i++] = StdIn.readString();
        }
        show(s);
        String[] result = CompoundWords.findCompound(s);
        show(s);
        show(result);
    }
}
