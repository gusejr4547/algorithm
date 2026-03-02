package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class P1539_이진_검색_트리 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        TreeSet<Integer> set = new TreeSet<>();
        int[] depth = new int[N + 1]; // 0~N 값의 깊이

        long answer = 0;

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            // x 값의 L, R을 set을 이용해 찾음
            Integer L = set.lower(x);
            Integer R = set.higher(x);

            int leftDepth = L != null ? depth[L] : 0;
            int rightDepth = R != null ? depth[R] : 0;

            // 더 깊은 depth + 1
            depth[x] = Math.max(leftDepth, rightDepth) + 1;

            // set에 추가
            set.add(x);
            answer += depth[x];
        }

        System.out.println(answer);
    }
}
