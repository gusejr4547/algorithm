package NYPC.기출2025.Round2_B;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 버블 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 수열의 인접한 항을 교환하는 작업을 최대 K번 수행해서 AN - A1 의 값을 가장 크게 만들고 싶다.
        // A[i]를 0 idx 로 옮길 때 N번쨰 위치할 수 있는 최대값.
        // 끝에서 부터 최대 값 미리 구하기
        int[] max = new int[N];
        max[N - 1] = arr[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            max[i] = Math.max(max[i + 1], arr[i]);
        }

        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int remain = K - i; // arr[i]를 arr[0]으로 옮기기 위한 swap count 를 뺀 남은 횟수
            if (remain < 0) {
                break;
            }

            // i < j 이면 j는 swap으로 인한 영향이 없다.
            if (i < N - 1 - remain) {
                int range = N - 1 - remain;
                // range ~ N-1 까지 범위에서 최대값
                // range가 0 이하가 될 수도 있음.
                range = Math.max(0, range);

                answer = Math.max(answer, max[range] - arr[i]);
            } else {
                int range = N - 2 - remain;
                range = Math.max(0, range);

                answer = Math.max(answer, max[range] - arr[i]);
            }
        }

        System.out.println(answer);
    }

    private static void swap(int a, int b, int[] arr) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
