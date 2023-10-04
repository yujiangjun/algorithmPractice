package dachang.class31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_0140_WordBreakII {

    public static class TrieNode {
        public StringBuilder path;
        public TrieNode[] next;
        public boolean isEnd;

        public TrieNode() {
            next = new TrieNode[26];
            path = new StringBuilder();
        }
    }


    public static TrieNode getTrie(List<String> wordDict) {
        TrieNode root = new TrieNode();
        for (String dict : wordDict) {
            TrieNode cur = root;
            for (char c : dict.toCharArray()) {
                int i = c - 'a';
                if (cur.next[i] == null) {
                    cur.next[i] = new TrieNode();
                }
                cur = cur.next[i];

            }
            cur.path=new StringBuilder(dict);
            cur.isEnd = true;
        }
        return root;
    }

    public static boolean[] getDp(String s, TrieNode root) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;
        for (int i = n - 1; i >= 0; i--) {
            TrieNode cur = root;

            for (int j = i; j < n; j++) {
                int index = s.charAt(j) - 'a';
                cur = cur.next[index];
                if (cur == null) {
                    break;
                }
                if (cur.isEnd&&dp[j + 1]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp;
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        TrieNode root = getTrie(wordDict);
        boolean[] dp = getDp(s, root);
        List<String> ans = new ArrayList<>();
        ArrayList<String> path = new ArrayList<>();
        process(s, 0, root, dp, path, ans);
        return ans;
    }


    public static void process(String s, int i, TrieNode root, boolean[] dp, ArrayList<String> path, List<String> ans) {
        if (i == s.length()) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < path.size() - 1; j++) {
                sb.append(path.get(j)).append(" ");
            }
            sb.append(path.get(path.size() - 1));
            ans.add(sb.toString());
        } else {
            TrieNode cur = root;
            for (int j = i; j < s.length(); j++) {
                int index = s.charAt(j) - 'a';
                cur = cur.next[index];
                if (cur == null) {
                    break;
                }
                if (cur.isEnd && dp[j + 1]) {
                    path.add(cur.path.toString());
                    process(s, j + 1, root, dp, path, ans);
                    path.remove(path.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        String s="catsanddog";
        String[] dict={"cat","cats","and","sand","dog"};
        System.out.println(Arrays.toString(wordBreak(s, Arrays.asList(dict)).toArray()));
    }
}
