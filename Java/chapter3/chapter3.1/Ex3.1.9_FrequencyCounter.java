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

// 统计词频率

// java-introcs FrequencyCounter 8 < tale.txt
// business 122
// lastWord :faltering
// count :135643

// java-introcs FrequencyCounter 10 < tale.txt
// monseigneur 101
// lastWord :disfigurement
// count :135643

public class FrequencyCounter {
    public static void main(String[] args) {
        String lastWord = " ";
        int count = 0;
        int minlen = Integer.parseInt(args[0]); // 最小键长
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            count++;
            if (word.length() < minlen) continue;
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
            lastWord = word;
        }

        String max = " ";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }
        StdOut.println(max + " " + st.get(max));
        StdOut.println("lastWord :" + lastWord);
        StdOut.println("count :" + count);
    }
}
