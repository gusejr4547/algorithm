package 프로그래머스.카카오_2025_하반기_1차;

import java.util.*;

public class 최고_속도 {
    public static void main(String[] args) {

    }

    // 2차원 평면. 1~n번호 도시
    // 도로는 x, y 축에 평행 선분
    // 모든 도로의 정중앙에는 과속 단속 카메라
    // 만약 한 지점에 카메라가 여러 개 있다면, 그중 제한 속도가 가장 낮은 걸로
    // 1번 도시에서 출발하며, 출발 직전에 단 하나의 속도 v를 정하고 도착할 때까지 일정한 속도를 유지합니다.
    // 2 ~ n번 도시 중 한 곳으로 갈 때, 각 도시마다 카메라 제한 속도를 지키면서 이동 가능한 최고 속도를 구해야 합니다.

    // n <= 100 도시 개수
    // m <= 1000 도로 개수
    // 좌표 절대값 <= 1 000 000 000

    int INF = Integer.MAX_VALUE;
    Map<Long, Integer> pointToId = new HashMap<>();
    List<Long> idToPoint = new ArrayList<>();
    Map<Integer, Integer> nodeLimit = new HashMap<>();
    List<Integer>[] adj;

    public int[] solution(int[][] city, int[][] road) {
        // 좌표가 커서 한칸씩 이동은 불가능.
        // 알아야하는 것
        // 도시위치
        // 도로끼리의 교차점
        // 카메라 위치
        // + 도로 끝점?

        // 도로 위에 점들 위치 정렬
        // 점들 연결해서 그래프 구성
        // 다익스트라로 1번 도시에서 출발해서 다른 도시로 가는 최대 속도 구하기

        int N = city.length;
        int M = road.length;
        // 도로에 있는 node 기록
        List<Integer>[] nodeInRoad = new List[M];
        for (int i = 0; i < M; i++) {
            nodeInRoad[i] = new ArrayList<>();
        }

        // x,y의 좌표 class 사용하면 equals랑 hashcode 오버라이딩 해야하는데 불편함
        // x << 32 | y 로 long 타입으로 나타냄

        // 도로끼리 교차점
        for (int i = 0; i < M; i++) {
            int[] a = road[i];
            boolean ah = (a[1] == a[3]);

            // 도로 중간 카메라
            int midX = (a[0] + a[2]) / 2;
            int midY = (a[1] + a[3]) / 2;
            int midId = getId(midX, midY);
            nodeInRoad[i].add(midId);
            nodeLimit.put(midId, Math.min(nodeLimit.get(midId), a[4]));

            for (int j = i + 1; j < M; j++) {
                int[] b = road[j];
                boolean bh = (b[1] == b[3]);

                // 가로 세로
                if (ah && !bh) {
                    if (a[0] <= b[0] && b[0] <= a[2] &&
                            b[1] <= a[1] && a[1] <= b[3]) {
                        int id = getId(b[0], a[1]);
                        nodeInRoad[i].add(id);
                        nodeInRoad[j].add(id);
                    }
                }
                // 세로 가로
                else if (!ah && bh) {
                    if (b[0] <= a[0] && a[0] <= b[2] &&
                            a[1] <= b[1] && b[1] <= a[3]) {
                        int id = getId(a[0], b[1]);
                        nodeInRoad[i].add(id);
                        nodeInRoad[j].add(id);
                    }
                }
                // 가로 가로
                else if (ah && bh) {
                    if (a[1] == b[1]) {
                        if (a[2] == b[0]) {
                            int id = getId(a[2], a[1]);
                            nodeInRoad[i].add(id);
                            nodeInRoad[j].add(id);
                        } else if (a[0] == b[2]) {
                            int id = getId(a[0], a[1]);
                            nodeInRoad[i].add(id);
                            nodeInRoad[j].add(id);
                        }
                    }
                }
                // 세로 세로
                else {
                    if (a[0] == b[0]) {
                        if (a[3] == b[1]) {
                            int id = getId(a[0], a[3]);
                            nodeInRoad[i].add(id);
                            nodeInRoad[j].add(id);
                        } else if (a[1] == b[3]) {
                            int id = getId(a[0], a[1]);
                            nodeInRoad[i].add(id);
                            nodeInRoad[j].add(id);
                        }
                    }
                }
            }
        }

        // 도시 추가
        for (int[] c : city) {
            int x = c[0];
            int y = c[1];
            int id = getId(x, y);

            // 어떤 도로에 있는가?
            for (int i = 0; i < M; i++) {
                int[] r = road[i];
                boolean rh = (r[1] == r[3]);
                // 가로
                if (rh) {
                    if (y == r[1] && r[0] <= x && x <= r[2]) {
                        nodeInRoad[i].add(id);
                    }
                }
                // 세로
                else {
                    if (x == r[0] && r[1] <= y && y <= r[3]) {
                        nodeInRoad[i].add(id);
                    }
                }
            }
        }

        // 정보를 바탕으로 adj 만들기
        int totalNode = idToPoint.size();
        adj = new List[totalNode];
        for (int i = 0; i < totalNode; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            List<Integer> nodes = nodeInRoad[i];
            int[] r = road[i];
            boolean isH = (r[1] == r[3]);

            // 가로는 x좌표 기준 정렬
            if (isH) {
                nodes.sort((o1, o2) -> Integer.compare(getX(idToPoint.get(o1)), getX(idToPoint.get(o2))));
            }
            // 세로는 y좌표 기준 정렬
            else {
                nodes.sort((o1, o2) -> Integer.compare(getY(idToPoint.get(o1)), getY(idToPoint.get(o2))));
            }

            // 연결
            // 중복 가능성
            int prev = -1;
            for (int id : nodes) {
                if (id == prev) {
                    continue;
                }
                if (prev != -1) {
                    adj[prev].add(id);
                    adj[id].add(prev);
                }
                prev = id;
            }
        }

        // 다익스트라로 1번 도시에서 출발
        int[] dp = new int[totalNode]; // 도달가능한 최대 속도를 저장
        Arrays.fill(dp, -1);
        PriorityQueue<Stat> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.limit, o1.limit));

        int startId = getId(city[0][0], city[0][1]);
        pq.offer(new Stat(startId, INF));
        dp[startId] = INF;

        while (!pq.isEmpty()) {
            Stat cur = pq.poll();

            if (cur.limit < dp[cur.id]) {
                continue;
            }

            // 다음
            for (int next : adj[cur.id]) {
                // 최대 속도 갱신 가능성
                int nextLimit = Math.min(cur.limit, nodeLimit.get(next));

                if (dp[next] < nextLimit) {
                    dp[next] = nextLimit;
                    pq.offer(new Stat(next, nextLimit));
                }
            }
        }

        // 결과
        int[] answer = new int[city.length - 1];
        for (int i = 1; i < city.length; i++) {
            int id = getId(city[i][0], city[i][1]);
            int limit = dp[id];

            // INF이면 0으로
            answer[i-1] = limit != INF ? limit : 0;
        }

        return answer;
    }

    private int getX(long point) {
        return (int) (point >> 32);
    }

    private int getY(long point) {
        return (int) point;
    }

    private int getId(int x, int y) {
        long key = getKey(x, y);
        if (!pointToId.containsKey(key)) {
            int id = pointToId.size();
            pointToId.put(key, id);
            idToPoint.add(key);
            nodeLimit.put(id, INF);
        }
        return pointToId.get(key);
    }

    private long getKey(int x, int y) {
        return (long) x << 32 | (y & 0xFFFF_FFFFL);
    }

    private class Stat {
        int id, limit;

        public Stat(int id, int limit) {
            this.id = id;
            this.limit = limit;
        }
    }
}
