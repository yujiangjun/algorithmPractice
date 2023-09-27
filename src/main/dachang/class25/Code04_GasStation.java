package dachang.class25;

public class Code04_GasStation {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0) {
            return -1;
        }
        if (gas.length == 1) {
            return gas[0] < cost[0] ? -1 : 0;
        }
        boolean[] good = stations(cost, gas);
        for (int i = 0; i < good.length; i++) {
            if (good[i]) {
                return i;
            }
        }
        return -1;
    }


    public static boolean[] stations(int[] cost, int[] gas) {
        if (cost == null || gas == null || cost.length < 2 || cost.length != gas.length) {
            return new boolean[cost.length];
        }
        int init = changeDisArrayGetInit(cost, gas);
        return init == -1 ? new boolean[cost.length] : enlargeArea(cost, init);
    }

    public static int lastIndex(int index, int size) {
        return index == 0 ? size - 1 : index - 1;
    }

    public static int nextIndex(int index, int size) {
        return index == size - 1 ? 0 : index + 1;
    }


    public static int changeDisArrayGetInit(int[] dis, int[] oil) {
        int init = -1;
        for (int i = 0; i < dis.length; i++) {
            dis[i] = oil[i] - dis[i];
            if (dis[i] >= 0) {
                init = i;
            }
        }
        return init;
    }

    public static boolean[] enlargeArea(int[] dis, int init) {
        boolean[] ans = new boolean[dis.length];
        int start = init;
        int end = nextIndex(init, dis.length);
        int need = 0;
        int rest = 0;
        do {
            if (start != init && start == lastIndex(end, dis.length)) {
                break;
            }
            if (dis[start] < need) {
                need -= dis[start];
            } else {
                rest += dis[start] - need;
                need = 0;
                while (rest >= 0 && end != start) {
                    rest += dis[end];
                    end = nextIndex(end, dis.length);
                }
                if (rest >= 0) {
                    ans[start] = true;
                    connectGood(dis, lastIndex(start, dis.length), init, ans);
                    break;
                }
            }
            start = lastIndex(start, dis.length);
        } while (start != init);
        return ans;
    }

    public static void connectGood(int[] dis, int start, int init, boolean[] res) {
        int need = 0;
        while (start != init) {
            if (dis[start] < need) {
                need -= dis[start];
            } else {
                res[start] = true;
                need = 0;
            }
            start = lastIndex(start, dis.length);
        }
    }
}
