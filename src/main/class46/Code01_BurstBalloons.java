package class46;

public class Code01_BurstBalloons {

    public static int maxCoins0(int[] arr) {
        int n = arr.length;
        int[] help = new int[n + 2];
        for (int i = 0; i < n; i++) {
            help[i + 1] = arr[i];
        }
        help[0] = 1;
        help[n + 1] = 1;
        return func(help, 1, n);
    }

    public static int func(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l - 1] * arr[l] * arr[r + 1];
        }
        int max = func(arr, l + 1, r) + arr[l - 1] * arr[l] * arr[r + 1];
        max = Math.max(max, func(arr, l, r - 1) + arr[l - 1] * arr[r] * arr[r + 1]);
        for (int i = l + 1; i < r; i++) {
            int left = func(arr, l, i - 1);
            int right = func(arr, i + 1, r);
            int last = arr[l - 1] * arr[i] * arr[r + 1];
            int cur = left + right + last;
            max = Math.max(max, cur);
        }
        return max;
    }

    public static int maxCoins1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int n = arr.length;
        int[] help = new int[n + 2];
        help[0] = 1;
        help[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            help[i + 1] = arr[i];
        }
        return process(help, 1, n);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l - 1] * arr[l] * arr[r + 1];
        }
        int max = Math.max(arr[l - 1] * arr[l] * arr[r + 1] + process(arr, l + 1, r),
                arr[l - 1] * arr[r] * arr[r + 1] + process(arr, l, r - 1));
        for (int i = l + 1; i < r; i++) {
            max = Math.max(max, arr[l - 1] * arr[i] * arr[r + 1] + process(arr, l, i - 1) + process(arr, i + 1, r));
        }
        return max;
    }

    public static int maxCoins2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int[] help = new int[n + 2];
        help[0] = 1;
        help[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            help[i + 1] = nums[i];
        }
        int[][] dp = new int[n + 2][n + 2];
        for (int i = 1; i <= n; i++) {
            dp[i][i] = help[i - 1] * help[i] * help[i + 1];
        }
        for (int l = n; l > 0; l--) {
            for (int r = l + 1; r <= n; r++) {
                int ans = help[l - 1] * help[l] * help[r + 1] + dp[l + 1][r];
                ans = Math.max(ans, help[l - 1] * help[r] * help[r + 1] + dp[l][r - 1]);
                for (int i = l + 1; i < r; i++) {
                    ans = Math.max(ans, help[l - 1] * help[i] * help[r + 1] + dp[l][i - 1] + dp[i + 1][r]);
                }
                dp[l][r] = ans;
            }
        }
        return dp[1][n];
    }
}
