package 코드트리.HSAT;

import java.util.*;

public class 배터리_효율_최적화_하기 {
    static int N, M, answer;
    static int[][] board;
    static boolean[] visit;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static List<Integer> validCell;
    static Set<List<Integer>> batterySet;
    static List<List<Integer>> batteryList;
    static List<Integer> currentCell;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        // N, M <= 10, N*M <= 16
        // 모듈 1개 당 5셀 => 인접하는 5칸
        // 2개의 셀이 겹친다. 겹치는 부분은 점수를 2배 얻을 수 있다.
        answer = Integer.MIN_VALUE;
        visit = new boolean[N * M];
        validCell = new ArrayList<>();
        batterySet = new HashSet<>();
        batteryList = new ArrayList<>();
        currentCell = new ArrayList<>();
        // 모든 모듈을 생성한다
        findAllModule();

        // 모듈 2개를 비교해서 겹치는 셀이 2개인 것 찾아서 => 점수계산
        for (int i = 0; i < batteryList.size(); i++) {
            for (int j = i + 1; j < batteryList.size(); j++) {
                List<Integer> battery1 = batteryList.get(i);
                List<Integer> battery2 = batteryList.get(j);
                int sameCnt = 0;
                for (int a = 0; a < 5; a++) {
                    for (int b = 0; b < 5; b++) {
                        if (battery1.get(a).equals(battery2.get(b))) {
                            sameCnt++;
                        }
                    }
                }

                // 2개 겹친 경우
                if (sameCnt == 2) {
                    int sum = 0;
                    for (int a = 0; a < 5; a++) {
                        int num = battery1.get(a);
                        int[] pos = reconv(num);
                        int y = pos[0];
                        int x = pos[1];
                        sum += board[y][x];
                    }
                    for (int a = 0; a < 5; a++) {
                        int num = battery2.get(a);
                        int[] pos = reconv(num);
                        int y = pos[0];
                        int x = pos[1];
                        sum += board[y][x];
                    }

                    answer = Math.max(answer, sum);
                }
            }
        }

        System.out.println(answer);
    }

    private static void make(int count) {
        if (count == 5) {
            List<Integer> module = new ArrayList<>(currentCell);
            module.sort((o1, o2) -> o1 - o2);
            if (!batterySet.contains(module)) {
                batterySet.add(module);
                batteryList.add(module);
            }
            return;
        }

        for (int i = 0; i < validCell.size(); i++) {
            int num = validCell.get(i);
            int[] pos = reconv(num);
            int y = pos[0];
            int x = pos[1];

            if(visit[num]){
                continue;
            }
            visit[num] = true;
            currentCell.add(num);
            // num을 선택
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
                    continue;
                }

                validCell.add(conv(ny, nx));
            }

            make(count + 1);

            // 선택했던거 원상복구
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
                    continue;
                }

                validCell.remove(validCell.size() - 1);
            }

            visit[num] = false;
            currentCell.remove(currentCell.size() - 1);
        }
    }

    private static void findAllModule() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                validCell.clear();
                currentCell.clear();

                int num = conv(i, j);
                visit[num] = true;
                currentCell.add(num);

                for (int d = 0; d < 4; d++) {
                    int ny = i + dy[d];
                    int nx = j + dx[d];
                    if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[conv(ny, nx)]) {
                        continue;
                    }
                    validCell.add(conv(ny, nx));
                }

                make(1);

                visit[num] = false;
            }
        }
    }

    // 좌표 변경
    private static int conv(int i, int j) {
        return i * M + j;
    }

    private static int[] reconv(int pos) {
        return new int[]{pos / M, pos % M};
    }
}

