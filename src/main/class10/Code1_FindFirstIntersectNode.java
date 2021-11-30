package class10;

/**
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。
 * 请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
 */
public class Code1_FindFirstIntersectNode {

    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node getIntersectNode(Node head1,Node head2){
        //head1或者head2为空，二者肯定不相交
        if (head1==null||head2==null){
            return null;
        }
        //获取环的头节点
        Node loop1=getLoopNode(head1);
        Node loop2=getLoopNode(head2);
        if (loop1==null &&loop2==null){
            return noLoop(head1,head2);
        }
        if (loop1!=null && loop2!=null){
            return bothLoop(head1,loop1,head2,loop2);
        }
        return null;
    }

    /**
     * 找到入环的头结点
     * 使用快慢指针。
     * @param head
     * @return
     */
    public static Node getLoopNode(Node head){
        if (head==null || head.next==null||head.next.next==null){
            return null;
        }
        Node slow=head.next;
        Node fast=head.next.next;
        while (slow!=fast){
            if (fast.next==null||fast.next.next==null){
                return null;
            }
            fast=fast.next.next;
            slow=slow.next;
        }
        fast=head;
        while (slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }

    /**
     * 二者都无环，但是会相交，最终汇聚到某一点如Y形
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoop(Node head1,Node head2){
        if (head1==null||head2==null){
            return null;
        }
        Node cur1=head1;
        Node cur2=head2;
        int n=0;
        while (cur1.next!=null){
            n++;
            cur1=cur1.next;
        }
        while (cur2.next!=null){
            n--;
            cur2=cur2.next;
        }
        if (cur1!=cur2){
            return null;
        }
        cur1=n>0?head1:head2; //谁长，谁的头编程cur1
        cur2=cur1==head1?head2:head1; //谁短，谁的头变cur2
        n=Math.abs(n);
        while (n!=0){
            n--;
            cur1=cur1.next;
        }
        while (cur1!=cur2){
            cur1=cur1.next;
            cur2=cur2.next;
        }
        return cur1;
    }

    /**
     * 两个有环链表，返回第一个相交节点
     * @param head1
     * @param loop1
     * @param head2
     * @param loop2
     * @return
     */
    public static Node bothLoop(Node head1,Node loop1,Node head2,Node loop2){
        Node cur1=null;
        Node cur2=null;
        if (loop1==loop2){ //入环节点相同，说明两条单链表相交形成一个环
            cur1=head1;
            cur2=head2;
            int n=0;
            while (cur1!=loop1){
                n++;
                cur1=cur1.next;
            }
            while (cur2!=loop2){
                n--;
                cur2=cur2.next;
            }
            cur1=n>0?head1:head2;
            cur2=cur1==head1?head2:head1;
            n=Math.abs(n);
            while (n!=0){
                n--;
                cur1=cur1.next;
            }
            while (cur1!=cur2){
                cur1=cur1.next;
                cur2=cur2.next;
            }
            return cur1;
        }else {
            cur1=loop1.next;
            while (cur1!=loop1){
                if (cur1==loop2){
                    return loop1;
                }
                cur1=cur1.next;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);
    }
}
