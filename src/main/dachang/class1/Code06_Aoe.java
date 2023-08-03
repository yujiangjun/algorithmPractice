package dachang.class1;

import java.util.Arrays;

public class Code06_Aoe {

    public static int minAoe1(int[] x, int[] hp, int range) {
        boolean allClear = true;
        for (int i : hp) {
            if (i > 0) {
                allClear = false;
                break;
            }
        }
        if (allClear) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int left = 0; left < x.length; left++) {
            if (hasHp(x, hp, left, range)) {
                minusOneHp(x, hp, left, range);
                ans = Math.min(ans, 1 + minAoe1(x, hp, range));
                addOneHp(x, hp, left, range);
            }
        }
        return ans;
    }

    public static boolean hasHp(int[] x, int[] hp, int left, int range) {
        for (int index = left; index < x.length && x[index] - x[left] <= range; index++) {
            if (hp[index] > 0) {
                return true;
            }
        }
        return false;
    }


    public static void minusOneHp(int[] x, int[] hp, int left, int range) {
        for (int index = left; index < x.length && x[index] - x[left] <= range; index++) {
            hp[index]--;
        }
    }

    public static void addOneHp(int[] x, int[] hp, int left, int range) {
        for (int index = left; index < x.length && x[index] - x[left] <= range; index++) {
            hp[index]++;
        }
    }

    // 贪心
    // 法术Aoe的左边界是i号怪兽，最右边的位置
    public static int minAoe2(int[] x,int[] hp,int range){
        int n=x.length;
        // cover[i]=j 含义：表示法术的左边界是i号怪兽的位置
        // 法术的右边界是j
        int[] cover=new int[n];
        int r=0;
        for (int i = 0; i < n; i++) {
            while (r<n&&x[r]-x[i]<=range){
                r++;
            }
            cover[i]=r;
        }

        // 根据贪心
        // 当来到cover[i]=j;
        // i号怪兽死亡，那么i+1到j-1号的怪兽hp-hp[i]
        int ans=0;
        for (int i = 0; i < n; i++) {
            if (hp[i]>0){
                int minus=hp[i];
                for (int left = 0; left < cover[i]; left++) {
                    hp[left]-=minus;
                }
                ans+=minus;
            }
        }
        return ans;
    }

    public static class SegmentTree {
        private int MAXN;
        private int[] arr;
        private int[] sum;
        private int[] lazy;

        public SegmentTree(int[] origin) {
            MAXN = origin.length + 1;
            arr = new int[MAXN];
            for (int i = 1; i < MAXN; i++) {
                arr[i] = origin[i - 1];
            }
            sum = new int[MAXN << 2];
            lazy = new int[MAXN << 2];
        }

        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        private void pushDown(int rt, int ln, int rn) {
            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * ln;
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1 | 1] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }

        public void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt);
        }

        public void add(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                sum[rt] += C * (r - l + 1);
                lazy[rt] += C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            int ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }

    }

    public static int minAoe3(int[] x,int[] hp,int range){
        int n=x.length;
        int[] cover=new int[n];
        int r=0;
        for (int i = 0; i < n; i++) {
            while (r<n&&x[r]-x[i]<=range){
                r++;
            }
            cover[i]=r-1;
        }
        SegmentTree st = new SegmentTree(hp);
        st.build(1,n,1);
        int ans=0;

        for (int i = 1; i <= n; i++) {
            int leftHp=st.query(i,i,1,n,1);
            if (leftHp>0){
                ans+=leftHp;
                st.add(i,cover[i-1]+1,-leftHp,1,n,1 );
            }
        }
        return ans;
    }

    // 为了测试
    public static int[] randomArray(int n, int valueMax) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * valueMax) + 1;
        }
        return ans;
    }

    // 为了测试
    public static int[] copyArray(int[] arr) {
        int N = arr.length;
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    // 为了测试
    public static void main(String[] args) {
        int N = 50;
        int X = 500;
        int H = 60;
        int R = 10;
        int testTime = 50000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[] x2 = randomArray(len, X);
            Arrays.sort(x2);
            int[] hp2 = randomArray(len, H);
            int[] x3 = copyArray(x2);
            int[] hp3 = copyArray(hp2);
            int range = (int) (Math.random() * R) + 1;
            int ans2 = minAoe2(x2, hp2, range);
            int ans3 = minAoe3(x3, hp3, range);
            if (ans2 != ans3) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");

        N = 500000;
        long start;
        long end;
        int[] x2 = randomArray(N, N);
        Arrays.sort(x2);
        int[] hp2 = new int[N];
        for (int i = 0; i < N; i++) {
            hp2[i] = i * 5 + 10;
        }
        int[] x3 = copyArray(x2);
        int[] hp3 = copyArray(hp2);
        int range = 10000;

        start = System.currentTimeMillis();
        System.out.println(minAoe2(x2, hp2, range));
        end = System.currentTimeMillis();
        System.out.println("运行时间 : " + (end - start) + " 毫秒");

        start = System.currentTimeMillis();
        System.out.println(minAoe3(x3, hp3, range));
        end = System.currentTimeMillis();
        System.out.println("运行时间 : " + (end - start) + " 毫秒");
    }
}
