package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P35309_잘_정의된_들여쓰기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            boolean isPossible = true;

            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            ArrayDeque<Integer> stack = new ArrayDeque<>();
            for (int i = 0; i < N; i++) {
                if (!isPossible) {
                    break;
                }

                // 이전 같은 레벨 찾기
                if (A[i] > 1) {
                    while (!stack.isEmpty() && A[i] - 1 != stack.peek()) {
                        stack.pop();
                    }
                    if (stack.isEmpty()) {
                        isPossible = false;
                    } else {
                        stack.pop();
                        stack.push(A[i]);
                    }
                }
                // 새로운 레벨
                else {
                    stack.push(A[i]);
                }
            }

            sb.append(isPossible ? "YES" : "NO").append('\n');
        }

        System.out.println(sb);
    }
}
