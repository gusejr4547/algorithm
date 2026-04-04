package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class P17822_원판_돌리기 {
    // N, M <= 50
    // 1 ≤ 원판에 적힌 수 ≤ 1,000

    static int N, M, T;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int[][] board;
    static int[] rotate;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[N + 1][M];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        rotate = new int[N + 1]; // 각 판의 회전 수
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // x배수인 원판을 d방향으로 k칸 회전
            // d=0 시계방향, 1 반시계
            for (int i = x; i <= N; i += x) {
                int next = (d == 0 ? -1 * k + M : k);
                rotate[i] = (rotate[i] + next) % M;
            }
//            System.out.println(Arrays.toString(rotate));

            // 원판에 숫자가 남아있으면
            // 인접하면서 수가 같은거 찾기
            boolean hasSameNum = false;
            Set<Integer> deleteSet = new HashSet<>();
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {
                    int curI = i;
                    int curJ = (j + rotate[i]) % M;
                    int value = board[curI][curJ];

                    // 현재 위치 0이면 다음 탐색
                    if (value == 0) {
                        continue;
                    }

                    boolean find = false;
                    // 좌
                    int left = (curJ - 1 + M) % M;
                    if (value == board[curI][left]) {
                        find = true;
                        // 지우기
                        deleteSet.add(curI * M + left);
                    }
                    // 우
                    int right = (curJ + 1) % M;
                    if (value == board[curI][right]) {
                        find = true;
                        // 지우기
                        deleteSet.add(curI * M + right);
                    }
                    // 상
                    if (i < N) {
                        int upI = i + 1;
                        int upJ = (j + rotate[upI]) % M;
                        if (value == board[upI][upJ]) {
                            find = true;
                            // 지우기
                            deleteSet.add(upI * M + upJ);
                        }
                    }
                    // 하
                    if (i > 1) {
                        int downI = i - 1;
                        int downJ = (j + rotate[downI]) % M;
                        if (value == board[downI][downJ]) {
                            find = true;
                            // 지우기
                            deleteSet.add(downI * M + downJ);
                        }
                    }

                    // 현재 위치 지우기
                    if (find) {
                        deleteSet.add(curI * M + curJ);
                    }
                }
            }

//            System.out.println(deleteSet);
            // 삭제
            if (!deleteSet.isEmpty()) {
                for (Integer p : deleteSet) {
                    int i = p / M;
                    int j = p % M;
                    board[i][j] = 0;
                }
            }
            // 없으면 원판에 적힌 수의 평균, 평균 보다 큰수 -1, 작은 수 +1
            else {
                int sum = 0;
                int cnt = 0;
                for (int i = 1; i <= N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (board[i][j] != 0) {
                            sum += board[i][j];
                            cnt++;
                        }
                    }
                }

                double avg = (double) sum / cnt;
                for (int i = 1; i <= N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (board[i][j] != 0) {
                            if (avg < board[i][j]) {
                                board[i][j]--;
                            } else if (avg > board[i][j]) {
                                board[i][j]++;
                            }
                        }
                    }
                }
            }
        }

        // 최종 합
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != 0) {
                    answer += board[i][j];
                }
            }
        }

        System.out.println(answer);
    }
}
