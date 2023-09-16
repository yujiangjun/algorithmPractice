package dachang.class15;

public class Code06_BestTimeToBuyAndSellStockWithTransactionFee {


    public static int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        int[] buy = new int[n];
        int[] sell = new int[n];
        buy[0] = -prices[0] - fee;
        sell[0] = 0;
        for (int i = 1; i < n; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i] - fee);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[n - 1];
    }

    public static int maxProfit2(int[] prices, int fee) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        int buy = -prices[0] - fee;
        int sell = 0;
        int preBuy, preSell;
        for (int i = 1; i < n; i++) {
            preBuy = buy;
            preSell = sell;
            buy = Math.max(preBuy, preSell - prices[i] - fee);
            sell = Math.max(preSell, preBuy + prices[i]);
        }
        return sell;
    }
}
