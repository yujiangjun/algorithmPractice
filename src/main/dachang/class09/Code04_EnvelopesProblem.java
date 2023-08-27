package dachang.class09;

import java.util.Arrays;
import java.util.Comparator;

public class Code04_EnvelopesProblem {


    public static int maxEnvelopes(int[][] envelopes) {
        Envelope[] arr = sort(envelopes);
        int[] ends = new int[envelopes.length];
        ends[0] = arr[0].height;
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i].height > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i].height;
        }
        return right + 1;
    }

    public static class Envelope {
        private final int weight;
        private final int height;

        public Envelope(int weight, int height) {
            this.weight = weight;
            this.height = height;
        }
    }

    public static class EnvelopeComparator implements Comparator<Envelope> {
        @Override
        public int compare(Envelope o1, Envelope o2) {
            return o1.weight != o2.weight ? o1.weight - o2.weight : o2.height - o1.height;
        }
    }

    public static Envelope[] sort(int[][] matrix) {
        Envelope[] res = new Envelope[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            res[i] = new Envelope(matrix[i][0], matrix[i][1]);
        }
        Arrays.sort(res, new EnvelopeComparator());
        return res;
    }
}
