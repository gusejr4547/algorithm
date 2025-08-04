package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P9659_돌_게임5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        System.out.println(N % 2 == 1 ? "SK" : "CY");
    }
}
