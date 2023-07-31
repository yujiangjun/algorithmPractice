package class43;

/**
 * N*M铺地砖问题
 */
public class Code03_PavingTile {

    public static int ways1(int n, int m) {
        if (n < 1 || m < 1 || ((m * n) & 1) != 0) {
            return 0;
        }
        if (n == 1 || m == 1) {
            return 1;
        }
        int[] pre = new int[m];// pre代表上一行的状态
        for (int i = 0; i < pre.length; i++) {
            pre[i] = 1;
        }
        return process(pre, 0, n);
    }

    /**
     * @param pre   表示level-1行的状态 0 表示没有砖 1表示有砖
     * @param level 正在level行做决定
     * @param n     表示有总共有多少行 固定参数
     *              level-2及其以上行的砖都已经铺满
     *              level做决定，让所有区域都铺满的方法数
     */
    public static int process(int[] pre, int level, int n) {
        if (level == n) {
            for (int num : pre) {
                if (num == 0) {
                    return 0;
                }
            }
            return 1;
        }

        // 没有到终止行，可以选择在当前的level行摆瓷砖
        int[] op = getOp(pre);
        return dfs(op, 0, level, n);
    }

    public static int[] getOp(int[] pre) {
        int[] cur = new int[pre.length];
        for (int i = 0; i < pre.length; i++) {
            cur[i] = pre[i] ^ 1;
        }
        return cur;
    }

    /**
     * op[i]=0 可以考虑摆砖
     * op[i]=1 只能竖着向上摆砖
     */
    public static int dfs(int[] op, int col, int level, int n) {
        if (col == op.length) {
            return process(op, level + 1, n);
        }
        int ans = 0;
        // col 位置不横摆
        //col 列不向右摆砖
        ans += dfs(op, col + 1, level, n);// col位置上不摆横转
        // col 位置横摆，向右
        if (col + 1 < op.length && op[col] == 0 && op[col + 1] == 0) {
            op[col] = 1;
            op[col + 1] = 1;
            ans += dfs(op, col + 2, level, n);
            op[col] = 0;
            op[col + 1] = 0;
        }
        return ans;
    }

    public static int ways2(int n, int m) {
        if (n < 1 || m < 1 || ((n * m) & 1) != 0) {
            return 0;
        }
        if (n == 1 || m == 1) {
            return 1;
        }
        int max = Math.max(n, m);
        int min = Math.min(n, m);
        int pre = (1 << min) - 1;
        return process2(pre, 0, max, min);
    }

    /**
     * pre 上一行的状态 limit是用来对齐的，固定参数
     * 当前来到i行，一共n行，返回填满的方法数
     */
    public static int process2(int pre, int i, int n, int m) {
        if (i == n) {
            return pre == ((1 << m) - 1) ? 1 : 0;
        }
        int op = ((~pre) & ((1 << m) - 1));
        return dfs2(op, m - 1, i, n, m);
    }

    public static int dfs2(int op, int col, int level, int n, int m) {
        if (col == -1) {
            return process2(op, level + 1, n, m);
        }
        int ans = 0;
        ans += dfs2(op, col - 1, level, n, m);
        if ((op & (1 << col)) == 0 && col - 1 >= 0 && (op & (1 << (col - 1))) == 0) {
            ans += dfs2((op | (3 << (col - 1))), col - 2, level, n, m);
        }
        return ans;
    }

    // 记忆化搜索版本
    public static int ways3(int n, int m) {
        if (n < 1 || m < 1 || ((n * m) & 1) != 0) {
            return 0;
        }
        if (n == 1 || m == 1) {
            return 1;
        }
        int max = Math.max(n, m);
        int min = Math.min(n, m);
        int pre = (1 << min) - 1;
        int[][] dp = new int[1 << min][max + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process3(pre, 0, max, min, dp);
    }

    public static int process3(int pre, int i, int n, int m, int[][] dp) {
        if (dp[pre][i] != -1) {
            return dp[pre][i];
        }
        int ans = 0;
        if (i == n) {
            ans = pre == ((1 << m) - 1) ? 1 : 0;
        } else {
            int op = ((~pre) & ((1 << m) - 1));
            ans = dfs3(op, m - 1, i, n, m, dp);
        }
        dp[pre][i] = ans;
        return ans;
    }

    public static int dfs3(int op, int col, int level, int n, int m, int[][] dp) {
        if (col == -1) {
            return process3(op, level + 1, n, m, dp);
        }
        int ans = 0;
        ans += dfs3(op, col - 1, level, n, m, dp);
        if (col > 0 && (op & (3 << (col - 1))) == 0) {
            ans += dfs3((op | (3 << (col - 1))), col - 2, level, n, m, dp);
        }
        return ans;
    }

    // 严格的位置依赖的动态规划
    public static int ways4(int n, int m) {
        if (n < 1 || m < 1 || ((n * m) & 1) != 0) {
            return 0;
        }
        if (n == 1 || m == 1) {
            return 1;
        }
        int big = n > m ? n : m;
        int small = big == n ? m : n;
        int sn = 1 << small;
        int limit = sn - 1;
        int[] dp = new int[sn];
        dp[limit] = 1;
        int[] cur = new int[sn];
        for (int level = 0; level < big; level++) {
            for (int status = 0; status < sn; status++) {
                if (dp[status] != 0) {
                    int op = (~status) & limit;
                    dfs4(dp[status], op, 0, small - 1, cur);
                }
            }
            for (int i = 0; i < sn; i++) {
                dp[i] = 0;
            }
            int[] tmp = dp;
            dp = cur;
            cur = tmp;
        }
        return dp[limit];
    }

    public static void dfs4(int way, int op, int index, int end, int[] cur) {
        if (index == end) {
            cur[op] += way;
        } else {
            dfs4(way, op, index + 1, end, cur);
            if (((3 << index) & op) == 0) {
                dfs4(way, op | (3 << index), index + 1, end, cur);
            }
        }
    }

    public static void main(String[] args) {
        int N = 8;
        int M = 6;
        System.out.println(ways1(N, M));
        System.out.println(ways2(N, M));
        System.out.println(ways3(N, M));
        System.out.println(ways4(N, M));

        N = 10;
        M = 10;
        System.out.println("=========");
        System.out.println(ways3(N, M));
        System.out.println(ways4(N, M));
    }
}
