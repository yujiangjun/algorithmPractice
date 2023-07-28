package class43;

public class Code01_CanIWin {

    public static boolean canIWin0(int choose, int total) {
        if (total == 0) {
            return true;
        }
        if ((choose * (choose + 1) >> 1) < total) {
            return false;
        }
        int[] arr = new int[choose];
        for (int i = 0; i < choose; i++) {
            arr[i] = i + 1;
        }
        return process(arr, total);
    }

    /**
     * 还剩下rest数，返回先手是否会赢
     */
    public static boolean process(int[] arr, int rest) {
        if (rest <= 0) {
            return false;
        }
        // 先手尝试所有的可能
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != -1) {
                int temp = arr[i];
                arr[i] = -1;
                boolean next = process(arr, rest - temp);
                arr[i] = temp;
                if (!next) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean canIWin1(int choose, int total) {
        if (total == 0) {
            return true;
        }
        if ((choose * (choose + 1) >> 1) < total) {
            return false;
        }
        return process1(choose,0,total);
    }

    /**
     * 先手在1到choose中自由选择
     * rest表示还剩余的数
     * status=0表示没拿
     * status=1表示拿了
     */
    public static boolean process1(int choose, int status, int rest) {
        if (rest <= 0) {
            return false;
        }
        //1-choose自由选择
        for (int i = 1; i <= choose; i++) {
            if (((1 << i) & status) == 0) {
                if (!process1(choose, ((1 << i) | status), rest - i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean canIWin2(int choose, int total) {
        if (total == 0) {
            return true;
        }
        if ((choose * (choose + 1) >> 1) < total) {
            return false;
        }
        int[] dp = new int[1 << (choose + 1)];
        return process2(choose,0,total,dp);
    }

    public static boolean process2(int choose, int status, int rest, int[] dp) {
        if (dp[status] != 0) {
            return dp[status] == 1 ? true : false;
        }
        boolean ans = false;
        if (rest > 0) {
            for (int i = 1; i <= choose; i++) {
                if (((1 << i) & status) == 0) {
                    if (!process2(choose, (status | (1 << i)), rest - i, dp)) {
                        ans = true;
                        break;
                    }
                }
            }
        }
        dp[status] = ans ? 1 : -1;
        return ans;
    }
}
