package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1515_수_이어_쓰기 {
    // 1부터 N까지 수가 이어져야함.
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();

        int answer = 1;
        int idx = 0;

        while (true) {
            String base = Integer.toString(answer);

            for (int i = 0; i < base.length(); i++) {
                if (base.charAt(i) == num.charAt(idx)) {
                    idx++;
                }

                if (idx == num.length()) {
                    break;
                }
            }

            if (idx == num.length()) {
                break;
            }


            answer++;
        }

        System.out.println(answer);
    }

}
