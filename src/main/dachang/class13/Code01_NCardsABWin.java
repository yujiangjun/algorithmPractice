package dachang.class13;

import java.text.DecimalFormat;

public class Code01_NCardsABWin {

    public static double f1() {
        return p1(0);
    }

    public static double p1(int cur) {
        if (cur >= 17 && cur < 21) {
            return 1.0;
        }
        if (cur >= 21) {
            return 0.0;
        }
        double w = 0.0;
        for (int i = 1; i <= 10; i++) {
            w += p1(cur + i);
        }
        return w / 10;
    }

    public static double f2(int n, int a, int b) {
        if (n < 1 || a >= b || a < 0 || b < 0) {
            return 0.0;
        }
        if (b - a >= n) {
            return 1.0;
        }
        return p2(0, n, a, b);
    }

    public static double p2(int cur, int n, int a, int b) {
        if (cur >= a && cur < b) {
            return 1.0;
        }
        if (cur >= b) {
            return 0.0;
        }
        double w = 0.0;
        for (int i = 1; i <= n; i++) {
            w += p2(cur + i, n, a, b);
        }
        return w / n;
    }

    public static double f3(int n, int a, int b) {
        if (n < 1 || a >= b || a < 0 || b < 0) {
            return 0.0;
        }
        if (b - a >= n) {
            return 1.0;
        }
        return p3(0, n, a, b);
    }

    public static double p3(int cur, int n, int a, int b) {
        if (cur >= a && cur < b) {
            return 1.0;
        }
        if (cur >= b) {
            return 0.0;
        }
        if (cur == a - 1) {
            return 1.0 * (b - a) / n;
        }
        double w = p3(cur + 1, n, a, b) + p3(cur + 1, n, a, b) * n;
        if (cur + 1 + n < b) {
            w -= p3(cur + 1 + n, n, a, b);
        }
        return w / n;
    }

    public static double f4(int n, int a, int b) {
        if (n < 1 || a >= b || a < 0 || b < 0) {
            return 0.0;
        }
        if (b - a >= n) {
            return 1.0;
        }
        double[] dp = new double[b];
        for (int i = a; i < b; i++) {
            dp[i] = 1.0;
        }
        if (a - 1 >= 0) {
            dp[a - 1] = 1.0 * (b - a) / n;
        }
        for (int cur = a - 2; cur >= 0; cur--) {
            double w = dp[cur + 1] + dp[cur + 1] * n;
            if (cur + 1 + n < b) {
                w -= dp[cur + 1 + n];
            }
            dp[cur] = w / n;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int N = 10;
        int a = 17;
        int b = 21;
        System.out.println("N = " + N + ", a = " + a + ", b = " + b);
        System.out.println(f1());
        System.out.println(f2(N, a, b));
        System.out.println(f3(N, a, b));
        System.out.println(f4(N, a, b));

        int maxN = 15;
        int maxM = 20;
        int testTime = 100000;
        System.out.println("测试开始");
        System.out.println("比对double类型答案可能会有精度对不准的问题");
        System.out.println("所以答案一律只保留小数点后四位进行比对");
        System.out.println("如果没有错误提示, 说明验证通过");
        DecimalFormat df = new DecimalFormat("#.####");
        for (int i = 0; i < testTime; i++) {
            N = (int) (Math.random() * maxN);
            a = (int) (Math.random() * maxM);
            b = (int) (Math.random() * maxM);
            double ans2 = Double.valueOf(df.format(f2(N, a, b)));
            double ans3 = Double.valueOf(df.format(f3(N, a, b)));
            double ans4 = Double.valueOf(df.format(f4(N, a, b)));
            if (ans2 != ans3 || ans2 != ans4) {
                System.out.println("Oops!");
                System.out.println(N + " , " + a + " , " + b);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println(ans4);
            }
        }
        System.out.println("测试结束");

        N = 10000;
        a = 67834;
        b = 72315;
        System.out.println("N = " + N + ", a = " + a + ", b = " + b + "时, 除了方法4外都超时");
        System.out.print("方法4答案: ");
        System.out.println(f4(N, a, b));
    }
}
