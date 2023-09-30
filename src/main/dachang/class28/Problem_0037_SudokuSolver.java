package dachang.class28;

public class Problem_0037_SudokuSolver {
    public static void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] bucket = new boolean[9][10];

        initMap(board, row, col, bucket);
        process(board,row,col,bucket,0,0);
    }

    public static void initMap(char[][] board, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    int bid = 3 * (i / 3) + j / 3;
                    row[i][num] = true;
                    col[j][num] = true;
                    bucket[bid][num] = true;
                }
            }
        }
    }

    public static boolean process(char[][] board, boolean[][] row, boolean[][] col,
                                  boolean[][] bucket, int i, int j) {
        if (i == 9) {
            return true;
        }
        int nextI = j != 8 ? i : i + 1;
        int nextJ = j != 8 ? j + 1 : 0;

        if (board[i][j]!='.'){// 无需填
            return process(board,row,col,bucket,nextI,nextJ);
        }else {
            int bid=3*(i/3)+j/3;
            for (int num = 1; num <= 9; num++) {
                if (!row[i][num]&&!col[j][num]&&!bucket[bid][num]){
                    // 1~9依次填
                    row[i][num]=true;
                    col[j][num]=true;
                    bucket[bid][num]=true;
                    board[i][j]= (char) (num+'0');
                    boolean res = process(board, row, col, bucket, nextI, nextJ);
                    if (res){
                        return true;
                    }
                    row[i][num]=false;
                    col[j][num]=false;
                    bucket[bid][num]=false;
                    board[i][j]= '.';
                }
            }
            return false;
        }
    }
}
