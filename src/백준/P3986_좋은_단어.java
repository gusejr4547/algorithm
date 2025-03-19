package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Stack;

// 좋은단어? - 단어 위로 아치형곡선을 그어, 같은 글자끼리 쌍을 짓는다.
// 선끼리 교차하지 않으면서 각 글자를 정확히 한 개의 다른 위치에 있는 글자와 짝 지을 수 있다면 좋은단어.
public class P3986_좋은_단어 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        for (int t = 0; t < N; t++) {
            String word = br.readLine();

            ArrayDeque<Character> stack = new ArrayDeque<>();
            for (int idx = 0; idx < word.length(); idx++) {
                char c = word.charAt(idx);
                if (stack.isEmpty()) {
                    stack.push(c);
                } else {
                    if (stack.peek() == c) {
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                }
            }

            if (stack.isEmpty()) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
