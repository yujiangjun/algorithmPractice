package prefixtree;

import java.util.Arrays;

public class Code4_RadixSort {

    public static void radixSort(int[] arr){
        if (arr==null || arr.length<2){
            return;
        }
        radixSort(arr,0,arr.length-1,maxbits(arr));
    }

    /**
     * 计算arr数组中最大值的位数
     * @param arr
     * @return
     */
    public static int maxbits(int[] arr){
        int max=Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max=Math.max(max,arr[i]);
        }
        int res=0;
        while (max!=0){
            res++;
            max/=10;
        }
        return res;
    }

    /**
     * 在L-R范围你进行排序
     * @param arr
     * @param L
     * @param R
     * @param digit 最大值的位数
     */
    public static void radixSort(int[] arr,int L,int R,int digit){
        final int radix=10;
        int i=0,j=0;
        int[] help=new int[R-L+1];
        for (int d = 1; d <=digit; d++) {
            /**
             * 10个空间
             * count[0]表示当前位是0的数字有多少
             * count[1]表示当前位是0和1的数字有多少
             * 以此类推
             */
            int[] count=new int[radix];
            for ( i = L; i <= R; i++) {
                j=getDigit(arr[i],d);
                count[j]++;
            }
            for (i = 1;  i<radix ; i++) {
                count[i]=count[i]+count[i-1];
            }
            for (i=R;i>=L;i--){
                j=getDigit(arr[i],d);
                help[count[j]-1]=arr[i];
                count[j]--;
            }
            for (i = L,j=0;  i<=R ; i++,j++) {
                arr[i]=help[j];
            }
        }
    }

    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }


    //test
    public static void comparator(int[] arr){
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        radixSort(arr);
        printArray(arr);

    }
}
