package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P19237_어른_상어 {
    static int N, M, K;
    static int[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][][] priority;
    static Shark[] sharks;
    static Smell[][] smells;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        sharks = new Shark[M + 1];
        smells = new Smell[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) {
                    sharks[map[i][j]] = new Shark(0, i, j);
                    smells[i][j] = new Smell(map[i][j], K);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            sharks[i].direction = Integer.parseInt(st.nextToken()) - 1;
        }

        priority = new int[M + 1][4][4];
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    priority[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        System.out.println(solution());
    }

    static int solution() {
        int answer = 0;
        while (answer <= 1000) {
            if (check()) {
                return answer;
            }

            move();
            decreaseSmell();
            createSmell();

            answer++;
        }

        return -1;
    }

    static boolean check() {
        for (int m = 2; m <= M; m++) {
            if (sharks[m] != null) {
                return false;
            }
        }
        return true;
    }

    static void move() {
        for (int m = 1; m <= M; m++) {
            if (sharks[m] == null) {
                continue;
            }

            Shark shark = sharks[m];
            int dir = shark.direction;
            boolean phase1 = false;

            for (int i = 0; i < 4; i++) {
                int nextDir = priority[m][dir][i];
                int ny = shark.y + dy[i];
                int nx = shark.x + dx[i];
                if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                    continue;
                }
                if (smells[ny][nx] == null) {
                    dir = nextDir;
                    phase1 = true;
                    break;
                }
            }

            if (!phase1) {
                for (int i = 0; i < 4; i++) {
                    int nextDir = priority[m][dir][i];
                    int ny = shark.y + dy[i];
                    int nx = shark.x + dx[i];
                    if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                        continue;
                    }
                    if (smells[ny][nx].sharkNumber == m) {
                        dir = nextDir;
                        break;
                    }
                }
            }

            int ny = shark.y + dy[dir];
            int nx = shark.x + dx[dir];
            shark.direction = dir;

            if (map[ny][nx] == 0) {
                map[ny][nx] = m;
                map[shark.y][shark.x] = 0;
                shark.y = ny;
                shark.x = nx;
            } else if (map[ny][nx] >= m) {
                map[ny][nx] = m;
                map[shark.y][shark.x] = 0;
                shark.y = ny;
                shark.x = nx;
            } else if (map[ny][nx] < m) {
                sharks[m] = null;
                map[shark.y][shark.x] = 0;
            }
        }
    }


    static void decreaseSmell() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (smells[i][j] != null && smells[i][j].time != 0) {
                    smells[i][j].time--;
                }
            }
        }
    }

    static void createSmell() {
        for (int m = 1; m <= M; m++) {
            Shark shark = sharks[m];
            if (shark != null) {
                smells[shark.y][shark.x] = new Smell(m, K);
            }
        }
    }

    static class Smell {
        int sharkNumber;
        int time;

        public Smell(int sharkNumber, int time) {
            this.sharkNumber = sharkNumber;
            this.time = time;
        }
    }

    static class Shark {
        int direction;
        int y;
        int x;

        public Shark(int direction, int y, int x) {
            this.direction = direction;
            this.y = y;
            this.x = x;
        }
    }
}
