package class9;

import java.util.Stack;

public class Code2_isPalindromeList {

    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }

        public static boolean isPalindrome1(Node head){
            Stack<Node> stack=new Stack<>();
            Node cur=head;
            while (cur!=null){
                stack.push(cur);
                cur=cur.next;
            }
            while (head!=null){
                if (head.value!=stack.pop().value){
                    return false;
                }
                head=head.next;
            }
            return true;
        }

        public static boolean isPalindrome2(Node head){
            if (head==null || head.next==null){
                return true;
            }
            Node right=head.next;
            Node cur=head;
            while (cur.next!=null&&cur.next.next!=null){
                right=right.next;
                cur=cur.next.next;
            }
            Stack<Node> stack = new Stack<>();
            while (right!=null){
                stack.push(right);
                right=right;
            }
            while (!stack.isEmpty()){
                if (head.value!=stack.pop().value){
                    return false;
                }
                head=head.next;
            }
            return true;
        }
    }
}
