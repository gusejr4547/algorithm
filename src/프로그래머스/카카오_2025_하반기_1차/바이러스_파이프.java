package 프로그래머스.카카오_2025_하반기_1차;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class 바이러스_파이프 {
    public static void main(String[] args) {
        바이러스_파이프 Main = new 바이러스_파이프();
        int n = 10;
        int infection = 1;
        int[][] edges = {{1, 2, 1}, {1, 3, 1}, {1, 4, 3}, {1, 5, 2}, {5, 6, 1}, {5, 7, 1}, {2, 8, 3}, {2, 9, 2}, {9, 10, 1}};
        int k = 2;
        System.out.println(Main.solution(n, infection, edges, k));
    }

    // 하나의 트리 모양 => 사이클이 없다
    // 배양체 중 하나가 바이러스에 감염되어 있습니다. 바이러스에 감염된 배양체는 열린 파이프를 통해 연결된 다른 인접한 배양체를 감염시킵니다.
    // 나는 종류가 같은 파이프를 모두 열고 닫을 수 있다
    // 하나를 열면 그걸 닫기 전에는 다른거 못연다.
    // 열고 닫는거 최대 k번 반복해서 최대한 많이 감염시키기.

    // k<=10

    int answer;
    boolean[] isInfect;
    List<Pipe>[] adj;

    public int solution(int n, int infection, int[][] edges, int k) {
        isInfect = new boolean[n + 1];
        isInfect[infection] = true;

        // adj 만들기
        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(new Pipe(edge[1], edge[2]));
            adj[edge[1]].add(new Pipe(edge[0], edge[2]));
        }

        answer = 1;
        dfs(0, n, k);

        return answer;
    }

    public void dfs(int openCnt, int n, int k) {
        // 최대 감염자 수를 갱신합니다.
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (isInfect[i]) {
                cnt++;
            }
        }
        answer = Math.max(answer, cnt);

        // 마지막
        if (openCnt == k) {
            return;
        }

        // type 하나씩 열어보기
        for (int type = 1; type <= 3; type++) {
            // 열고나서는 bfs로 최대한 확산
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            for (int i = 1; i <= n; i++) {
                if (isInfect[i]) {
                    queue.offer(i);
                }
            }

            // 새롭게 감염되는 곳
            List<Integer> addP = new ArrayList<>();

            // bfs
            while (!queue.isEmpty()) {
                int cur = queue.poll();

                for (Pipe next : adj[cur]) {
                    // 같은 type만
                    if (type != next.type) {
                        continue;
                    }

                    // 아직 감염 안된곳
                    if (!isInfect[next.to]) {
                        queue.offer(next.to);
                        addP.add(next.to);
                        isInfect[next.to] = true;
                    }
                }
            }

            // 새로운 감염이 없으면 다음으로 가는거 의미없다.
            // 다음
            if(!addP.isEmpty()){
                dfs(openCnt + 1, n, k);
            }

            // 초기화
            for (int node : addP) {
                isInfect[node] = false;
            }
        }
    }

    public class Pipe {
        int to, type;

        public Pipe(int to, int type) {
            this.to = to;
            this.type = type;
        }
    }
}
