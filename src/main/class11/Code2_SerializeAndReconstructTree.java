package class11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 序列化树和反序列化树
 * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
 * 以下代码全部实现了。
 * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
 * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
 * 比如如下两棵树
 *         __2
 *        /
 *       1
 *       和
 *       1__
 *          \
 *           2
 * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
 */
public class Code2_SerializeAndReconstructTree {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Queue<String> perSerial(Node head){
        Queue<String> ans = new LinkedList<>();
        pres(head,ans);
        return ans;
    }

    //前序
    public static void pres(Node head,Queue<String> ans){
        if (head==null){
            ans.add(null);
        }else {
            ans.add(String.valueOf(head.value));
            pres(head.left,ans);
            pres(head.right,ans);
        }
    }

    public static Queue<String> inSerial(Node head){
        Queue<String> ans=new LinkedList<>();
        ins(head,ans);
        return ans;
    }

    //中序
    public static void ins(Node head,Queue<String> ans){
        if (head==null){
            ans.add(null);
        }else {
            ins(head.left,ans);
            ans.add(String.valueOf(head.value));
            ins(head.right,ans);
        }
    }

    public static Queue<String> posSerial(Node head){
        Queue<String> ans=new LinkedList<>();
        poss(head,ans);
        return ans;
    }
    //后序
    public static void poss(Node head,Queue<String> ans){
        if (head==null){
            ans.add(null);
        }else {
            poss(head.left,ans);
            poss(head.right,ans);
            ans.add(String.valueOf(head.value));
        }
    }

    public static Node buildByPreQueue(Queue<String> preList){
        if (preList==null|| preList.isEmpty()){
            return null;
        }
        return preb(preList);
    }

    public static Node preb(Queue<String> preList){
        String value = preList.poll();
        if (value==null){
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left=preb(preList);
        head.right=preb(preList);
        return head;
    }

    public static Node buildByPosQueue(Queue<String> posList){
        if (posList==null || posList.isEmpty()){
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!posList.isEmpty()){
            stack.push(posList.poll());
        }
        return posb(stack);
    }
    public static Node posb(Stack<String> posStack){
        String value = posStack.pop();
        if (value==null){
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.right=posb(posStack);
        head.left=posb(posStack);
        return head;
    }
}
