package dachang.class25;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code01_IPToCIDR {

    public static Map<Integer, Integer> map = new HashMap<>();

    static {
        map.put(0, 32);
        for (int i = 0; i < 32; i++) {
            map.put(1 << i, i);
        }
    }

    public static int status(String ip) {
        int status = 0;
        int moved = 24;
        for (String s : ip.split("\\.")) {
            status |= Integer.valueOf(s) << moved;
            moved -= 8;
        }
        return status;
    }

    public static int mostRightPower(int cur) {
        return map.get(cur & (-cur));
    }

    public static String content(int status, int power) {
        StringBuilder sb = new StringBuilder();
        for (int move = 24; move >= 0; move-=8) {
            sb.append(((status & (255 << move)) >>> move) + ".");
        }
        sb.setCharAt(sb.length() - 1, '/');
        sb.append(32 - power);
        return sb.toString();
    }

    public static List<String> ipToCIDR(String ip, int n) {
        List<String> ans = new ArrayList<>();
        int maxPower = 0;
        int power = 0;
        int solved = 0;
        int cur = status(ip);
        while (n > 0) {
            maxPower = mostRightPower(cur);
            solved = 1;
            power=0;
            while ((solved << 1) <= n && (power + 1) <= maxPower) {
                power++;
                solved <<= 1;
            }
            ans.add(content(cur, power));
            n -= solved;
            cur += solved;
        }
        return ans;
    }
}
