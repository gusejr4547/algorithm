package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P9658_돌_게임4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] win = new boolean[1001];
        win[0] = true;
        win[2] = true;
        win[4] = true;

        for (int i = 5; i <= N; i++) {
            if(!win[i-1] || !win[i-3] || !win[i-4]){
                win[i] = true;
            }
        }
//        System.out.println(Arrays.toString(win));
        System.out.println(win[N] ? "SK" : "CY");
    }
}
