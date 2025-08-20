package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P10836_여왕벌 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] grid = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = 1;
            }
        }

        int[] prefix = new int[M + M];

        for (int day = 0; day < N; day++) {
            st = new StringTokenizer(br.readLine());
            int i = 0;
            for (int d = 0; d < 3; d++) {
                int count = Integer.parseInt(st.nextToken());
                prefix[i] += d;
                prefix[i + count] -= d;
                i += count;
            }


//            int i = M - 1;
//            int j = 0;

//            for (int d = 0; d < 3; d++) {
//                int count = Integer.parseInt(st.nextToken());
//                while (count-- > 0) {
//                    grid[i][j] += d;
//
//                    if (i > 0) {
//                        i--;
//                    } else {
//                        j++;
//                    }
//                }
//            }
        }

        // 누적합
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] += prefix[i - 1];
        }
//        System.out.println(Arrays.toString(prefix));
        // prefix를 grid에 계산
        int r = M - 1;
        int c = 0;
        for (int count = 0; count < prefix.length - 1; count++) {
            grid[r][c] += prefix[count];
            if (r > 0) {
                r--;
            } else {
                c++;
            }
        }


        for (int i = 1; i < M; i++) {
            for (int j = 1; j < M; j++) {
                grid[i][j] = Math.max(Math.max(grid[i][j - 1], grid[i - 1][j]), grid[i - 1][j - 1]);
            }
        }

//        for (int[] g : grid) {
//            System.out.println(Arrays.toString(g));
//        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(grid[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
