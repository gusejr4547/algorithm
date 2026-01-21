package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 자주_등장한_top_K_숫자 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> map = new HashMap<>();
        List<Node> arr = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        int idx = 0;
        while (N-- > 0) {
            int num = Integer.parseInt(st.nextToken());
            if (!map.containsKey(num)) {
                map.put(num, idx);
                arr.add(new Node(num, 1));
                idx++;
            } else {
                int i = map.get(num);
                arr.get(i).count++;
            }
        }

        // 정렬
        arr.sort((o1, o2) ->
                o1.count == o2.count ? Integer.compare(o2.value, o1.value) : Integer.compare(o2.count, o1.count));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            sb.append(arr.get(i).value).append(" ");
        }

        System.out.println(sb);
    }

    static class Node {
        int value, count;

        public Node(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }
}
