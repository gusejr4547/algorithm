package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

public class P1062_가르침 {
    static int N, K, answer;
    static int use;
    static String[] words;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (K < 5) {
            System.out.println(0);
            return;
        } else if (K == 26) {
            System.out.println(N);
            return;
        }

        // antic 미리 사용
        char[] pre = "antic".toCharArray();
        for (char c : pre)
            use = use | (1 << (c - 'a'));

        words = new String[N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            String word = s.substring(4, s.length() - 4);
            words[i] = word;
        }

        combination(0, 0, use);

        System.out.println(answer);
    }

    static void combination(int idx, int start, int use) {
        // 끝
        if (idx == K - 5) {
            int count = 0;
            for (String word : words) {
                boolean isVaild = true;
                for (char c : word.toCharArray()) {
                    if ((use & (1 << (c - 'a'))) == 0) {
                        isVaild = false;
                        break;
                    }
                }
                if (isVaild)
                    count++;
            }

            answer = Math.max(answer, count);
            return;
        }

        // 배울 알파벳 선택
        for (int i = start; i < 26; i++) {
            if ((use & (1 << i)) != 0) continue;

            combination(idx + 1, i + 1, use | (1 << i));
        }
    }
}
