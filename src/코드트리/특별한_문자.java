package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 특별한_문자 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Character, Integer> m = new HashMap<>();
        String str = br.readLine();

        for (char c : str.toCharArray()) {
            m.put(c, m.getOrDefault(c, 0) + 1);
        }

        for (char c : str.toCharArray()) {
            if (m.get(c) == 1) {
                System.out.println(c);
                return;
            }
        }

        System.out.println("None");
    }
}
