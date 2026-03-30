package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class P5658_보물상자_비밀번호 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            String input = br.readLine();

            // F535 86D7 6286 B2D8
            // 8F53 586D 7628 6B2D
            // D8F5 3586 D762 86B2
            // 2D8F 5358 6D76 286B
            // B2D8 F535 86D7 6286 => 0이랑 동일

            // N/4 만큼 가능하다
            // N <= 28 이므로 전부 해도 될듯?

            int len = N / 4;
            input = input + input.substring(0, len);

            // 내림차순
            List<Integer> arr = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                String hex = input.substring(i, i + len);
                int num = Integer.parseInt(hex, 16);
                if (!arr.contains(num)) {
                    arr.add(num);
                }
            }

            arr.sort((o1, o2) -> Integer.compare(o2, o1));
            sb.append("#").append(test_case).append(' ').append(arr.get(K - 1)).append('\n');
        }
        System.out.println(sb);
    }
}
