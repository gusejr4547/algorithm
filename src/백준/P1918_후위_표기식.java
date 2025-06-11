package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class P1918_후위_표기식 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        StringBuilder sb = new StringBuilder();

        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isAlphabetic(c)) {
                sb.append(c);
            } else {
                if ('(' == c) {
                    stack.push(c);
                } else if (')' == c) {
                    while (!stack.isEmpty()) {
                        if ('(' == stack.peek()) {
                            stack.pop();
                            break;
                        }
                        sb.append(stack.pop());
                    }
                } else if ('*' == c || '/' == c) {
                    while (!stack.isEmpty()) {
                        if ('*' == stack.peek() || '/' == stack.peek()) {
                            sb.append(stack.pop());
                        } else {
                            break;
                        }
                    }
                    stack.push(c);
                } else if ('+' == c || '-' == c) {
                    while (!stack.isEmpty()) {
                        if ('(' == stack.peek() || ')' == stack.peek()) {
                            break;
                        }
                        sb.append(stack.pop());
                    }
                    stack.push(c);
                }
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }
}
