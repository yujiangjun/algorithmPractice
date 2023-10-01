package dachang.class30;

public class Problem_0079_WordSearch {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) {
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (process(board, i, j, word.toCharArray(), 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean process(char[][] board, int i, int j, char[] w, int k) {
        if (k == w.length) {
            return true;
        }
        if (i < 0 || i == board.length || j < 0 || j == board[0].length) {
            return false;
        }
        if (board[i][j] != w[k]) {
            return false;
        }
        char temp = board[i][j];
        board[i][j] = 0;
        boolean ans = false;
        ans |= process(board, i + 1, j, w, k + 1);
        ans |= process(board, i - 1, j, w, k + 1);
        ans |= process(board, i, j - 1, w, k + 1);
        ans |= process(board, i, j + 1, w, k + 1);
        board[i][j] = temp;
        return ans;
    }
}
