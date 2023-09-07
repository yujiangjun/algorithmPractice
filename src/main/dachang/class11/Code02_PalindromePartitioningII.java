package dachang.class11;

public class Code02_PalindromePartitioningII {

    public static int minCut(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int n = s.length();
        boolean[][] checkMap = createCheckMap(s, n);
        int[] dp = new int[n + 1];
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (checkMap[i][n - 1]) {
                dp[i] = 1;
            } else {
                int next = Integer.MAX_VALUE;
                for (int j = i; j < n; j++) {
                    if (checkMap[i][j]) {
                        next = Math.min(next, dp[j + 1]);
                    }
                }
                dp[i] = next + 1;
            }
        }
        return dp[0] - 1;
    }

    public static boolean[][] createCheckMap(String s, int n) {
        boolean[][] checkMap = new boolean[n][n];

        char[] str = s.toCharArray();
        for (int i = 0; i < n - 1; i++) {
            checkMap[i][i] = true;
            checkMap[i][i + 1] = str[i] == str[i + 1];
        }
        checkMap[n - 1][n - 1] = true;

        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                checkMap[i][j] = str[i] == str[j] && checkMap[i + 1][j - 1];
            }
        }
        return checkMap;
    }
}
