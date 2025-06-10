package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1259_팰린드롬수 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String s = br.readLine();
            if ("0".equals(s)) {
                break;
            }

            int l = 0;
            int r = s.length() - 1;

            boolean isP = true;
            while (l <= r) {
                if (s.charAt(l) != s.charAt(r)) {
                    isP = false;
                    break;
                }
                l++;
                r--;
            }

            System.out.println(isP ? "yes" : "no");
        }
    }
}
