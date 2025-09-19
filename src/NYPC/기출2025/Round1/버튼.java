package NYPC.기출2025.Round1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 버튼 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // N개 버튼 중 K개의 버튼을 순서대로 누르면 자물쇠가 열린다.
        // 첫번째 버튼 알아내기 최대 N개
        // 두번째 버튼 알아내기 첫번째 버튼 + 두번째버튼 N-1개

        long answer = 0;
        int seq = 1;
        int button = N;
        while (seq <= K) {
            // button개수만큼 눌러봐야함.
            // 앞서 누른 버튼은 유지된다.
            answer -= seq - 1;
            answer += (long) button * (1 + (seq - 1));

            button--;
            seq++;
        }

        System.out.println(answer);
    }
}
