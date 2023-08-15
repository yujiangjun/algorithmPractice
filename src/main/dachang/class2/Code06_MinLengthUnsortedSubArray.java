package dachang.class2;

public class Code06_MinLengthUnsortedSubArray {

    public static int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int n = nums.length;
        int right = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (max > nums[i]) {
                right = i;
            }
            max = Math.max(max, nums[i]);
        }
        int left = n;
        int min = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (min < nums[i]) {
                left = i;
            }
            min = Math.min(min, nums[i]);
        }
        return Math.max(0, right - left + 1);
    }
}
