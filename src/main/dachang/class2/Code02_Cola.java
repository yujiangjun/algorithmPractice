package dachang.class2;

public class Code02_Cola {

    /**
     * @param m 可乐数量
     * @param a 面值a
     * @param b 面值b
     * @param c 面值c
     * @param x 每一瓶可乐所需的费用
     * @return 购买 m瓶可乐，每瓶费用x的需要投币的次数
     */
    public static int right(int m, int a, int b, int c, int x) {
        int[] qian = {100, 50, 10};
        int[] zhang = {c, b, a};
        int puts = 0;
        while (m != 0) {
            int cur = buy(qian, zhang, x);
            if (cur == -1) {
                return -1;
            }
            m--;
            puts += cur;
        }
        return puts;
    }

    public static int buy(int[] qian, int[] zhang, int rest) {
        int first = -1;
        for (int i = 0; i < zhang.length; i++) {
            if (zhang[i] != 0) {
                first = i;
                break;
            }
        }
        if (first == -1) {
            return -1;
        }
        if (qian[first] >= rest) {
            zhang[first]--;
            giveRest(qian, zhang, first + 1, qian[first] - rest, 1);
            return 1;
        } else {
            zhang[first]--;
            int next = buy(qian, zhang, rest - qian[first]);
            if (next == -1) {
                return -1;
            }
            return 1 + next;
        }
    }

    /**
     * @param i
     * @param oneTimeRest 每次买一瓶可乐，突出的找零总钱数
     * @param times       一共买的可乐数
     */
    public static void giveRest(int[] qian, int[] zhang, int i, int oneTimeRest, int times) {
        for (; i < 3; i++) {
            zhang[i] += (oneTimeRest / qian[i]) * times;
            oneTimeRest %= qian[i];
        }
    }

    public static int putTimes(int m, int a, int b, int c, int x) {
        int[] qian = {100, 50, 10};
        int[] zhang = {c, b, a};
        int puts = 0;
        // 之前的面值的钱还剩下多少总钱数
        int preQianRest = 0;
        // 之前面值钱还剩下的总张数
        int preQianZhang = 0;
        for (int i = 0; i < 3 && m != 0; i++) {
            //当前购买一瓶可乐需要的总张数
            int curQianFirstBuyZhang = (x - preQianRest + qian[i] - 1) / qian[i];
            if (zhang[i] >= curQianFirstBuyZhang) {
                giveRest(qian, zhang, i + 1, (preQianRest + qian[i] * curQianFirstBuyZhang) - x, 1);
                puts += curQianFirstBuyZhang + preQianZhang;
                zhang[i] -= curQianFirstBuyZhang;
                m--;
            } else {// 之前的钱和当前面值的钱不能凑出一瓶可乐
                preQianRest += qian[i] * zhang[i];
                preQianZhang += zhang[i];
                continue;
            }
            // 凑出第一瓶可乐后，还可能凑出更多的可乐
            int curQianBuyOneColaZhang = (x + qian[i] - 1) / qian[i];
            // 用当前的面值的钱，一共可以搞定几瓶可乐
            int curQianBuyColas = Math.min(zhang[i] / curQianBuyOneColaZhang, m);
            // 用当前面值的钱，每搞定一瓶可乐，售货机会吐出多少零钱
            int oneTimeRest = qian[i] * curQianBuyOneColaZhang - x;
            giveRest(qian, zhang, i + 1, oneTimeRest, curQianBuyColas);
            puts += curQianBuyOneColaZhang * curQianBuyColas;
            m -= curQianBuyColas;
            //当前面值可能还剩下若干张，要参与到后续买可乐的过程中
            // 更新preQianRest和preQianZhang
            zhang[i] -= curQianBuyOneColaZhang * curQianBuyColas;
            preQianRest = qian[i] * zhang[i];
            preQianZhang = zhang[i];
        }
        return m == 0 ? puts : -1;
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int zhangMax = 10;
        int colaMax = 10;
        int priceMax = 20;
        System.out.println("如果错误会打印错误数据，否则就是正确");
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int m = (int) (Math.random() * colaMax);
            int a = (int) (Math.random() * zhangMax);
            int b = (int) (Math.random() * zhangMax);
            int c = (int) (Math.random() * zhangMax);
            int x = ((int) (Math.random() * priceMax) + 1) * 10;
            int ans1 = putTimes(m, a, b, c, x);
            int ans2 = right(m, a, b, c, x);
            if (ans1 != ans2) {
                System.out.println("int m = " + m + ";");
                System.out.println("int a = " + a + ";");
                System.out.println("int b = " + b + ";");
                System.out.println("int c = " + c + ";");
                System.out.println("int x = " + x + ";");
                break;
            }
        }
        System.out.println("test end");
    }
}
