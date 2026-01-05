package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P34927_박스_쌓기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] weight = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        // 박스를 탑처럼 쌓는다.
        // 모든 박스는 자신 위에 쌓인 박스 무게 합이 자신의 무게 이하가 되게 쌓아야 함

        // 박스 위에는 최소 무게, 최대 개수

        // 오름차순 정렬
        Arrays.sort(weight);

        int count = 0;
        long sum = 0;
        for (int i = 0; i < N; i++) {
            if (sum <= weight[i]) {
                sum += weight[i];
                count++;
            }
        }

        System.out.println(count);
    }
}
