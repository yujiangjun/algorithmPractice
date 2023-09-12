package dachang.class15;

public class Code04_BestTimeToBuyAndSellStoneIV {

    public static int maxProfit2(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k < 1) {
            return 0;
        }
        int n = prices.length;
        if (k >= n / 2) {
            return allTrans(prices);
        }
        int[][] dp = new int[n][k + 1];
        for (int j = 1; j <= k; j++) {
            /*
             dp[j][1]=Max{
                dp[j-1][1],
                dp[j-1][0]+ p[1]-p[1],
                dp[j-2][0]+ p[1]-p[0],
             }=Max{
                dp[j-1][1],
                best[j][1]+p[1]
             }
             dp[1][j]
             */
            int p1 = dp[0][j];
            int best = Math.max(dp[1][j - 1] - prices[1], dp[0][j - 1] - prices[0]);
            dp[1][j] = Math.max(p1, best + prices[1]);
            for (int i = 2; i < n; i++) {
                p1 = dp[i - 1][j];
                best = Math.max(dp[i][j - 1] - prices[i], best);
                dp[i][j] = Math.max(p1, best + prices[i]);
            }
        }

        return dp[n - 1][k];
    }

    public static int maxProfit(int K, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;
        if (K >= N / 2) {
            return allTrans(prices);
        }
        int[][] dp = new int[K + 1][N];
        int ans = 0;
        for (int tran = 1; tran < K; tran++) {
            int pre = dp[tran][0];
            int best = pre - prices[0];
            for (int index = 1; index < N; index++) {
                pre = dp[tran - 1][index];
                dp[tran][index] = Math.max(dp[tran][index - 1], prices[index] + best);
                best = Math.max(best, pre - prices[index]);
                ans = Math.max(dp[tran][index], ans);
            }
        }
        return ans;
    }

    public static int maxProfit3(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k < 1) {
            return 0;
        }
        int n = prices.length;
        if (k >= n / 2) {
            return allTrans(prices);
        }
        int[][] dp = new int[n][k + 1];

        for (int j = 1; j <= k; j++) {
            //dp[1][j]
            int p1 = dp[0][j];
            int best = Math.max(dp[1][j] - prices[1], dp[0][j - 1] - prices[0]);
            dp[1][j] = Math.max(best + prices[1], p1);
            for (int i = 2; i < n; i++) {
                p1 = dp[i - 1][j];
                best = Math.max(best, dp[i][j - 1] - prices[i]);
                dp[i][j] = Math.max(best + prices[i], p1);
            }
        }
        return dp[n - 1][k];
    }

    public static int allTrans(int[] prices) {
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            sum += Math.max(prices[i] - prices[i - 1], 0);
        }
        return sum;
    }
}
