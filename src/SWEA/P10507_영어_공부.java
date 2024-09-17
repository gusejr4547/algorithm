package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10507_영어_공부 {
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken()); // 추가로 출석체크 가능 수

            int[] visitDay = new int[n];
            // 공부한 날의 번호
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                visitDay[i] = Integer.parseInt(st.nextToken());
            }

            int score = 0;

            for (int i = 0; i < n; i++) {
                // 시작한 날 idx = i
                int left = i;
                int right = n - 1;

                while (left <= right) {
                    int mid = (left + right) / 2;
                    int blank = (visitDay[mid] - visitDay[i] + 1) - (mid - i + 1); // 전체일수 - 방문일수

                    if (blank <= p) {
                        left = mid + 1;
                        score = Math.max(score, (visitDay[mid] - visitDay[i] + 1) + (p - blank)); // 전체일수 + 추가 일수
                    } else {
                        right = mid - 1;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(score).append("\n");
        }
        System.out.println(sb.toString());
    }
}
