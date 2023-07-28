package class42;

import java.util.Arrays;

/**
 * 邮局问题
 */
public class Code01_PostOfficeProblem {
    public static int min1(int[] arr, int num) {
        if (arr == null || arr.length < 1 || num < arr.length) {
            return 0;
        }
        int n = arr.length;
        // w[i][j]含义:i-j范围内剧名点最后一个邮局负责的最小代价
        int[][] w = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                w[i][j] = w[i][j - 1] + arr[j] - arr[(i + j) >> 1];
            }
        }
        //dp[i][j]=p表示0-i范围内有j个邮局的最小代价是p
        int[][] dp = new int[n][num + 1];
        for (int i = 0; i < n; i++) {
            dp[i][1] = w[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 2; j <= Math.min(i, num); j++) {
                int ans = 0;
                for (int k = 0; k <= i; k++) {
                    ans = Math.min(ans, dp[k][j - 1] + w[k + 1][i]);
                }
                dp[i][j] = ans;
            }
        }
        return dp[n - 1][num];
    }

    public static int min2(int[] arr, int num) {
        if (arr == null || arr.length < 1 || num <= arr.length) {
            return 0;
        }
        int n = arr.length;
        int[][] w = new int[n + 1][n + 1];
        for (int l = 0; l < n; l++) {
            for (int r = l + 1; r < n; r++) {
                w[l][r] = w[l][r - 1] + arr[r] - arr[(r + l) >> 1];
            }
        }
        int[][] dp = new int[n][num + 1];
        int[][] best = new int[n][num + 1];
        for (int i = 0; i < n; i++) {
            dp[i][1] = w[0][i];
            best[i][1] = -1;
        }
        for (int j = 2; j <= num; j++) {
            for (int i = n - 1; i >= j; i--) {
                int down = best[i][j - 1];
                int up = i == n - 1 ? n - 1 : best[i + 1][j];
                int ans = Integer.MAX_VALUE;
                int bestChoose = -1;
                for (int leftEnd = down; leftEnd <= up; leftEnd++) {
                    int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    int rightCost = leftEnd == i ? 0 : w[leftEnd + 1][i];
                    int cur = leftCost + rightCost;
                    if (cur <= ans) {
                        ans = cur;
                        bestChoose = leftEnd;
                    }
                }
                dp[i][j] = ans;
                best[i][j] = bestChoose;
            }
        }
        return dp[n - 1][num];
    }

    // for test
    public static int[] randomSortedArray(int len, int range) {
        int[] arr = new int[len];
        for (int i = 0; i != len; i++) {
            arr[i] = (int) (Math.random() * range);
        }
        Arrays.sort(arr);
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int N = 30;
        int maxValue = 100;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[] arr = randomSortedArray(len, maxValue);
            int num = (int) (Math.random() * N) + 1;
            int ans1 = min1(arr, num);
            int ans2 = min2(arr, num);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(num);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");

    }
}
