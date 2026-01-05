package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P34928_PAUL_문자열_Hard {
    static int N;
    static String str;
    static boolean answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        str = br.readLine();

        // 길이를 2씩 줄여서 4(PAUL)로 만들어야 하므로, N은 짝수여야 함
        if (N % 2 != 0) {
            System.out.println("NO");
            return;
        }

        String PAUL = "PAUL";
        int idx = 0;
        // 원본 문자열에서 짝수 인덱스의 P, 홀수 인덱스의 A, 짝수 인덱스의 U, 홀수 인덱스의 L을 순서대로 찾을 수 있다면 가능
        for (int i = 0; i < N && idx < 4; i++) {
            if (PAUL.charAt(idx) == str.charAt(i) && i % 2 == idx % 2) {
                idx++;
            }
        }

        System.out.println(idx == 4 ? "YES" : "NO");
    }
}
