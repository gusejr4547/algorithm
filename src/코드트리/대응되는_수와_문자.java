package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 대응되는_수와_문자 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] arr = new String[N + 1];
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            arr[i] = br.readLine();
            map.put(arr[i], i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            // 문자
            if (!Character.isDigit(str.charAt(0))) {
                sb.append(map.get(str)).append('\n');
            }
            // 숫자
            if (Character.isDigit(str.charAt(0))) {
                int idx = Integer.parseInt(str);
                sb.append(arr[idx]).append('\n');
            }
        }

        System.out.println(sb);
    }
}
