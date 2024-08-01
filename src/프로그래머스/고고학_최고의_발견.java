package 프로그래머스;


public class 고고학_최고의_발견 {
    public static void main(String[] args) {
        고고학_최고의_발견 Main = new 고고학_최고의_발견();
        int[][] clockHands = {{0, 3, 3, 0}, {3, 2, 2, 3}, {0, 3, 2, 0}, {0, 3, 3, 3}};
        System.out.println(Main.solution(clockHands));
    }

    // 각 시곗바늘은 시계방향으로만 돌릴 수 있고 한 번의 조작으로 90도씩 돌릴 수 있습니다.
    // 시계의 시곗바늘을 돌리면 그 시계의 상하좌우로 인접한 시계들의 시곗바늘도 함께 돌아갑니다.
    // 모든 시곗바늘이 12시 방향을 가리키면 퍼즐이 해결되어 성궤를 봉인하고 있는 잠금장치가 열릴 것입니다.

    // 0은 12시 방향, 1은 3시 방향, 2는 6시 방향, 3은 9시 방향을 나타냅니다.
    int answer;
    int[][] clock;
    int[] dy = {0, 0, 0, 1, -1};
    int[] dx = {0, 1, -1, 0, 0};

    public int solution(int[][] clockHands) {
        int size = clockHands.length;
        clock = clockHands;
        answer = Integer.MAX_VALUE;
        dfs(new int[size], 0, size);
        return answer;
    }

    private void dfs(int[] rotateArr, int idx, int size) {
        if (idx == size) {
            int[][] map = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    map[i][j] = clock[i][j];
                }
            }
            // 회전
            int cnt = 0;
            int[] nextRotate = rotateArr.clone();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    cnt += nextRotate[j];
                    // 회전
                    for (int d = 0; d < 5; d++) {
                        int y = i + dy[d];
                        int x = j + dx[d];
                        if (y < 0 || x < 0 || y >= size || x >= size)
                            continue;

                        map[y][x] = (map[y][x] + nextRotate[j]) % 4;
                    }
                }
                for (int j = 0; j < size; j++) {
                    nextRotate[j] = (4 - map[i][j]) % 4;
                }
            }

            // 마지막줄 유효? => 전부 0인가?
            boolean valid = true;
            for (int j = 0; j < size; j++) {
                if (map[size - 1][j] != 0) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                answer = Math.min(answer, cnt);
            }
            return;
        }
        for (int rotate = 0; rotate < 4; rotate++) {
            rotateArr[idx] = rotate;
            dfs(rotateArr, idx + 1, size);
        }
    }
}
