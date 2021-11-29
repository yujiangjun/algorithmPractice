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
                right=right.next;
            }
            while (!stack.isEmpty()){
                if (head.value!=stack.pop().value){
                    return false;
                }
                head=head.next;
            }
            return true;
        }

        public static boolean isPalindrome3(Node head){
            if (head==null||head.next==null){
                return true;
            }
            Node n1=head;
            Node n2=head;
            while (n2.next!=null&&n2.next.next!=null){
                n1=n1.next;
                n2=n2.next.next;
            }
            n2=n1.next;
            n1.next=null;
            Node n3=null;
            while (n2!=null){
                n3=n2.next;
                n2.next=n1;
                n1=n2;
                n2=n3;
            }
            n3=n1;
            n2=head;
            boolean res=true;
            while (n1!=null&&n2!=null){
                if (n1.value!=n2.value){
                    res=false;
                    break;
                }
                n1=n1.next;
                n2=n2.next;
            }
            n1=n3.next;
            n3.next=null;
            while (n1!=null){
                n2=n1.next;
                n1.next=n3;
                n3=n1;
                n1=n2;
            }
            return res;
        }

        public static void printLinkList(Node node){
            System.out.print("Linked List:");
            while (node!=null){
                System.out.print(node.value+"  ");
                node=node.next;
            }
            System.out.println();
        }

        public static void main(String[] args) {
            Node head = null;
            printLinkList(head);
            System.out.print(isPalindrome1(head) + " | ");
            System.out.print(isPalindrome2(head) + " | ");
            System.out.println(isPalindrome3(head) + " | ");
            printLinkList(head);
            System.out.println("=========================");

            head = new Node(1);
            printLinkList(head);
            System.out.print(isPalindrome1(head) + " | ");
            System.out.print(isPalindrome2(head) + " | ");
            System.out.println(isPalindrome3(head) + " | ");
            printLinkList(head);
            System.out.println("=========================");

            head = new Node(1);
            head.next = new Node(2);
            printLinkList(head);
            System.out.print(isPalindrome1(head) + " | ");
            System.out.print(isPalindrome2(head) + " | ");
            System.out.println(isPalindrome3(head) + " | ");
            printLinkList(head);
            System.out.println("=========================");

            head = new Node(1);
            head.next = new Node(1);
            printLinkList(head);
            System.out.print(isPalindrome1(head) + " | ");
            System.out.print(isPalindrome2(head) + " | ");
            System.out.println(isPalindrome3(head) + " | ");
            printLinkList(head);
            System.out.println("=========================");

            head = new Node(1);
            head.next = new Node(2);
            head.next.next = new Node(3);
            printLinkList(head);
            System.out.print(isPalindrome1(head) + " | ");
            System.out.print(isPalindrome2(head) + " | ");
            System.out.println(isPalindrome3(head) + " | ");
            printLinkList(head);
            System.out.println("=========================");

            head = new Node(1);
            head.next = new Node(2);
            head.next.next = new Node(1);
            printLinkList(head);
            System.out.print(isPalindrome1(head) + " | ");
            System.out.print(isPalindrome2(head) + " | ");
            System.out.println(isPalindrome3(head) + " | ");
            printLinkList(head);
            System.out.println("=========================");

            head = new Node(1);
            head.next = new Node(2);
            head.next.next = new Node(3);
            head.next.next.next = new Node(1);
            printLinkList(head);
            System.out.print(isPalindrome1(head) + " | ");
            System.out.print(isPalindrome2(head) + " | ");
            System.out.println(isPalindrome3(head) + " | ");
            printLinkList(head);
            System.out.println("=========================");

            head = new Node(1);
            head.next = new Node(2);
            head.next.next = new Node(2);
            head.next.next.next = new Node(1);
            printLinkList(head);
            System.out.print(isPalindrome1(head) + " | ");
            System.out.print(isPalindrome2(head) + " | ");
            System.out.println(isPalindrome3(head) + " | ");
            printLinkList(head);
            System.out.println("=========================");

            head = new Node(1);
            head.next = new Node(2);
            head.next.next = new Node(3);
            head.next.next.next = new Node(2);
            head.next.next.next.next = new Node(1);
            printLinkList(head);
            System.out.print(isPalindrome1(head) + " | ");
            System.out.print(isPalindrome2(head) + " | ");
            System.out.println(isPalindrome3(head) + " | ");
            printLinkList(head);
            System.out.println("=========================");
        }
    }
}
