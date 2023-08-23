package dachang.class3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class Code07_FreedomTrail {

    public static int findRotateSteps1(String ring, String key) {
        char[] r = ring.toCharArray();
        int n = r.length;
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(r[i])) {
                map.put(r[i], new ArrayList<>());
            }
            map.get(r[i]).add(i);
        }
        char[] str = key.toCharArray();
        int m = str.length;
        int[][] dp = new int[n][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = -1;
            }
        }
        return process(0, 0, str, map, n, dp);
    }

    public static int process(int preButton, int index, char[] str, HashMap<Character, ArrayList<Integer>> map, int n, int[][] dp) {
        if (dp[preButton][index] != -1) {
            return dp[preButton][index];
        }
        int ans = Integer.MAX_VALUE;
        if (index == str.length) {
            ans = 0;
        } else {
            char cur = str[index];
            ArrayList<Integer> nextPositions = map.get(cur);
            for (Integer next : nextPositions) {
                int cost = dial(preButton, next, n) + 1 + process(next, index + 1, str, map, n, dp);
                ans = Math.min(ans, cost);
            }
        }
        dp[preButton][index] = ans;
        return ans;
    }

    public static int dial(int i1, int i2, int size) {
        return Math.min(Math.abs(i1 - i2), Math.min(i1, i2) + size - Math.max(i1, i2));
    }

    private int ringLength;
    private char[] key;
    private ArrayList<TreeSet<Integer>> ringSet;
    private final HashMap<Integer, Integer> dp = new HashMap<>();


    public int findRotateSteps2(String ring, String key) {
        char[] chars = ring.toCharArray();
        this.key = key.toCharArray();
        ringLength = chars.length;
        ringSet = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            ringSet.add(new TreeSet<>());
        }
        for (int i = 0; i < chars.length; i++) {
            ringSet.get(chars[i] - 'a').add(i);
        }
        return findRotateSteps(0,0);
    }

    private int findRotateSteps(int kIndex, int cur) {
        if (kIndex == key.length) {
            return 0;
        }

        int k = kIndex * 100 + cur - 1;
        Integer v = dp.get(k);
        if (v != null) {
            return v;
        } else {
            v = Integer.MAX_VALUE;
        }
        TreeSet<Integer> treeSet = ringSet.get(key[kIndex] - 'a');
        Integer floor = treeSet.floor(cur);
        if (floor == null) {
            floor = treeSet.last();
        }
        int len = Math.abs(cur - floor);
        len = Math.min(len, ringLength - len);
        v = Math.min(v, len + 1 + findRotateSteps(kIndex + 1, floor));
        Integer ceiling = treeSet.ceiling(cur);
        if (ceiling != null) {
            ceiling = treeSet.first();
        }
        len = Math.abs(cur - ceiling);
        len = Math.min(len, ringLength - len);
        v = Math.min(v, len + 1 + findRotateSteps(kIndex + 1, ceiling));
        dp.put(k, v);
        return v;
    }
}
