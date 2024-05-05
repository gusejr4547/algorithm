package 프로그래머스.KAKAO_BLIND_RECRUITMENT_2023;

public class 미로_탈출_명령어 {
    public static void main(String[] args) {
        미로_탈출_명령어 Main = new 미로_탈출_명령어();
        System.out.println(Main.solution(3, 4, 2, 3, 3, 1, 5));
    }

    // 사전순 d l r u
    int[] dy = {1, 0, 0, -1};
    int[] dx = {0, -1, 1, 0};
    final int DOWN = 0;
    final int LEFT = 1;
    final int RIGHT = 2;
    final int UP = 3;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        StringBuilder answer = new StringBuilder();
        int distance = Math.abs(x - r) + Math.abs(y - c);
        // 해답 없음
        if (distance > k || (k - distance) % 2 != 0) {
            return "impossible";
        }

        int[] directionCount = new int[4]; // d l r u
        // 최단거리
        if (x < r) {
            directionCount[DOWN] = r - x;
        } else {
            directionCount[UP] = x - r;
        }
        if (y < c) {
            directionCount[RIGHT] = c - y;
        } else {
            directionCount[LEFT] = y - c;
        }
        k = k - distance;

        // d
        answer.append("d".repeat(directionCount[DOWN]));
        int moreDown = Math.min(k / 2, n - (x + directionCount[DOWN]));
        directionCount[UP] += moreDown;
        answer.append("d".repeat(moreDown));
        k = k - moreDown * 2;

        // l
        answer.append("l".repeat(directionCount[LEFT]));
        int moreLeft = Math.min(k / 2, y - directionCount[LEFT] - 1);
        directionCount[RIGHT] += moreLeft;
        answer.append("l".repeat(moreLeft));
        k = k - moreLeft * 2;

        // rl 남은 k 여기다 사용
        answer.append("rl".repeat(k / 2));

        // r
        answer.append("r".repeat(directionCount[RIGHT]));

        // u
        answer.append("u".repeat(directionCount[UP]));

        return answer.toString();
    }
}
