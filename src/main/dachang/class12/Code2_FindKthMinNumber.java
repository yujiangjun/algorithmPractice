package dachang.class12;

public class Code2_FindKthMinNumber {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        boolean even = (size & 1) == 0;
        if (nums1.length != 0 && nums2.length != 0) {
            if (even) {
                return (double) (findKthNum(nums1, nums2, size / 2) + findKthNum(nums1, nums2, size / 2 + 1)) / 2d;
            } else {
                return findKthNum(nums1, nums2, size / 2 + 1);
            }
        } else if (nums1.length != 0) {
            if (even) {
                return (double) (nums1[size / 2] + nums1[(size - 1) / 2]) / 2d;
            } else {
                return nums1[size / 2];
            }
        } else if (nums2.length != 0) {
            if (even) {
                return (double) (nums2[size / 2] + nums2[(size - 1) / 2]) / 2d;
            } else {
                return nums2[size / 2];
            }
        } else {
            return 0;
        }
    }

    public int findKthNum(int[] nums1, int[] nums2, int kth) {
        int[] longNum = nums1.length >= nums2.length ? nums1 : nums2;
        int[] shortNum = nums1.length < nums2.length ? nums1 : nums2;
        int l = longNum.length;
        int s = shortNum.length;
        if (kth <= s) {
            return getMiddleUp(nums1, 0, kth - 1, nums2, 0, kth - 1);
        } else if (kth > l) {
            /*
            mergeNum: 1,2,3,4,5,6,7,8,9
            shortNum: 1,7,8,9
            longNum: 2,3,4,5,6
            kth:7 mergeNum[6] short[1]
             */
            if (shortNum[kth - l - 1] >= longNum[l - 1]) {
                return shortNum[kth - l - 1];
            } else if (longNum[kth - s - 1] >= shortNum[s - 1]) {
                /*
            mergeNum: 1,2,3,4,5,6,7,8,9
            longNum: 1,5,6,7,8,9
            shortNum: 2,3,4
            kth:7 mergeNum[6] longNum[3]
             */
                return longNum[kth - s - 1];
            }
            return getMiddleUp(shortNum, kth - l, s - 1, longNum, kth - s, l - 1);
        } else {
            if (longNum[kth - s - 1] >= shortNum[s - 1]) {
                return longNum[kth - s - 1];
            }
            return getMiddleUp(shortNum, 0, s - 1, longNum, kth - s, kth - 1);
        }
    }

    public int getMiddleUp(int[] num1, int s1, int e1, int[] num2, int s2, int e2) {
        int mid1 = 0;
        int mid2 = 0;
        while (s1 < e1) {
            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;
            if (num1[mid1] == num2[mid2]) {
                return num1[mid1];
            }
            if (((e1 - s1 + 1) & 1) == 1) { //长度是奇数
                if (num1[mid1] > num2[mid2]) {
                    if (num2[mid2] >= num1[mid1 - 1]) {
                        return num2[mid2];
                    }
                    e1 = mid1 - 1;
                    s2 = mid2 + 1;
                } else {
                    if (num1[mid1] >= num2[mid2 - 1]) {
                        return num1[mid1];
                    }
                    s1 = mid1 + 1;
                    e2 = mid2 - 1;
                }
            } else {
                if (num1[mid1] > num2[mid2]) {
                    e1 = mid1;
                    s2 = mid2 + 1;
                } else {
                    s1 = mid1 + 1;
                    e2 = mid2;
                }
            }
        }
        return Math.min(num1[s1], num2[s2]);
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        boolean even = (size & 1) == 0;
        if (nums1.length != 0 && nums2.length != 0) {
            if (even) {
                return (double) (findKthNum2(nums1, nums2, size / 2) + findKthNum2(nums1, nums2, size / 2 + 1)) / 2d;
            } else {
                return findKthNum2(nums1, nums2, size / 2 + 1);
            }
        } else if (nums1.length != 0) {
            if (even) {
                return (double) (nums1[size / 2] + nums1[(size - 1) / 2]) / 2d;
            } else {
                return nums1[size / 2];
            }
        } else if (nums2.length != 0) {
            if (even) {
                return (double) (nums2[size / 2] + nums2[(size - 1) / 2]) / 2d;
            } else {
                return nums2[size / 2];
            }
        } else {
            return 0;
        }
    }

    public static int findKthNum2(int[] nums1,int[] nums2,int kth){
        int p1=0,p2=0;
        int count=0;
        int[] newNums = new int[nums1.length+nums2.length];
        while (p1<nums1.length&&p2<nums2.length){
            if (nums1[p1]<nums2[p2]){
                p1++;
                newNums[count++]=nums1[p1-1];
            }else {
                p2++;
                newNums[count++]=nums2[p2-1];
            }
            if (count==kth){
                return newNums[count-1];
            }
        }
        while (p1<nums1.length){
            p1++;
            newNums[count++]=nums1[p1-1];
            if (kth==count){
                return newNums[count-1];
            }
        }
        while (p2<nums2.length){
            p2++;
            newNums[count++]=nums2[p2-1];
            if (kth==count){
                return newNums[count-1];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums1={1,3};
        int[] nums2={2};
        System.out.println(findKthNum2(nums1,nums2,2));
    }
}
