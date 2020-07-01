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


// random queue base on array
// notice : use with Card.java

public class RandomQueue<Item> {

    private Item[] a;
    private int N;

    // 构造函数
    public RandomQueue(int max) {
        a = (Item[]) new Object[max];
        N = 0;
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void enqueue(Item item) {
        if (a.length == N) {
            resize(a.length * 2);
        }
        a[N++] = item;
    }

    // 随机返回一个元素，取样且放回
    public Item sample() {
        int idx = StdRandom.uniform(N);
        return a[idx];
    }

    // 随机返回一个元素，取样且不放回
    public Item dequeue() {
        int idx = StdRandom.uniform(N);  //result always : 0 ~ N - 1
        Item temp = a[idx];
        a[idx] = a[N - 1];
        a[N - 1] = temp;

        temp = a[--N];
        a[N] = null;
		
		if (N > 0 && a.length / 4 == N) {
            resize(a.length / 2);
        }

        return temp;
    }

    public static void main(String[] args) {

        // test random api
        int num = StdRandom.uniform(2); // result is always 0 and 1
        StdOut.printf("random num is : %d\n", num);

        // 编译错误：java找不到类Card。解决方法：将Card.java单独编译一遍即可。
        // 延申：Card.java与本文件在同一个文件夹下，不需要import。
        RandomQueue<Card> r = new RandomQueue<Card>(60);

        for (int i = 0; i < 52; i++) {  // 52张牌
            // Card card; // 编译错误，在下面for循环中提示未初始化的变量。
            Card card = new Card();
            card.setCardValue(i);
            card.setCardName("");
            r.enqueue(card);
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                StdOut.print(r.dequeue().getCardValue() + " ");
            }
            StdOut.println();
        }
        StdOut.println();
    }
}
