package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P34924_PAUL_문자열_Easy {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        String target = "PAUL";
        boolean canMake = true;
        int tIdx = 0;
        int prev = 0;
        for (int i = 0; i < N && tIdx < 4; i++) {
            char c = str.charAt(i);
            if (target.charAt(tIdx) == c) {
                int inter = i - prev;
                if (inter % 2 != 0) {
                    canMake = false;
                    break;
                }
                prev = i + 1;
                tIdx++;
            }
        }

        // tIdx == 4가 아니면 글자 못만듬
        if (tIdx != 4) {
            canMake = false;
        } else {
            // 글자 만들 수 있는데 'L' 부터 끝까지 2의 배수길이 남아야함
            int inter = N - prev;
            if (inter % 2 != 0) {
                canMake = false;
            }
        }
        System.out.println(canMake ? "YES" : "NO");
    }
}
