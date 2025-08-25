package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 조건
// 2 <= i <= N-1일 때,  i-1, i+1번째 중 하나는 i번째 보다 키가 크고, 다른 하나는 i보다 키가 작으면, i번째는 튀지 않는다. 그외는 튄다.
// 1, N번째는 무조건 튄다.

// 키는 1 ~ N까지 서로 다르게 주어짐

// 벨은 1초마다 서로 다른 두 Bangboo의 위치를 맞바꿀 수 있다
// 벨이 원하는 만큼 Bangboo들의 위치를 바꾸었을 때, 웅나한 Bangboo들의 수의 최댓값과 최대한 많은 Bangboo들이 웅나해질 때까지 걸리는 최소 시간을 계산
public class P34078_벨과_와이즈의_가게_홍보 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬하면 그게 최적이다.
        // 위치를 서로 바꾸면서 몇번만에 완성하나?
        int[] desc = new int[N];
        for (int i = 0; i < N; i++) {
            desc[i] = N - arr[i] + 1;
        }

        int ascSwap = minSwapCnt(arr);
        int descSwap = minSwapCnt(desc);
//        System.out.println(ascSwap);
        int min = Math.min(ascSwap, descSwap);
        System.out.println(N - 2 + " " + min);
    }

    // 1~n까지 숫자가 배열에 있다고 가정
    private static int minSwapCnt(int[] arr) {
        int N = arr.length;
        boolean[] visit = new boolean[N];
        int swapCnt = 0;

        for (int i = 0; i < N; i++) {
            if (visit[i]) {
                continue;
            }

            int idx = arr[i] - 1;
            int len = 0;
            while (!visit[idx]) {
                visit[idx] = true;
                idx = arr[idx] - 1;
                len++;
            }

            swapCnt += len - 1;
        }

        return swapCnt;
    }
}
