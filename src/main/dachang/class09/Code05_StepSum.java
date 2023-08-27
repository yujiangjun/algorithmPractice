package dachang.class09;

public class Code05_StepSum {


    public static boolean isStepSum(int stepSum) {
        int l = 0;
        int r = stepSum;
        int m = 0;
        int cur = 0;
        while (l <= r) {
            m = l + ((r - l) >> 1);
            cur = stepSum(m);
            if (cur == stepSum) {
                return true;
            } else if (cur < stepSum) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return false;
    }

    public static int stepSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num;
            num /= 10;
        }
        return sum;
    }
}
