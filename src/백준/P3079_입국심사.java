package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P3079_입국심사 {
    static int N, M;
    static long max, answer;
    static int[] entryTime;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        entryTime = new int[N];
        for (int i = 0; i < N; i++) {
            entryTime[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, entryTime[i]);
        }

        answer = Long.MAX_VALUE;
        searchMinTime();
        System.out.println(answer);
    }

    public static void searchMinTime() {
        long low = 0;
        long high = max * M;

        while (low <= high) {
            long mid = (low + high) / 2;
            long total = 0;
            for (int time : entryTime) {
                long count = mid / time;

                if (total >= M) {
                    break;
                }

                total += count;
            }

            if (total >= M) {
                high = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                low = mid + 1;
            }
        }
    }
}
