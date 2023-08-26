package dachang.class09;

public class Code01_LightProblem {

    public static int noLoopRight(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        } else if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        return f1(arr, 0);
    }

    public static int f1(int[] arr, int i) {
        if (i == arr.length) {
            return valid(arr) ? 0 : Integer.MAX_VALUE;
        }
        int p1 = f1(arr, i + 1);
        change1(arr, i);
        int p2 = f1(arr, i + 1);
        change1(arr,i);
        p2 = (p2 == Integer.MAX_VALUE) ? p2 : (p2 + 1);
        return Math.min(p1, p2);
    }

    public static boolean valid(int[] arr) {
        for (int i : arr) {
            if (i == 0) {
                return false;
            }
        }
        return true;
    }


    public static void change1(int[] arr, int i) {
        if (i == 0) {
            arr[0] ^= 1;
            arr[1] ^= 1;
        } else if (i == arr.length - 1) {
            arr[i] ^= 1;
            arr[i - 1] ^= 1;
        } else {
            arr[i] ^= 1;
            arr[i - 1] ^= 1;
            arr[i + 1] ^= 1;
        }
    }

    public static int process1(int[] arr, int preStatus, int curStatus, int nextIndex) {
        if (nextIndex == arr.length) {
            return preStatus != curStatus ? Integer.MAX_VALUE : preStatus ^ 1;
        }
        // preStatus=0,当前需要按下开关，因为如果当前不按下开关，pre的位置永远是不亮的，
        // 后续无法在改变pre的状态
        // 当前要做改变
        if (preStatus == 0) {
            curStatus ^= 1;
            int cur = arr[nextIndex] ^ 1;
            int next = process1(arr,  curStatus, cur,nextIndex + 1);
            return next == Integer.MAX_VALUE ? next : (next + 1);
        } else {
            return process1(arr, curStatus, arr[nextIndex], nextIndex + 1);
        }
    }

    public static int noLoopMinStep1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] ^ 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : arr[0] ^ 1;
        }
        // 在1 位置不按开关
        int p1 = process1(arr, arr[0], arr[1], 2);
        // 在1 位置按快关
        int p2 = process1(arr, arr[0] ^ 1, arr[1] ^ 1, 2);
        p2 = p2 == Integer.MAX_VALUE ? Integer.MAX_VALUE : p2 + 1;
        return Math.min(p1, p2);
    }

    public static int traceNoLoop(int[] arr, int preStatus, int curStatus) {
        int i = 2;
        int op = 0;
        while (i != arr.length) {
            if (preStatus == 0) {
                preStatus = curStatus ^ 1;
                curStatus = arr[i++] ^ 1;
                op++;
            } else {
                preStatus = curStatus;
                curStatus = arr[i++];
            }
        }
        return curStatus != preStatus ? Integer.MAX_VALUE : op + (curStatus ^ 1);
    }

    public static int noLoopMinStep2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] ^ 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : arr[0] ^ 1;
        }
        // 在1 位置不按开关
        int p1 = traceNoLoop(arr, arr[0], arr[1]);
        // 在1 位置按快关
        int p2 = traceNoLoop(arr, arr[0] ^ 1, arr[1] ^ 1);
        p2 = p2 == Integer.MAX_VALUE ? Integer.MAX_VALUE : p2 + 1;
        return Math.min(p1, p2);
    }


    // 有环改灯问题的暴力版本
    public static int loopRight(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        return f2(arr, 0);
    }

    public static int f2(int[] arr, int i) {
        if (i == arr.length) {
            return valid(arr) ? 0 : Integer.MAX_VALUE;
        }
        int p1 = f2(arr, i + 1);
        change2(arr, i);
        int p2 = f2(arr, i + 1);
        change2(arr,i);
        p2 = p2 == Integer.MAX_VALUE ? Integer.MAX_VALUE : (p2 + 1);
        return Math.min(p1, p2);
    }

    public static void change2(int[] arr, int i) {
        arr[lastIndex(i, arr.length)] ^= 1;
        arr[i] ^= 1;
        arr[nextIndex(i, arr.length)] ^= 1;
    }

    public static int lastIndex(int i, int n) {
        return i == 0 ? n - 1 : i - 1;
    }

    public static int nextIndex(int i, int n) {
        return i == n - 1 ? 0 : i + 1;
    }

    public static int process2(int[] arr, int nextIndex, int preStatus, int curStatus, int endStatus, int firstStatus) {
        if (nextIndex == arr.length) {
            return (endStatus != firstStatus || endStatus != preStatus) ? Integer.MAX_VALUE : (endStatus ^ 1);
        }

        int noNextPreStatus = 0;
        int yesNextPreStatus = 0;
        int noNextCurStatus = 0;
        int yesNextCurStatus = 0;
        int noEndStatus = 0;
        int yesEndStatus = 0;
        if (nextIndex < arr.length - 1) {
            noNextPreStatus = curStatus;
            yesNextPreStatus = curStatus ^ 1;
            noNextCurStatus = arr[nextIndex];
            yesNextCurStatus = arr[nextIndex] ^ 1;
        } else {
            noNextPreStatus = curStatus;
            yesNextPreStatus = curStatus ^ 1;
            noNextCurStatus = endStatus;
            yesNextCurStatus = endStatus ^ 1;
            noEndStatus = endStatus;
            yesEndStatus = endStatus ^ 1;
        }
        if (preStatus == 0) {
            int next = process2(arr, nextIndex + 1, yesNextPreStatus, yesNextCurStatus, nextIndex == arr.length - 1 ? yesEndStatus : endStatus, firstStatus);
            return next == Integer.MAX_VALUE ? next : (next + 1);
        } else {
            return process2(arr, nextIndex + 1, noNextPreStatus, noNextCurStatus, nextIndex == arr.length - 1 ? noEndStatus : endStatus, firstStatus);
        }
    }

    public static int loopMinStep1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] ^ 1;
        }
        if (arr.length == 2) {
            return arr[0] == arr[1] ? arr[0] ^ 1 : Integer.MAX_VALUE;
        }
        if (arr.length == 3) {
            return (arr[0] != arr[1] || arr[1] != arr[2]) ? Integer.MAX_VALUE : arr[0] ^ 1;
        }
        // 0不变，1不变
        int p1 = process2(arr, 3, arr[1], arr[2], arr[arr.length - 1], arr[0]);
        // 0改变，1不变
        int p2 = process2(arr, 3, arr[1] ^ 1, arr[2], arr[arr.length - 1] ^ 1, arr[0] ^ 1);
        // 0不变，1改变
        int p3 = process2(arr, 3, arr[1] ^ 1, arr[2] ^ 1, arr[arr.length - 1], arr[0] ^ 1);
        // 0改变，1改变
        int p4 = process2(arr, 3, arr[1], arr[2] ^ 1, arr[arr.length - 1] ^ 1, arr[0]);
        p2 = p2 != Integer.MAX_VALUE ? (p2 + 1) : p2;
        p3 = p3 != Integer.MAX_VALUE ? (p3 + 1) : p3;
        p4 = p4 != Integer.MAX_VALUE ? (p4 + 2) : p4;
        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }

    public static int traceLoop(int[] arr, int preStatus, int curStatus, int endStatus, int firstStatus) {
        int i = 3;
        int op = 0;
        while (i != arr.length - 1) {
            if (preStatus == 0) {
                preStatus = curStatus ^ 1;
                curStatus = arr[i++] ^ 1;
                op++;
            } else {
                preStatus = curStatus;
                curStatus = arr[i++];
            }
        }
        if (preStatus == 0) {
            op++;
            preStatus = curStatus ^ 1;
            endStatus ^= 1;
            curStatus = endStatus;
        } else {
            preStatus = curStatus;
            curStatus = endStatus;
        }
        return (endStatus != firstStatus || endStatus != preStatus) ? Integer.MAX_VALUE : (op + (endStatus ^ 1));
    }

    public static int loopMinStep2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        if (arr.length == 3) {
            return (arr[0] != arr[1] || arr[0] != arr[2]) ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        int p1 = traceLoop(arr, arr[1], arr[2], arr[arr.length - 1], arr[0]);
        // 0变 1不变
        int p2 = traceLoop(arr, arr[1] ^ 1, arr[2], arr[arr.length - 1] ^ 1, arr[0] ^ 1);
        // 0不变 1变
        int p3 = traceLoop(arr, arr[1] ^ 1, arr[2] ^ 1, arr[arr.length - 1], arr[0] ^ 1);
        // 0改变 1改变
        int p4 = traceLoop(arr, arr[1], arr[2] ^ 1, arr[arr.length - 1] ^ 1, arr[0]);
        p2 = p2 != Integer.MAX_VALUE ? (p2 + 1) : p2;
        p3 = p3 != Integer.MAX_VALUE ? (p3 + 1) : p3;
        p4 = p4 != Integer.MAX_VALUE ? (p4 + 2) : p4;
        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }

    // 生成长度为len的随机数组，值只有0和1两种值
    public static int[] randomArray(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 2);
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println("如果没有任何Oops打印，说明所有方法都正确");
        System.out.println("test begin");
        int testTime = 20000;
        int lenMax = 12;
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * lenMax);
            int[] arr = randomArray(len);
            int ans1 = noLoopRight(arr);
            int ans2 = noLoopMinStep1(arr);
            int ans3 = noLoopMinStep2(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("1 Oops!");
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * lenMax);
            int[] arr = randomArray(len);
            int ans1 = loopRight(arr);
            int ans2 = loopMinStep1(arr);
            int ans3 = loopMinStep2(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("2 Oops!");
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("test end");

        int len = 100000000;
        System.out.println("性能测试");
        System.out.println("数组大小：" + len);
        int[] arr = randomArray(len);
        long start = 0;
        long end = 0;
        start = System.currentTimeMillis();
        noLoopMinStep2(arr);
        end = System.currentTimeMillis();
        System.out.println("noLoopMinStep2 run time: " + (end - start) + "(ms)");

        start = System.currentTimeMillis();
        loopMinStep2(arr);
        end = System.currentTimeMillis();
        System.out.println("loopMinStep2 run time: " + (end - start) + "(ms)");
    }
}
