package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 정답이 3보다 큰 값이면 -1을 출력한다. 또, 불가능한 경우에도 -1을 출력한다.
public class P15684_사다리_조작 {
    static int N, M, H, answer;
    static int[][] map;
    static boolean finish;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1;
            map[a][b + 1] = 2;
        }

        answer = -1;
        finish = false;
        for (int i = 0; i <= 3; i++) {
            addLine(i, 0, 1);
            if (finish) break;
        }

        System.out.println(finish ? answer : -1);
    }

    // 선 추가해보기
    private static void addLine(int totalAddLineNum, int addLineNum, int row) {
        // 정답 구했을때 함수 종료시킬 조건
        if (finish) {
            return;
        }

        if (totalAddLineNum == addLineNum) {
            if (check()) {
                answer = totalAddLineNum;
                finish = true;
            }
            return;
        }

        for (int i = row; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (map[i][j] == 0 && map[i][j + 1] == 0) {
                    map[i][j] = 1;
                    map[i][j+1] = 2;
                    addLine(totalAddLineNum, addLineNum + 1, i);
                    map[i][j] = 0;
                    map[i][j + 1] = 0;
                }
            }
        }
    }

    // 정답인지 확인
    private static boolean check() {
        for (int i = 1; i <= N; i++) {
            int row = 1;
            int col = i;
            while (row <= H) {
                if (map[row][col] == 1) {
                    col++;
                } else if (map[row][col] == 2) {
                    col--;
                }
                row++;
            }
//            for (int j = 0; j < H; j++) {
//
//            }
            if (col != i)
                return false;
        }
        return true;
    }
}
