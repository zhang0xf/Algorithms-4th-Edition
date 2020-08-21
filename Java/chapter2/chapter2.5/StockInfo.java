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

// use with StockTrade.java
public class StockInfo implements Comparable<StockInfo> {

    private String code;    // 股票代码
    private Double price;   // 股票价格

    public StockInfo(String s, Double p) {
        code = s;
        price = p;
    }

    public Double price() {
        return price;
    }

    @Override

    public int compareTo(StockInfo that) {
        if (!this.code.equals(that.code)) throw new NullPointerException(); // 这里是随便抛出的一个异常
        return this.price.compareTo(that.price);
    }

    public static void main(String[] args) {

    }
}
