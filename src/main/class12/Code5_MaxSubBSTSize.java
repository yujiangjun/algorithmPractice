package class12;

import java.util.ArrayList;

public class Code5_MaxSubBSTSize {

    public static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int getBSTSize(Node head){
        if (head==null){
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head,arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value<=arr.get(i-1).value){
                return 0;
            }
        }
        return arr.size();
    }

    public static void  in(Node head,ArrayList<Node> arr){
        if (head==null){
            return;
        }
        in(head.left,arr);
        arr.add(head);
        in(head.right,arr);
    }


    public static int maxSubBSTSize1(Node head){
        if (head==null){
            return 0;
        }
        int h=getBSTSize(head);
        if (h!=0){
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left),maxSubBSTSize1(head.right));
    }


//    public static int maxSubBSTSize2(Node head){
//        if (head==null){
//            return 0;
//        }
//        return process(head).maxSubBSTSize;
//    }
//
//    public static class Info{
//        boolean isAllBST;
//        public int maxSubBSTSize;
//        int min;
//        int max;
//
//        public Info(boolean isAllBST, int maxSubBSTSize, int min, int max) {
//            this.isAllBST = isAllBST;
//            this.maxSubBSTSize = maxSubBSTSize;
//            this.min = min;
//            this.max = max;
//        }
//    }
//
//    public static Info process(Node x){
//        if (x==null){
//            return null;
//        }
//        Info leftInfo=process(x.left);
//        Info rightInfo=process(x.right);
//
//        int min=x.value;
//        int max=x.value;
//        if (leftInfo!=null){
//            min=leftInfo.min;
//            max=leftInfo.max;
//        }
//        if (rightInfo!=null){
//            min=Math.min(min,rightInfo.min);
//            max=Math.max(max,rightInfo.max);
//        }
//
//        int maxSubBSTSize=0;
//        if (leftInfo!=null){
//            maxSubBSTSize= leftInfo.maxSubBSTSize;
//        }
//        if (rightInfo!=null){
//            maxSubBSTSize=Math.max(maxSubBSTSize, rightInfo.maxSubBSTSize);
//        }
//
//        boolean isAllBST=false;
//
//        if (
//                (leftInfo==null?true: leftInfo.isAllBST)
//                &&
//                        (rightInfo==null?true: rightInfo.isAllBST)
//                &&
//                        (leftInfo==null?true:leftInfo.max<x.value)
//                &&
//                        (rightInfo==null?true:rightInfo.min>x.value)
//        ){
//            maxSubBSTSize=
//                    (leftInfo==null?0: leftInfo.maxSubBSTSize)
//                    +
//                            (rightInfo==null?0: rightInfo.maxSubBSTSize)
//                    +1;
//            isAllBST=true;
//        }
//        return new Info(isAllBST,maxSubBSTSize,min,max);
//    }

    public static int maxSubBSTSize2(Node head) {
        if(head == null) {
            return 0;
        }
        return process(head).maxBSTSubtreeSize;
    }

    public static class Info {
        public int maxBSTSubtreeSize;
        public int allSize;
        public int max;
        public int min;

        public Info(int m, int a, int ma, int mi) {
            maxBSTSubtreeSize = m;
            allSize = a;
            max = ma;
            min = mi;
        }
    }

    public static Info process(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int max = x.value;
        int min = x.value;
        int allSize = 1;
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
            allSize += leftInfo.allSize;
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
            allSize += rightInfo.allSize;
        }
        int p1 = -1;
        if (leftInfo != null) {
            p1 = leftInfo.maxBSTSubtreeSize;
        }
        int p2 = -1;
        if (rightInfo != null) {
            p2 = rightInfo.maxBSTSubtreeSize;
        }
        int p3 = -1;
        boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
        boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
        if (leftBST && rightBST) {
            boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.value);
            boolean rightMinMoreX = rightInfo == null ? true : (x.value < rightInfo.min);
            if (leftMaxLessX && rightMinMoreX) {
                int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                p3 = leftSize + rightSize + 1;
            }
        }
        return new Info(Math.max(p1, Math.max(p2, p3)), allSize, max, min);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
