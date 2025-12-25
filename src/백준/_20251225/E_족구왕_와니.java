package 백준._20251225;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class E_족구왕_와니 {
    // 자기 위치 -> 목표 좌표 , 직선거리가 최대 반경 이하... 팀원
    // 없으면 공 놓침 => 다음 공을 안참.
    // 있으면 실력 제일 높은 사람이 참

    // 와니 공 몇번 찼나?

    // 좌표 0<= x,y,r <= 10000
    // 거리 계산 제곱해도 100 000 000

    static Stat[] team;
    static Point[] target;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        // 첫번째는 와니
        team = new Stat[N];
        // 팀원 고정 위치, 최대 반경, 실력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            team[i] = new Stat(x, y, r, s);
        }
        target = new Point[M];
        // 공 목표 좌표
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int tx = Integer.parseInt(st.nextToken());
            int ty = Integer.parseInt(st.nextToken());
            target[i] = new Point(tx, ty);
        }

        int answer = 0;
        //100 * 1000 = 100 000
        int i = 0;
        while (i < M) {
            int kickIdx = -1;
            int maxS = -1;

            for (int j = 0; j < N; j++) {
                // 찰수있는 사람? = 직선거리가 최대 반경 이하
                if (getDist(target[i], team[j].x, team[j].y) <= team[j].r) {
                    // 실력 높은 사람이 참
                    if (maxS < team[j].s) {
                        kickIdx = j;
                        maxS = team[j].s;
                    }
                }
            }

            // 없으면 => 다음공 넘어감
            if (kickIdx == -1) {
                i++;
            }
            // 있으면 => 0번이 차는지 확인
            else {
                if (kickIdx == 0) {
                    answer++;
                }
            }
            i++;
        }

        System.out.println(answer);
    }

    private static double getDist(Point t, int x, int y) {
        return Math.sqrt((t.x - x) * (t.x - x) + (t.y - y) * (t.y - y));
    }

    private static class Stat {
        int x, y, r, s;

        public Stat(int x, int y, int r, int s) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.s = s;
        }
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
