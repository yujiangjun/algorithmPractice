package dachang.class29;

public class Problem_0062_UniquePaths {

    public static int uniquePaths(int m, int n) {
        int all = n + m - 2;
        int right = n - 1;
        long o1 = 1;
        long o2 = 1;
        for (int i = right + 1, j = 1; i <= all; i++, j++) {
            o1 *= i;
            o2 *= j;
            long gcd = gcd(o1,o2);
            o1/=gcd;
            o2/=gcd;
        }
        return (int) o1;
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
