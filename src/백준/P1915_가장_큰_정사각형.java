package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1915_가장_큰_정사각형 {
    public static void main(String[] args) throws Exception {
        prefixSolution();
    }

    private static void prefixSolution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] prefixSum = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            char[] carr = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                prefixSum[i + 1][j + 1] = prefixSum[i + 1][j] + prefixSum[i][j + 1] - prefixSum[i][j] + (carr[j] - '0');
            }
        }

//        for (int[] s : prefixSum) {
//            System.out.println(Arrays.toString(s));
//        }

        int answer = 0;
        int max = Math.min(n, m);
        int min = 1;
        while (max >= min) {
            int mid = (max + min) / 2;
            if (isValid(mid, prefixSum, n, m)) {
                min = mid + 1;
                answer = mid;
            } else {
                max = mid - 1;
            }
        }

        System.out.println(answer * answer);
    }

    private static boolean isValid(int sideLength, int[][] prefixSum, int n, int m) {
        int area = sideLength * sideLength;
        for (int i = sideLength; i <= n; i++) {
            for (int j = sideLength; j <= m; j++) {
                if (area <= prefixSum[i][j]) {
                    int currArea = prefixSum[i][j] - prefixSum[i - sideLength][j] - prefixSum[i][j - sideLength] + prefixSum[i - sideLength][j - sideLength];
                    if (currArea == area) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
