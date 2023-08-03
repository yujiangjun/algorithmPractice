package class46;

import java.util.LinkedList;

public class Code04_MaxSumLengthNoMore {

    public static int test(int[] arr, int m) {
        if (arr == null || arr.length == 0 || m < 1) {
            return 0;
        }
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        for (int l = 0; l < n; l++) {
            int sum = 0;
            for (int r = l; r < n; r++) {
                if (r - l + 1 > m) {
                    break;
                }
                sum += arr[r];
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    public static int maxSum(int[] arr, int m) {
        if (arr == null || arr.length == 0 || m < 1) {
            return 0;
        }
        int n = arr.length;
        int[] sum = new int[n];
        sum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        int i = 0;
        int end = Math.min(n, m);
        for (; i < end; i++) {
            while (!qmax.isEmpty() && sum[qmax.peekLast()] <= sum[i]) {
                qmax.pollLast();
            }
            qmax.add(i);
        }
        int max = sum[qmax.peekFirst()];
        int l = 0;
        for (; i < n; l++, i++) {
            if (qmax.peekFirst() == l) {
                qmax.pollFirst();
            }
            while (!qmax.isEmpty() && sum[qmax.peekLast()] <= sum[i]) {
                qmax.pollLast();
            }
            qmax.add(i);
            max = Math.max(max, sum[qmax.peekFirst()] - sum[l]);
        }
        for (; l < n - 1; l++) {
            if (qmax.peekFirst() == l) {
                qmax.pollFirst();
            }
            max = Math.max(max, sum[qmax.peekFirst()] - sum[l]);
        }
        return max;
    }

    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // 用作测试
    public static void main(String[] args) {
        int maxN = 50;
        int maxValue = 100;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxN);
            int M = (int) (Math.random() * maxN);
            int[] arr = randomArray(N, maxValue);
            int ans1 = test(arr, M);
            int ans2 = maxSum(arr, M);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}
