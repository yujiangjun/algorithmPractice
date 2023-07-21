package class40;

// 将一个矩阵顺时针旋转90°
// 方法: 从最外层一层一层开始做个元素进行90°旋转,
public class Code6_RotateMatrix {
    public static void rotate(int[][] matrix) {
        int leftRow = 0;
        int leftCol = 0;
        int rightRow = matrix.length - 1;
        int rightCol = matrix[0].length - 1;
        while (leftCol < rightCol) {
            rotateEdge(matrix, leftRow++, leftCol++, rightRow--, rightCol--);
        }
    }

    public static void rotateEdge(int[][] m, int leftRow, int leftCol, int rightRow, int rightCol) {
        int temp = 0;
        // 分成rightCol-leftCol组,对这些组内元素进行旋转
        for (int i = 0; i < rightCol - leftCol; i++) {
            temp = m[leftRow][leftCol + i];
            m[leftRow][leftCol + i] = m[rightRow - i][leftCol];
            m[rightRow - i][leftCol] = m[rightRow][rightCol - i];
            m[rightRow][rightCol - i] = m[leftRow + i][rightCol];
            m[leftRow + i][rightCol] = temp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);
    }
}
