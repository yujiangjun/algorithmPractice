package class10;

import java.util.Stack;

/**
 * 非递归遍历二叉树
 */
public class Code3_UnRecursiveTraversalBT {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 前序遍历
     * @param head
     */
    public static void pre(Node head){
        System.out.print("pre-order: ");
        if (head==null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()){
            head=stack.pop();
            System.out.print(head.value+"  ");
            if (head.right!=null){
                stack.push(head.right);
            }
            if (head.left!=null){
                stack.push(head.left);
            }
        }
        System.out.println();
    }

    public static void in(Node head){
        System.out.print("in-order: ");
        if (head!=null){
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty()||head!=null){
                if (head!=null){
                    stack.push(head);
                    head=head.left;
                }else {
                    head=stack.pop();
                    System.out.print(head.value+" ");
                    head=head.right;
                }
            }
        }
        System.out.println();
    }

    public static void pos1(Node head){
        System.out.print("pos-order: ");
        if (head!=null){
            Stack<Node> s1=new Stack<>();
            Stack<Node> s2=new Stack<>();
            s1.push(head);
            while (!s1.isEmpty()){
                head=s1.pop();
                s2.push(head);
                if (head.left!=null){
                    s1.push(head.left);
                }
                if (head.right!=null){
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()){
                System.out.print(s2.pop().value+" ");
            }
        }
        System.out.println();
    }

    public static void  pos2(Node h){
        System.out.println("pos-order:");
        if (h!=null){
            Stack<Node> stack=new Stack<>();
            stack.push(h);
            Node c = null;
            while (!stack.isEmpty()){
                c=stack.peek();
                if (c.left!=null&&h!=c.left&&h!=c.right){
                    stack.push(c.left);
                }else if (c.right!=null&&h!=c.right){
                    stack.push(c.right);
                }else {
                    System.out.print(stack.pop().value+" ");
                    h=c;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos1(head);
        System.out.println("========");
        pos2(head);
        System.out.println("========");
    }
}
