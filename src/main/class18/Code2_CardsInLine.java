package class18;

public class Code2_CardsInLine {

    public static int win1(int[] arr){
        if (arr==null || arr.length==0){
            return 0;
        }
        int first=f1(arr,0,arr.length-1);
        int second=g1(arr,0,arr.length-1);
        return Math.max(first,second);
    }

    public static int f1(int[] arr,int L,int R){
        if (L==R){
            return arr[L];
        }
        int p1=arr[L]+g1(arr,L+1,R);
        int p2=arr[R]+g1(arr,L,R-1);
        return Math.max(p1,p2);
    }

    public static int g1(int[] arr,int L,int R){
        if (L==R){
            return 0;
        }
        int p1=f1(arr,L+1,R);
        int p2=f1(arr,L,R-1);
        return Math.min(p1,p2);
    }

    public static int win2(int[] arr){
        if (arr==null||arr.length==0){
            return 0;
        }
        int n=arr.length;
        int[][] fmap=new int[n][n];
        int[][] gmap=new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fmap[i][j]=-1;
                gmap[i][j]=-1;
            }
        }
        int first=f2(arr,0,arr.length-1,fmap,gmap);
        int second=g2(arr,0,arr.length-1,fmap,gmap);
        return Math.max(first,second);
    }

    public static int f2(int[] arr,int L,int R,int[][] fmap,int[][] gmap){
        if (fmap[L][R]!=-1){
            return fmap[L][R];
        }
        int ans=0;
        if (L==R){
            ans=arr[L];
        }else {
            int p1=arr[L]+g2(arr,L+1,R,fmap,gmap);
            int p2=arr[R]+g2(arr,L,R-1,fmap,gmap);
            ans=Math.max(p1,p2);
        }
        fmap[L][R]=ans;
        return ans;
    }
    public static int g2(int[] arr,int L,int R,int[][] fmap,int[][] gmap){
        if (gmap[L][R]!=-1){
            return gmap[L][R];
        }
        int ans=0;
        if (L!=R){
            int p1=f2(arr,L+1,R,fmap,gmap);
            int p2=f2(arr,L,R-1,fmap,gmap);
            ans=Math.min(p1,p2);
        }
        gmap[L][R]=ans;
        return ans;
    }

    public static int win3(int[] arr){
        if (arr==null||arr.length==0){
            return 0;
        }
        int n= arr.length;
        int[][] fmap=new int[n][n];
        int[][] gmap= new int[n][n];
        for (int i = 0; i < n; i++) {
            fmap[i][i]=arr[i];
        }
        for (int startCol = 1; startCol < n; startCol++) {
            int L=0;
            int R=startCol;
            while (R<n){
                fmap[L][R]=Math.max(arr[L]+gmap[L+1][R],arr[R]+gmap[L][R-1] );
                gmap[L][R]=Math.min(fmap[L+1][R],fmap[L][R-1]);
                L++;
                R++;
            }
        }
        return Math.max(fmap[0][n-1],gmap[0][n-1]);
    }

    public static void main(String[] args) {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));

    }
}
