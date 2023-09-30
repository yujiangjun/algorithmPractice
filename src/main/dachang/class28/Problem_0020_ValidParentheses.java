package dachang.class28;

import java.util.Stack;

public class Problem_0020_ValidParentheses {
    public static boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.pop() != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static boolean isValid2(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        int n = s.length();
        char[] stack = new char[n];
        int size = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack[size++] = ')';
            } else if (c == '[') {
                stack[size++] = ']';
            } else if (c == '{') {
                stack[size++] = '}';
            } else {
                if (size==0){
                    return false;
                }
                char pop = stack[--size];
                if (pop != c) {
                    return false;
                }
            }
        }
        return size == 0;
    }

    public static void main(String[] args) {
        String s = "()";
        System.out.println(isValid(s));
        System.out.println(isValid2(s));
    }
}
