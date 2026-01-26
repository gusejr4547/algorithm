package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class 최대로_연속한_숫자 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        TreeSet<Pair> border = new TreeSet<>((o1, o2) -> o1.start - o2.start);
        TreeMap<Integer, Integer> lengthMap = new TreeMap<>();
        border.add(new Pair(0, N));
        lengthMap.put(N + 1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            Pair p = new Pair(num, num);
            Pair target = border.floor(p); // num을 포함하는 구간

            if (target != null) {
                border.remove(target);
                int len = target.end - target.start + 1;
                int count = lengthMap.get(len);
                if (count == 1) {
                    lengthMap.remove(len);
                } else {
                    lengthMap.put(len, count - 1);
                }

                // 왼쪽
                if (target.start <= num - 1) {
                    border.add(new Pair(target.start, num - 1));
                    lengthMap.put(num - target.start, lengthMap.getOrDefault(num - target.start, 0) + 1);
                }

                // 오른쪽
                if (num + 1 <= target.end) {
                    border.add(new Pair(num + 1, target.end));
                    lengthMap.put(target.end - num, lengthMap.getOrDefault(target.end - num, 0) + 1);
                }
            }

            if (lengthMap.isEmpty()) {
                sb.append(0).append('\n');
            } else {
                sb.append(lengthMap.lastKey()).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static class Pair {
        int start, end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
