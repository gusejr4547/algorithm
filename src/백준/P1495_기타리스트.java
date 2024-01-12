package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P1495_기타리스트 {
    static int N, S, M;
    static int[] V;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
        }

        int[] vol = new int[M + 1];
        Arrays.fill(vol, -1);
        vol[S] = 0;

        List<Integer> changes = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            changes.clear();
            for (int j = 0; j <= M; j++) {
                if (vol[j] == i - 1) {
                    if (j - V[i] >= 0) {
                        changes.add(j - V[i]);
                    }
                    if (j + V[i] <= M) {
                        changes.add(j + V[i]);
                    }
                }
            }
            for (int change : changes) {
                vol[change] = i;
            }
        }

        int answer = -1;
        for (int i = 0; i <= M; i++) {
            if (vol[i] == N)
                answer = Math.max(answer, i);
        }

        System.out.println(answer);
    }
}
