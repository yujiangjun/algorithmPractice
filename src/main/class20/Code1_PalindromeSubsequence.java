package class20;

//测试链接：https://leetcode.com/problems/longest-palindromic-subsequence/
public class Code1_PalindromeSubsequence {

    public static int lpsl1(String s){
        if (s==null||s.length()==0){
            return 0;
        }
        char[] str=s.toCharArray();
        return f(str,0,str.length-1);
    }

    public static int f(char[] str,int L,int R){
        if (L==R){
            return 1;
        }
        if (L==R-1){
            return str[L]==str[R]?2:1;
        }
        int p1=f(str,L+1,R-1);
        int p2=f(str,L,R-1);
        int p3=f(str,L+1,R);
        int p4=str[L]!=str[R]?0:(2+f(str,L+1,R-1));
        return Math.max(Math.max(p1,p2),Math.max(p3,p4));
    }

    public static int lpsl2(String s){
        if (s==null||s.length()==0){
            return 0;
        }
        char[] str=s.toCharArray();
        int n=str.length;
        int[][] dp=new int[n][n];
        dp[n-1][n-1]=1;
        for (int i = 0; i < n - 1; i++) {
            dp[i][i]=i;
            dp[i][i+1]=str[i]==str[i+1]?2:1;
        }
        for (int L = n-3; L >= 0; L--) {
            for (int R = L+2; R < n; R++) {
                dp[L][R]=Math.max(dp[L][R-1],dp[L+1][R]);
                if (str[L]==str[R]){
                    dp[L][R]=Math.max(dp[L][R],2+dp[L+1][R-1]);
                }
            }
        }
        return dp[0][n-1];
    }

    public static int longestPalindromeSubseq1(String s){
        if (s==null||s.length()==0){
            return 0;
        }
        if (s.length()==1){
            return 1;
        }
        char[] str=s.toCharArray();
        char[] reverse=reverse(str);
        return longestCommonSubsequence(str,reverse);
    }

    private static int longestCommonSubsequence(char[] str1, char[] str2) {
        int n=str1.length;
        int m=str2.length;
        int[][] dp=new int[n][m];
        for (int i = 1; i < n; i++) {
            dp[i][0]=str1[i]==str2[0]?1:dp[i-1][0];
        }
        for (int j = 1; j <m; j++) {
            dp[0][j]=str1[0]==str2[0]?1:dp[0][j-1];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                if (str1[i]==str2[j]){
                    dp[i][j]=Math.max(dp[i][j],dp[i-1][j-1]+1);
                }
            }
        }
        return dp[n-1][m-1];
    }

    public static char[] reverse(char[] str){
        int n=str.length;
        char[] reverse=new char[str.length];
        for (int i = 0; i < str.length; i++) {
            reverse[--n]=str[i];
        }
        return reverse;
    }


    public static int longestPalindromeSubseq2(String s){
        if (s==null||s.length()==0){
            return 0;
        }
        if (s.length()==1){
            return 1;
        }
        char[] str=s.toCharArray();
        int n=str.length;
        int[][] dp=new int[n][n];
        dp[n-1][n-1]=1;
        for (int i = 0; i < n - 1; i++) {
            dp[i][i]=1;
            dp[i][i+1]=str[i]==str[i+1]?2:1;
        }
        for (int i = n-3; i >=0; i--) {
            for (int j = i+2; j < n; j++) {
                dp[i][j]=Math.max(dp[i][j-1],dp[i+1][j]);
                if (str[i]==str[j]){
                    dp[i][j]=Math.max(dp[i][j],dp[i+1][j-1]+2);
                }
            }
        }
        return dp[0][n-1];
    }
}
