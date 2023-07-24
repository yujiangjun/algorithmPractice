package class41;

public class Code03_StoneMerge {

    public static int[] sum(int[] arr) {
        int n = arr.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < arr.length; i++) {
            s[i + 1] = s[i] + arr[i];
        }
        return s;
    }

    public static int w(int[] s, int l, int r) {
        return s[r + 1] - s[l];
    }

    /**
     * 合并l..r范围内石子的最小得分
     */
    public static int process(int l,int r,int[] s){
        if (l==r){
            return 0;
        }
    }
}
