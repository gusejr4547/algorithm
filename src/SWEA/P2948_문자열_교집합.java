package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class P2948_문자열_교집합 {
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            String[] a = br.readLine().split(" ");
            String[] b = br.readLine().split(" ");

            Set<String> aSet = new HashSet<>();
            for (int i = 0; i < N; i++) {
                aSet.add(a[i]);
            }

            int result = 0;
            for (int i = 0; i < M; i++) {
                if (aSet.contains(b[i])) {
                    result++;
                }
            }

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
    }
}
