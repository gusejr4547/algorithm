package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 순서를_바꾸었을_때_같은_단어_그룹화하기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<Character, Integer>[] count = new Map[N];

        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            // 문자 등장 횟수 기록
            Map<Character, Integer> m = new HashMap<>();
            for (char c : line) {
                m.put(c, m.getOrDefault(c, 0) + 1);
            }
            count[i] = m;
        }

        boolean[] used = new boolean[N];
        int answer = 0;

        for (int i = 0; i < N; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            int same = 1;
            for (int j = i + 1; j < N; j++) {
                if (used[j]) {
                    continue;
                }
                // i,j비교
                if (isEqual(count[i], count[j])) {
                    same++;
                    used[j] = true;
                }
            }

            answer = Math.max(answer, same);
        }

        System.out.println(answer);
    }

    private static boolean isEqual(Map<Character, Integer> a, Map<Character, Integer> b) {
        if (a.size() != b.size()) {
            return false;
        }

        for (char c : a.keySet()) {
            if (!b.containsKey(c) || !a.get(c).equals(b.get(c))) {
                return false;
            }
        }

        return true;
    }
}
