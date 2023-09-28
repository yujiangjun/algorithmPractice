package dachang.class27;

import java.util.Arrays;

public class Code03_MinPeople {

    public static int numRabbits(int[] answers) {
        if (answers == null || answers.length == 0) {
            return 0;
        }
        Arrays.sort(answers);
        int x = answers[0];
        int c = 1;
        int ans = 0;
        for (int i = 1; i < answers.length; i++) {
            if (x != answers[i]) {
                ans += ((c + x) / (x + 1)) * (x + 1);
                x = answers[i];
                c = 1;
            } else {
                c++;
            }
        }
        ans += ((c + x) / (x + 1)) * (x + 1);
        return ans;
    }
}
