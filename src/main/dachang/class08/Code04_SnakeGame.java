package dachang.class08;

import java.util.Arrays;

public class Code04_SnakeGame {


    public static class Info {
        public int no;
        public int yes;

        public Info(int no, int yes) {
            this.no = no;
            this.yes = yes;
        }
    }

    // 从最左列的某个最优点开始出发，一直移动到i,j位置
    // 返回一个不用能力的最大值
    // 返回用了一次能力后的最大值
    // 如果怎么也到不了i,j位置 返回-1
    public static Info f(int[][] matrix, int i, int j) {
        if (j == 0) {
            int no = Math.max(matrix[i][j], -1);
            int yes = Math.max(-matrix[i][j], -1);
            return new Info(no, yes);
        }
        int preNo = -1;
        int preYes = -1;
        Info pre = f(matrix, i, j - 1);
        preNo = Math.max(preNo, pre.no);
        preYes = Math.max(preYes, pre.yes);
        if (i > 0) {
            pre = f(matrix, i - 1, j - 1);
            preNo = Math.max(preNo, pre.no);
            preYes = Math.max(preYes, pre.yes);
        }
        if (i < matrix.length - 1) {
            pre = f(matrix, i + 1, j - 1);
            preNo = Math.max(preNo, pre.no);
            preYes = Math.max(preYes, pre.yes);
        }
        int no = preNo == -1 ? -1 : Math.max(-1, preNo + matrix[i][j]);
        // 之前用过了一次能力
        int p1 = preYes == -1 ? -1 : Math.max(-1, preYes + matrix[i][j]);
        //之前没有用过能力
        int p2 = preNo == -1 ? -1 : Math.max(-1, preNo - matrix[i][j]);
        int yes = Math.max(p1, p2);
        return new Info(no, yes);
    }

    public static int walk1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Info f = f(matrix, i, j);
                ans = Math.max(ans, Math.max(f.no, f.yes));
            }
        }
        return ans;
    }

    public static int walk2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int max = Integer.MIN_VALUE;
        int[][][] dp = new int[n][m][2];
        for (int i = 0; i < n; i++) {

            dp[i][0][0] = matrix[i][0];
            dp[i][0][1] = -matrix[i][0];
            max = Math.max(max, Math.max(dp[i][0][0], dp[i][0][1]));
        }

        for (int j = 1; j < m; j++) {
            for (int i = 0; i < n; i++) {
                int preNo = dp[i][j - 1][0];
                int preYes = dp[i][j - 1][1];

                if (i > 0) {
                    preNo = Math.max(preNo, dp[i - 1][j - 1][0]);
                    preYes = Math.max(preYes, dp[i - 1][j - 1][1]);
                }
                if (i < n - 1) {
                    preNo = Math.max(preNo, dp[i + 1][j - 1][0]);
                    preYes = Math.max(preYes, dp[i + 1][j - 1][1]);
                }
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
                if (preNo >= 0) {
                    dp[i][j][0] = matrix[i][j] + preNo;
                    dp[i][j][1] = -matrix[i][j] + preNo;
                }
                if (preYes >= 0) {
                    dp[i][j][1] = Math.max(dp[i][j][1], matrix[i][j] + preYes);
                }
                max = Math.max(max, Math.max(dp[i][j][0], dp[i][j][1]));
            }
        }
        return max;

    }

    public static int walk3(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int[][][] dp = new int[matrix.length][matrix[0].length][2];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0][0] = matrix[i][0];
            dp[i][0][1] = -matrix[i][0];
            max = Math.max(max, Math.max(dp[i][0][0], dp[i][0][1]));
        }
        for (int j = 1; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                int preUnuse = dp[i][j - 1][0];
                int preUse = dp[i][j - 1][1];
                if (i - 1 >= 0) {
                    preUnuse = Math.max(preUnuse, dp[i - 1][j - 1][0]);
                    preUse = Math.max(preUse, dp[i - 1][j - 1][1]);
                }
                if (i + 1 < matrix.length) {
                    preUnuse = Math.max(preUnuse, dp[i + 1][j - 1][0]);
                    preUse = Math.max(preUse, dp[i + 1][j - 1][1]);
                }
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
                if (preUnuse >= 0) {
                    dp[i][j][0] = matrix[i][j] + preUnuse;
                    dp[i][j][1] = -matrix[i][j] + preUnuse;
                }
                if (preUse >= 0) {
                    dp[i][j][1] = Math.max(dp[i][j][1], matrix[i][j] + preUse);
                }
                max = Math.max(max, Math.max(dp[i][j][0], dp[i][j][1]));
            }
        }
        return max;
    }

    public static int[][] generateRandomArray(int row, int col, int value) {
        int[][] arr = new int[row][col];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = (int) (Math.random() * value) * (Math.random() > 0.5 ? -1 : 1);
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 7;
        int M = 7;
        int V = 10;
        int times = 1000000;
        for (int i = 0; i < times; i++) {
            int r = (int) (Math.random() * (N + 1));
            int c = (int) (Math.random() * (M + 1));
            int[][] matrix = generateRandomArray(r, c, V);
            int ans1 = walk1(matrix);
            int ans2 = walk2(matrix);
            if (ans1 != ans2) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.println(Arrays.toString(matrix[j]));
                }
                System.out.println("Oops   ans1: " + ans1 + "   ans2:" + ans2);
                break;
            }
        }
        System.out.println("finish");
    }
}
