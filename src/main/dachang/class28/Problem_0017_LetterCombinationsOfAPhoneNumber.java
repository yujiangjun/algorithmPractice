package dachang.class28;

import java.util.*;

public class Problem_0017_LetterCombinationsOfAPhoneNumber {


    public static final Map<Integer, char[]> numCharMap = new HashMap<>();

    static {
        numCharMap.put(2, new char[]{'a', 'b', 'c'});
        numCharMap.put(3, new char[]{'d', 'e', 'f'});
        numCharMap.put(4, new char[]{'g', 'h', 'i'});
        numCharMap.put(5, new char[]{'j', 'k', 'l'});
        numCharMap.put(6, new char[]{'m', 'n', 'o'});
        numCharMap.put(7, new char[]{'p', 'q', 'r', 's'});
        numCharMap.put(8, new char[]{'t', 'u', 'v'});
        numCharMap.put(9, new char[]{'w', 'x', 'y', 'z'});
    }

    public static List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return ans;
        }
        int n = digits.length();
        char[] str = digits.toCharArray();
        char[] path = new char[n];
        process(str, 0, path, ans);
        return ans;
    }

    public static void process(char[] str, int index, char[] path, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(path));
            return;
        }
        int num = str[index] - '0';
        char[] chars = numCharMap.get(num);
        for (int i = 0; i < chars.length; i++) {
            path[index] = chars[i];
            process(str, index + 1, path, ans);
        }
    }

    public static void main(String[] args) {
        String d1="2";
        System.out.println(Arrays.toString(letterCombinations(d1).toArray()));

        String d2="23";
        System.out.println(Arrays.toString(letterCombinations(d2).toArray()));
    }
}
