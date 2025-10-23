package 프로그래머스.KAKAO_BLIND_RECRUITMENT_2023;

public class 미로_탈출_명령어 {
    public static void main(String[] args) {
        미로_탈출_명령어 Main = new 미로_탈출_명령어();
        System.out.println(Main.solution(3, 4, 2, 3, 3, 1, 5));
    }

    // 사전순 d l r u
    int N, M, R, C, K;
    String answer = "impossible";
    boolean[][][] visit;
    String[] dir = {"d", "l", "r", "u"};
    int[] dy = {1, 0, 0, -1};
    int[] dx = {0, -1, 1, 0};

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        R = r;
        C = c;
        K = k;
        visit = new boolean[N + 1][M + 1][K + 1];

        StringBuilder path = new StringBuilder();
        dfs(x, y, k, path);
        return answer;
    }

    private void dfs(int y, int x, int remain, StringBuilder path) {
        // 이미 정답 구한 경우
        if (!answer.equals("impossible")) {
            return;
        }

        // 남은 이동이 없음
        if (remain == 0) {
            if (y == R && x == C) {
                answer = path.toString();
            }
            return;
        }

        // 이동거리
        int dist = Math.abs(y - R) + Math.abs(x - C);
        if (dist > remain || (remain - dist) % 2 != 0) {
            return;
        }

        // 이미 방문한 위치
        if (visit[y][x][remain]) {
            return;
        }
        visit[y][x][remain] = true;

        // d l r u 순서로
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (ny < 1 || nx < 1 || ny > N || nx > M) {
                continue;
            }

            path.append(dir[d]);
            dfs(ny, nx, remain - 1, path);
            path.deleteCharAt(path.length() - 1); // 추가한 path 지우기
        }
    }


//    int[] dy = {1, 0, 0, -1};
//    int[] dx = {0, -1, 1, 0};
//    final int DOWN = 0;
//    final int LEFT = 1;
//    final int RIGHT = 2;
//    final int UP = 3;
//
//    public String solution(int n, int m, int x, int y, int r, int c, int k) {
//        StringBuilder answer = new StringBuilder();
//        int distance = Math.abs(x - r) + Math.abs(y - c);
//        // 해답 없음
//        if (distance > k || (k - distance) % 2 != 0) {
//            return "impossible";
//        }
//
//        int[] directionCount = new int[4]; // d l r u
//        // 최단거리
//        if (x < r) {
//            directionCount[DOWN] = r - x;
//        } else {
//            directionCount[UP] = x - r;
//        }
//        if (y < c) {
//            directionCount[RIGHT] = c - y;
//        } else {
//            directionCount[LEFT] = y - c;
//        }
//        k = k - distance;
//
//        // d
//        answer.append("d".repeat(directionCount[DOWN]));
//        int moreDown = Math.min(k / 2, n - (x + directionCount[DOWN]));
//        directionCount[UP] += moreDown;
//        answer.append("d".repeat(moreDown));
//        k = k - moreDown * 2;
//
//        // l
//        answer.append("l".repeat(directionCount[LEFT]));
//        int moreLeft = Math.min(k / 2, y - directionCount[LEFT] - 1);
//        directionCount[RIGHT] += moreLeft;
//        answer.append("l".repeat(moreLeft));
//        k = k - moreLeft * 2;
//
//        // rl 남은 k 여기다 사용
//        answer.append("rl".repeat(k / 2));
//
//        // r
//        answer.append("r".repeat(directionCount[RIGHT]));
//
//        // u
//        answer.append("u".repeat(directionCount[UP]));
//
//        return answer.toString();
//    }
}
