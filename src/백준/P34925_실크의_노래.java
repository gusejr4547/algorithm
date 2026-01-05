package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P34925_실크의_노래 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long H = Long.parseLong(st.nextToken());
        long S = Long.parseLong(st.nextToken());

        // 피격 = 체력 -2
        // 회복 = 실크 -1, 체력 +3 | 초과시 최대 체력

        if (H <= 2) {
            System.out.println(1);
        } else if (H <= 4) {
            System.out.println(2 + S);
        } else {
            long ans = H / 2;
            if (H % 2 != 0 && S % 2 == 0) {
                ans++;
            }
            ans += 3 * (S / 2);
            if (S % 2 != 0) {
                ans += 2;
            }
            System.out.println(ans);
        }
    }
}
