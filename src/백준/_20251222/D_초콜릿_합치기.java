package 백준._20251222;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class D_초콜릿_합치기 {
    // 초콜릿 몇개 골라서 합치기
    // 합치는 초콜릿은 세로길이 모두 같아야함.
    // 세로길이 원하는 만큼 줄일 수 있다.
    // 합친 후 초콜릿 크기 (세로길이)*(가로길이)
    // 최댓값?

    static int[] H, W;
    static List<Choco> chocoList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        H = new int[N];
        W = new int[N];
        chocoList = new ArrayList<>();
        // 세로
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }
        // 가로
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            W[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            chocoList.add(new Choco(H[i], W[i]));
        }

        // 세로는 내가 정한다...
        // 세로보다 작은거는 선택안하고 큰거만 선택해서 가로길이 만든다.

        // 초코 높이 내림차순 정렬
        chocoList.sort(((o1, o2) -> Long.compare(o2.h, o1.h)));

        // 길이 누적합...
        long[] prefixSum = new long[N];
        prefixSum[0] = chocoList.get(0).w;
        for (int i = 1; i < N; i++) {
            prefixSum[i] = prefixSum[i - 1] + chocoList.get(i).w;
        }

        long answer = 0;
        // 큰거부터 크기 계산
        for (int i = 0; i < N; i++) {
            long h = chocoList.get(i).h;
            long size = h * prefixSum[i];

            answer = Math.max(answer, size);
        }

        System.out.println(answer);
    }

    private static class Choco {
        long h, w;

        public Choco(long h, long w) {
            this.h = h;
            this.w = w;
        }
    }
}
