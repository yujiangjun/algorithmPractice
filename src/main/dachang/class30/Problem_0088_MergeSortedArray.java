package dachang.class30;

import java.util.Arrays;

public class Problem_0088_MergeSortedArray {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int all = m + n;
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[--all] = nums1[i--];
            } else if (nums1[i] < nums2[j]) {
                nums1[--all] = nums2[j--];
            }
        }
        while (i >= 0) {
            nums1[--all] = nums1[i--];
        }
        while (j >= 0) {
            nums1[--all] = nums2[j--];
        }
    }

    public static void main(String[] args) {
        int[] data1 = {0};
        int[] data2 = {1};
        merge(data1, 0, data2, 1);
        System.out.println(Arrays.toString(data1));
    }
}
