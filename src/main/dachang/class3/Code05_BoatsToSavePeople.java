package dachang.class3;

import java.util.Arrays;

public class Code05_BoatsToSavePeople {

    public static int numRescueBoats1(int[] arr, int limit) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        Arrays.sort(arr);
        if (arr[n - 1] > limit) {
            return -1;
        }
        int lessR = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] <= (limit / 2)) {
                lessR = i;
                break;
            }
        }

        if (lessR == -1) {
            return n;
        }
        int l = lessR;
        int r = lessR + 1;
        int noUsed = 0;
        while (l >= 0) {
            int solved = 0;
            while (r < n && arr[l] + arr[r] <= limit) {
                r++;
                solved++;
            }
            if (solved == 0) {
                noUsed++;
                l--;
            } else {
                l = Math.max(-1, l - solved);
            }
        }
        int all = lessR + 1;
        int used = all - noUsed;
        int moreUnsolved = (n - all) - used;
        return used + ((noUsed + 1) >> 1) + moreUnsolved;
    }

    public static int numRescueBoats2(int[] people, int limit) {
        Arrays.sort(people);
        int ans = 0;
        int l = 0;
        int r = people.length - 1;
        int sum = 0;
        while (l <= r) {
            sum = l == r ? people[l] : people[l] + people[r];
            if (sum > limit) {
                r--;
            } else {
                l++;
                r--;
            }
            ans++;
        }
        return ans;
    }
}
