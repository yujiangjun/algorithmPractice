package dachang.class12;

import java.util.Arrays;
import java.util.Objects;

public class Code01_ContainAllCharExactly {

    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) {
            return false;
        }

        char[] str1 = s1.toCharArray();
        Arrays.sort(str1);
        String str1Aim = String.valueOf(str1);
        for (int l = 0; l < s2.length(); l++) {
            for (int r = l; r < s2.length(); r++) {
                String sub = s2.substring(l, r + 1);
                char[] subStr = sub.toCharArray();
                Arrays.sort(subStr);
                String newSubStr = String.valueOf(subStr);
                if (Objects.equals(newSubStr, str1Aim)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkInclusion2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) {
            return false;
        }

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        for (int i = 0; i <= str2.length - str1.length; i++) {
            if (isEqual(str2, i, str1)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEqual(char[] str2, int l, char[] str1) {
        int[] count = new int[256];
        for (int i = 0; i < str1.length; i++) {
            count[str1[i]]++;
        }
        for (int i = 0; i < str1.length; i++) {
            if (count[str2[l + i]]-- == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean containExactly3(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length;
        int all = n;
        int r = 0;
        int[] count = new int[256];
        for (char c : str1) {
            count[c - 'a']++;
        }
        for (; r < n; r++) {
            if (count[str2[r]]-- > 0) {
                all--;
            }
        }
        for (; r < str2.length; r++) {
            if (all == 0) {
                return true;
            }
            if (count[str2[r]]-- > 0) {
                all--;
            }
            if (count[str2[r - n]]++ >= 0) {
                all++;
            }
        }
        return all == 0;
    }

    public static boolean containExactly4(String s1,String s2){
        if (s1==null||s2==null||s1.length()>s2.length()){
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        int[] count = new int[256];

        for (char c : str1) {
            count[c]++;
        }

        int winSize= str1.length;

        int r=0;
        for (;r< str1.length;r++){
            if (count[str2[r]]-- >0){
                winSize--;
            }
        }
        for (;r< str2.length;r++){
            if (winSize==0){
                return true;
            }
            if (count[str2[r]]-- >0){
                winSize--;
            }
            if (count[str2[r- str1.length]]++ >=0){
                winSize++;
            }
        }
        return winSize==0;
    }
}
