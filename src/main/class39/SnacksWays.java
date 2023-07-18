package class39;

@SuppressWarnings("all")
public class SnacksWays {


    public static int way1(int[] arr, int w) {
        return process(arr, 0, w);
    }

    // 当来到index号零食,背包容量还剩rest
    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return -1;//无效解
        }
        if (index == arr.length) {
            return 1;
        }
        int p1 = process(arr, index + 1, rest);
        int p2 = process(arr, index + 1, rest - arr[index]);
        return p1 + (p2 != -1 ? p2 : 0);
    }

    public static int way2(int[] arr, int w) {
        int n = arr.length;
        int[][] dp = new int[n + 1][w + 1];
        for (int i = 0; i <= w; i++) {
            dp[n][i] = 1;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 1; rest <= w; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest >= arr[index]) {
                    dp[index][rest] += dp[index + 1][rest - arr[index]];
                }
            }
        }
        return dp[0][w];
    }

    public static int way3(int[] arr, int w) {
        int n = arr.length;
        // dp[i][j]含义:在0~index号零食中选择,体积是j的方式有几个
        int[][] dp = new int[n][w + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        if (arr[0] <= w) {
            dp[0][arr[0]] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= w; j++) {
                dp[i][j] = dp[i - 1][j] + (j - arr[i] >= 0 ? dp[i-1][j - arr[i]] : 0);
            }
        }
        int ans = 0;
        for (int i = 0; i <= w; i++) {
            ans += dp[n - 1][i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 4, 3, 2, 9 };
        int w = 8;
        System.out.println(way1(arr, w));
        System.out.println(way2(arr, w));
        System.out.println(way3(arr, w));
    }
}
