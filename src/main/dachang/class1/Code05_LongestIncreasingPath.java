package dachang.class1;

public class Code05_LongestIncreasingPath {

    // 从i,j位置开始走出的最长增长链的长度
    // 暴力递归
    public static int process1(int[][] m, int i, int j) {
        // 向上走
        int up = 0;
        if (i > 0 && m[i][j] < m[i - 1][j]) {
            up = process1(m, i - 1, j);
        }
        // 向下走
        int down = 0;
        if (i < m.length - 1 && m[i][j] < m[i + 1][j]) {
            down = process1(m, i + 1, j);
        }
        // 向左走
        int left = 0;
        if (j > 0 && m[i][j] < m[i][j - 1]) {
            left = process1(m, i, j - 1);
        }
        // 向右走
        int right = 0;
        if (j < m[0].length - 1 && m[i][j] < m[i][j + 1]) {
            right = process1(m, i, j + 1);
        }
        return Math.max(Math.max(up, down), Math.max(left, right)) + 1;
    }

    public static int longestIncreasingPath1(int[][] matrix) {
        int ans = 0;
        int n = matrix.length;
        int m = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, process1(matrix, i, j));
            }
        }
        return ans;
    }

    public static int process2(int[][] m, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        // 向上走
        int up = 0;
        if (i > 0 && m[i][j] < m[i - 1][j]) {
            up = process2(m, i - 1, j, dp);
        }
        // 向下走
        int down = 0;
        if (i < (m.length - 1) && m[i][j] < m[i + 1][j]) {
            down = process2(m, i + 1, j, dp);
        }
        // 向左走
        int left = 0;
        if (j > 0 && m[i][j] < m[i][j - 1]) {
            left = process2(m, i, j - 1, dp);
        }
        // 向右走
        int right = 0;
        if (j < (m[0].length - 1) && m[i][j] < m[i][j + 1]) {
            right = process2(m, i, j + 1, dp);
        }
        int ans = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        dp[i][j] = ans;
        return ans;
    }

    public static int longestIncreasingPath2(int[][] matrix) {
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process2(matrix, i, j, dp));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] m = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(longestIncreasingPath2(m));
        System.out.println(longestIncreasingPath1(m));
    }
}
