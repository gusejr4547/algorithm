package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P35273_착신_전환 {
    static int N;
    static int[] A, inDegree, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        inDegree = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            inDegree[A[i]]++;
        }

        // 각 전화별로 전화를 걸었을때 최종적으로 어떤 전화가 울리나?
        // 사이클은 자기자신이전 전화걸림
        // 일방통행은 마지막에 전화걸림

        answer = new int[N + 1];

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] order = new int[N + 1];
        int idx = 0;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            order[idx++] = x;
            inDegree[x]--;
            if (inDegree[A[x]] == 0) {
                queue.offer(x);
            }
        }

        // inDegree에 1 남은거는 사이클임
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] > 0) {
                answer[A[i]] = i;
            }
        }

        // 나머지
        for (int i = idx - 1; i >= 0; i--) {
            int node = order[i];
            answer[node] = answer[A[node]];
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(answer[i]).append(' ');
        }

        System.out.println(sb);
    }
}
