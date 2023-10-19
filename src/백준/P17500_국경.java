package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P17500_국경 {

    static int N;
    static char[][] map;
    static char[][] ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 초기화
        ans = new char[2 * N + 3][4 * N + 3];
        for (int i = 0; i < 2 * N + 3; i++) {
            for (int j = 0; j < 4 * N + 3; j++) {
                if (i == 0 || i == 2 * N + 2) {
                    ans[i][j] = '#';
                }else if(j == 0 || j == 4*N+2){
                    ans[i][j] = '#';
                }else if(i%2==1 && j%4==1){
                    ans[i][j] = '+';
                }else if(i%2==0 && j%4==3){
                    ans[i][j] = map[i/2-1][j/4];
                }else{
                    ans[i][j] = ' ';
                }
            }
        }
        for(char[] arr : ans){
            System.out.println(arr);
        }



    }
}
