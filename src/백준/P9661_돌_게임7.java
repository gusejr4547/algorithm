package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P9661_돌_게임7 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        if (N % 5 == 0 || N % 5 == 2) {
            System.out.println("CY");
        }else{
            System.out.println("SK");
        }
    }
}
