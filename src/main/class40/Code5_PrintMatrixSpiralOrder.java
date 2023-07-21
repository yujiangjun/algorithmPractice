package class40;

// 按顺时针方向螺旋打印一个矩阵
public class Code5_PrintMatrixSpiralOrder {

    public static void spiralOrderPrint(int[][] matrix) {
        // 左上角的左边
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while (tC <= dC && tR <= dR) {
            printEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    public static void printEdge(int[][] m, int tR, int tC, int dR, int dC) {
        if (tR == dR) {
            for (int i = tC; i <= dC; i++) {
                System.out.print(m[tR][i] + "  ");
            }
        } else if (tC == dC) {
            for (int i = tR; i <= dR; i++) {
                System.out.print(m[i][tC] + "  ");
            }
        } else {
            int curR = tR;
            int curC = tC;
            while (curC != dC) {
                System.out.print(m[tR][curC] + "  ");
                curC++;
            }
            while (curR != dR) {
                System.out.print(m[curR][dR] + "  ");
                curR++;
            }
            while (curC != tC) {
                System.out.print(m[dR][curC] + "  ");
                curC--;
            }
            while (curR != tR) {
                System.out.print(m[curR][tC] + "   ");
                curR--;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},
                {13, 14, 15, 16}};
        spiralOrderPrint(matrix);
    }
}
