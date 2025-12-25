package 백준._20251225;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class A_생수병_놓기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String morning = br.readLine();
        String evening = br.readLine();

        // N<=150
        boolean diff = false;

        int mCnt = 0;
        int eCnt = 0;
        for (int i = 0; i < N; i++) {
            if (morning.charAt(i) == 'w') {
                mCnt++;
            }
            if (evening.charAt(i) == 'w') {
                eCnt++;
            }

            if (morning.charAt(i) != evening.charAt(i)) {
                diff = true;
            }
        }

        if (mCnt > eCnt) {
            System.out.println("Oryang");
        } else if (mCnt < eCnt) {
            System.out.println("Manners maketh man");
        } else {
            if (diff) {
                System.out.println("Its fine");
            } else {
                System.out.println("Good");
            }
        }
    }

}
