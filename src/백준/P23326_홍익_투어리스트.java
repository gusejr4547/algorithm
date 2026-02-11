package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class P23326_홍익_투어리스트 {
    // N개가 원형으로 이루어짐
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        // A를 2배로 늘려 놓으면 무조건 한바퀴 전부 돌수있다.
        TreeSet<Integer> treeSet = new TreeSet<>();

        int[] A = new int[N * 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int p = Integer.parseInt(st.nextToken());
            if (p == 1) {
                treeSet.add(i);
                treeSet.add(i + N);
            }
        }

        StringBuilder sb = new StringBuilder();

        int my = 0;
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            if (1 == query) {
                int p = Integer.parseInt(st.nextToken()) - 1;

                if (!treeSet.contains(p)) {
                    treeSet.add(p);
                    treeSet.add(p + N);
                } else {
                    treeSet.remove(p);
                    treeSet.remove(p + N);
                }
            } else if (2 == query) {
                int x = Integer.parseInt(st.nextToken());
                my = (my + x) % N;
            } else if (3 == query) {
                // my에서 다음 명소
                Integer next = treeSet.ceiling(my);
                if (next != null) {
                    sb.append(next - my).append('\n');
                } else {
                    sb.append(-1).append('\n');
                }
            }
        }
        System.out.println(sb);
    }
}
