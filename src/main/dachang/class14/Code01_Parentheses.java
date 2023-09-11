package dachang.class14;

public class Code01_Parentheses {

    public static int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        int n = s.length();
        char[] str = s.toCharArray();
        int[] dp = new int[n];
        dp[0] = 0;
        int pre = 0;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (str[i] == ')') {
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && str[pre] == '(') {
                    dp[i] = 2 + dp[i - 1] + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
