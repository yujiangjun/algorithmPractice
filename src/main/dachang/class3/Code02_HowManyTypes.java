package dachang.class3;

import java.util.HashSet;

public class Code02_HowManyTypes {

    public static int types1(String[] arr) {
        HashSet<String> set = new HashSet<>();

        for (String s : arr) {
            char[] str = s.toCharArray();
            boolean[] map = new boolean[256];
            for (char c : str) {
                map[c - 'a'] = true;
            }
            String key = "";
            for (int i = 0; i < map.length; i++) {
                if (map[i]) {
                    key += i + 'a';
                }
            }
            set.add(key);
        }
        return set.size();
    }

    public static int types2(String[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (String s : arr) {
            char[] str = s.toCharArray();
            int key = 0;
            for (int i = 0; i < str.length; i++) {
                key |= (1 << (str[i] - 'a'));
            }
            set.add(key);
        }
        return set.size();
    }

    // for test
    public static String[] getRandomStringArray(int possibilities, int strMaxSize, int arrMaxSize) {
        String[] ans = new String[(int) (Math.random() * arrMaxSize) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = getRandomString(possibilities, strMaxSize);
        }
        return ans;
    }

    // for test
    public static String getRandomString(int possibilities, int strMaxSize) {
        char[] ans = new char[(int) (Math.random() * strMaxSize) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strMaxSize = 10;
        int arrMaxSize = 100;
        int testTimes = 500000;
        System.out.println("test begin, test time : " + testTimes);
        for (int i = 0; i < testTimes; i++) {
            String[] arr = getRandomStringArray(possibilities, strMaxSize, arrMaxSize);
            int ans1 = types1(arr);
            int ans2 = types2(arr);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");

    }
}
