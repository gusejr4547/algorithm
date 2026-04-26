package 백준._20260426;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
$N$개의 문제로 구성된 문제집의
$i$번째 문제는 지금보다 지식이
$a_i$ 더 필요하고, 구현력이
$b_i$만큼 더 필요하고, 사고력이
$c_i$만큼 더 필요하다.

동규는 하루를 사용해 아직 안 푼 문제 중 하나를 풀 수 있다. 그렇지 않다면, 하루를 사용하여 지식을
$1$ 쌓거나, 구현력을
$1$ 올리거나, 사고력을
$1$ 키울 수 있다.

동규는
$i$번째 문제를 늦어도
$p_i$일차까지는 해결하려고 한다. 마감일은 항상 뒤쪽 문제로 갈수록 더 여유로워진다. 즉,
$p_{i+1}$은
$p_i$와 같거나 더 크다. (
$1\le i\le N-1$)

동규가 계획을 지킬 수 있을까?
 */

public class B_Good_Bye_설탕_배달 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            long maxA = 0;
            long maxB = 0;
            long maxC = 0;
            boolean possible = true;
            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                long a = Long.parseLong(st.nextToken());
                long b = Long.parseLong(st.nextToken());
                long c = Long.parseLong(st.nextToken());
                long p = Long.parseLong(st.nextToken());
                maxA = Math.max(maxA, a);
                maxB = Math.max(maxB, b);
                maxC = Math.max(maxC, c);

                // 총 걸리는 시간
                // 문제수 + maxA + maxB + maxC;
                long total = i + maxA + maxB + maxC;
                if (total > p) {
                    possible = false;
                }
            }
            if (possible) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.println(sb);
    }
}
