package dachang.class08;

public class Code_2_ContainerWithMostWater {

    public static int maxArea1(int[] h) {
        int max = 0;
        int n = h.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                max = Math.max(max, Math.min(h[i], h[j]) * (j - i));
            }
        }
        return max;
    }
    public static int maxArea2(int[] height){
        int max=0;
        int n= height.length;
        int l=0;
        int r=n-1;
        while (l<r){
            max=Math.max(max,Math.min(height[l],height[r])*(r-l));
            if (height[l]<height[r]){
                l++;
            }else {
                r--;
            }
        }
        return max;
    }
}
