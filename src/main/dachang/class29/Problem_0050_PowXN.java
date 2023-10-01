package dachang.class29;

public class Problem_0050_PowXN {
    public static double myPow(double x, int n) {
        if (n == 0) {
            return 1d;
        }
        int power = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
        double t = x;
        double ans = 1;
        while (power != 0) {
            if ((power & 1) == 1) {
                ans *= t;
            }
            power >>= 1;
            t = t * t;
        }
        if (n == Integer.MIN_VALUE) {
            ans *= x;
        }
        return n > 0 ? ans : 1 / ans;
    }
}
