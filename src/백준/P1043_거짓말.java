package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P1043_거짓말 {
    static List<Integer> know;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        know = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            int num = Integer.parseInt(st.nextToken());
            know.add(num);
        }

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        List<Integer>[] party = new List[M];
        for (int i = 0; i < M; i++) {
            party[i] = new ArrayList<>();
        }

        for (int pIdx = 0; pIdx < M; pIdx++) {
            st = new StringTokenizer(br.readLine());
            int pCnt = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            party[pIdx].add(first);
            for (int i = 1; i < pCnt; i++) {
                int num = Integer.parseInt(st.nextToken());
                party[pIdx].add(num);
                union(first, num);
            }
        }
//        System.out.println(Arrays.toString(parent));

        int answer = 0;
        for (List<Integer> o : party) {
            int group = find(o.get(0));
            boolean gura = true;
            for (int i = 0; i < know.size(); i++) {
                int knowGroup = find(know.get(i));
                if (group == knowGroup) {
                    gura = false;
                    break;
                }
            }

            if(gura){
//                System.out.println(o);
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }

}
