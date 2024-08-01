package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P11722_가장_긴_감소하는_부분_수열 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] len = new int[N];
        int result = 0;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            len[i] = 1;
            for (int j = 0; j < i; j++) {
                if (num < arr[j]) {
                    len[i] = Math.max(len[i], len[j] + 1);
                }
            }
            result = Math.max(result, len[i]);
        }
//        System.out.println(Arrays.toString(len));
        System.out.println(result);
    }
}
