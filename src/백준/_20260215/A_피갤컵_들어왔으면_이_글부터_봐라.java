package 백준._20260215;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class A_피갤컵_들어왔으면_이_글부터_봐라 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        // 최소한의 글자를 바꿔서 eagle로 만들어야함.
        String eagle = "eagle";
        int minDiff = 5;
        for (int i = 0; i <= N - 5; i++) {
            int diff = 0;
            for (int j = 0; j < 5; j++) {
                if (eagle.charAt(j) != str.charAt(i + j)) {
                    diff++;
                }
            }
            minDiff = Math.min(minDiff, diff);
        }

        System.out.println(minDiff);
    }
}
