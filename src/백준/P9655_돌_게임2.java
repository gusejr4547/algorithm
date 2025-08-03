package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P9655_돌_게임2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N % 2 == 1) {
            System.out.println("CY");
        } else {
            System.out.println("SK");
        }
    }
}
