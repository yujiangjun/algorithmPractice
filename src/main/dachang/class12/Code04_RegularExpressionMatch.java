package dachang.class12;

public class Code04_RegularExpressionMatch {

    public static boolean isMatch1(String s, String p) {
        if (p == null || p.length() == 0) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] ptr = p.toCharArray();
        return process1(str, ptr, 0, 0);
    }

    public static boolean process1(char[] s, char[] p, int i, int j) {
        if (i == s.length) {
            if (j == p.length) {
                return true;
            }
            return j + 1 < p.length && p[j + 1] == '*' && process1(s, p, i, j + 2);
        } else if (j == p.length) {
            return false;
        } else {
            if (j + 1 == p.length || p[j + 1] != '*') {
                return (s[i] == p[j] || p[j] == '.') && process1(s, p, i + 1, j + 1);
            } else {

                //p[j+1]=='*',当前p[j..j+1]=x*,不让他搞定s[i],那么s[i..]由j+2...搞定
                boolean p1 = process1(s, p, i, j + 2);
                //当前p[j..j+1]是x*,如果可以搞定s[i],那么i+1...有j+1搞定
                boolean p2 = (s[i] == p[j] || p[j] == '.') && process1(s, p, i + 1, j);
                return p1 || p2;
            }
        }
    }

    public static boolean isMatch2(String s, String p) {
        if (p == null || p.length() == 0) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] ptr = p.toCharArray();
        int[][] dp = new int[str.length + 1][ptr.length + 1];
        return process2(str, ptr, 0, 0, dp) == 1;
    }

    public static int process2(char[] s, char[] p, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int ans = 0;
        if (i == s.length) {
            if (j == p.length) {
                ans = 1;
            } else {
                ans = (j + 1 < p.length && p[j + 1] == '*' && process2(s, p, i, j + 2, dp) == 1) ? 1 : -1;
            }

        } else if (j == p.length) {
            ans = -1;
        } else {
            if (j + 1 == p.length || p[j + 1] != '*') {
                ans = ((s[i] == p[j] || p[j] == '.') && process2(s, p, i + 1, j + 1, dp) == 1) ? 1 : -1;
            } else {

                //p[j+1]=='*',当前p[j..j+1]=x*,不让他搞定s[i],那么s[i..]由j+2...搞定
                boolean p1 = process2(s, p, i, j + 2, dp) == 1;
                //当前p[j..j+1]是x*,如果可以搞定s[i],那么i+1...有j+1搞定
                boolean p2 = ((s[i] == p[j] || p[j] == '.') && process2(s, p, i + 1, j, dp) == 1);
                ans = (p1 || p2) ? 1 : -1;
            }
        }
        dp[i][j] = ans;
        return ans;
    }

    public static boolean isMatch3(String s, String p) {
        if (p == null || p.length() == 0) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] ptr = p.toCharArray();
        int n = str.length;
        int m = ptr.length;
        boolean[][] dp = new boolean[n + 1][m + 1];

        dp[n][m] = true;
        for (int j = m - 2; j >= 0; j--) {
            dp[n][j] = ptr[j + 1] == '*' && dp[n][j + 2];
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (j + 1 == m || ptr[j + 1] != '*') {
                    dp[i][j] = (str[i] == ptr[j] || ptr[j] == '.') && dp[i + 1][j + 1];
                } else {
                    boolean p1 = dp[i][j + 2];
                    boolean p2 = (str[i] == ptr[j] || ptr[j] == '.') && dp[i + 1][j];
                    dp[i][j] = p1 || p2;
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        String s = "aa";
        String p = "a*";
        System.out.println(isMatch1(s, p));
        System.out.println(isMatch2(s, p));
        System.out.println(isMatch3(s, p));
    }
}
