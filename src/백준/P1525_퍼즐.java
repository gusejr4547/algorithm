package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P1525_퍼즐 {
    static List<Integer>[] adj;
    static Set<String> visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 0은 상하좌우로 이동(swap) 가능.
        // 2차원 좌표를 1차원으로 변경 => 미리 이동 가능 idx 만들어놓기
        adjInit();
        visit = new HashSet<>();

        // 상태를 string 으로 표현
        // 최종상태가 123456780 이 되면 종료.

        StringBuilder startSB = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                startSB.append(Integer.parseInt(st.nextToken()));
            }
        }
        String start = startSB.toString();

        int answer = -1;
        ArrayDeque<State> queue = new ArrayDeque<>();
        queue.offer(new State(start, 0));
        visit.add(start);

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if ("123456780".equals(cur.state)) {
                answer = cur.moveCount;
                break;
            }

            // 0 찾기?
            int zeroIdx = 0;
            for (int i = 0; i < 9; i++) {
                if (cur.state.charAt(i) == '0') {
                    zeroIdx = i;
                    break;
                }
            }

            // 다음 상태
            for (int next : adj[zeroIdx]) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 9; i++) {
                    if (i == next) {
                        sb.append(cur.state.charAt(zeroIdx));
                    } else if (i == zeroIdx) {
                        sb.append(cur.state.charAt(next));
                    } else {
                        sb.append(cur.state.charAt(i));
                    }
                }

                if (!visit.contains(sb.toString())) {
                    visit.add(sb.toString());
                    queue.offer(new State(sb.toString(), cur.moveCount + 1));
                }
            }
        }

        System.out.println(answer);
    }


    private static void adjInit() {
        adj = new List[9];
        for (int i = 0; i < 9; i++) {
            adj[i] = new ArrayList<>();
        }

        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int d = 0; d < 4; d++) {
                    int ny = i + dy[d];
                    int nx = j + dx[d];
                    if (ny >= 0 && nx >= 0 && ny < 3 && nx < 3) {
                        adj[i * 3 + j].add(ny * 3 + nx);
                    }
                }
            }
        }
    }

    private static class State {
        String state;
        int moveCount;

        public State(String state, int moveCount) {
            this.state = state;
            this.moveCount = moveCount;
        }
    }
}
