package dachang.class08;

public class Code03_FindWordInMatrix {

    // 从i,j位置出发，能够走出从k位置开始到最后的str字符串
    public static boolean canLoop(char[][] m, int i, int j, int k, char[] str) {
        if (k == str.length) {
            return true;
        }
        if (i == -1 || i == m.length || j == -1 || j == m[0].length || m[i][j] != str[k]) {
            return false;
        }
        if (canLoop(m, i + 1, j, k + 1, str) || canLoop(m, i - 1, j, k + 1, str) || canLoop(m, i, j + 1, k + 1, str) || canLoop(m, i, j + 1, k + 1, str)) {
            return true;
        }
        return false;
    }

    // 从i,j位置出发，能够走出从k位置开始到最后的str字符串
    // 但是走过的路不能重复走
    public static boolean noLoop(char[][] m, int i, int j, int k, char[] str) {
        if (k == str.length) {
            return true;
        }
        if (i == -1 || i == m.length || j == -1 || j == m[0].length || m[i][j] != str[k]) {
            return false;
        }
//        if (i==-1||i== m.length||j==-1||j== m[0].length || m[i][j]==0 || m[i][j]!=str[k]){
//            return false;
//        }
        // 当m[i][j]==0 显然m[i][j]!=str[k],所以做了优化，优化成上面那样
        //将走过的路设置为0，当m[i][j]==0表示走过了
        m[i][j]=0;
        if (noLoop(m, i + 1, j, k + 1, str) || noLoop(m, i - 1, j, k + 1, str) || noLoop(m, i, j - 1, k + 1, str) || noLoop(m, i, j + 1, k + 1, str)) {
            return true;
        }
        m[i][j]=str[k];
        return false;
    }

    public static boolean findWord2(char[][] m,String word){
        if (word==null||word.isEmpty()){
            return true;
        }
        if (m==null||m.length==0||m[0]==null||m[0].length==0){
            return false;
        }
        char[] wordChars = word.toCharArray();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (noLoop(m,i,j,0,wordChars)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean findWord1(char[][] matrix,String word){
        if (word==null||word.isEmpty()){
            return true;
        }
        if (matrix==null||matrix.length==0||matrix[0]==null||matrix[0].length==0){
            return false;
        }
        int n=matrix.length;
        int m= matrix[0].length;
        char[] str = word.toCharArray();
        boolean[][][] dp = new boolean[n][m][str.length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j][0]=matrix[i][j]==str[0];
            }
        }

        for (int k = 1; k < str.length; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    dp[i][j][k]=matrix[i][j]==str[k]&&checkPrevious(dp,i,j,k);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j][str.length-1]){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkPrevious(boolean[][][] dp, int i, int j, int k) {
        boolean up = i > 0 ? dp[i - 1][j][k - 1] : false;
        boolean down = i < dp.length - 1 ? dp[i + 1][j][k - 1] : false;
        boolean left = j > 0 ? dp[i][j - 1][k - 1] : false;
        boolean right = j < dp[0].length - 1 ? dp[i][j + 1][k - 1] : false;
        return up || down || left || right;
    }

    public static void main(String[] args) {
        char[][] m = { { 'a', 'b', 'z' }, { 'c', 'd', 'o' }, { 'f', 'e', 'o' }, };
        String word1 = "zoooz";
        String word2 = "zoo";
        // 可以走重复路的设定
        System.out.println(findWord1(m, word1));
        System.out.println(findWord1(m, word2));
        // 不可以走重复路的设定
        System.out.println(findWord2(m, word1));
        System.out.println(findWord2(m, word2));

    }
}
