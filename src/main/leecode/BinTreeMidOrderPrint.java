package leecode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 二叉树中序遍历
public class BinTreeMidOrderPrint {

     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            Info info = recursive(root);
            return info.sub;
        }

        public class Info{
            List<Integer> sub;

            public Info(List<Integer> sub){
                this.sub=sub;
            }
        }
        public Info recursive(TreeNode root){
            if(root == null){
                return new Info(new ArrayList<Integer>());
            }
            List<Integer> left=recursive(root.left).sub;
            List<Integer> right=recursive(root.right).sub;

            List<Integer> all = new ArrayList<>();
            all.addAll(left);
            all.add(root.val);
            all.addAll(right);
            return new Info(all);

        }
    }
}
