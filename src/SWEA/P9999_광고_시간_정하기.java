package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P9999_광고_시간_정하기 {
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            int adTime = Integer.parseInt(br.readLine());
            int N = Integer.parseInt(br.readLine());

            int[][] peekTimeList = new int[N][2];
            int[] prefixSum = new int[N];
            // ei < si+1 (1 ≤ i < N)을 만족한다.
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int start = peekTimeList[i][0] = Integer.parseInt(st.nextToken());
                int end = peekTimeList[i][1] = Integer.parseInt(st.nextToken());

                if (i == 0) {
                    prefixSum[i] = end - start;
                } else {
                    prefixSum[i] = prefixSum[i - 1] + (end - start);
                }
            }

            int result = 0;
            for (int i = 0; i < N; i++) {
                // i번째 피크 시작시간에 광고
                int adEndTime = peekTimeList[i][0] + adTime;

                // adEndTime은 몇번째 피크까지 포함?
                int maxIdx = search(peekTimeList, adEndTime);

                int timeSum = prefixSum[maxIdx - 1];
                if (i != 0) timeSum -= prefixSum[i - 1];

                // 걸쳐있는거 있으면 추가
                if (maxIdx != N && peekTimeList[maxIdx][0] < adEndTime) {
                    timeSum += adEndTime - peekTimeList[maxIdx][0];
                }

                result = Math.max(result, timeSum);
            }

            sb.append(result).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static int search(int[][] peekTimeList, int endTIme) {
        int left = 0;
        int right = peekTimeList.length - 1;
        int result = peekTimeList.length;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (peekTimeList[mid][1] > endTIme) {
                right = mid - 1;
                result = mid;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
}
