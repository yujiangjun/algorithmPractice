package dachang.class1;

import java.util.HashMap;

public class Code07_TargetSum {

    public static int findTargetSumWays1(int[] arr, int s) {
        return process1(arr, 0, s);
    }

    /**
     * 从Index位置开始以及后面所有的数自由选择，
     * 搞出rest这个数字的方法数
     */
    public static int process1(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        // index 位置的数前面+
        int p1 = process1(arr, index + 1, rest - arr[index]);
        // Index位置的数前面-
        int p2 = process1(arr, index + 1, rest + arr[index]);
        return p1 + p2;
    }

    public static int process2(int[] arr, int index, int rest, HashMap<Integer, HashMap<Integer, Integer>> dp) {
        if (dp.containsKey(index) && dp.containsKey(rest)) {
            return dp.get(index).get(rest);
        }
        int ans = 0;
        if (index == arr.length && rest == 0) {
            ans = 1;
        } else {
            int p1 = process2(arr, index + 1, rest - arr[index], dp);
            int p2 = process2(arr, index + 1, rest + arr[index], dp);
            ans = p1 + p2;
        }
        if (!dp.containsKey(index)) {
            dp.put(index, new HashMap<>());
        }
        dp.get(index).put(rest, ans);
        return ans;
    }

    /**
     * 优化点1：虽然arr中有负数，但是把arr改成都是整数的数组，方法数也不会有变化
     * 优化点2：如果target> arr数组的累加和，那么方法数为0
     * 优化点3：由于不管怎么+或者-，都不会改变其奇偶性，所以如果累加和和target的奇偶性不同，那么方法数为0
     * 优化点4.P 为arr中的整数集合，N为负数集合，那么任何一种方案中
     * 一定有sum(P)-sum(N)=target
     * sum(P)-sum(N)+sum(P)+sum(N)=target+sum(P)+sum(N)
     * sum(P)=(target+sum)/2
     * 那么只有在arr数组中，找到一个集合的累加和=(target+sum)/2,那么久找到一种方案
     * 所以有多少个这样的集合P，就有多少种方法数能够搞出target
     * 优化点5：空间压缩
     */
    public static int findTargetSumWays(int[] arr, int target) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum < target || ((target & 1) ^ (sum & 1)) != 0 ? 0 : subset2(arr, (target + sum) >> 1);
    }

    public static int subset1(int[] arr, int s) {
        if (s<0){
            return 0;
        }
        int n=arr.length;
        // dp[i][j]: arr前缀长度为i的所有子集中，有多少累加和是j的
        //dp[i][j]=p表示 0..i位置的数自由选择，累加和为j的子集数为p
        int[][] dp = new int[n+1][s+1];
        dp[0][0]=1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= s; j++) {
                // i位置的数不用
                dp[i][j]=dp[i-1][j];
                // i位置的数用
                if (j-arr[i]>=0){
                    dp[i][j]+=dp[i-1][j-arr[i]];
                }
            }
        }
        return dp[n][s];
    }
    public static int subset2(int[] arr,int s){
        if (s<0){
            return 0;
        }
        int n=arr.length;
        int[] dp = new int[s+1];
        dp[0]=1;
        for (int i = 0; i < n; i++) {
            for (int j=s;j>=arr[i];j--){
                dp[j]+=dp[j-arr[i]];
            }
        }
        return dp[s];
    }
}
