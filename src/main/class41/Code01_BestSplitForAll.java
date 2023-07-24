package class41;

public class Code01_BestSplitForAll {

    public static int bestSplit1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int n = arr.length;
        int ans = 0;
        for (int l = 0; l < n - 1; l++) {
            int sumL = 0;
            int sumR = 0;
            for (int s = 0; s <= l; s++) {
                sumL += arr[s];
            }
            for (int r = l + 1; r < n; r++) {
                sumR += arr[r];
            }
            ans = Math.max(ans, Math.min(sumL, sumR));
        }
        return ans;
    }

    public static int bestSplit2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int n = arr.length;
        int ans = 0;
        int sumL = 0;
        int sumR = 0;
        int sumALl = 0;
        for (int i : arr) {
            sumALl += i;
        }
        for (int i = 0; i < n - 1; i++) {
            sumL += arr[i];
            sumR = sumALl - sumL;
            ans = Math.max(ans, Math.min(sumL, sumR));
        }
        return ans;
    }


    public static int[] randomArray(int len, int max) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 20;
        int max = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, max);
            int ans1 = bestSplit1(arr);
            int ans2 = bestSplit2(arr);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }

}
