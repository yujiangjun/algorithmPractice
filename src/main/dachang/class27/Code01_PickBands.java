package dachang.class27;

import java.util.Arrays;

public class Code01_PickBands {

    public static int minCost1(int[][] programs, int nums) {
        if (nums == 0 || programs == null || programs.length == 0) {
            return 0;
        }
        int size = clean(programs);
        int[] map1 = initMap(1 << (nums << 1));
        int[] map2 = null;
        if ((nums & 1) == 0) {
            process(programs, size, 0, nums >> 1, 0, 0, map1);
            map2 = map1;
        } else {
            process(programs, size, 0, nums >> 1, 0, 0, map1);
            map2 = initMap(1 << (nums << 1));
            process(programs, size, 0, nums - (nums >> 1), 0, 0, map2);
        }
        int mask = (1 << (nums << 1)) - 1;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < map1.length; i++) {
            if (map1[i] != Integer.MAX_VALUE && map2[mask & (~i)] != Integer.MAX_VALUE) {
                ans = Math.min(ans, map1[i] + map2[mask & (~i)]);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    public static int clean(int[][] programs) {
        for (int[] p : programs) {
            int min = Math.max(p[0], p[1]);
            int max = Math.min(p[0], p[1]);
            p[0] = min;
            p[1] = max;
        }

        Arrays.sort(programs, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else if (a[1] != b[1]) {
                return a[1] - b[1];
            } else {
                return a[2] - b[2];
            }
        });
        int min = programs[0][0];
        int max = programs[0][1];

        for (int i = 1; i < programs.length; i++) {
            if (programs[i][0] == min && programs[i][1] == max) {
                programs[i] = null;
            } else {
                min = programs[i][0];
                max = programs[i][1];
            }
        }
        int size = 1;
        for (int i = 1; i < programs.length; i++) {
            if (programs[i] != null) {
                programs[size++] = programs[i];
            }
        }
        return size;
    }


    public static int[] initMap(int size) {
        int[] map = new int[size];
        for (int i = 0; i < size; i++) {
            map[i] = Integer.MAX_VALUE;
        }
        return map;
    }


    public static void process(int[][] programs, int size, int index, int rest, int pick, int cost, int[] map) {
        if (rest == 0) {
            map[pick] = Math.min(map[pick], cost);
        } else {
            if (index != size) {
                // 当前不选择
                process(programs, size, index + 1, rest, pick, cost, map);
                // 选择当前
                int x = programs[index][0];
                int y = programs[index][1];
                int cur = 0 | (1 << x) | (1 << y);
                if ((pick & cur) == 0) {
                    process(programs, size, index + 1, rest - 1, pick | cur, cost + programs[index][2], map);
                }
            }
        }
    }

    public static int right(int[][] programs, int nums) {
        min = Integer.MAX_VALUE;
        r(programs, 0, nums, 0, 0);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static int min = Integer.MAX_VALUE;

    public static void r(int[][] programs, int index, int rest, int pick, int cost) {
        if (rest == 0) {
            min = Math.min(min, cost);
        } else {
            if (index < programs.length) {
                r(programs, index + 1, rest, pick, cost);
                int cur = (1 << programs[index][0]) | (1 << programs[index][1]);
                if ((pick & cur) == 0) {
                    r(programs, index + 1, rest - 1, pick | cur, cost + programs[index][2]);
                }
            }
        }
    }

    // 为了测试
    public static int[][] randomPrograms(int N, int V) {
        int nums = N << 1;
        int n = nums * (nums - 1);
        int[][] programs = new int[n][3];
        for (int i = 0; i < n; i++) {
            int a = (int) (Math.random() * nums);
            int b = 0;
            do {
                b = (int) (Math.random() * nums);
            } while (b == a);
            programs[i][0] = a;
            programs[i][1] = b;
            programs[i][2] = (int) (Math.random() * V) + 1;
        }
        return programs;
    }

    // 为了测试
    public static int[][] copyPrograms(int[][] programs) {
        int n = programs.length;
        int[][] ans = new int[n][3];
        for (int i = 0; i < n; i++) {
            ans[i][0] = programs[i][0];
            ans[i][1] = programs[i][1];
            ans[i][2] = programs[i][2];
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 4;
        int V = 100;
        int T = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < T; i++) {
            int nums = (int) (Math.random() * N) + 1;
            int[][] programs1 = randomPrograms(nums, V);
            int[][] programs2 = copyPrograms(programs1);
            int[][] programs3 = copyPrograms(programs1);
            int ans1 = right(programs1, nums);
            int ans2 = minCost1(programs2, nums);
//            int ans3 = minCost2(programs3, nums);
//            if (ans1 != ans2 || ans1 != ans3) {
            if (ans1 != ans2) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");

        long start;
        long end;
        int[][] programs;

        programs = randomPrograms(7, V);
        start = System.currentTimeMillis();
        right(programs, 7);
        end = System.currentTimeMillis();
        System.out.println("right方法，在nums=7时候的运行时间(毫秒) : " + (end - start));

        programs = randomPrograms(10, V);
        start = System.currentTimeMillis();
        minCost1(programs, 10);
        end = System.currentTimeMillis();
        System.out.println("minCost方法，在nums=10时候的运行时间(毫秒) : " + (end - start));

//        programs = randomPrograms(10, V);
//        start = System.currentTimeMillis();
//        minCost2(programs, 10);
//        end = System.currentTimeMillis();
//        System.out.println("minCost2方法，在nums=10时候的运行时间(毫秒) : " + (end - start));
    }
}
