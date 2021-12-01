package class11;

import java.util.ArrayList;
import java.util.List;

// 本题测试链接：https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree
public class Code3EncodeNaryTreeToBinaryTree {

    public static class Node{
        public int value;
        public List<Node> children;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, List<Node> children) {
            this.value = value;
            this.children = children;
        }
    }

    public static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    class Codec{
        // Encodes an n-ary tree to a binary tree.

        public TreeNode encode(Node root){
            if (root==null){
                return null;
            }
            TreeNode head = new TreeNode(root.value);
            head.left=en(root.children);
            return head;
        }

        private TreeNode en(List<Node> children){
            TreeNode head = null;
            TreeNode cur = null;
            for (Node child:children){
                TreeNode tNode=new TreeNode(child.value);
                if (head==null){
                    head=tNode;
                }else {
                    cur.right=tNode;
                }
                cur=tNode;
                cur.left=en(child.children);
            }
            return head;
        }

        // Decodes your binary tree to an n-ary tree.
        public Node decode(TreeNode root){
            if (root==null){
                return null;
            }
            return new Node(root.value,de(root.left));
        }

        public List<Node> de(TreeNode root){
            List<Node> children=new ArrayList<>();
            while (root!=null){
                Node cur = new Node(root.value,de(root.left));
                children.add(cur);
                root=root.right;
            }
            return children;
        }
    }
}
