package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class P9935_문자열_폭발 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String boom = br.readLine();

        ArrayDeque<Character> stack = new ArrayDeque<>();
        int idx = s.length() - 1;
        while (idx >= 0) {
            stack.push(s.charAt(idx));

            if (!stack.isEmpty() && stack.peek() == boom.charAt(0)) {
                ArrayDeque<Character> temp = new ArrayDeque<>();
                boolean isEqual = true;
                for (int i = 0; i < boom.length(); i++) {
                    if (!stack.isEmpty() && boom.charAt(i) == stack.peek()) {
                        temp.push(stack.pop());
                    } else {
                        isEqual = false;
                        break;
                    }
                }

                if (!isEqual) {
                    while (!temp.isEmpty()) {
                        stack.push(temp.pop());
                    }
                }
            }

            idx--;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb.length() == 0 ? "FRULA" : sb);
    }
}
