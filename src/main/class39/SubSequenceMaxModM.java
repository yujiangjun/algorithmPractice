package class39;

import java.util.HashSet;
import java.util.TreeSet;

/**
 *
 */
// 给定一个非负数组arr,和一个正数m.返回arr的所有子序列中累加和%m之后的最大值
public class SubSequenceMaxModM {


    // 0~index 中自由选择形成的子序列累加和为sum
    public static void process(int[] arr, int index, int sum, HashSet<Integer> set) {
        if (index == arr.length) {
            set.add(sum);
        } else {
            // 来到Index位置,有2种方案
            // 1 index位置的数不选择
            process(arr, index + 1, sum, set);
            // 2 index位置的选择
            process(arr, index + 1, sum + arr[index], set);
        }
    }

    public static int max1(int[] arr, int m) {
        HashSet<Integer> set = new HashSet<>();
        process(arr, 0, 0, set);
        int max = 0;
        for (Integer num : set) {
            max = Math.max(max, num % m);
        }
        return max;
    }

    public static int max2(int[] arr, int m) {
        int sum = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }
        // dp[i][j]表示0~i 位置的数自由选择形成的子序列的累加和=sum,是否有效
        boolean[][] dp = new boolean[n][sum + 1];
        for (int i = 0; i < n; i++) {
            // 0~ i 位置自由选择的子序列的累加和=0的情况是一个数也不选择 ,所以有效
            dp[i][0] = true;
        }
        // 0~0 位置自由选择,那么只有一种情况就是选择0位置的数,所以累加和为arr[0]
        dp[0][arr[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= sum; j++) {
                // 来到i位置,2种情况 选择i位置的数和不选择i位置的数
                // 1 不选择i 位置的数
                dp[i][j] = dp[i - 1][j];
                // 2 选择i位置的数 所以dp[i][j]=dp[i-1][j-arr[i]]
                if (j >= arr[i]) {
                    dp[i][j] |= dp[i - 1][j - arr[i]];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= sum; i++) {
            if (dp[n - 1][i]) {
                ans = Math.max(ans, i % m);
            }
        }
        return ans;
    }


    public static int max3(int[] arr, int m) {
        int n = arr.length;
        // dp[i][j] 含义是0~i位置的数自由选择形成的子序列的累加和%m=j是否是有效的
        boolean[][] dp = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0] % m] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                // 不选择i位置的数
                dp[i][j] = dp[i - 1][j];
                // 选择i位置的数
                int cur = arr[i] % m;
                // 1. arr[i]%m <j
                if (cur <= j) {
                    dp[i][j] |= dp[i - 1][j - cur];
                } else {
                    // arr[i]%m>j
                    dp[i][j] |= dp[i - 1][m + j - cur];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            if (dp[n - 1][i]) {
                ans = i;
            }
        }
        return ans;
    }

    // 如果arr的累加和很大,m也很大
    // 但是arr的长度相对不大
    // 则采用分治算法进行求解
    public static int max4(int[] arr, int m) {
        if (arr.length == 1) {
            return arr[0] % m;
        }
        int mid = (arr.length - 1) / 2;
        TreeSet<Integer> sortSet1 = new TreeSet<>();
        process4(arr, 0, 0, mid, m, sortSet1);
        TreeSet<Integer> sortSet2 = new TreeSet<>();
        process4(arr, mid + 1, 0, arr.length - 1, m, sortSet2);
        int ans = 0;
        // 1.0~mid形成的子序列累加和%m在sortSet1中
        // 2.mid+1 到arr.length-1形成的子序列的累加和%m在sortSet2中
        // 3. 0~ arr.length-1 形成的子序列的累加和%m
        for (Integer leftMod : sortSet1) {
            // 求sort2中小于m-1-leftMod 最接近的数
            ans = Math.max(ans, leftMod + sortSet2.floor(m - 1 - leftMod));
        }
        return ans;
    }

    // 从index开始出发,最后有边界是end+1,arr[index...end]
    // 从index到end+1,自由选择形成的累加和%m,加入到treeSet中
    public static void process4(int[] arr, int index, int sum, int end, int m, TreeSet<Integer> sortSet) {
        if (index == end + 1) {
            sortSet.add(sum % m);
        } else {
            process4(arr, index + 1, sum, end, m, sortSet);
            process4(arr, index + 1, sum + arr[index], end, m, sortSet);
        }
    }

    public static int[] generateRandomArray(int len, int value) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value);
        }
        return ans;
    }

    public static void main(String[] args) {
        int len = 10;
        int value = 100;
        int m = 76;
        int testTime = 500000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(len, value);
            int ans1 = max1(arr, m);
            int ans2 = max2(arr, m);
            int ans3 = max3(arr, m);
            int ans4 = max4(arr, m);
            if (ans1 != ans2 || ans2 != ans3 || ans3 != ans4) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }

}
