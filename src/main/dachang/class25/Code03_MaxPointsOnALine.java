package dachang.class25;

import java.util.HashMap;
import java.util.Map;

public class Code03_MaxPointsOnALine {

    public static int maxPoints(int[][] points) {
        if (points == null) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        int ans = 0;
        int n = points.length;
        for (int i = 0; i < n; i++) {
            map.clear();
            int samePoint = 1;
            int sameX = 0;
            int sameY = 0;
            int line = 0;
            for (int j = i + 1; j < n; j++) {
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];
                if (x == 0 && y == 0) {
                    samePoint++;
                } else if (x == 0) {
                    sameX++;
                } else if (y == 0) {
                    sameY++;
                } else {
                    int z = gcd(x, y);
                    int x1 = x / z;
                    int y1 = y / z;
                    if (!map.containsKey(x1)) {
                        map.put(x1, new HashMap<>());
                    }
                    if (!map.get(x1).containsKey(y1)) {
                        map.get(x1).put(y1, 0);
                    }
                    Integer old = map.get(x1).get(y1);
                    map.get(x1).put(y1, old + 1);
                    line = Math.max(line, map.get(x1).get(y1));
                }
            }
            ans = Math.max(ans, Math.max(Math.max(sameX, sameY), line) + samePoint);
        }
        return ans;
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
