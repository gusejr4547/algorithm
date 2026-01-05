package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P34923_꼬마_미타 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String alarmClock = br.readLine();
        String wallClock = br.readLine();

        int a = time2Int(alarmClock);
        int b = time2Int(wallClock);

        int diff = Math.min(a - b < 0 ? a - b + 12 * 60 : a - b,
                b - a < 0 ? b - a + 12 * 60 : b - a);

        System.out.println(diff * 360 / 60);
    }

    private static int time2Int(String hhmm) {
        String[] ss = hhmm.split(":");
        return Integer.parseInt(ss[0]) * 60 + Integer.parseInt(ss[1]);
    }
}
