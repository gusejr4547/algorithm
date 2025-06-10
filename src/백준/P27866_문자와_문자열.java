package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P27866_문자와_문자열 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println(s.charAt(Integer.parseInt(br.readLine())-1));
    }
}
