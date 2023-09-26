package dachang.class12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Code03_LongestConsecutive {
    public static int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            if (map.containsKey(num)) {
                continue;
            }
            map.put(num, 1);
            int preLen = map.getOrDefault(num - 1, 0);
            int postLen = map.getOrDefault(num + 1, 0);
            int all = preLen + postLen + 1;
            map.put(num - preLen, all);
            map.put(num + postLen, all);
            ans = Math.max(ans, all);
        }
        return ans;
    }

    public static int longestConsecutive2(int[] nums) {
        HashMap<Integer, Integer> headMap = new HashMap<>();
        HashMap<Integer, Integer> tailMap = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();
        for (int num : nums) {
            if (visited.contains(num)) {
                continue;
            }
            headMap.put(num, 1);
            tailMap.put(num, 1);
            visited.add(num);
            if (tailMap.containsKey(num - 1)) {
                int preLen = tailMap.get(num - 1);
                int preHead = num - preLen;
                headMap.put(preHead, preLen + 1);
                tailMap.put(num, preLen + 1);
                headMap.remove(num);
                tailMap.remove(num - 1);
            }
            if (headMap.containsKey(num + 1)) { // num与num+1合并
                int preLen = tailMap.get(num);
                int preHead = num - preLen + 1;
                int postLen = headMap.get(num + 1);
                int postHead = postLen + num;
                headMap.put(preHead, preLen + postLen);
                headMap.remove(num + 1);
                tailMap.put(postHead, preLen + postLen);
                tailMap.remove(num);
            }
        }
        int ans = 0;
        for (Integer value : headMap.values()) {
            ans = Math.max(ans, value);
        }
        return ans;
    }
}
