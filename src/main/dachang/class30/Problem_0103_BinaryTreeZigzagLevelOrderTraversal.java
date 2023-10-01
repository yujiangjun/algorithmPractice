package dachang.class30;

import java.util.*;

public class Problem_0103_BinaryTreeZigzagLevelOrderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        int size = 0;
        Deque<TreeNode> left = new LinkedList<>();
        Deque<TreeNode> right = new LinkedList<>();
        boolean isLeft = true;
        left.addLast(root);
        while (!left.isEmpty()||!right.isEmpty()) {
            List<Integer> items = new ArrayList<>();
            if (isLeft){
                size = left.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = left.pollLast();
                    items.add(node.val);
                    if (node.left!=null){
                        right.addLast(node.left);
                    }
                    if (node.right!=null){
                        right.addLast(node.right);
                    }
                }
            }else {
                size=right.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = right.pollLast();
                    items.add(node.val);
                    if (node.right!=null){
                        left.addLast(node.right);
                    }
                    if (node.left!=null){
                        left.addLast(node.left);
                    }
                }
            }
            isLeft = !isLeft;
            ans.add(items);
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root= new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);
        root.right.left=new TreeNode(6);
        root.right.right=new TreeNode(7);
        List<List<Integer>> ans = zigzagLevelOrder(root);
        for (List<Integer> res : ans) {
            System.out.println(Arrays.toString(res.toArray()));
        }
    }
}
