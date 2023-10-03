package dachang.class31;

public class Problem_0125_ValidPalindrome {


    public static boolean isPalindrome(String s){
        if (s==null||s.isEmpty()){
            return true;
        }

        char[] str = s.toCharArray();
        int l=0;
        int r= str.length-1;

        while (l<=r){
            if (!validChar(str[l])&&!validChar(str[r])){
                l++;
                r--;
            }else if (!validChar(str[l])){
                l++;
            } else if (!validChar(str[r])) {
                r--;
            } else {
                if (!equal(str[l], str[r])){
                    return false;
                }
                l++;
                r--;
            }
        }
        return true;
    }

    public static boolean validChar(char c){
        return (c>='0'&&c<='9')||((c>='a'&&c<='z')||(c>='A'&&c<='Z'));
    }
    public static boolean isDigit(char c){
        return c>='0'&&c<='9';
    }

    public static boolean isLetter(char c){
        return (c>='a'&&c<='z')||(c>='A'&&c<='Z');
    }

    public static boolean equal(char c1,char c2){
        if (isDigit(c1)&&isDigit(c2)){
            return c1==c2;
        } else if (isDigit(c1)||isDigit(c2)) {
            return false;
        }else {
            return c1==c2||Math.max(c1,c2)==Math.min(c1,c2)+32;
        }
    }

    public static void main(String[] args) {
        String s1="A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s1));
        String s2="race a car";
        System.out.println(isPalindrome(s2));
        String s3=" ";
        System.out.println(isPalindrome(s3));
    }
}
