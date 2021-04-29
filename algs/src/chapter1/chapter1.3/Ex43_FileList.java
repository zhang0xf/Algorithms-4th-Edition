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

// list all files bellow one filefloder by queue
// 由于接口返回的是数组，顾没有使用队列！掌握基本思想之后，队列也会做！

import edu.princeton.cs.algs4.StdOut;

import java.io.File;

public class Ex43_FileList {

    public void listFiles(String path, int tab) {
        File fp = new File(path);
        File[] list = fp.listFiles();
        for (File file : list) {
            for (int j = 0; j < tab; j++) {
                StdOut.print("\t");
            }
            StdOut.println(file.getName());
            if (file.isDirectory()) {
                listFiles(file.getAbsolutePath(), tab + 1);
            }
            StdOut.print();
        }
    }

    public static void main(String[] args) {
        Ex43_FileList file = new Ex43_FileList();
        String path = "d:\\Code\\Algorithms-Java\\hello";
        file.listFiles(path, 0);
    }
}
