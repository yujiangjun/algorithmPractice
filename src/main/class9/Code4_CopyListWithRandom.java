package class9;

import java.util.HashMap;

/**
 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
 */
public class Code4_CopyListWithRandom {
    public static class Node{
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next=null;
            this.random=null;
        }
    }

    public static Node copyRandomList1(Node head){
        HashMap<Node,Node> map = new HashMap<>();
        Node cur=head;
        while (cur!=null){
            map.put(cur,new Node(cur.val));
            cur=cur.next;
        }

        cur=head;
        while (cur!=null){
            map.get(cur).next=map.get(cur.next);
            map.get(cur).random=map.get(cur.random);
            cur=cur.next;
        }
        return map.get(head);
    }

    public static Node copyRandomList2(Node head){
        if (head==null){
            return null;
        }
        Node cur=head;
        Node next=null;
        while (cur!=null){
            next=cur.next;
            cur.next=new Node(cur.val);
            cur.next.next=next;
            cur=next;
        }
        cur=head;
        Node copy=null;
        while (cur!=null){
            next=cur.next.next;
            copy=cur.next;
            copy.random=cur.random!=null?cur.random.next:null;
            cur=next;
        }
        Node res=head.next;
        cur=head;
        while (cur!=null){
            next=cur.next.next;
            copy=cur.next;
            cur.next=next;
            copy.next=next!=null?next.next:null;
            cur=next;
        }
        return res;
    }
}
