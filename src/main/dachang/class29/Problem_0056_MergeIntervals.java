package dachang.class29;

import java.util.Arrays;
import java.util.Comparator;

public class Problem_0056_MergeIntervals {
    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int start = intervals[0][0];
        int end = intervals[0][1];
        int size = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > end) {
                intervals[size][0] = start;
                intervals[size][1] = end;
                size++;
                start = intervals[i][0];
                end = intervals[i][1];
            } else {
                end = Math.max(end, intervals[i][1]);
            }
        }
        intervals[size][0] = start;
        intervals[size][1] = end;
        size++;
        return Arrays.copyOf(intervals, size);
    }
}
