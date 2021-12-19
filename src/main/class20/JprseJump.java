package class20;

public class JprseJump {

    /**
     * 10*9棋盘
     * 从 (0,0)位置经过k步到 (a,b)位置
     * @param a 位置a 坐标
     * @param b 位置b 坐标
     * @param k 需要k步
     * @return
     */
    public static int jump(int a,int b, int k){
        return process(0,0,k,a,b);
    }

    /**
     * 当前坐标x,y
     * 还有rest步
     * 目标 a,b坐标
     * 请同学们自行搜索或者想象一个象棋的棋盘，
     * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
     * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
     * 给你三个 参数 x，y，k
     * 返回“马”从(0,0)位置出发，必须走k步
     * 最后落在(x,y)上的方法数有多少种?
     * @param x
     * @param y
     * @param rest
     * @param a
     * @param b
     * @return
     */
    public static int process(int x,int y,int rest, int a,int b){
        if (x<0||x>9||y<0 || y>8){
            return 0;
        }
        if (rest==0){
            if (x==a&&y==b){
                return 1;
            }else {
                return 0;
            }
        }else {
            int ways=process(x+1,y+2,rest-1,a,b);
            ways+=process(x+2,y+1,rest-1,a,b);
            ways+=process(x+1,y-2,rest-1,a,b);
            ways+=process(x+2,y-1,rest-1,a,b);
            ways+=process(x-1,y+2,rest-1,a,b);
            ways+=process(x-2,y+1,rest-1,a,b);
            ways+=process(x-1,y-2,rest-1,a,b);
            ways+=process(x-2,y-1,rest-1,a,b);
            return ways;
        }
    }

    public static int dp(int a,int b,int k){
        //建立dp表 10row 9 col k+1 level
        int[][][] dp=new int[10][9][k+1];

        dp[a][b][0]=1;
        for (int rest = 1; rest <=k ; rest++) {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 9; y++) {

                    int ways = pick(dp, x + 2, y + 1, rest - 1);
                    ways += pick(dp, x + 1, y + 2, rest - 1);
                    ways += pick(dp, x - 1, y + 2, rest - 1);
                    ways += pick(dp, x - 2, y + 1, rest - 1);
                    ways += pick(dp, x - 2, y - 1, rest - 1);
                    ways += pick(dp, x - 1, y - 2, rest - 1);
                    ways += pick(dp, x + 1, y - 2, rest - 1);
                    ways += pick(dp, x + 2, y - 1, rest - 1);
                    dp[x][y][rest]=ways;
                }
            }
        }
        return dp[0][0][k];
    }

    public static int pick(int[][][] dp,int x,int y,int k){
        if (x<0||x>9||y<0||y>8){
            return 0;
        }
        return dp[x][y][k];
    }


    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
//        System.out.println(ways(x, y, step));
        System.out.println(dp(x, y, step));

        System.out.println(jump(x, y, step));
    }
}
