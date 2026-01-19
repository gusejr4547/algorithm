package 백준._20260116;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C_39420 {
    // 0 ~ 9 정수로 이루어진 NxM 행렬 주어짐
    // 여기서 그릴 수 있는 직사각형 N+1C2 x M+1C2 개 중
    // 모든 숫자가 다른 직사각형 몇개?

    // N, M <= 1000
    static int N, M;
    static int[][] matrix;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = str.charAt(j) - '0';
            }
        }

        long answer = 0;
        // 숫자 10개니까 10개 이상 칸은 무조건 겹침
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 사각형 세로
                for (int h = 1; h <= 10 && i + h - 1 < N; h++) {
                    // 사각형 가로
                    for (int w = 1; h * w <= 10 && j + w - 1 < M; w++) {
                        int mask = 0;
                        boolean isOk = true;

                        // 내부 계산
                        if (check(i, j, h, w)) {
                            answer++;
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean check(int r, int c, int h, int w) {
        int mask = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int v = matrix[r + i][c + j];

                // & 계산했는데 0이 아니면 이미 등장한 숫자
                if ((mask & (1 << v)) != 0) {
                    return false;
                }

                // 방문처리
                mask |= (1 << v);
            }
        }

        return true;
    }

}
