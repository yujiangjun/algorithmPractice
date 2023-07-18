package class39;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class SnacksWaysMain1 {

    // 在0~end范围上,当来到了index号零食,当前选择零食的体积
    public static long process(int[] arr, int index, long w, int end, int bag, TreeMap<Long, Long> map) {
        if (w > bag) {
            return 0;
        }
        if (index > end) {
            if (w != 0) {
                if (!map.containsKey(w)) {
                    map.put(w, 1L);
                } else {
                    map.put(w, map.get(w) + 1);
                }
                return 1;
            } else {
                return 0;
            }
        } else {
            long ways = process(arr, index + 1, w, end, bag, map);
            ways += process(arr, index + 1, w + arr[index], end, bag, map);
            return ways;
        }
    }

    public static long ways(int[] arr, int bag) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] <= bag ? 2 : 1;
        }
        int mid = (arr.length - 1) / 2;
        TreeMap<Long, Long> lMap = new TreeMap<>();
        long ways = process(arr, 0, 0, mid, bag, lMap);
        TreeMap<Long, Long> rMap = new TreeMap<>();
        ways += process(arr, mid + 1, 0, arr.length - 1, bag, rMap);
        TreeMap<Long, Long> rPre = new TreeMap<>();
        long pre = 0;
        for (Map.Entry<Long, Long> entry : rMap.entrySet()) {
            pre += entry.getValue();
            rPre.put(entry.getKey(), pre);
        }
        for (Map.Entry<Long, Long> entry : lMap.entrySet()) {
            long lweight = entry.getKey();
            long lways = entry.getValue();
            Long floor = rPre.floorKey(bag - lweight);
            if (floor != null) {
                long rways = rPre.get(floor);
                ways += lways * rways;
            }
        }
        return ways + 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int bag = (int) in.nval;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            long ways = ways(arr, bag);
            out.println(ways);
            out.flush();
        }
    }
}
