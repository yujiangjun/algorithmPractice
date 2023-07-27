package class41;

public class Code03_StoneMerge {

    public static int[] sum(int[] arr) {
        int n = arr.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < arr.length; i++) {
            s[i + 1] = s[i] + arr[i];
        }
        return s;
    }

    public static int w(int[] s, int l, int r) {
        return s[r + 1] - s[l];
    }

    /**
     * 合并l..r范围内石子的最小得分
     */
    public static int process1(int l, int r, int[] s) {
        if (l == r) {
            return 0;
        }
        int next = Integer.MAX_VALUE;
        for (int leftEnd = l; leftEnd < r; leftEnd++) {
            next = Math.min(next, process1(l, leftEnd, s) + process1(leftEnd + 1, r, s));
        }
        return next + w(s, l, r);
    }

    public static int min1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int n = arr.length;
        int[] s = sum(arr);
        return process1(0, n - 1, s);
    }

    public static int min2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int n = arr.length;
        int[] s = sum(arr);
        int[][] dp = new int[n][n];
        //dp[i][i]=0;
        for (int l = n - 2; l >= 0; l--) {
            for (int r = l + 1; r < n; r++) {
                int next = Integer.MAX_VALUE;
                for (int leftEnd = l; leftEnd < r; leftEnd++) {
                    next = Math.min(next, dp[l][leftEnd] + dp[leftEnd + 1][r]);
                }
                dp[l][r] = next + w(s, l, r);
            }
        }
        return dp[0][n - 1];
    }

    public static int min3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int n = arr.length;
        int[] s = sum(arr);
        int[][] dp = new int[n][n];
        int[][] best = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            best[i][i + 1] = i;
            dp[i][i + 1] = w(s, i, i + 1);
        }
        for (int l = n - 3; l >= 0; l--) {
            for (int r = l + 2; r < n; r++) {
                int next = Integer.MAX_VALUE;
                int choose = -1;
                for (int leftEnd = best[l][r - 1]; leftEnd <= best[l + 1][r]; leftEnd++) {
                    int cur = dp[l][leftEnd] + dp[leftEnd + 1][r];
                    if (cur <= next) {
                        next = cur;
                        choose = leftEnd;
                    }
                }
                best[l][r] = choose;
                dp[l][r] = next + w(s, l, r);
            }
        }
        return dp[0][n - 1];
    }

    public static int[] randomArray(int len, int maxValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 15;
        int maxValue = 100;
        int testTime = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, maxValue);
            int ans1 = min1(arr);
            int ans2 = min2(arr);
            int ans3 = min3(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}
