package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12969_ABC {
    static int N, K;
    static String answer;
    static boolean[][][][] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visit = new boolean[N + 1][N + 1][N + 1][N * (N - 1) / 2 + 1];
        answer = null;
        find(0, 0, 0, 0, 0, new char[N]);

        System.out.println(answer == null ? "-1" : answer);
    }

    private static void find(int length, int a, int b, int c, int count, char[] arr) {
        if (answer != null) {
            return;
        }

        if (length == N) {
            if (count == K) {
                answer = new String(arr);
            }
            return;
        }

        if (visit[a][b][c][count]) {
            return;
        }
        visit[a][b][c][count] = true;

        arr[length] = 'A';
        find(length + 1, a + 1, b, c, count, arr);
        arr[length] = 'B';
        find(length + 1, a, b + 1, c, count + a, arr);
        arr[length] = 'C';
        find(length + 1, a, b, c + 1, count + a + b, arr);
    }

}
