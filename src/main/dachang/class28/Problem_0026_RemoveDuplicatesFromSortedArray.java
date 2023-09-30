package dachang.class28;

import java.util.Arrays;

public class Problem_0026_RemoveDuplicatesFromSortedArray {

    public static int removeDuplicates(int[] nums) {
        if (nums==null||nums.length==0){
            return 0;
        }
        int size=0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[size]!=nums[i]){
                nums[++size]=nums[i];
            }
        }
        return size+1;
    }

    public static void main(String[] args) {
        int[] data1={1,2,2};
        System.out.println(removeDuplicates(data1));
        System.out.println(Arrays.toString(data1));
    }
}
