package dachang.class31;

import java.util.Stack;

public class Problem_0150_EvaluateReversePolishNotation {

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("*") || token.equals("/") || token.equals("+") || token.equals("-")) {
                compute(stack, token);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.peek();
    }

    public static void compute(Stack<Integer> stack, String op) {
        int n1 = stack.pop();
        int n2 = stack.pop();
        int sum = 0;
        if (op.equals("*")) {
            sum = n1 * n2;
        } else if (op.equals("/")) {
            sum = n2 / n1;
        } else if (op.equals("+")) {
            sum = n1 + n2;
        } else {
            sum = n1 - n2;
        }
        stack.push(sum);
    }
}
