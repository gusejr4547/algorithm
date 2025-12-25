package 백준._20251225;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class D_맞혀보세요 {
    // 규칙
    // X 뒤에는 {Y1 ... YM} 에 속하는 문자가 오거나 그냥 문자열 끝이거나

    static Map<Character, Set<Character>> rule;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        rule = new HashMap<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char X = st.nextToken().charAt(0);
            int M = Integer.parseInt(st.nextToken());

            Set<Character> set = new HashSet<>();
            for (int j = 0; j < M; j++) {
                char Y = st.nextToken().charAt(0);
                set.add(Y);
            }

            rule.put(X, set);
        }
        String S = br.readLine();

        // 각 문자가 규칙을 만족하는가?
        boolean answer = true;
        for (int i = 0; i < S.length() - 1; i++) {
            char c = S.charAt(i);
            if (rule.containsKey(c)) {
                if (!rule.get(c).contains(S.charAt(i + 1))) {
                    answer = false;
                    break;
                }
            }
        }

        System.out.println(answer ? "yes" : "no");
    }
}
