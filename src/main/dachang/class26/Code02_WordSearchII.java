package dachang.class26;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Code02_WordSearchII {

    public static class TrieNode {
        public TrieNode[] nexts;
        public int pass;
        public boolean end;

        public TrieNode() {
            nexts = new TrieNode[26];
            this.pass = 0;
            this.end = false;
        }
    }


    public List<String> findWords(char[][] board, String[] words) {
        TrieNode head = new TrieNode();
        HashSet<String> set = new HashSet<>();
        for (String word : words) {
            if (set.contains(word)) {
                continue;
            }
            set.add(word);
            fillTrieTree(head, word);
        }
        int row = board.length;
        int col = board[0].length;
        List<String> ans = new ArrayList<>();
        LinkedList<Character> path = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                process(board, i, j, path, head, ans);
            }
        }
        return ans;
    }

    public static void fillTrieTree(TrieNode head, String word) {
        head.pass++;
        TrieNode cur = head;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (cur.nexts[index] == null) {
                cur.nexts[index] = new TrieNode();
            }
            cur.nexts[index].pass++;
            cur = cur.nexts[index];
        }
        cur.end=true;
    }

    public static int process(char[][] board, int row, int col, LinkedList<Character> path, TrieNode cur, List<String> res) {
        char c = board[row][col];
        if (c == 0) {
            return 0;
        }
        int index = c - 'a';
        if (cur.nexts[index] == null || cur.nexts[index].pass == 0) {
            return 0;
        }
        cur = cur.nexts[index];
        path.addLast(c);
        int fix = 0;
        if (cur.end ) {
            res.add(generatePath(path));
            cur.end=false;
            fix++;
        }
        board[row][col]=0;
        if (row > 0) {
            fix += process(board, row - 1, col, path, cur, res);
        }
        if (col > 0) {
            fix += process(board, row, col - 1, path, cur, res);
        }
        if (row < board.length - 1) {
            fix += process(board, row + 1, col, path, cur, res);
        }
        if (col < board[0].length-1) {
            fix += process(board, row, col + 1, path, cur, res);
        }
        board[row][col] = c;
        path.pollLast();
        cur.pass -= fix;
        return fix;
    }

    public static int process2(char[][] board,int row,int col,LinkedList<Character> path,TrieNode cur,List<String> res){
        int fix=0;
        char c = board[row][col];
        if (c==0){
            return 0;
        }
        int index=c-'a';
        if (cur.nexts[index]==null||cur.nexts[index].pass==0){
            return 0;
        }
        cur=cur.nexts[index];
        path.add(c);
        if (cur.end){
            res.add(generatePath(path));
            cur.end=false;
            fix++;
        }

        board[row][col]=0;
        if (row>0){
            fix+=process2(board, row-1, col, path, cur, res);
        }
        if (col>0){
            fix+=process2(board, row, col-1, path, cur, res);
        }
        if (row< board.length-1){
            fix+=process2(board, row+1, col, path, cur, res);
        }
        if (col< board[0].length-1){
            fix+=process2(board, row, col+1, path, cur, res);
        }

        board[row][col]=c;
        path.pollLast();
        cur.pass-=fix;
        return fix;
    }

    public static String generatePath(List<Character> path) {
        StringBuilder sb = new StringBuilder();
        for (Character c : path) {
            sb.append(c);
        }
        return sb.toString();
    }
}
