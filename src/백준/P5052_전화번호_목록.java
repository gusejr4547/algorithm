package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P5052_전화번호_목록 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            int N = Integer.parseInt(br.readLine());
            String[] phoneNums = new String[N];
            for (int i = 0; i < N; i++) {
                phoneNums[i] = br.readLine();
            }

            Arrays.sort(phoneNums);
            boolean consistent = true;
            for (int i = 0; i < N - 1; i++) {
                if (phoneNums[i + 1].startsWith(phoneNums[i])) {
                    consistent = false;
                    break;
                }
            }

            if (consistent) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
