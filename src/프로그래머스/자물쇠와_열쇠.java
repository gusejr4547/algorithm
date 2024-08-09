package 프로그래머스;

import java.util.Arrays;

public class 자물쇠와_열쇠 {
    public static void main(String[] args) {
        자물쇠와_열쇠 Main = new 자물쇠와_열쇠();
        int[][] key = {
                {0, 0, 0},
                {1, 0, 0},
                {0, 1, 1}
        };
        int[][] lock = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };
        System.out.println(Main.solution(key, lock));
    }

    // M<=N
    public boolean solution(int[][] key, int[][] lock) {
        int M = key.length;
        int N = lock.length;

        // 자물쇠 범위 확장 : M-1 만큼 상하좌우로
        int expandN = N + (M - 1) * 2;
        int[][] expansionLock = new int[expandN][expandN];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                expansionLock[M - 1 + i][M - 1 + j] = lock[i][j];
            }
        }

        // 확인 해야될 범위 => M-1 <= y,x < M-1+N
        boolean answer = false;
        for (int rotate = 0; rotate < 4; rotate++) {
            answer = openLock(expansionLock, key, N);
            if (answer) {
                return answer;
            }
            // 회전
            key = rotate90(key);
        }

        return answer;
    }

    private boolean openLock(int[][] lock, int[][] key, int originalLockSize) {
        int N = lock.length;
        int M = key.length;
        for (int i = 0; i <= N - M; i++) {
            for (int j = 0; j <= N - M; j++) {
                // 색칠
                for (int y = 0; y < M; y++) {
                    for (int x = 0; x < M; x++) {
                        lock[i + y][j + x] += key[y][x];
                    }
                }

                if (isFit(lock, M, originalLockSize)) {
                    return true;
                }

                // 지우기
                for (int y = 0; y < M; y++) {
                    for (int x = 0; x < M; x++) {
                        lock[i + y][j + x] -= key[y][x];
                    }
                }
            }
        }
        return false;
    }

    private boolean isFit(int[][] lock, int keySize, int originalLockSize) {
        for (int i = keySize - 1; i < originalLockSize + keySize - 1; i++) {
            for (int j = keySize - 1; j < originalLockSize + keySize - 1; j++) {
                if (lock[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] rotate90(int[][] key) {
        int N = key.length;
        int[][] result = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[j][N - 1 - i] = key[i][j];
            }
        }

        return result;
    }
}
