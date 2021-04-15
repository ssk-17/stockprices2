import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StockPrices {
    public static void main(String[] args) {

        //edit this input to test various test cases.
        int[] stockPrices = new int[]{6, 5, 2, 4, 9, 6, 10, 3};

        System.out.println("Max Profit:" + getMaxProfit(stockPrices));
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        System.out.println(k + "th Highest element is:" + getKthHighestStockPrice(stockPrices, k));
    }

    private static int getMaxProfit(int[] stockPrices) {
        if (stockPrices == null) return -1; //invalid input
        int len = stockPrices.length;
        if (len <= 1) return 0;

        int maxProfit = 0; // 0 profit can be attained by buying and selling on same day.
        int maxStockPriceAfter = stockPrices[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            if (stockPrices[i] <= maxStockPriceAfter)
                maxProfit = Math.max(maxProfit, maxStockPriceAfter - stockPrices[i]);
            else {
                maxStockPriceAfter = stockPrices[i];
            }
        }
        return maxProfit;
    }

    private static int getKthHighestStockPrice(int[] stockPrices, int k) {
        // construct priority queue with size k elements
        // with default comparator, so it will be used as Min Heap.

        List<Integer> stockPricesList = Arrays.stream(stockPrices).boxed().collect(Collectors.toList());

        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(stockPricesList.subList(0, k));

        // add element to min heap if the element is greater than root of min heap
        for (int i = k; i < stockPricesList.size(); i++) {
            if (minHeap.peek() >= stockPricesList.get(i)) {
                continue;
            }
            //eliminate the min element from heap so that
            minHeap.poll();
            minHeap.add(stockPricesList.get(i));
        }

        // return the root of min-heap
        return minHeap.peek();
    }
}
