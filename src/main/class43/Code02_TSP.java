package class43;

import java.util.ArrayList;
import java.util.List;

public class Code02_TSP {

    public static int t1(int[][] matrix) {
        int n = matrix.length;
        List<Integer> set = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            set.add(1);
        }
        return func1(matrix, set, 0);
    }

    public static int func1(int[][] matrix, List<Integer> set, int start) {
        int cityNum = 0;
        for (int i = 0; i < set.size(); i++) {
            if (set.get(i) != null) {
                cityNum++;
            }
        }
        if (cityNum == 1) {
            return matrix[start][0];
        }
        set.set(start, null);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < set.size(); i++) {
            if (set.get(i) != null) {
                int cur = matrix[start][i] + func1(matrix, set, i);
                min = Math.min(min, cur);
            }
        }
        set.set(start, 1);
        return min;
    }
}
