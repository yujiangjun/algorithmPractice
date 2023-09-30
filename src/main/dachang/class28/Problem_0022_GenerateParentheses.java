package dachang.class28;

import java.util.ArrayList;
import java.util.List;

public class Problem_0022_GenerateParentheses {
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        char[] path = new char[n << 1];
        process(path, 0, 0, n, ans);
        return ans;
    }

    public static void process(char[] path, int index, int leftMinusRight, int leftRest, List<String> ans) {
        if (index == path.length) {
            ans.add(String.valueOf(path));
            return;
        }
        if (leftRest > 0) {
            path[index] = '(';
            process(path, index + 1, leftMinusRight + 1, leftRest - 1, ans);
        }
        if (leftMinusRight > 0) {
            path[index] = ')';
            process(path, index + 1, leftMinusRight - 1, leftRest, ans);
        }
    }

}
