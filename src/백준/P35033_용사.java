package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P35033_용사 {
    // N개의 비석 존재
    // 비석 사이에 N-1개의 길. 비석은 트리구조
    // 1. 직접 연결된 비석의 개수가 하나 이하인 비석을 고르고 제거. 비석에 저주의 낙인이 있으면 같이 사라짐
    // 2. 해당 비석과 직접 연결된 비석에 저주의 낙인이 새겨진다. 이미 있으면 변하지 않음

    // 저주의 낙인이 새겨진 비석이 20개 넘어가면 안됨.
    // 1번 비석에 크크발의 영혼이 있으므로 1번 제거는 제일 마지막...

    // 제거 순서 출력.

    // N <= 200 000

    static List<Integer>[] adj, child;
    static boolean[] visit;
    static int[] noroi;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        // 1번을 루트로 하는 트리..
        // 후위순회?
        // 그냥 후위순회 하면 안되고 가장 낙인이 많이 생기는 서브 트리 부터 후위순회 해야지 최적

        // 트리 만들기
        visit = new boolean[N + 1];
        child = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            child[i] = new ArrayList<>();
        }
        makeTree();

        // 서브트리 없앴을때 최대 낙인 개수 기록
        noroi = new int[N + 1];
        getMaxNoroi(1);

        // 정렬
        for (int i = 1; i <= N; i++) {
            child[i].sort((o1, o2) -> noroi[o2] - noroi[o1]);
        }

        sb = new StringBuilder();
        int[] stack = new int[N + 1];
        int top = 0;
        stack[top++] = 1; // 루트
        int[] count = new int[N + 1];

        while (top > 0) {
            int node = stack[top - 1]; // 가장 위에 있는거
            // 아직 서브트리 존재
            if (count[node] < child[node].size()) {
                int next = child[node].get(count[node]);
                stack[top++] = next;
                count[node]++;
            }
            // 서브트리 전부 순회했음
            else {
                top--;
                sb.append(node).append(' ');
            }
        }

        System.out.println(sb);
    }

    private static void makeTree() {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        visit[1] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : adj[cur]) {
                if (!visit[next]) {
                    visit[next] = true;
                    child[cur].add(next);
                    queue.offer(next);
                }
            }
        }
    }

    private static int getMaxNoroi(int node) {
        if (child[node].size() == 0) {
            return noroi[node] = 0;
        }

        // 자식 중에 noroi 가장 큰거 2번째로 큰거
        int first = -1;
        int second = -1;
        for (int next : child[node]) {
            int val = getMaxNoroi(next);
            if (val > first) {
                second = first;
                first = val;
            } else if (val > second) {
                second = val;
            }
        }

        if (second >= 0) {
            return noroi[node] = Math.max(first, second + 1);
        } else {
            return noroi[node] = first;
        }
    }

    private static void postOrder(int node) {
        // 자식
        for (int next : child[node]) {
            postOrder(next);
        }

        // 서브 루트
        sb.append(node).append(" ");
    }
}
