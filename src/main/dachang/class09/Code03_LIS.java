package dachang.class09;

public class Code03_LIS {

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] ends = new int[nums.length];
        ends[0] = nums[0];
        int right = 0;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            int l = 0;
            int r = right;
            while (l <= r) {
                int m = (l + r) / 2;
                if (nums[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = nums[i];
            max = Math.max(max, l + 1);
        }
        return max;
    }
}
