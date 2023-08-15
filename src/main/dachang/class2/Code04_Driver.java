package dachang.class2;

import java.util.Arrays;

public class Code04_Driver {

    public static int maxMoney1(int[][] incomes) {
        if (incomes == null || incomes.length < 2 || (incomes.length & 1) != 0) {
            return 0;
        }
        int n = incomes.length;
        int m = n >> 1;
        return process1(incomes, 0, m);
    }

    /**
     * @param incomes 收入数组
     * @param index   index 以及后面的司机
     * @param rest    A区域还剩下rest下司机
     * @return 最大收入
     */
    public static int process1(int[][] incomes, int index, int rest) {
        if (index == incomes.length) {
            return 0;
        }
        //还剩下rest名司机
        if (incomes.length - index == rest) {
            return incomes[index][0] + process1(incomes, index + 1, rest - 1);
        }
        if (rest == 0) {
            return incomes[index][1] + process1(incomes, index + 1, rest);
        }
        int p1 = incomes[index][0] + process1(incomes, index + 1, rest - 1);
        int p2 = incomes[index][1] + process1(incomes, index + 1, rest);
        return Math.max(p1, p2);
    }

    public static int maxMoney2(int[][] incomes) {
        int n = incomes.length;
        int m = n >> 1;
        int[][] dp = new int[n + 1][m + 1];
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= m; rest++) {
                if (n - index == rest) {
                    dp[index][rest] = incomes[index][0] + dp[index + 1][rest - 1];
                } else if (rest == 0) {
                    dp[index][rest] = incomes[index][1] + dp[index + 1][rest];
                } else {
                    int p1 = incomes[index][0] + dp[index + 1][rest - 1];
                    int p2 = incomes[index][1] + dp[index + 1][rest];
                    dp[index][rest] = Math.max(p1, p2);
                }
            }
        }
        return dp[0][m];
    }

    public static int maxMoney3(int[][] incomes) {
        int n = incomes.length;
        int[] arr = new int[n];
        int sum = 0;
        for (int i = 0; i < incomes.length; i++) {
            arr[i] = incomes[i][1] - incomes[i][0];
            sum += incomes[i][0];
        }
        Arrays.sort(arr);
        int m = n >> 1;
        for (int i = n - 1; i >= m; i--) {
            sum += arr[i];
        }
        return sum;
    }

    public static int twoCitySchedCost(int[][] cost) {
        int n = cost.length;
        int[] arr = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += cost[i][0];
            arr[i] = cost[i][1] - cost[i][0];
        }
        Arrays.sort(arr);
        int m = n >> 1;
        for (int i = 0; i < m; i++) {
            sum += arr[i];
        }
        return sum;
    }

    // 返回随机len*2大小的正数矩阵
    // 值在0~value-1之间
    public static int[][] randomMatrix(int len, int value) {
        int[][] ans = new int[len << 1][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i][0] = (int) (Math.random() * value);
            ans[i][1] = (int) (Math.random() * value);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 10;
        int value = 100;
        int testTime = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[][] matrix = randomMatrix(len, value);
            int ans1 = maxMoney1(matrix);
            int ans2 = maxMoney2(matrix);
            int ans3 = maxMoney3(matrix);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}
