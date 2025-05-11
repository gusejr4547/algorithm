package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 위상정렬
public class P2623_음악프로그램 {
    static List<Integer>[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        int[] inEdge = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            int[] seq = new int[len];
            for (int j = 0; j < len; j++) {
                seq[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < len - 1; j++) {
                adj[seq[j]].add(seq[j + 1]);
                inEdge[seq[j + 1]]++;
            }
        }

        List<Integer> result = topologySort(inEdge);
        if(result.size() != N){
            System.out.println(0);
        }else{
            for(int num : result){
                System.out.println(num);
            }
        }
    }

    private static List<Integer> topologySort(int[] inEdge) {
        List<Integer> result = new ArrayList<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < inEdge.length; i++) {
            if (inEdge[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            result.add(cur);

            for (int next : adj[cur]) {
                inEdge[next]--;
                if (inEdge[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return result;
    }
}
