package prefixtree;

import java.util.HashMap;

public class Code1 {

    public static class Node1{
        public int pass;
        public int end;
        public Node1[] next;

        public Node1() {
            pass=0;
            end=0;
            next=new Node1[26];
        }
    }

    public static class Tree1{
        Node1 root;

        public Tree1() {
            root=new Node1();
        }

        public void insert(String word){
            if (word==null){
                return;
            }
            char[] chars = word.toCharArray();
            Node1 node1=root;
            int index=0;
            node1.pass++;
            for (int i = 0; i < chars.length; i++) {
                index=chars[i]-'a';
                if (node1.next[index]==null){
                    node1.next[index]=new Node1();
                }
                node1=node1.next[index];
                node1.pass++;
            }
            node1.end++;
        }

        public int search(String word){
            if (word==null){
                return 0;
            }
            char[] chars = word.toCharArray();
            Node1 node1=root;
            int index=0;
            for (int i = 0; i < chars.length; i++) {
                index=chars[i]-'a';
                if (node1.next[index]==null){
                    return 0;
                }
                node1=node1.next[index];
            }
            return node1.end;
        }

        public int prefixNum(String word){
            if (word==null){
                return 0;
            }
            char[] chars = word.toCharArray();
            Node1 node1=root;
            int index=0;
            for (int i = 0; i < chars.length; i++) {
                index=chars[i]-'a';
                if (node1.next[index]==null){
                    return 0;
                }
                node1=node1.next[index];
            }
            return node1.pass;
        }

        public void delete(String word){
            if (search(word)==0){
                return;
            }
            char[] chars = word.toCharArray();
            Node1 node1=root;
            int index=0;
            node1.pass--;
            for (int i = 0; i < chars.length; i++) {
                index=chars[i]-'a';
                if (--node1.next[index].pass==0){
                    node1.next[index]=null;
                    return;
                }
                node1=node1.next[index];
            }
            node1.end--;
        }
    }

    public static class Node2{
        public int pass;
        public int end;
        public HashMap<Integer,Node2> next;

        public Node2() {
            pass=0;
            end=0;
            next=new HashMap<>();
        }
    }

    public static class Tree2{
        public Node2 root;

        public Tree2() {
            root=new Node2();
        }

        public void insert(String word){
            if (word==null){
                return;
            }
            char[] chars = word.toCharArray();
            Node2 node2=root;
            int index=0;
            node2.pass++;
            for (int i = 0; i < chars.length; i++) {
                index=chars[i];
                if (!node2.next.containsKey(index)){
                    node2.next.put(index,new Node2());
                }
                node2=node2.next.get(index);
                node2.pass++;
            }
            node2.end++;
        }

        public int search(String word){
            if (word==null){
                return 0;
            }
            char[] chars = word.toCharArray();
            Node2 node2=root;
            int index;
            for (int i = 0; i < chars.length; i++) {
                index=chars[i];
                if (!node2.next.containsKey(index)){
                    return 0;
                }
                node2=node2.next.get(index);
            }
            return node2.end;
        }

        public int prefixNum(String word){
            if (word==null){
                return 0;
            }
            char[] chars = word.toCharArray();
            Node2 node2=root;
            int index;
            for (int i = 0; i < chars.length; i++) {
                index=chars[i];
                if (!node2.next.containsKey(index)){
                    return 0;
                }
                node2=node2.next.get(index);
            }
            return node2.pass;
        }
    }
}
