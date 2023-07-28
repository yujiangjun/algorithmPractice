package class42;

public class ThrowChessPiecesProblem {

    public static int superEggDrop1(int kChess, int nLevel) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        return process1(nLevel, kChess);
    }

    /**
     * 还剩rest层楼,还有k个棋子
     * 在最坏的情况下确定最小的不会从这个楼层扔下棋子
     * 不会摔碎的最小操作次数
     */
    public static int process1(int rest, int k) {
        if (rest == 0) {
            return 0;
        }
        if (k == 1) {
            return rest;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i != rest + 1; i++) {
            // 在i层扔下摔碎的情况
            int p1 = process1(i - 1, k - 1);
            // 在i层扔下没有摔碎的情况
            int p2 = process1(rest - i, k);
            min = Math.min(min, Math.max(p1, p2));
        }
        return min + 1;
    }

    public static int superEggDrop2(int k, int n) {
        if (n < 1 || k < 1) {
            return 0;
        }
        if (k == 1) {
            return n;
        }
        int[][] dp = new int[n + 1][k + 1];
        for (int rest = 1; rest < dp.length; rest++) {
            dp[rest][1] = rest;
        }
        for (int rest = 1; rest <= n; rest++) {
            for (int j = 2; j <= k; j++) {

                int min = Integer.MAX_VALUE;
                for (int i = 1; i != rest + 1; i++) {
                    int p1 = dp[i - 1][j - 1];
                    int p2 = dp[rest - i][j];
                    min = Math.min(min, Math.max(p1, p2));
                }
                dp[rest][j] = min + 1;
            }
        }
        return dp[n][k];
    }

    public static int superEggDrop3(int k, int n) {
        if (n < 1 || k < 1) {
            return 0;
        }
        if (k == 1) {
            return n;
        }
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][1] = i;
        }
        int[][] best = new int[n + 1][k + 1];
        for (int i = 0; i <= k; i++) {
            dp[1][i] = 1;
            best[1][i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = k; j > 1; j--) {
                int ans = Integer.MAX_VALUE;
                int bestChoose = -1;
                int down = best[i - 1][j];
                int up = j == k ? i : best[i][j + 1];
                for (int first = down; first <= up; first++) {
                    int cur = Math.max(dp[first - 1][j - 1], dp[i - first][j]);
                    if (cur <= ans) {
                        ans = cur;
                        bestChoose = first;
                    }
                }
                dp[i][j] = ans + 1;
                best[i][j] = bestChoose;
            }
        }
        return dp[n][k];
    }

    public static int superEggDrop4(int k, int n) {
        if (n < 1 || k < 1) {
            return 0;
        }
        int[] dp = new int[k];
        int res = 0;
        while (true) {
            res++;
            int pre = 0;
            for (int i = 0; i < k; i++) {
                int tmp = dp[i];
                dp[i] = dp[i] + pre + 1;
                pre = tmp;
                if (dp[i] >= n) {
                    return res;
                }
            }
        }
    }

    public static int superEggDrop5(int k, int n) {
        if (n < 1 || k < 1) {
            return 0;
        }
        int bsTime = log2N(n) + 1;
        if (k >= bsTime) {
            return bsTime;
        }
        int[] dp = new int[k];
        int res = 0;
        while (true) {
            res++;
            int pre = 0;
            for (int i = 0; i < dp.length; i++) {
                int tmp = dp[i];
                dp[i] = dp[i] + pre + 1;
                pre = tmp;
                if (dp[i] >= n) {
                    return res;
                }
            }
        }
    }

    public static int log2N(int n) {
        int res = -1;
        while (n != 0) {
            res++;
            n >>>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int maxN = 500;
        int maxK = 30;
        int testTime = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxN) + 1;
            int K = (int) (Math.random() * maxK) + 1;
            int ans2 = superEggDrop2(K, N);
            int ans3 = superEggDrop3(K, N);
            int ans4 = superEggDrop4(K, N);
            int ans5 = superEggDrop5(K, N);
            if (ans2 != ans3 || ans4 != ans5 || ans2 != ans4) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }
}
