package class43;

import java.util.ArrayList;
import java.util.List;

public class Code02_TSP {

    public static int t1(int[][] matrix) {
        int n = matrix.length;
        List<Integer> set = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            set.add(1);
        }
        return func1(matrix, set, 0);
    }

    public static int func1(int[][] matrix, List<Integer> set, int start) {
        int cityNum = 0;
        for (int i = 0; i < set.size(); i++) {
            if (set.get(i) != null) {
                cityNum++;
            }
        }
        if (cityNum == 1) {
            return matrix[start][0];
        }
        set.set(start, null);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < set.size(); i++) {
            if (set.get(i) != null) {
                int cur = matrix[start][i] + func1(matrix, set, i);
                min = Math.min(min, cur);
            }
        }
        set.set(start, 1);
        return min;
    }

    public static int t2(int[][] matrix) {
        int n = matrix.length;
        int allCity = (1 << n) - 1;
        return f2(matrix, allCity, 0);
    }

    // 从start出发，把set里面的城市都走一遍，最后来到0这个城市的最小距离
    public static int f2(int[][] matrix, int cityStatus, int start) {
        // 二进制中只有一个1了
        if (cityStatus == (cityStatus & (~cityStatus + 1))) {
            return matrix[start][0];
        }
        // start位置的1去掉
        cityStatus &= (~(1 << start));
        int min = Integer.MAX_VALUE;
        for (int move = 0; move < matrix.length; move++) {
            if ((cityStatus & (1 << move)) != 0) {
                int cur = matrix[start][move] + f2(matrix, cityStatus, move);
                min = Math.min(min, cur);
            }
        }
        cityStatus |= (1 << start);
        return min;
    }

    public static int f3(int[][] matrix, int cityStatus, int start, int[][] dp) {
        if (dp[cityStatus][start] != -1) {
            return dp[cityStatus][start];
        }
        if (cityStatus == (cityStatus & (~cityStatus + 1))) {
            dp[cityStatus][start] = matrix[start][0];
        } else {
            cityStatus &= (~(1 << start));
            int min = Integer.MAX_VALUE;
            //枚举所有的城市
            for (int move = 0; move < matrix.length; move++) {
                if (move != start && (cityStatus & (1 << move)) != 0) {
                    int cur = matrix[start][move] + f3(matrix, cityStatus, move, dp);
                    min = Math.min(min, cur);
                }
            }
            cityStatus |= (1 << start);
            dp[cityStatus][start] = min;
        }
        return dp[cityStatus][start];
    }

    public static int t3(int[][] matrix) {
        int n = matrix.length;
        int allCity = (1 << n) - 1;
        int[][] dp = new int[1 << n][n];
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        return f3(matrix, allCity, 0, dp);
    }

    public static int t4(int[][] matrix) {
        int n = matrix.length;
        int statusNums = 1 << n;
        int[][] dp = new int[statusNums][n];
        for (int status = 0; status < statusNums; status++) {
            for (int start = 0; start < n; start++) {
                if ((status & (1 << start)) != 0) {
                    if (status == (status & (~status + 1))) {
                        dp[status][start] = matrix[start][0];
                    } else {
                        int min = Integer.MAX_VALUE;
                        int preStatus = status & (~(1 << start));
                        for (int i = 0; i < n; i++) {
                            if ((preStatus & (1 << i)) != 0) {
                                int cur = matrix[start][i] + dp[preStatus][i];
                                min = Math.min(min, cur);
                            }
                        }
                        dp[status][start] = min;
                    }
                }
            }
        }
        return dp[statusNums - 1][0];
    }

    public static int tsp1(int[][] matrix, int origin) {
        if (matrix == null || matrix.length < 2 || origin < 0 || origin >= matrix.length) {
            return 0;
        }
        ArrayList<Integer> cities = new ArrayList<>();
        //cities[0]!=null 表示0城在集合里
        // cities[i]!=null 表示i城在集合里
        for (int i = 0; i < matrix.length; i++) {
            cities.add(1);
        }
        cities.set(origin, null);
        return process(matrix, origin, cities, origin);
    }

    // origin 固定参数 唯一的目标
    //cities 要考虑的集合 一定不含有origin
    // 当前来到的城市cur
    public static int process(int[][] matrix, int aim, ArrayList<Integer> cities, int cur) {
        boolean hasCity = false;//集合中还是否有城市
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i) != null) {
                hasCity = true;
                cities.set(i, null);
                ans = Math.min(ans, matrix[cur][i] + process(matrix, aim, cities, i));
                cities.set(i, 1);
            }
        }
        return hasCity ? ans : matrix[cur][aim];
    }

    // cities里，一定含有cur这座城市
    // 解决的是，集合从cur出发，通过集合里面的所有城市，最终来到aim的最短距离
    public static int process2(int[][] matrix, int aim, ArrayList<Integer> cities, int cur) {
        if (cities.size() == 1) {
            return matrix[cur][aim];
        }
        cities.set(cur, null);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i) != null) {
                int dis = matrix[cur][i] + process2(matrix, aim, cities, i);
                ans = Math.min(ans, dis);
            }
        }
        cities.set(cur, 1);
        return ans;
    }

    public static int tsp2(int[][] matrix, int origin) {
        if (matrix == null || matrix.length < 2 || origin < 0 || origin >= matrix.length) {
            return 0;
        }
        int N = matrix.length - 1; // 除去origin之后是n-1个点
        int S = 1 << N; // 状态数量
        int[][] dp = new int[S][N];
        int icity = 0;
        int kcity = 0;
        for (int i = 0; i < N; i++) {
            icity = i < origin ? i : i + 1;
            // 00000000 i
            dp[0][i] = matrix[icity][origin];
        }
        for (int status = 1; status < S; status++) {
            // 尝试每一种状态 status = 0 0 1 0 0 0 0 0 0
            // 下标 8 7 6 5 4 3 2 1 0
            for (int i = 0; i < N; i++) {
                // i 枚举的出发城市
                dp[status][i] = Integer.MAX_VALUE;
                if ((1 << i & status) != 0) {
                    // 如果i这座城是可以枚举的，i = 6 ， i对应的原始城的编号，icity
                    icity = i < origin ? i : i + 1;
                    for (int k = 0; k < N; k++) { // i 这一步连到的点，k
                        if ((1 << k & status) != 0) { // i 这一步可以连到k
                            kcity = k < origin ? k : k + 1; // k对应的原始城的编号，kcity
                            dp[status][i] = Math.min(dp[status][i], dp[status ^ (1 << i)][k] + matrix[icity][kcity]);
                        }
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            icity = i < origin ? i : i + 1;
            ans = Math.min(ans, dp[S - 1][i] + matrix[origin][icity]);
        }
        return ans;
    }

    public static int[][] generateGraph(int maxSize, int maxValue) {
        int len = (int) (Math.random() * maxSize) + 1;
        int[][] matrix = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                matrix[i][j] = (int) (Math.random() * maxValue) + 1;
            }
        }
        for (int i = 0; i < len; i++) {
            matrix[i][i] = 0;
        }
        return matrix;
    }

    public static void main(String[] args) {
        int len = 10;
        int value = 100;
        System.out.println("功能测试开始");
        for (int i = 0; i < 20000; i++) {
            int[][] matrix = generateGraph(len, value);
            int origin = (int) (Math.random() * matrix.length);
            int ans1 = t3(matrix);
            int ans2 = t4(matrix);
            int ans3 = tsp2(matrix, origin);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("fuck");
                break;
            }
        }
        System.out.println("功能测试结束");

        len = 22;
        System.out.println("性能测试开始，数据规模 : " + len);
        int[][] matrix = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                matrix[i][j] = (int) (Math.random() * value) + 1;
            }
        }
        for (int i = 0; i < len; i++) {
            matrix[i][i] = 0;
        }
        long start;
        long end;
        start = System.currentTimeMillis();
        t4(matrix);
        end = System.currentTimeMillis();
        System.out.println("运行时间 : " + (end - start) + " 毫秒");
        System.out.println("性能测试结束");

    }
}
