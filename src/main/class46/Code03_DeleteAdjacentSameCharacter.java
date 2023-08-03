package class46;

public class Code03_DeleteAdjacentSameCharacter {

    public static int restMin1(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() < 2) {
            return s.length();
        }
        int minLen = s.length();
        for (int l = 0; l < s.length(); l++) {
            for (int r = l + 1; r < s.length(); r++) {
                if (canDelete(s.substring(l, r + 1))) {
                    minLen = Math.min(minLen, restMin1(s.substring(0, l) + s.substring(r + 1, s.length())));
                }
            }
        }
        return minLen;
    }

    public static boolean canDelete(String s) {
        char[] str = s.toCharArray();
        for (int i = 1; i < str.length; i++) {
            if (str[i - 1] != str[i]) {
                return false;
            }
        }
        return true;
    }

    public static int restMin2(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() < 2) {
            return s.length();
        }
        char[] str = s.toCharArray();
        return process(str, 0, str.length - 1, false);
    }

    public static int process(char[] str, int l, int r, boolean has) {
        if (l > r) {
            return 0;
        }
        if (l == r) {
            return has ? 0 : 1;
        }
        int index = l;
        int k = has ? 1 : 0;
        while (index <= r && str[index] == str[l]) {
            k++;
            index++;
        }
        int way1 = (k > 1 ? 0 : 1) + process(str, index, r, false);
        int way2 = Integer.MAX_VALUE;
        for (int split = index; split <= r; split++) {
            if (str[split] == str[l] && str[split] != str[split - 1]) {
                if (process(str, index, split - 1, false) == 0) {
                    way2 = Math.min(way2, process(str, split, r, k != 0));
                }
            }
        }
        return Math.min(way1, way2);
    }

    public static int restMin3(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() < 2) {
            return s.length();
        }
        char[] str = s.toCharArray();
        int n = str.length;
        int[][][] dp = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 2; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return dpProcess(str, 0, n - 1, false, dp);
    }

    public static int dpProcess(char[] str, int l, int r, boolean has, int[][][] dp) {
        if (l > r) {
            return 0;
        }
        int k = has ? 1 : 0;
        if (dp[l][r][k] != -1) {
            return dp[l][r][k];
        }
        int ans = 0;
        if (l == r) {
            ans = (k == 0 ? 1 : 0);
        } else {
            int index = l;
            int all = k;
            while (index <= r && str[index] == str[l]) {
                all++;
                index++;
            }
            int way1 = (all > 1 ? 0 : 1) + dpProcess(str, index, r, false, dp);
            int way2 = Integer.MAX_VALUE;
            for (int split = index; split <= r; split++) {
                if (str[split] == str[l] && str[split] != str[split - 1]) {
                    if (dpProcess(str, index, split - 1, false, dp) == 0) {
                        way2 = Math.min(way2, dpProcess(str, split, r, all > 0, dp));
                    }
                }
            }
            ans = Math.min(way1, way2);
        }
        dp[l][r][k] = ans;
        return ans;
    }

    public static String randomString(int len, int variety) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * variety) + 'a');
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int maxLen = 16;
        int variety = 3;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            String str = randomString(len, variety);
            int ans1 = restMin1(str);
            int ans2 = restMin2(str);
            int ans3 = restMin3(str);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println(str);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
