package 프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 몸짱_트레이너_라이언의_고민 {
    public static void main(String[] args) {
        몸짱_트레이너_라이언의_고민 Main = new 몸짱_트레이너_라이언의_고민();
        int n = 3;
        int m = 2;
        int[][] timetable = {{1170, 1210}, {1200, 1260}};
        System.out.println(Main.solution(n, m, timetable));
    }

    int N;

    public int solution(int n, int m, int[][] timetable) {
        N = n;
        Arrays.sort(timetable, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<Integer> endTimePQ = new PriorityQueue<>();
        int time = 0;
        int maxP = 0;
        for (int[] res : timetable) {
            time = res[0];
            endTimePQ.offer(res[1]);

            while (!endTimePQ.isEmpty() && endTimePQ.peek() < time) {
                endTimePQ.poll();
            }

            maxP = Math.max(maxP, endTimePQ.size());
        }

//        System.out.println(maxP);

        // 겹치는 손님 없음.
        if (maxP == 1) {
            return 0;
        }

        // 최소 거리 몇이지?
        int min = 1;
        int max = n + n - 2; // 정사각형 끝점 => 끝점 거리
        int answer = 0;
        while (min <= max) {
            int mid = (min + max) / 2;

            // mid 최소 거리로 배치가 되나?
            System.out.println("############" + mid +"##############");
            boolean valid = canPlace(mid, maxP);

            if (valid) {
                min = mid + 1;
                answer = mid;
            } else {
                max = mid - 1;
            }
        }

        return answer;
    }


    private boolean canPlace(int dist, int maxP) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                List<Pos> uses = new ArrayList<>();
                uses.add(new Pos(i, j));

                for (int y = i; y < N; y++) {
                    for (int x = i == y ? j + 1 : 0; x < N; x++) {
                        // (y,x) 놓을 수 있는 곳인가?
                        boolean valid = true;
                        for (Pos p : uses) {
                            int diff = Math.abs(p.y - y) + Math.abs(p.x - x);
                            if (diff < dist) {
                                valid = false;
                                break;
                            }
                        }

                        if (valid) {
                            uses.add(new Pos(y, x));
                            if (uses.size() == maxP) {
                                return true;
                            }
                        }
                    }
                }

//                if (dfs(i, j, 0, maxP, dist, new Pos[maxP])) {
//                    return true;
//                }
            }
        }

        return false;
    }

    private boolean dfs(int y, int x, int count, int maxP, int dist, Pos[] uses) {
        uses[count] = new Pos(y, x);

        if (count == maxP - 1) {
            System.out.println(Arrays.toString(uses));
            return true;
        }

        // 다음 dist만큼 떨어진 장소 구하기
        boolean result = false;
        for (int i = y; i < N; i++) {
            for (int j = i == y ? x + 1 : 0; j < N; j++) {
                int d = Math.abs(y - i) + Math.abs(x - j);
                if (d == dist) {
                    // 여기 배치하면 dist안에 다른 거 없나?
                    boolean valid = true;
                    for (int k = 0; k <= count; k++) {
                        int diff = Math.abs(uses[k].y - i) + Math.abs(uses[k].x - j);
                        if (diff < dist) {
                            valid = false;
                            break;
                        }
                    }
                    if (valid) {
                        result = result || dfs(i, j, count + 1, maxP, dist, uses);
                    }
                }

                if (result) {
                    break;
                }
            }
        }

        return result;
    }

    private class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "y=" + y +
                    ", x=" + x +
                    '}';
        }
    }
}
