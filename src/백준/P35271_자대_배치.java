package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P35271_자대_배치 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] A = new int[M + 1]; // i의 티오
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            int third = Integer.parseInt(st.nextToken());
            if (A[first] > 0) {
                sb.append(first);
                A[first]--;
            } else if (A[second] > 0) {
                sb.append(second);
                A[second]--;
            } else if (A[third] > 0) {
                sb.append(third);
                A[third]--;
            } else {
                sb.append(-1);
            }
            sb.append(" ");
        }
        System.out.println(sb);
    }
}
