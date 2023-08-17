package dachang.class07;

import java.util.Arrays;
import java.util.HashSet;

public class Code05_WorldBreak {

    public static int ways1(String str, String[] arr) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        HashSet<String> set = new HashSet<>();
        for (String s : arr) {
            set.add(s);
        }
        return process(str, 0, set);
    }

    public static int process(String str, int index, HashSet<String> set) {
        if (index == str.length()) {
            return 1;
        }
        int n = str.length();
        int ways = 0;
        for (int end = index; end < n; end++) {
            String pre = str.substring(index, end + 1);
            if (set.contains(pre)) {
                ways += process(str, end + 1, set);
            }
        }
        return ways;
    }

    public static int ways2(String str, String[] arr) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        HashSet<String> set = new HashSet<>();
        for (String s : arr) {
            set.add(s);
        }

        int n = str.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int end = i; end < n; end++) {
                String pre = str.substring(i, end + 1);
                if (set.contains(pre)) {
                    dp[i] += dp[end + 1];
                }
            }
        }
        return dp[0];
    }

    public static class Node {
        private Node[] next;
        private boolean end;

        public Node() {
            next = new Node[26];
        }
    }

    public static int ways3(String str, String[] arr) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        Node root = new Node();
        for (String s : arr) {
            char[] chars = s.toCharArray();
            Node cur = root;
            for (char c : chars) {
                if (cur.next[c - 'a'] == null) {
                    Node next = new Node();
                    cur.next[c - 'a'] = next;
                }
                cur = cur.next[c - 'a'];
            }
            cur.end = true;
        }
        return process2(str, root, 0);
    }

    public static int process2(String str, Node root, int index) {
        if (index == str.length()) {
            return 1;
        }
        int n = str.length();
        int ways = 0;
        Node cur = root;
        for (int end = index; end < n; end++) {
            if (cur.next[str.charAt(end) - 'a'] == null) {
                break;
            }
            cur = cur.next[str.charAt(end) - 'a'];
            if (cur.end) {
                ways += process2(str, root, end + 1);
            }
        }
        return ways;
    }

    public static int ways4(String str, String[] arr) {
        if (str == null || str.isEmpty() || arr==null || arr.length==0) {
            return 0;
        }
        Node root = new Node();

        for (String s : arr) {
            Node cur = root;
            char[] chars = s.toCharArray();
            for (char c : chars) {
                if (cur.next[c - 'a'] == null) {
                    cur.next[c - 'a'] = new Node();
                }
                cur = cur.next[c - 'a'];
            }
            cur.end = true;
        }
        int n = str.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;

        for (int i = n - 1; i >= 0; i--) {
            Node cur = root;
            int ways = 0;
            for (int end = i; end < n; end++) {
                if (cur.next[str.charAt(end) - 'a'] == null) {
                    break;
                }
                cur = cur.next[str.charAt(end) - 'a'];
                if (cur.end) {
                    ways += dp[end + 1];
                }
            }
            dp[i] = ways;
        }
        return dp[0];
    }

    // 随机样本产生器
    public static RandomSample generateRandomSample(char[] candidates, int num, int len, int joint) {
        String[] seeds = randomSeeds(candidates, num, len);
        HashSet<String> set = new HashSet<>();
        for (String str : seeds) {
            set.add(str);
        }
        String[] arr = new String[set.size()];
        int index = 0;
        for (String str : set) {
            arr[index++] = str;
        }
        StringBuilder all = new StringBuilder();
        for (int i = 0; i < joint; i++) {
            all.append(arr[(int) (Math.random() * arr.length)]);
        }
        return new RandomSample(all.toString(), arr);
    }

    public static String[] randomSeeds(char[] candidates, int num, int len) {
        String[] arr = new String[(int) (Math.random() * num) + 1];
        for (int i = 0; i < arr.length; i++) {
            char[] str = new char[(int) (Math.random() * len) + 1];
            for (int j = 0; j < str.length; j++) {
                str[j] = candidates[(int) (Math.random() * candidates.length)];
            }
            arr[i] = String.valueOf(str);
        }
        return arr;
    }

    // 以下的逻辑都是为了测试
    public static class RandomSample {
        public String str;
        public String[] arr;

        public RandomSample(String s, String[] a) {
            str = s;
            arr = a;
        }
    }
    public static void main(String[] args) {
        char[] candidates = { 'a', 'b' };
        int num = 20;
        int len = 4;
        int joint = 5;
        int testTimes = 30000;
        boolean testResult = true;
        for (int i = 0; i < testTimes; i++) {
            RandomSample sample = generateRandomSample(candidates, num, len, joint);
            int ans1 = ways1(sample.str, sample.arr);
            int ans2 = ways2(sample.str, sample.arr);
            int ans3 = ways3(sample.str, sample.arr);
            int ans4 = ways4(sample.str, sample.arr);
            if (ans1 != ans2 || ans3 != ans4 || ans2 != ans4) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println(ans4);
                testResult = false;
                break;
            }
        }
        System.out.println(testTimes + "次随机测试是否通过：" + testResult);
    }
}
