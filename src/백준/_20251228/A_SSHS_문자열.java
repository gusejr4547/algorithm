package 백준._20251228;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class A_SSHS_문자열 {
    // A문자열은 S와 H로만 이루어져 있다.
    // A의 부분 문자열 중 SSHS의 개수와 A를 뒤집은 문자열의 부분 문자열 중 SSHS의 개수의 합이 길이 N인 모든 문자열 중 최대이다.
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 길이 N의 SSHS 문자열을 만들어야한다

        // 문자열 => SSHS 개수
        // 문자열 reverse => SSHS 개수

        // 최적 문자열
        // SSHSSHSSHSSHSSHSSHSSHS

        StringBuilder sb = new StringBuilder();
        sb.append("S");

        int loop = (N - 1) / 3;
        int seg = (N - 1) % 3;

        sb.append("SHS".repeat(loop)).append("SHS".substring(0, seg));

        System.out.println(sb);
    }
}
