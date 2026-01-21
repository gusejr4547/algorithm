package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 원소의_합이_0 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        HashMap<Long, Long> A = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long v = Integer.parseInt(st.nextToken());
            A.put(v, A.getOrDefault(v, 0L) + 1);
        }
        HashMap<Long, Long> B = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long v = Integer.parseInt(st.nextToken());
            B.put(v, B.getOrDefault(v, 0L) + 1);
        }
        HashMap<Long, Long> C = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long v = Integer.parseInt(st.nextToken());
            C.put(v, C.getOrDefault(v, 0L) + 1);
        }
        HashMap<Long, Long> D = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long v = Integer.parseInt(st.nextToken());
            D.put(v, D.getOrDefault(v, 0L) + 1);
        }

        // A+B
        HashMap<Long, Long> AB = new HashMap<>();
        for (long ak : A.keySet()) {
            long aCnt = A.get(ak);
            for (long bk : B.keySet()) {
                long bCnt = B.get(bk);
                AB.put(ak + bk, AB.getOrDefault(ak + bk, 0L) + (aCnt * bCnt));
            }
        }

        // C+D
        HashMap<Long, Long> CD = new HashMap<>();
        for (long ck : C.keySet()) {
            long cCnt = C.get(ck);
            for (long dk : D.keySet()) {
                long dCnt = D.get(dk);
                CD.put(ck + dk, CD.getOrDefault(ck + dk, 0L) + (cCnt * dCnt));
            }
        }

        // AB + CD
        long answer = 0;
        for (long abk : AB.keySet()) {
            long target = abk * -1;
            if (CD.containsKey(target)) {
                answer += AB.get(abk) * CD.get(target);
            }
        }

        System.out.println(answer);
    }
}
