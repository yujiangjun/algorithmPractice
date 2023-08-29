package dachang.class10;

public class Code04_BSTToDubleLinkedList {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        Node start;
        Node end;

        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(null, null);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        head.left = leftInfo.end;
        if (leftInfo.end != null) {
            leftInfo.end.right = head;
        }
        head.right = rightInfo.start;
        if (rightInfo.start != null) {
            rightInfo.start.left = head;
        }
        return new Info(leftInfo.start != null ? leftInfo.start : head, rightInfo.end != null ? rightInfo.end : head);
    }

    public static Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Info all = process(root);
        Node start = all.start;
        Node end = all.end;
        if (start != null) {
            start.left = end;
        }
        if (end != null) {
            end.right = start;
        }
        return start;
    }
}
