package 백준._20251220;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_오름차순과_비내림차순 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long prev = Long.MAX_VALUE;
        int answer = 1;
        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(st.nextToken());
            if (prev == num) {
                answer = 0;
                break;
            }
            prev = num;
        }

        System.out.println(answer);
    }
}
