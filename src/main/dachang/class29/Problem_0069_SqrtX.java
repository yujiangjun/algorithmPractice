package dachang.class29;

public class Problem_0069_SqrtX {
    public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x < 3) {
            return 1;
        }
        long l = 1;
        long r = x;
        long ans = 0;
        while (l <= r) {
            long mid = (l + r) / 2;
            if (mid * mid > x) {
                r = mid - 1;
            } else {
                ans = mid;
                l = mid + 1;
            }
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        int x=15;
        System.out.println(mySqrt(x));
    }
}
