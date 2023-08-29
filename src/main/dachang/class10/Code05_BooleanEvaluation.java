package dachang.class10;

public class Code05_BooleanEvaluation {

    public static class Info {
        public int falseNum;
        public int trueNum;

        public Info(int falseNum, int trueNum) {
            this.falseNum = falseNum;
            this.trueNum = trueNum;
        }
    }

    // 在l到r范围内返回表达式结果是0和1的方法数
    public static Info process(char[] str, int l, int r, Info[][] dp) {
        if (dp[l][r] != null) {
            return dp[l][r];
        }
        int falseNum = 0;
        int trueNum = 0;
        if (l == r) {
            falseNum = str[l] == '0' ? 1 : 0;
            trueNum = str[l] == '1' ? 1 : 0;
            return new Info(falseNum, trueNum);
        }

        for (int split = l + 1; split < r; split += 2) {
            Info left = process(str, l, split - 1, dp);
            Info rightInfo = process(str, split + 1, r, dp);
            int leftFalse = left.falseNum;
            int leftTrue = left.trueNum;
            int rightFalse = rightInfo.falseNum;
            int rightTrue = rightInfo.trueNum;
            if (str[split] == '&') {
                // 左0右0+左1右0+左0右1
                falseNum += leftFalse * rightFalse + leftTrue * rightFalse + leftFalse * rightTrue;
                // 左1右1
                trueNum += leftTrue * rightTrue;
            } else if (str[split] == '|') {
                // 左0右0
                falseNum += leftFalse * rightFalse;
                //左1右1+左0右1+左1右0
                trueNum += leftTrue * rightTrue + leftTrue * rightFalse + leftFalse * rightTrue;
            } else {
                //左0右0+左1右1
                falseNum += leftFalse * rightFalse + leftTrue * rightTrue;
                //左1右0+左0右1
                trueNum += leftTrue * rightFalse + leftFalse * rightTrue;
            }
        }
        dp[l][r] = new Info(falseNum, trueNum);
        return dp[l][r];
    }

    public static int countEval(String s, int result) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        Info[][] dp = new Info[n][n];
        Info process = process(str, 0, n - 1, dp);
        return result == 1 ? process.trueNum : process.falseNum;
    }
}
