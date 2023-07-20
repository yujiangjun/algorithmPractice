package class39;

import java.io.*;
import java.util.Arrays;

public class SnacksWaysMain2 {

    // 用来收集所有输入的数字
    public static long[] arr = new long[31];
    public static int size = 0;
    // 左半部分的累加和
    public static long[] leftSum = new long[1 << 16];
    // 左半部分累加和的数量
    public static int leftSize = 0;
    public static long[] rightSum = new long[1 << 16];
    public static int rightSize = 0;


    public static long ways(long w) {
        if (size == 0) {
            return 0;
        }
        if (size == 1) {
            return arr[0] <= w ? 2 : 1;
        }
        // 将arr数组拆成左右两个数组分别求和
        int mid = size / 2;
        dfsLeft(0, mid + 1, 0);
        dfsRight(mid + 1, size, 0);
        Arrays.sort(leftSum, 0, leftSize);
        Arrays.sort(rightSum, 0, rightSize);

        // leftSum:  0 1 1 1 2 2 3 4 4
        // rightSum: 0 1 2 3 3 3 4 4 5
        // w=5
        // leftSum中是累加和是0 的个数为1
        // 所以rightSum中找到小于等于5的累计和是9个
        // 依次类推

        long ans = 0;
        long count = 1;
        for (int i = 1; i < leftSize; i++) {
            if (leftSum[i] == leftSum[i - 1]) {
                count++;
            } else {
                ans += (count * find(w - leftSum[i - 1]));
                count = 1;
            }
        }
        ans += count * find(w - leftSum[leftSize - 1]);
        return ans;
    }


    public static void dfsLeft(int cur, int end, long sum) {
        if (cur == end) {
            leftSum[leftSize++] = sum;
            return;
        }
        dfsLeft(cur + 1, end, sum);
        dfsLeft(cur + 1, end, sum + arr[cur]);
    }

    public static void dfsRight(int cur, int end, long sum) {
        if (cur == end) {
            rightSum[rightSize++] = sum;
            return;
        }
        dfsRight(cur + 1, end, sum);
        dfsRight(cur + 1, end, sum + arr[cur]);
    }

    /**
     * 统计rightSum中小于等于sum的个数
     *
     * @param sum 和
     * @return 个数
     */
    public static long find(long sum) {
        int ans = -1;
        int l = 0;
        int r = rightSize - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (rightSum[mid] <= sum) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans + 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            size = (int) in.nval;
            in.nextToken();
            int bag = (int) in.nval;
            for (int i = 0; i < size; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            long ways = ways(bag);
            out.println(ways);
            out.flush();
        }
    }
}
