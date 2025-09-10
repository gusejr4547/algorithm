package 코드트리.HSAT;

import java.util.*;

public class 플레이페어_암호 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String msg = sc.next();
        String key = sc.next();

        // key로 5x5 칸 채우기
        char[][] grid = new char[5][5];
        boolean[] use = new boolean[26];
        int idx = 0;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!use[c - 'A']) {
                use[c - 'A'] = true;
                grid[idx / 5][idx % 5] = c;
                idx++;
            }
        }
        for (int i = 0; i < use.length; i++) {
            if (use[i] || 'A' + i == 'J') {
                continue;
            }
            grid[idx / 5][idx % 5] = (char) ('A' + i);
            idx++;
        }
        // map으로 맵핑
        Map<Character, Pos> pos = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                pos.put(grid[i][j], new Pos(i, j));
            }
        }


        // 2글자 씩 나누기
        List<Pair> pairList = new ArrayList<>();

        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                if (c == 'X' && stack.peek() == 'X') {
                    pairList.add(new Pair(stack.pop(), 'Q'));
                    stack.push(c);
                } else if (c == stack.peek()) {
                    pairList.add(new Pair(stack.pop(), 'X'));
                    stack.push(c);
                } else {
                    pairList.add(new Pair(stack.pop(), c));
                }
            }
        }
        if (!stack.isEmpty()) {
            pairList.add(new Pair(stack.pop(), 'X'));
        }

        // 두 글자 씩 나눈 것을 암호화
        StringBuilder sb = new StringBuilder();
        for (Pair pair : pairList) {
            char a = pair.first;
            char b = pair.second;
            Pos aP = pos.get(a);
            Pos bP = pos.get(b);

            // 같은 row
            if (aP.r == bP.r) {
                sb.append(grid[aP.r][(aP.c + 1) % 5]);
                sb.append(grid[bP.r][(bP.c + 1) % 5]);
            }
            // 같은 col
            else if (aP.c == bP.c) {
                sb.append(grid[(aP.r + 1) % 5][aP.c]);
                sb.append(grid[(bP.r + 1) % 5][bP.c]);
            }
            // 이외
            else {
                sb.append(grid[aP.r][bP.c]);
                sb.append(grid[bP.r][aP.c]);
            }
        }

        System.out.println(sb.toString());
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static class Pair {
        char first, second;

        public Pair(char first, char second) {
            this.first = first;
            this.second = second;
        }
    }
}
