package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 특별한_문자 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Node> cArr = new ArrayList<>();
        Map<Character, Integer> m = new HashMap<>();
        String str = br.readLine();

        int idx = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (m.containsKey(c)) {
                int k = m.get(c);
                cArr.get(k).cnt++;
            } else {
                m.put(c, idx);
                cArr.add(new Node(1, i, c));
                idx++;
            }
        }

        for (Node n : cArr) {
            if (n.cnt == 1) {
                System.out.println(n.c);
                return;
            }
        }

        System.out.println("None");
    }

    private static class Node {
        int cnt, firstIdx;
        char c;

        public Node(int cnt, int firstIdx, char c) {
            this.cnt = cnt;
            this.firstIdx = firstIdx;
            this.c = c;
        }
    }

}
