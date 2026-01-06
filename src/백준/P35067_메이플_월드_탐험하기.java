package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P35067_메이플_월드_탐험하기 {
    /*
    메이플 월드는 N개의 지역과 이를 양방향으로 잇는 N - 1개의 길로 이루어진 트리 구조
    각 지역 마력수치 A[i]
    i번째날 i번 지역부터 탐험 시작
    - 어떤 지역을 한번 방문하고 다른 지역으로 이동 -> 다시 방문 불가능
    - 현재 위치한 지역에서 못가는곳 빼고 인접 지역중 마력 가장 큰곳으로 이동
    - 이동 불가능하면 탐험 종료
    - 탐험 종료되면 방문 불가능한곳 다시 복구... 다음 탐험에 영향 x

    모든 날에 대해 여행이 끝나는 지역 출력

    N <= 500 000
    A <= N
    */
    static int N;
    static int[] A;
    static List<Integer>[] adj;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        // 인접 노드 마력 내림차순으로 정렬
        for (int i = 1; i <= N; i++) {
            adj[i].sort((o1, o2) -> Integer.compare(A[o2], A[o1]));
        }

        // i를 루트로하는 노드에서 인접한 다른 노드를 부모로 하는 서브 트리에서 최종 목적지를 계산한 dp
        dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            makeTreeDP(i, 0);
        }

        for(int[] d : dp){
            System.out.println(Arrays.toString(d));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(dp[i][0]).append('\n');
        }

        System.out.println(sb);
    }

    private static int makeTreeDP(int node, int parent) {
        // 미리 계산된 값
        if (dp[node][parent] != 0) {
            return dp[node][parent];
        }

        // node에서 parent를 제외한 가장 마력이 큰 곳으로 이동
        for (int next : adj[node]) {
            if (parent == next) {
                continue;
            }
            return dp[node][parent] = makeTreeDP(next, node);
        }

        // 다른곳 못가면 자기자신 종료
        return dp[node][parent] = node;
    }
}
