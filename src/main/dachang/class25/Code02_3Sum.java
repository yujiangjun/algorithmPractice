package dachang.class25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code02_3Sum {

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = n - 1; i > 1; i--) {
            if (i == n - 1 || nums[i] != nums[i + 1]) {
                List<List<Integer>> cur = twoSum(nums, i - 1, -nums[i]);
                for (List<Integer> next : cur) {
                    next.add(nums[i]);
                    ans.add(next);
                }
            }
        }
        return ans;
    }

    public static List<List<Integer>> twoSum(int[] nums, int end, int target) {
        int l = 0;
        int r = end;
        List<List<Integer>> ans = new ArrayList<>();
        while (l < r) {
            if (nums[l] + nums[r] > target) {
                r--;
            } else if (nums[l] + nums[r] < target) {
                l++;
            } else {
                if (l == 0 || nums[l] != nums[l - 1]) {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(nums[l]);
                    cur.add(nums[r]);
                    ans.add(cur);
                }
                l++;
            }
        }
        return ans;
    }
}
