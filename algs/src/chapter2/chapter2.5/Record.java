/******************************************************************************
 *  Compilation:  javac Record.java
 *  Execution:    java Record
 *  Dependencies: StdOut.java
 *
 *  A data type that implements the Comparable interface.
 *
 *  % java Record
 *      54 Sedgewick
 *      65 Wayne
 *     234 Bob
 *     343 Kevin
 *     555 Hello
 *   56454 World
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Record implements Comparable<Record> {
    private final String name;
    private final long val;

    public Record(String name, long val) {
        this.name = name;
        this.val = val;
    }

    public int compareTo(Record that) {
        if (this.val < that.val) return -1;
        else if (this.val > that.val) return +1;
        else return 0;
    }

    public String toString() {
        return String.format("%9d %10s", val, name);
    }

    /**
     * Compares this record to the specified object.
     *
     * @param other the other transaction
     * @return true if this record is equal to {@code other}; false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Record that = (Record) other;
        return (this.val == that.val) && this.name.equals(that.name);
    }

    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + name.hashCode();
        hash = 31 * hash + ((Long) val).hashCode();
        return hash;
    }


    public static void main(String[] args) {
        Record r1 = new Record("Hello", 555);
        Record r2 = new Record("World", 56454);
        Record r3 = new Record("Kevin", 343);
        Record r4 = new Record("Wayne", 65);
        Record r5 = new Record("Bob", 234);
        Record r6 = new Record("Sedgewick", 54);
        Record r7 = new Record("World", 56454);
        Record r8 = new Record("World", 555);
        Record r9 = new Record("Hello", 56454);
        Record[] records = { r1, r2, r3, r4, r5, r6, r7, r8, r9 };
        Arrays.sort(records);
        StdOut.printf("%9s %10s\n", "val", "name");
        StdOut.println("--------------------");
        for (int i = 0; i < records.length; i++)
            StdOut.println(records[i]);
    }
}
