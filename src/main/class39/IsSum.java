package class39;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 题目:arr中的值可能是正数/负数/0
 * 自由选择arr中的数,能不能累加到sum
 */
public class IsSum {

    public static boolean isSum1(int[] arr, int sum) {
        if (sum == 0) {
            return true;
        }
        if (arr == null || arr.length == 0) {
            return false;
        }
        return process1(arr, arr.length - 1, sum);
    }

    /**
     * 从0到i位置上的数自由选择,累加和是否可以到sum
     *
     * @param arr
     * @param i
     * @param sum
     * @return
     */
    public static boolean process1(int[] arr, int i, int sum) {
        if (sum == 0) {
            return true;
        }
        if (i == -1) {
            return false;
        }
        boolean p1 = process1(arr, i - 1, sum);
        boolean p2 = process1(arr, i - 1, sum - arr[i]);
        return p1 || p2;
    }

    public static boolean process2(int[] arr, int i, int sum, HashMap<Integer, HashMap<Integer, Boolean>> dp) {
        if (dp.containsKey(i) && dp.get(i).containsKey(sum)) {
            return dp.get(i).get(sum);
        }
        boolean ans = false;
        if (sum == 0) {
            ans = true;
        } else if (i != -1) {
            ans = process2(arr, i - 1, sum, dp) || process2(arr, i - 1, sum - arr[i], dp);
        }
        if (!dp.containsKey(i)) {
            dp.put(i, new HashMap<>());
        }
        dp.get(i).put(sum, ans);
        return ans;
    }

    public static boolean isSum2(int[] arr, int sum) {
        if (sum == 0) {
            return true;
        }
        if (arr == null || arr.length == 0) {
            return false;
        }
        return process2(arr, arr.length - 1, sum, new HashMap<>());
    }

    public static boolean isSum3(int[] arr, int sum) {
        if (sum == 0) {
            return true;
        }
        if (arr == null || arr.length == 0) {
            return false;
        }
        int min = 0;
        int max = 0;
        for (int num : arr) {
            min += num < 0 ? num : 0;
            max += num > 0 ? num : 0;
        }
        if (sum < min || sum > max) {
            return false;
        }
        int n = arr.length;
        // dp[i][j]含义: 在0-i位置上自由选择的子数组的累加和是否有等于j的?
        boolean[][] dp = new boolean[n][max - min + 1];
        dp[0][-min] = true;
        dp[0][arr[0] - min] = true;
        for (int i = 1; i < n; i++) {
            for (int j = min; j <= max; j++) {
                dp[i][j - min] = dp[i - 1][j - min];
                int next = j - min - arr[i];
                dp[i][j - min] |= (next >= 0 && next <= max - min && dp[i - 1][next]);
            }
        }
        return dp[n - 1][sum - min];
    }

    public static void process4(int[] arr, int i, int end, int pre, HashSet<Integer> ans){
        if (i==end){
            ans.add(pre);
        }else {
            process4(arr,i+1,end,pre,ans);
            process4(arr,i+1,end,pre+arr[i],ans);
        }
    }

    public static boolean isSum4(int[] arr,int sum){
        if (sum==0){
            return true;
        }
        if (arr==null||arr.length==0){
            return false;
        }
        if (arr.length==1){
            return arr[0]==sum;
        }
        int n=arr.length;
        int mid=n>>1;
        HashSet<Integer> leftSum = new HashSet<>();
        HashSet<Integer> rightSum = new HashSet<>();
        process4(arr,0,mid,0,leftSum);
        process4(arr,mid,n,0,rightSum);
        for (Integer l : leftSum) {
            if (rightSum.contains(sum-l)){
                return true;
            }
        }
        return false;
    }

    // 为了测试
    // 生成长度为len的随机数组
    // 值在[-max, max]上随机
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * ((max << 1) + 1)) - max;
        }
        return arr;
    }

    // 对数器验证所有方法
    public static void main(String[] args) {
        int N = 20;
        int M = 100;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int size = (int) (Math.random() * (N + 1));
            int[] arr = randomArray(size, M);
            int sum = (int) (Math.random() * ((M << 1) + 1)) - M;
            boolean ans1 = isSum1(arr, sum);
            boolean ans2 = isSum2(arr, sum);
            boolean ans3 = isSum3(arr, sum);
            boolean ans4 = isSum4(arr, sum);
            if (ans1 ^ ans2 || ans3 ^ ans4 || ans1 ^ ans3) {
                System.out.println("出错了！");
                System.out.print("arr : ");
                for (int num : arr) {
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println("sum : " + sum);
                System.out.println("方法一答案 : " + ans1);
                System.out.println("方法二答案 : " + ans2);
                System.out.println("方法三答案 : " + ans3);
                System.out.println("方法四答案 : " + ans4);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
