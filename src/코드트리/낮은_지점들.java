package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 낮은_지점들 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> xGrid = new HashMap<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (xGrid.containsKey(x)) {
                int y2 = xGrid.get(x);
                xGrid.put(x, Math.min(y, y2));
            } else {
                xGrid.put(x, y);
            }
        }

        long answer = 0;
        for (int k : xGrid.keySet()) {
            answer += (long) xGrid.get(k);
        }

        System.out.println(answer);
    }
}
