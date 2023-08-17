package dachang.class07;

import java.util.HashMap;

public class Code06_SplitStringMaxValue {

    public static int maxRecord1(String str, int k, String[] parts, int[] record) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        HashMap<String, Integer> records = new HashMap<>();
        for (int i = 0; i < parts.length; i++) {
            records.put(parts[i], record[i]);
        }
        return process1(str, 0, k, records);
    }


    /*
    process1 含义：字符串从index位置开始一直到结尾，分割成rest个部分，得到的最大得分是多少
     */
    public static int process1(String s, int index, int rest, HashMap<String, Integer> records) {
        if (rest < 0) {
            return -1;// 无效解
        }
        if (index == s.length()) {
            return rest == 0 ? 0 : -1;
        }
        // 从index开始，分别到index，index+1... n-1为一部分，剩下的为rest-1部分
        int n = s.length();
        int ans = -1;
        for (int end = index; end < n; end++) {
            String pre = s.substring(index, end + 1);
            int next = records.containsKey(pre) ? process1(s, end + 1, rest - 1, records) : -1;
            if (next != -1) {
                ans = Math.max(ans, records.get(pre) + next);
            }
        }
        return ans;
    }

    public static int maxRecord2(String str, int k, String[] parts, int[] record) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        HashMap<String, Integer> records = new HashMap<>();
        for (int i = 0; i < parts.length; i++) {
            records.put(parts[i], record[i]);
        }
        int n = str.length();
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= k; i++) {
            dp[n][i] = -1;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= k; rest++) {
                int ans = -1;
                for (int end = index; end < n; end++) {
                    String pre = str.substring(index, end + 1);
                    int next = rest>0&& records.containsKey(pre) ? dp[end + 1][rest - 1] : -1;
                    if (next != -1) {
                        ans = Math.max(ans, records.get(pre) + next);
                    }
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][k];
    }


    public static class TrieNode {
        public TrieNode[] next;
        public int value;

        public TrieNode() {
            next = new TrieNode[26];
            value = -1;
        }
    }

    /*
    构建前缀树
     */
    public static TrieNode rootNode(String[] parts, int[] record) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < parts.length; i++) {
            char[] chars = parts[i].toCharArray();
            TrieNode cur = root;
            for (char c : chars) {
                int index = c - 'a';
                if (cur.next[index] == null) {
                    cur.next[index] = new TrieNode();
                }
                cur = cur.next[index];
            }
            cur.value = record[i];
        }
        return root;
    }

    public static int maxRecord3(String s, int k, String[] parts, int[] record) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        TrieNode root = rootNode(parts, record);
        return process2(s, 0, k, root);
    }

    public static int process2(String s, int index, int rest, TrieNode root) {
        if (rest < 0) {
            return 0;
        }
        if (index == s.length()) {
            return rest == 0 ? 0 : -1;
        }
        int n = s.length();
        TrieNode cur = root;
        int ans = -1;
        for (int end = index; end < n; end++) {
            int path = s.charAt(end) - 'a';
            if (cur.next[path] == null) {
                break;
            }
            int next = cur.next[path].value != -1 ? process2(s, end + 1, rest - 1, root) : -1;
            if (next != -1) {
                ans = Math.max(ans, next + cur.next[path].value);
            }
            cur = cur.next[path];
        }
        return ans;
    }

    public static int maxRecord4(String s, int k, String[] parts, int[] record) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        TrieNode root = rootNode(parts, record);
        int n = s.length();
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= k; i++) {
            dp[n][i] = -1;
        }

        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= k; rest++) {
                TrieNode cur = root;
                int ans = -1;
                for (int end = index; end < n; end++) {
                    int path = s.charAt(end) - 'a';
                    if (cur.next[path] == null) {
                        break;
                    }
                    int next = cur.next[path].value != -1 && rest > 0 ? dp[end + 1][rest - 1] : -1;
                    if (next != -1) {
                        ans = Math.max(ans, next + cur.next[path].value);
                    }
                    cur = cur.next[path];
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][k];
    }

    public static void main(String[] args) {
        String str = "abcdefg";
        int K = 3;
        String[] parts = { "abc", "def", "g", "ab", "cd", "efg", "defg" };
        int[] record = { 1, 1, 1, 3, 3, 3, 2 };
        System.out.println(maxRecord1(str, K, parts, record));
        System.out.println(maxRecord2(str, K, parts, record));
        System.out.println(maxRecord3(str, K, parts, record));
        System.out.println(maxRecord4(str, K, parts, record));
    }
}
