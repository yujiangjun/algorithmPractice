package dachang.class31;

import java.util.Arrays;
import java.util.List;

public class Problem_0139_WordBreak {

    public static class TrieNode {
        public TrieNode[] next;
        public boolean isEnd;

        public TrieNode() {
            isEnd = false;
            next = new TrieNode[26];
        }
    }

    public static boolean wordBreak1(String s, List<String> wordDict) {
        return process(s, wordDict, 0);
    }

    public static boolean wordBreak2(String s, List<String> wordDict) {

        if (wordDict == null || wordDict.isEmpty()) {
            return false;
        }
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;
        for (int i = n - 1; i >= 0; i--) {
            boolean ans = false;
            for (int j = i; j < s.length(); j++) {
                if (wordDict.contains(s.substring(i, j + 1))) {
                    if (dp[j + 1]) {
                        ans = dp[j + 1];
                        break;
                    }
                }
            }
            dp[i] = ans;
        }
        return dp[0];
    }

    public static boolean wordBreak3(String s, List<String> wordDict) {

        TrieNode root = new TrieNode();
        for (String dict : wordDict) {
            char[] str = dict.toCharArray();
            TrieNode cur = root;
            for (char c : str) {
                if (cur.next[c-'a']==null){
                    cur.next[c-'a']=new TrieNode();
                }
                cur = cur.next[c - 'a'];
            }
            cur.isEnd = true;
        }
        return process(s, root, 0);
    }

    public static boolean process(String str, TrieNode root, int i) {

        if (i == str.length()) {
            return true;
        }

        boolean ans = false;
        TrieNode cur = root;
        for (int j = i; j < str.length(); j++) {

            int index = str.charAt(j) - 'a';
            if (cur.next[index] == null) {
                break;
            }
            cur = cur.next[index];
            if (!cur.isEnd) {
                continue;
            }
            ans = process(str, root, j + 1);
            if (ans) {
                break;
            }
        }
        return ans;
    }


    public static boolean wordBreak4(String s, List<String> wordDict) {
        if (wordDict == null || wordDict.isEmpty()) {
            return false;
        }
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;

        TrieNode root = new TrieNode();
        for (String dict : wordDict) {
            char[] str = dict.toCharArray();
            TrieNode cur = root;
            for (char c : str) {
                if (cur.next[c-'a']==null){
                    cur.next[c-'a']=new TrieNode();
                }
                cur = cur.next[c - 'a'];
            }
            cur.isEnd = true;
        }
        for (int i = n - 1; i >= 0; i--) {
            TrieNode cur = root;
            boolean ans=false;
            for (int j = i; j < s.length(); j++) {
                int index = s.charAt(j) - 'a';
                if (cur.next[index] == null) {
                    break;
                }
                cur = cur.next[index];
                if (!cur.isEnd) {
                    continue;
                }
                ans = dp[ j + 1];
                if (ans) {
                    break;
                }
            }
            dp[i]=ans;
        }
        return dp[0];
    }
    public static boolean process(String str, List<String> wordDict, int i) {
        if (i == str.length()) {
            return true;
        }
        for (int index = i; index < str.length(); index++) {
            if (wordDict.contains(str.substring(i, index + 1))) {
                if (process(str, wordDict, index + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        String s = "leetcode";
//        String[] dict = {"leet", "code"};
//        System.out.println(wordBreak1(s, Arrays.asList(dict)));
//        System.out.println(wordBreak2(s, Arrays.asList(dict)));
//        System.out.println(wordBreak3(s, Arrays.asList(dict)));
//        System.out.println(wordBreak4(s, Arrays.asList(dict)));
//        System.out.println("====================");
//
//        String s1 = "applepenapple";
//        String[] dict1 = {"apple", "pen"};
//        System.out.println(wordBreak1(s1, Arrays.asList(dict1)));
//        System.out.println(wordBreak2(s1, Arrays.asList(dict1)));
//        System.out.println(wordBreak3(s1, Arrays.asList(dict1)));
//        System.out.println(wordBreak4(s1, Arrays.asList(dict1)));
//        System.out.println("====================");
//
//        String s2 = "catsandog";
//        String[] dict2 = {"cats", "dog", "sand", "and", "cat"};
//        System.out.println(wordBreak1(s2, Arrays.asList(dict2)));
//        System.out.println(wordBreak2(s2, Arrays.asList(dict2)));
//        System.out.println(wordBreak3(s2, Arrays.asList(dict2)));
//        System.out.println(wordBreak4(s2, Arrays.asList(dict2)));
//        System.out.println("====================");

        String s2 = "bb";
        String[] dict2 = {"a","b","bbb","bbbb"};
        System.out.println(wordBreak1(s2, Arrays.asList(dict2)));
        System.out.println(wordBreak2(s2, Arrays.asList(dict2)));
        System.out.println(wordBreak3(s2, Arrays.asList(dict2)));
        System.out.println(wordBreak4(s2, Arrays.asList(dict2)));
        System.out.println("====================");
    }
}
