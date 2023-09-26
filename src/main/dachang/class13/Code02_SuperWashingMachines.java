package dachang.class13;

public class Code02_SuperWashingMachines {

    public static int findMinMoves(int[] machines) {
        if (machines == null || machines.length == 0) {
            return 0;
        }
        int size = machines.length;
        int sum = 0;
        for (int num : machines) {
            sum += num;
        }
        if (sum % size != 0) {
            return -1;
        }
        int avg = sum / size;
        int leftSum = 0;
        int ans = 0;

        for (int i = 0; i < size; i++) {
            int leftRest = leftSum - avg * i;
            int rightRest = sum - leftSum - machines[i] - (size - i - 1) * avg;
            if (leftRest < 0 && rightRest < 0) {
                ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }
            leftSum += machines[i];
        }
        return ans;
    }
}
