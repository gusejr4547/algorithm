package 프로그래머스;

import java.util.*;

public class 미로_탈출LV4 {
    public static void main(String[] args) {
        미로_탈출LV4 Main = new 미로_탈출LV4();
        int n = 3;
        int start = 1;
        int end = 3;
        int[][] roads = {{1, 2, 2}, {3, 2, 3}};
        int[] traps = {2};
        System.out.println(Main.solution(n, start, end, roads, traps));
    }

    // 서로 다른 두 방 사이에 직접 연결된 길이 여러 개 존재할 수도 있습니다.
    // 함정 방으로 이동하는 순간, 이동한 함정 방과 연결되어있는 모든 길의 방향이 반대가 됩니다.
    // 여러 번 방문하여 계속 길의 방향을 반대로 뒤집을 수 있습니다.
    Map<Integer, Integer> trapMap; // 함정노드번호 넣으면 몇번째 비트인지 알려줌
    int[][] adj;
    boolean[][] visit;
    int N;

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        N = n;
        trapMap = new HashMap<>();
        for (int i = 0; i < traps.length; i++) {
            trapMap.put(traps[i], i);
        }

        adj = new int[N + 1][N + 1];
        for (int[] a : adj) {
            Arrays.fill(a, Integer.MAX_VALUE);
        }
        for (int[] road : roads) {
            int P = road[0];
            int Q = road[1];
            int S = road[2];
            // 서로 다른 두 방 사이에 길이 여러 개 존재할 경우 최소값 저장
            adj[P][Q] = Math.min(adj[P][Q], S);
        }

        // visit[노드][방문트랩] 2^traps.length만큼 경우의 수 존재
        visit = new boolean[N + 1][1 << traps.length];


        int answer = findPath(start, end);
        return answer;
    }

    private int findPath(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.offer(new Node(start, 0, 0));
//        int[] distance = new int[N + 1];
//        Arrays.fill(distance, Integer.MAX_VALUE);
//        distance[start] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.num == end) {
                return cur.cost;
            }

            // 현재 방문 노드가 함정인가?
            boolean isTrap = false;
            if (trapMap.containsKey(cur.num)) {
                isTrap = true;
                int bitIdx = trapMap.get(cur.num);
                cur.trapInfo ^= 1 << bitIdx;
            }

            // 방문체크
            if (visit[cur.num][cur.trapInfo]) {
                continue;
            }
            visit[cur.num][cur.trapInfo] = true;

            // 다음 노드로 이동
            for (int next = 1; next <= N; next++) {
                if (next == cur.num) continue;
                // next 함정?
                boolean isNextTrap = trapMap.containsKey(next);
                // 현재 정점이 함정? 다음 정점이 함정?
                if (isTrap && isNextTrap) {
                    boolean curVisit = (cur.trapInfo & (1 << trapMap.get(cur.num))) > 0;
                    boolean nextVisit = (cur.trapInfo & (1 << trapMap.get(next))) > 0;

                    if (curVisit ^ nextVisit) {
                        // 역방향
                        if (adj[next][cur.num] != Integer.MAX_VALUE) {
                            pq.offer(new Node(next, cur.trapInfo, cur.cost + adj[next][cur.num]));
                        }
                    } else {
                        // 정방향
                        if (adj[cur.num][next] != Integer.MAX_VALUE) {
                            pq.offer(new Node(next, cur.trapInfo, cur.cost + adj[cur.num][next]));
                        }
                    }
                }
                if (isTrap && !isNextTrap) {
                    boolean curVisit = (cur.trapInfo & (1 << trapMap.get(cur.num))) > 0;

                    if (curVisit) {
                        // 역방향
                        if (adj[next][cur.num] != Integer.MAX_VALUE) {
                            pq.offer(new Node(next, cur.trapInfo, cur.cost + adj[next][cur.num]));
                        }
                    } else {
                        // 정방향
                        if (adj[cur.num][next] != Integer.MAX_VALUE) {
                            pq.offer(new Node(next, cur.trapInfo, cur.cost + adj[cur.num][next]));
                        }
                    }
                }
                if (!isTrap && isNextTrap) {
                    boolean nextVisit = (cur.trapInfo & (1 << trapMap.get(next))) > 0;

                    if (nextVisit) {
                        // 역방향
                        if (adj[next][cur.num] != Integer.MAX_VALUE) {
                            pq.offer(new Node(next, cur.trapInfo, cur.cost + adj[next][cur.num]));
                        }
                    } else {
                        // 정방향
                        if (adj[cur.num][next] != Integer.MAX_VALUE) {
                            pq.offer(new Node(next, cur.trapInfo, cur.cost + adj[cur.num][next]));
                        }
                    }
                }
                // 둘다 함정 아니면 갈수있으면 그냥 ㄱ
                if (!isTrap && !isNextTrap && adj[cur.num][next] != Integer.MAX_VALUE) {
                    pq.offer(new Node(next, cur.trapInfo, cur.cost + adj[cur.num][next]));
                }
            }
        }
        return -1;
    }

    private class Node {
        int num;
        int trapInfo;
        int cost;

        public Node(int num, int trapInfo, int cost) {
            this.num = num;
            this.trapInfo = trapInfo;
            this.cost = cost;
        }
    }

}
