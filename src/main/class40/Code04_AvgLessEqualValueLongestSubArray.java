package class40;

import java.util.TreeMap;

public class Code04_AvgLessEqualValueLongestSubArray {

    // 暴力解
    public static int ways1(int[] arr, int v) {
        int ans = 0;
        for (int l = 0; l < arr.length; l++) {
            for (int r = l; r < arr.length; r++) {
                int sum = 0;
                int k = r - l + 1;
                for (int j = l; j <= r; j++) {
                    sum += arr[j];
                }
                double avg = (double) sum / (double) k;
                if (avg <= v) {
                    ans = Math.max(ans, k);
                }
            }
        }
        return ans;
    }

    /**
     * 题目要求找到满足平均值小于等于v的最长子数组的长度
     * 比如数组 7 4 3 9 6
     * v=5
     * 可以对arr数组做一下转化,每个元素都减去5
     * 2 -1 -2 4 1
     * 原数组求<=5的最长子数组
     * 转化为 求这个新数组平均值<=0的最长子数组
     * 子数组必须以i结尾,累计和<=0的最长子数组
     * 代码中sum,就是每一个从原始数组的值先转化,然后把转化的值累加起来的的前缀和
     * 比如转化数组
     * 2 -1 -2 4 1
     * sum依次为2 1 -1 3 4
     * 那么,比如当前你来到一个位置i
     * sum就是0...i的转化前缀和,假设是100
     * 小于等于0的最长子数组的长度
     * 那就是找>=100且距离100最近的转化前缀和最早出现在哪
     *
     * @param arr arr
     * @param v   平均值
     * @return 最长子数组的长度
     */
    public static int ways2(int[] arr, int v) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] -= v;
        }
        // key: 前缀和sum
        // value:sum最早出现的位置i
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, -1);
        int sum = 0;
        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            Integer ceiling = map.ceilingKey(sum);
            if (ceiling != null) {
                len = Math.max(len, i - map.get(ceiling));
            } else {
                map.put(sum, i);
            }
        }
        return len;
    }

    public static int ways3(int[] arr, int v) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] -= v;
        }
        return maxLengthAwesome(arr, 0);
    }

    public static int maxLengthAwesome(int[] arr, int k) {
        int n = arr.length;
        int[] sums = new int[n];
        int[] ends = new int[n];
        sums[n - 1] = arr[n - 1];
        ends[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (sums[i + 1] < 0) {
                sums[i] = arr[i] + sums[i + 1];
                ends[i] = ends[i + 1];
            } else {
                sums[i] = arr[i];
                ends[i] = i;
            }
        }
        int end = 0;
        int sum = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            while (end < n && sum + sums[end] <= k) {
                sum += sums[end];
                end = ends[end] + 1;
            }
            res = Math.max(res, end - i);
            if (end > i) {
                sum -= arr[i];
            } else {
                end = i + 1;
            }
        }
        return res;
    }

    // 用于测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * maxValue);
        }
        return ans;
    }

    // 用于测试
    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    // 用于测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 用于测试
    public static void main(String[] args) {
        System.out.println("测试开始");
        int maxLen = 20;
        int maxValue = 100;
        int testTime = 500000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int value = (int) (Math.random() * maxValue);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int[] arr3 = copyArray(arr);
            int ans1 = ways1(arr1, value);
            int ans2 = ways2(arr2, value);
            int ans3 = ways3(arr3, value);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("测试出错！");
                System.out.print("测试数组：");
                printArray(arr);
                System.out.println("子数组平均值不小于 ：" + value);
                System.out.println("方法1得到的最大长度：" + ans1);
                System.out.println("方法2得到的最大长度：" + ans2);
                System.out.println("方法3得到的最大长度：" + ans3);
                System.out.println("=========================");
            }
        }
        System.out.println("测试结束");
    }
}
