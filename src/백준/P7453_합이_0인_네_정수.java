package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P7453_합이_0인_네_정수 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        long[] AB = new long[N * N];
        long[] CD = new long[N * N];

        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[idx] = A[i] + B[j];
                CD[idx] = C[i] + D[j];
                idx++;
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        long result = 0;
        int i = 0;
        int j = N * N - 1;

        while (i < AB.length && j >= 0) {
            long sum = AB[i] + CD[j];

            if (sum < 0) {
                i++;
            } else if (sum > 0) {
                j--;
            } else {
                // 중복 원소 찾기
                long ABCnt = 1;
                long CDCnt = 1;
                while (i < AB.length - 1 && AB[i] == AB[i + 1]) {
                    ABCnt++;
                    i++;
                }
                while (j > 0 && CD[j] == CD[j - 1]) {
                    CDCnt++;
                    j--;
                }

                result += ABCnt * CDCnt;
                i++;
                j--;
            }
        }

        System.out.println(result);
    }
}
