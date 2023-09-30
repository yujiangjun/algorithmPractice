package dachang.class28;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_0038_CountAndSay {

    public static List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] couter = new int[26];
            char[] charArray = str.toCharArray();
            for (char c : charArray) {
                couter[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i : couter) {
                sb.append(i).append('_');
            }
            if (!map.containsKey(sb.toString())) {
                map.put(sb.toString(), new ArrayList<>());
            }
            List<String> list = map.get(sb.toString());
            list.add(str);
        }
        List<List<String>> ans = new ArrayList<>();
        ans.addAll(map.values());
        return ans;
    }
}
