package dachang.class08;

import java.util.LinkedList;

public class Code01_ExpressionCompute {

    public static int calculate(String str) {
        return f(str.toCharArray(), 0)[0];
    }

    /**
     * 从i位置开始到右括号或者str最后一个位置结果
     *
     * @param str
     * @param i
     * @return bra[0] 从i位置到右括号或者最后的结果 bra[1]表示计算到的位置p
     */
    public static int[] f(char[] str, int i) {
        LinkedList<String> queue = new LinkedList<>();
        int cur = 0;
        int[] bra = null;
        while (i < str.length && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';;
            } else if (str[i] != '(') { // +-*/
                addNum(queue, cur, str[i++]);
                cur = 0;
            } else { // (
                bra = f(str, i + 1);
                cur = bra[0];
                i = bra[1] + 1;
            }
        }
        addNum(queue, cur, '+');
        return new int[]{getAns(queue), i};
    }

    public static void addNum(LinkedList<String> queue, int cur, char op) {
        if (!queue.isEmpty() && ("*".equals(queue.peekLast()) || "/".equals(queue.peekLast()))) {
            String topOp = queue.pollLast();
            String topNum = queue.pollLast();
            cur = "*".equals(topOp) ? Integer.parseInt(topNum) * cur : Integer.parseInt(topNum) / cur;
        }
        queue.addLast(String.valueOf(cur));
        queue.addLast(String.valueOf(op));
    }

    public static int getAns(LinkedList<String> queue) {
        int ans = Integer.valueOf(queue.pollFirst());
        while (queue.size() > 1) {
            String op = queue.pollFirst();
            int num = Integer.valueOf(queue.pollFirst());
            ans += op.equals("+") ? num : -num;
        }
        return ans;
    }

    public static void main(String[] args) {
        int calculate = calculate("30");
        System.out.println(calculate);
    }
}
