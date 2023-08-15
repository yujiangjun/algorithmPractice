package dachang.class3;

public class Code01_LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        map[str[0]] = 0;
        int n = str.length;
        int ans = 1;
        int pre = 1;
        for (int i = 1; i < n; i++) {
            pre = Math.min(i - map[str[i]], pre + 1);
            ans = Math.max(ans, pre);
            map[str[i]] = i;
        }
        return ans;
    }
}
