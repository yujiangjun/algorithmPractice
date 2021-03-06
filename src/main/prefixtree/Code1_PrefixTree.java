package prefixtree;

import java.util.HashMap;

public class Code1_PrefixTree {

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

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                Node2 node = root;
                node.pass--;
                int index = 0;
                for (int i = 0; i < chs.length; i++) {
                    index = (int) chs[i];
                    if (--node.next.get(index).pass == 0) {
                        node.next.remove(index);
                        return;
                    }
                    node = node.next.get(index);
                }
                node.end--;
            }
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

    public static class Right{
        private HashMap<String,Integer> box;
        public Right(){
            box=new HashMap<>();
        }

        public void insert(String word){
            if (!box.containsKey(word)){
                box.put(word,1);
            }else {
                box.put(word,box.get(word)+1);
            }
        }

        public void delete(String word){
            if (box.containsKey(word)){
                if (box.get(word)==1){
                    box.remove(word);
                }else {
                    box.put(word,box.get(word)-1);
                }
            }
        }

        public int search(String word){
            if (!box.containsKey(word)){
                return 0;
            }else {
                return box.get(word);
            }
        }

        public int prefixNum(String pre){
            int count=0;
            for (String cur:box.keySet()){
                if (cur.startsWith(pre)){
                    count++;
                }
            }
            return count;
        }
    }
    public static String generateRandomString(int strLen){
        char[] chars = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < chars.length; i++) {
            int value = (int) (Math.random() * 6);
            chars[i]= (char) (97+value);
        }
        return String.valueOf(chars);
    }

    public static String[] generateRandomStringArray(int arrLen,int strLen){
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i]=generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Tree1 trie1 = new Tree1();
            Tree2 trie2 = new Tree2();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNum(arr[j]);
                    int ans2 = trie2.prefixNum(arr[j]);
                    int ans3 = right.prefixNum(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");
    }
}
