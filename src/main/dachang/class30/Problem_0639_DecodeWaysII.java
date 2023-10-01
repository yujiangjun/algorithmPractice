package dachang.class30;

import java.util.Arrays;

public class Problem_0639_DecodeWaysII {

    public static int numDecodings0(String str) {
        return process(str.toCharArray(), 0);
    }

    public static int process(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }
        int p1 = 0;
        if (str[i] != '*') {
            p1 = process(str, i + 1);
        } else {
            p1 = process(str, i + 1) * 9;
        }
        if (i == str.length - 1) {
            return p1;
        }
        int p2 = 0;
        if (str[i] != '*') {
            if (str[i + 1] != '*') {
                int code = (str[i] - '0') * 10 + str[i + 1] - '0';
                if (code < 27) {
                    p2 = process(str, i + 2);
                }
            } else {
                if (str[i] < '3') {
                    p2 = (str[i] == '1' ? 9 : 6) * process(str, i + 2);
                }
            }
        } else {
            if (str[i + 1] != '*') {
                if (str[i + 1] < '7') {
                    p2 = 2 * process(str, i + 2);
                } else {
                    p2 = process(str, i + 2);
                }
            } else {
                p2 = process(str, i + 2) * 15;
            }
        }
        return p1 + p2;
    }

    public static int numDecodings(String str) {
        int n = str.length();
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);
        return (int) process1(str.toCharArray(), 0, dp);
    }

    public static long process1(char[] str, int i, long[] dp) {
        if (dp[i] != -1) {
            return dp[i];
        }
        if (i == str.length) {
            dp[i] = 1;
            return 1;
        }
        if (str[i] == '0') {
            dp[i] = 0;
            return 0;
        }
        long p1 = 0;
        if (str[i] != '*') {
            p1 = process1(str, i + 1, dp);
        } else {
            p1 = process1(str, i + 1, dp) * 9L;
        }
        if (i == str.length - 1) {
            dp[i] = (p1 % 1000000007);
            return (p1 % 1000000007);
        }
        long p2 = 0;
        if (str[i] != '*') {
            if (str[i + 1] != '*') {
                int code = (str[i] - '0') * 10 + str[i + 1] - '0';
                if (code < 27) {
                    p2 = process1(str, i + 2, dp);
                }
            } else {
                if (str[i] < '3') {
                    p2 = (str[i] == '1' ? 9 : 6) * process1(str, i + 2, dp);
                }
            }
        } else {
            if (str[i + 1] != '*') {
                if (str[i + 1] < '7') {
                    p2 = 2L * process1(str, i + 2, dp);
                } else {
                    p2 = process1(str, i + 2, dp);
                }
            } else {
                p2 = process1(str, i + 2, dp) * 15L;
            }
        }
        dp[i] = (p1 + p2) % 1000000007;
        return (p1 + p2) % 1000000007;
    }

    public static int numDecodings2(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        long[] dp = new long[n + 1];
        dp[n] = 1;
        dp[n - 1] = s[n - 1] == '0' ? 0 : (s[n - 1] == '*' ? 9 * dp[n] : dp[n]);
        for (int i = n - 2; i >= 0; i--) {
            if (s[i] == '0') {
                dp[i] = 0;
                continue;
            }
            long p1 = 0;
            long p2 = 0;
            if (s[i] != '*') {
                p1 = dp[i + 1];
                if (s[i + 1] != '*') {
                    int code = (s[i] - '0') * 10 + s[i + 1] - '0';
                    if (code < 27) {
                        p2 = dp[i + 2];
                    }
                } else {
                    if (s[i] < '3') {
                        p2 = (s[i] == '1' ? 9 : 6) * dp[i + 2];
                    }
                }
            } else {
                p1 = 9 * dp[i + 1];
                if (s[i + 1] != '*') {
                    if (s[i + 1] < '7') {
                        p2 = 2L * dp[i + 2];
                    } else {
                        p2 = dp[i + 2];
                    }
                } else {
                    p2 = dp[i + 2] * 15L;
                }
            }
            dp[i] = (p1 + p2) % 1000000007;
        }

        return (int) dp[0];
    }
}
