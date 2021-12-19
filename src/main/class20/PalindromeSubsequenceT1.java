package class20;

public class PalindromeSubsequenceT1 {

    public static int lpsl1(String str){
        if (str==null||str.length()==0){
            return 0;
        }
        return process(str.toCharArray(),0,str.length()-1);
    }

    public static int process(char[] str,int L,int R){
        if (L==R){
            return 1;
        }
        if (L==R-1){
            return str[L]==str[R]?2:1;
        }
        //不以L开头，不以R结尾
        int p1=process(str,L+1,R-1);
        //以L开头，不以R结尾
        int p2=process(str,L,R-1);
        //不以L开头，以R结尾
        int p3=process(str,L+1,R);
        //以L开头，R结尾
        int p4=str[L]!=str[R]?0:(2+process(str,L+1,R-1));

        return Math.max(Math.max(p1,p2),Math.max(p3,p4));
    }

    public static int lpsl2(String s){

        if (s==null||s.length()==0){
            return 0;
        }
        int n = s.length();
        char[] str = s.toCharArray();
        int[][] dp=new int[s.length()][s.length()];
        dp[n-1][n-1]=1;
        for (int i = 0; i < n-1; i++) {
            dp[i][i]=1;
            if (str[i]==str[i+1]){
                dp[i][i+1]=2;
            }else {
                dp[i][i+1]=1;
            }
        }

        for (int L = n-3; L >=0; L--) {
            for (int R = L+2; R < n; R++) {
                int p1=dp[L+1][R-1];
                //以L开头，不以R结尾
                int p2=dp[L][R-1];
                //不以L开头，以R结尾
                int p3=dp[L+1][R];
                //以L开头，R结尾
                int p4=str[L]!=str[R]?0:(2+dp[L+1][R-1]);
                dp[L][R]=Math.max(Math.max(p1,p2),Math.max(p3,p4));
            }
        }
        return dp[0][n-1];
    }

    public static int lpsl3(String s){

        if (s==null||s.length()==0){
            return 0;
        }
        int n = s.length();
        char[] str = s.toCharArray();
        int[][] dp=new int[s.length()][s.length()];
        dp[n-1][n-1]=1;
        for (int i = 0; i < n-1; i++) {
            dp[i][i]=1;
            if (str[i]==str[i+1]){
                dp[i][i+1]=2;
            }else {
                dp[i][i+1]=1;
            }
        }

        for (int L = n-3; L >=0; L--) {
            for (int R = L+2; R < n; R++) {
                int p1=Math.max(dp[L][R-1],dp[L+1][R]);
                int p4=str[L]!=str[R]?0:(2+dp[L+1][R-1]);
                dp[L][R]=Math.max(p1,p4);
            }
        }
        return dp[0][n-1];
    }
}
