package dachang.class15;

public class Code05_BestTimeToBuyAndSellStockWithCoolDown {


    public static int maxProfit1(int[] prices) {
        return process1(prices, false, 0, 0);
    }

    public static int process1(int[] prices, boolean buy, int index, int buyPrices) {
        if (index >= prices.length) {
            return 0;
        }
        if (buy) {
            int noSell = process1(prices, true, index + 1, buyPrices);
            int yesSell = prices[index] - buyPrices + process1(prices, false, index + 2, 0);
            return Math.max(noSell, yesSell);
        } else {
            int noBuy = process1(prices, false, index + 1, 0);
            int yesBuy = process1(prices, true, index, prices[index]);
            return Math.max(noBuy, yesBuy);
        }
    }

    public static int maxProfit2(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        int[] buy = new int[n];
        int[] sell = new int[n];
        buy[0] = -prices[0];
        sell[0] = 0;
        buy[1] = Math.max(buy[0], 0 - prices[1]);
        sell[1] = Math.max(sell[0], buy[0] + prices[1]);
        for (int i = 2; i < n; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[n - 1];
    }

    public static int maxProfit3(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        int buy, sell;
        int temp;
        // 1位置
        temp = -prices[0];
        buy = Math.max(temp, -prices[1]);
        sell = Math.max(0, temp + prices[1]);
        int lastSell1 = Math.max(0, prices[1] - prices[0]);
        int lastSell2 = 0;
        int lastBuy1 = buy;
        for (int i = 2; i < n; i++) {
            buy = Math.max(lastBuy1, lastSell2 - prices[i]);
            sell = Math.max(lastSell1, lastBuy1 + prices[i]);
            lastSell2 = lastSell1;
            lastSell1 = sell;
            lastBuy1 = buy;
        }
        return sell;
    }
}
