package 백준._20260116;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class A_2026 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 제곱근의 수가 1, 11, 101, 1001, ... 이 순서
        // 이걸 제곱하면 1, 121, 10201, 1002001, ... 이 순서

        int N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(1906);
            return;
        }

        StringBuilder sb = new StringBuilder();
        // N길이의 0 문자열에서 양끝 1, 중간 2로 변경
        for (int i = 0; i < N; i++) {
            sb.append('0');
        }

        sb.setCharAt(0, '1');
        sb.setCharAt(N - 1, '1');
        sb.setCharAt(N / 2, '2');

        BigInteger b = new BigInteger(sb.toString());

        System.out.println(b.add(new BigInteger("1905")));
    }
}
