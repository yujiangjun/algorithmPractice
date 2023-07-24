package class41;

/**
 * code01题目的增强
 * 给你一个arr数组
 * 要求返回一个s[]数组
 * s[i]表示在arr[0..i]中找到一个最后划分,左部分和右部分的最小值最大
 */
public class Code02_BestSplitForEveryPosition {

    public static int[] bestSplit1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return new int[0];
        }
        int n = arr.length;
        int[] ans = new int[n];
        ans[0] = 0;
        for (int range = 1; range < n; range++) {
            int s = 0;
            for (int i = 0; i < range; i++) {
                int sumL = 0;
                int sumR = 0;
                for (int l = 0; l <= i; l++) {
                    sumL += arr[l];
                }
                for (int r = i + 1; r <= range; r++) {
                    sumR += arr[r];
                }
                s = Math.max(s, Math.min(sumL, sumR));
            }
            ans[range] = s;
        }
        return ans;
    }

    public static int[] bestSplit2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return new int[0];
        }
        int n = arr.length;
        int[] ans = new int[n];
        ans[0] = 0;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }

        for (int range = 1; range < n; range++) {
            for (int s = 0; s < range; s++) {
                int sumL = sum(sum, 0, s);
                int sumR = sum(sum, s + 1, range);
                ans[range] = Math.max(ans[range], Math.min(sumL, sumR));
            }
        }
        return ans;
    }

    public static int[] bestSplit3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return new int[0];
        }
        int n = arr.length;
        int[] ans = new int[n];
        ans[0] = 0;
        int[] sum = new int[n + 1];
        for (int i = 0; i < arr.length; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        int best = 0;
        for (int range = 1; range < n; range++) {

            while (best + 1 < range) {
                int beforeBest = Math.min(sum(sum, 0, best), sum(sum, best + 1, range));
                int afterBest = Math.min(sum(sum, 0, best+1), sum(sum, best + 2, range));
                if (afterBest >= beforeBest) {
                    best++;
                } else {
                    break;
                }
            }
            ans[range] = Math.min(sum(sum, 0, best), sum(sum, best + 1, range));
        }
        return ans;
    }

    public static int sum(int[] sum, int l, int r) {
        return sum[r + 1] - sum[l];
    }

    public static int[] randomArray(int len, int max) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        return ans;
    }

    public static boolean isSameArray(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        int N = arr1.length;
        for (int i = 0; i < N; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int N = 20;
        int max = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, max);
            int[] ans1 = bestSplit1(arr);
            int[] ans2 = bestSplit2(arr);
            int[] ans3 = bestSplit3(arr);
            if (!isSameArray(ans1, ans2) || !isSameArray(ans1, ans3)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }

}
