package dachang.class28;

import java.util.Arrays;

public class Problem_0034_FindFirstAndLastPositionOfElementInSortedArray {

    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int l = lessMostRightPos(nums, target) + 1;
        if (l == nums.length || nums[l] != target) {
            return new int[]{-1, -1};
        }
        int r = lessMostRightPos(nums, target + 1);
        return new int[]{l, r};
    }


    public static int lessMostRightPos(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] < target) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] data = {5, 5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(searchRange(data, 5)));
    }
}
