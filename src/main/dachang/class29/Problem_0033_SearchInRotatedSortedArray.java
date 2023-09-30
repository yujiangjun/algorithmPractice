package dachang.class29;

public class Problem_0033_SearchInRotatedSortedArray {
    public static int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[l] == nums[mid] && nums[mid] == nums[r]) {
                while (l != mid && nums[l] == nums[mid]) {
                    l++;
                }
                if (l == mid) {
                    l = mid+1;
                    continue;
                }
            }
            if (nums[l] != nums[mid]) {
                if (nums[mid] > nums[l]) {
                    if (target >= nums[l] && target < nums[mid]) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                } else {
                    if (target > nums[mid] && target <= nums[r]) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
            } else { //[mid]!=[r]
                if (nums[r] > nums[mid]) {
                    // mid-r有序
                    if (target > nums[mid] && target <= nums[r]) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                } else {
                    //[mid]>r
                    if (target >= nums[l] && target < nums[mid]) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                }
            }
        }
        return -1;
    }
}
