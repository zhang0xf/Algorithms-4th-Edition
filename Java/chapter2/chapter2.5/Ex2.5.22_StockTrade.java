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

// 股票交易
// use with MinPriorityQueue.java and StockInfo.java and MaxPoiorityQueue.java
public class StockTrade {

    public class StockTradeInfo {
        private long num;   // 股票交易数量
        private Double price;   // 股票交易价格

        public Double getPrice() {
            return price;
        }

        public long getNum() {
            return num;
        }
    }

    private MinPriorityQueue<StockInfo> pq1;   // 卖家队列
    private MaxPoiorityQueue<StockInfo> pq2;   // 买家队列

    public StockTrade(int max) {
        pq1 = new MinPriorityQueue<StockInfo>(max);
        pq2 = new MaxPoiorityQueue<StockInfo>(max);
    }

    // 卖股票：股票代码 + 最低卖出价
    public void soldStock(StockInfo s) {
        pq1.insert(s);
    }

    // 买股票：股票代码 + 最高买入价
    public void buyStock(StockInfo s) {
        pq2.insert(s);
    }

    public boolean less(StockInfo v, StockInfo w) {
        return v.price().compareTo(w.price()) < 0;
    }

    // 确定交易
    public StockTradeInfo deal() {
        if (less(pq2.max(), pq1.min())) return null;
        StockTradeInfo temp = new StockTradeInfo();
        while (!pq1.isEmpty() && !pq2.isEmpty() && !less(pq2.max(), pq1.min())) {
            temp.price = pq2.max().price(); // 出价大于最低定价，出价作为交易价格
            pq1.delMin();
            temp.num++;
        }
        return temp;
    }

    // 显示交易
    public void showTrade(StockTradeInfo info) {
        if (info != null) {
            StdOut.println("stock trade price is " + info.getPrice());
            StdOut.println("stock trade num is " + info.getNum());
        }
        else
            StdOut.println("no trade");
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        StockTrade t = new StockTrade(N);

        t.soldStock(new StockInfo("spaceX", 3.5));
        t.soldStock(new StockInfo("spaceX", 3.4));
        t.soldStock(new StockInfo("spaceX", 3.32));
        t.soldStock(new StockInfo("spaceX", 3.1));

        t.buyStock(new StockInfo("spaceX", 2.2));
        t.showTrade(t.deal());

        t.buyStock(new StockInfo("spaceX", 2.8));
        t.showTrade(t.deal());

        // t.buyStock(new StockInfo("spaceX", 3.2));
        // t.showTrade(t.deal());

        t.buyStock(new StockInfo("spaceX", 3.4));
        t.showTrade(t.deal());
    }
}
