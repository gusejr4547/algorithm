package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P16198_에너지_모으기 {
    static int N, answer;
    static int[] weights;
    static boolean[] used;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weights = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }
        used = new boolean[N];

        dfs(0, 0);

        System.out.println(answer);
    }

    private static void dfs(int count, int sum) {
        if (count == N - 2) {
            answer = Math.max(answer, sum);
            return;
        }

        // 0,N-1을 제외하고 나머지 중 1개 선택해서 지우기
        for (int i = 1; i < N - 1; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            dfs(count + 1, sum + multi(i));
            used[i] = false;
        }
    }

    private static int multi(int idx) {
        // idx의 양옆의 곱을 구함.
        int left = 0;
        for (int i = idx - 1; i >= 0; i--) {
            if (!used[i]) {
                left = weights[i];
                break;
            }
        }

        int right = 0;
        for (int i = idx + 1; i < N; i++) {
            if (!used[i]) {
                right = weights[i];
                break;
            }
        }
        return left * right;
    }
}
