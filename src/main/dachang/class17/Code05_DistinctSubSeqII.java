package dachang.class17;

import java.util.HashMap;
import java.util.Map;

public class Code05_DistinctSubSeqII {

    public static int distinctSubseqII(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int all = 1;
        int m = 1000000007;
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            int newAdd = all;
            int cur = all;
            cur = (cur + newAdd) % m;
            cur = (cur - countMap.getOrDefault(c, 0) + m) % m;
            all = cur;
            countMap.put(c, newAdd);
        }
        return (all - 1 + m) % m;
    }

    public static int distinctSubseqII2(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int all = 1;
        int m = 1000000007;
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            int newAdd = all;
            int cur = (all + newAdd) % m;
            cur = (cur - count[c - 'a'] + m) % m;
            all = cur;
            count[c - 'a'] = newAdd;
        }
        return (all - 1 + m) % m;
    }
}
