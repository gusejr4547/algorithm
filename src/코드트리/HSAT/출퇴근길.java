package 코드트리.HSAT;

import java.util.*;

public class 출퇴근길 {
    static int n, m, S, T;
    static List<Integer>[] adj, reverse;
//    static boolean[] visit;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        adj = new List[n + 1];
        reverse = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            adj[x].add(y);
            reverse[y].add(x);
        }
        S = sc.nextInt();
        T = sc.nextInt();

        // S에서 도달할 수 있는 노드 구하기, T에서 역방향으로 갈수있는 곳
//        List<Integer> canGoS = move(S, adj, T);
//        System.out.println(canGoS);
//        List<Integer> canGoReverseT = move(T, reverse, -1);
//        System.out.println(canGoReverseT);
        // 2개의 교집합이 경로에 있는 노드
        List<Integer> go = intersect(move(S, adj, T), move(T, reverse, -1));
//        System.out.println(go);

        // T에서
//        List<Integer> canGoT = move(T, adj, S);
//        List<Integer> canGoReverseS = move(S, reverse, -1);
        List<Integer> come = intersect(move(T, adj, S), move(S, reverse, -1));
//        System.out.println(come);

        // go, come 교집합
        List<Integer> result = intersect(go, come);

        // 만족하는거 계산
        int answer = 0;
        for (int num : result) {
            if (num != S && num != T) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static List<Integer> intersect(List<Integer> a, List<Integer> b) {
        Collections.sort(a);
        Collections.sort(b);
        List<Integer> same = new ArrayList<>();

        int ai = 0;
        int bi = 0;
        while (ai < a.size() && bi < b.size()) {
            if (a.get(ai).equals(b.get(bi))) {
                same.add(a.get(ai));
                ai++;
                bi++;
            } else if (a.get(ai) < b.get(bi)) {
                ai++;
            } else {
                bi++;
            }
        }
        return same;
    }

    private static List<Integer> move(int node, List<Integer>[] edge, int endPoint) {
        List<Integer> canGo = new ArrayList<>();
        boolean[] visit = new boolean[n + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(node);
        visit[node] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            canGo.add(cur);
            if (cur == endPoint) {
                continue;
            }

            for (int next : edge[cur]) {
                if (visit[next]) {
                    continue;
                }
                visit[next] = true;
                queue.offer(next);
            }
        }
        return canGo;
    }
}
