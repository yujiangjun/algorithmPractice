package dachang.class07;

public class Code03_MaxGap {

    /*
    1. 找到最大值和最小值
    2. 在min-max值之间分成n+1个桶
    3. 将min-max区间等分成n+1份
    4. n个数在n+1个桶中
    5.每个桶维护最大值和最小值
    6.相邻元素的最大差值在桶的最小值和前一个不是空桶的桶的最小值
     */
    public static int maximumGap(int[] nums){
        if (nums==null||nums.length<2){
            return 0;
        }
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;

        for (int num : nums) {
            min=Math.min(min,num);
            max=Math.max(max,num);
        }
        if (max==min){
            return 0;
        }
        int len=nums.length;
        boolean[] has = new boolean[len+1];
        int[] buketMax=new int[len+1];
        int[] buketMin=new int[len+1];
        int bid=0;
        for (int num : nums) {
            bid=bucket(num,len,min,max);
            buketMax[bid]=has[bid]?Math.max(buketMax[bid],num):num;
            buketMin[bid]=has[bid]?Math.min(buketMin[bid],num):num;
            has[bid]=true;
        }
        int res=0;
        int lastMax=buketMax[0];
        for (int i = 1; i <= len; i++) {
            if (has[i]){
                res=Math.max(res,buketMin[i]-lastMax);
                lastMax=buketMax[i];
            }
        }
        return res;
    }

    public static int bucket(int num,int len,int min,int max){
        double bucketRange=(double) (max-min)/len;
        double distance=(double) num-(double) min;
        return (int) (distance/bucketRange);
    }
}
