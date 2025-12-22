package 백준._20251222;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class A_채플이_너무해 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());

        if (x >= 6) {
            System.out.println("Success!");
        } else {
            System.out.println("Oh My God!");
        }
    }
}
