package dachang.class07;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 给定一个数组，有正数，负数，0.
 统计arr数组中每个数平方后不同的数有几个
 */
public class Code04_Power2Diffs {
    public static int diff1(int[] arr){
        if (arr==null||arr.length==0){
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : arr) {
            set.add(i*i);
        }
        return set.size();
    }

    /*
    1.使用双指针。定义2个左右指针
    2. 比较左右指针的绝对值，谁大谁向中间靠拢。个数加1
    3. 如果左右指针的绝对值相同。2个指针都向中间靠拢，个数+1
    4.注意边界条件 如-8 -8 -7 0 4 9 9 9
     */
    public static int diff2(int[] arr){
        if (arr==null||arr.length==0){
            return 0;
        }
        int n= arr.length;
        int l=0;
        int r=n-1;
        int leftAbs=0;
        int rightAbs=0;
        int count=0;
        while (l<=r){
            count++;
            leftAbs=Math.abs(arr[l]);
            rightAbs=Math.abs(arr[r]);
            if (leftAbs<rightAbs){
                while (r>=0&&Math.abs(arr[r])==rightAbs){
                    r--;
                }
            }else if (leftAbs>rightAbs){
                while (l<n&&Math.abs(arr[l])==leftAbs){
                    l++;
                }
            }else {
                while (r>=0&&Math.abs(arr[r])==rightAbs){
                    r--;
                }
                while (l<n&&Math.abs(arr[l])==leftAbs){
                    l++;
                }
            }
        }
        return count;
    }

    // for test
    public static int[] randomSortedArray(int len, int value) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) - (int) (Math.random() * value);
        }
        Arrays.sort(ans);
        return ans;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int cur : arr) {
            System.out.print(cur + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 100;
        int value = 500;
        int testTimes = 200000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomSortedArray(len, value);
            int ans1 = diff1(arr);
            int ans2 = diff2(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish");
    }
}
