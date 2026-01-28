package 코드트리.삼성;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 기본 출발은 0번도시
// cost는 출발지로부터 dest에 도달하기까지 최단거리
// 상품을 팔면 관리목록에서 제거
public class 코드트리_투어 {
    static int n, m;
    static List<Node>[] adj;
    static Set<Integer> removeTravelItemSet;
    static PriorityQueue<Item> itemPQ;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine()); // 명령 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 항상 첫번째명령은 100이다. => 코드트리 랜드 건설
        int operation = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adj[a].add(new Node(b, cost));
            adj[b].add(new Node(a, cost));
        }

        removeTravelItemSet = new HashSet<>();
        itemPQ = new PriorityQueue<>((o1, o2) ->
                (o2.revenue - o2.cost) == (o1.revenue - o1.cost) ?
                        o1.id - o2.id : Integer.compare((o2.revenue - o2.cost), (o1.revenue - o1.cost)));
        int startPoint = 0;
        int[] minDist = calMinDist(startPoint);

        // 명령어에 따라 수행
        // 400 명령어에 대해 최적의 여행상품 id(없으면 -1)을 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            operation = Integer.parseInt(st.nextToken());
            if (200 == operation) {
                int id = Integer.parseInt(st.nextToken());
                int revenue = Integer.parseInt(st.nextToken());
                int dest = Integer.parseInt(st.nextToken());
                addTravelItem(id, revenue, dest, minDist);
            } else if (300 == operation) {
                int id = Integer.parseInt(st.nextToken());
                removeTravelItem(id);
            } else if (400 == operation) {
                // 최적의 여행 상품 판매
                int id = getBestTravelItem();
                sb.append(id).append("\n");
            } else if (500 == operation) {
                // 여행 출발지 변경
                startPoint = Integer.parseInt(st.nextToken());
                // 다시 거리 계산
                minDist = calMinDist(startPoint);
                // 여행상품 다시 계산
                renewItem(minDist);
            }
        }

        System.out.println(sb);
    }

    private static void renewItem(int[] dist) {
        List<Item> itemList = new ArrayList<>();
        while (!itemPQ.isEmpty()) {
            Item item = itemPQ.poll();
            itemList.add(new Item(item.id, item.revenue, item.destination, dist[item.destination]));
        }

        itemPQ.addAll(itemList);
    }

    private static int getBestTravelItem() {
        // 제거목록에 있는 상품 거르기
        while (!itemPQ.isEmpty() && removeTravelItemSet.contains(itemPQ.peek().id)) {
            itemPQ.poll();
        }

        // 상품없음.
        if (itemPQ.isEmpty()) {
            return -1;
        }

        Item item = itemPQ.peek();

        // 여행사가 얻을 수 있는게 없음
        if (item.revenue < item.cost) {
            return -1;
        }

        return itemPQ.poll().id;
    }

    private static int[] calMinDist(int start) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.id] < cur.cost) {
                continue;
            }

            for (int i = 0; i < adj[cur.id].size(); i++) {
                Node next = adj[cur.id].get(i);
                if (dist[next.id] > cur.cost + next.cost) {
                    dist[next.id] = cur.cost + next.cost;
                    pq.offer(new Node(next.id, dist[next.id]));
                }
            }
        }

        return dist;
    }

    private static void addTravelItem(int id, int revenue, int dest, int[] dist) {
        itemPQ.offer(new Item(id, revenue, dest, dist[dest]));
        removeTravelItemSet.remove(id);
    }

    private static void removeTravelItem(int id) {
        removeTravelItemSet.add(id);
    }

    private static class Node {
        int id, cost;

        public Node(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }
    }

    private static class Item {
        int id, revenue, destination, cost;

        public Item(int id, int revenue, int destination) {
            this.id = id;
            this.revenue = revenue;
            this.destination = destination;
        }

        public Item(int id, int revenue, int destination, int cost) {
            this.id = id;
            this.revenue = revenue;
            this.destination = destination;
            this.cost = cost;
        }
    }
}
