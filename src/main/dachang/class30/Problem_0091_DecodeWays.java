package dachang.class30;

public class Problem_0091_DecodeWays {

    public int numDecodings(String s) {
        return process(s.toCharArray(), 0);
    }

    public static int process(char[] s, int index) {
        if (index == s.length) {
            return 1;
        }
        if (s[index] == '0') {
            return 0;
        }
        int ways = process(s, index + 1);
        if (index == s.length - 1) {
            return ways;
        }
        int code = (s[index] - '0') * 10 + s[index + 1] - '0';
        if (code < 27) {
            ways += process(s, index + 2);
        }
        return ways;
    }

    public int numDecodings2(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        dp[n - 1] = str[n - 1] == '0' ? 0 : dp[n];
        for (int i = n - 2; i >= 0; i--) {
            if (str[i]=='0'){
                dp[i]=0;
                continue;
            }
            dp[i] = dp[i + 1];
            int code = (str[i] - '0') * 10 + str[i + 1] - '0';
            if (code < 27) {
                dp[i] += dp[i + 2];
            }
        }
        return dp[0];
    }
    public int numDecodings3(String s) {
        char[] str = s.toCharArray();
        int n=str.length;
        int dp1=1;
        int dp2=str[n-1]=='0'?0:dp1;
        for (int i = n - 2; i >= 0; i--) {
            int sum=0;
            if (str[i]!='0'){
                sum=dp2;
                int code = (str[i] - '0') * 10 + str[i + 1] - '0';
                if (code<27){
                    sum+=dp1;
                }
                dp1=dp2;
                dp2=sum;
            }else {
                dp1=dp2;
                dp2=0;
            }
        }
        return dp2;
    }
}
