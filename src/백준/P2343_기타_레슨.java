package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2343_기타_레슨 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] time = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        // 순서는 지켜야함
        // N개의 강의를 M개로 나누어야함. 가능한 최소 크기로 나누기

        int maxTime = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            maxTime = Math.max(maxTime, time[i]);
            sum += time[i];
        }


        int answer = 0;
        int left = maxTime;
        int right = sum;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (getCount(time, mid) <= M) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static int getCount(int[] time, int max) {
        int cnt = 1;
        int sum = 0;
        for (int i = 0; i < time.length; i++) {
            if (sum + time[i] > max) {
                cnt++;
                sum = 0;
            }
            sum += time[i];
        }

        return cnt;
    }
}
