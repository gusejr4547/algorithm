package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class P2295_세_수의_합 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        Set<Integer> numSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            numSet.add(arr[i]);
        }

        // 오른차순 정렬
        Arrays.sort(arr);

        // 2개 합친 수 저장
        Set<Integer> sumSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sumSet.add(arr[i] + arr[j]);
            }
        }

        // arr[i] 만들수있는지?
        int answer = 0;
        for (int i = N - 1; i >= 0; i--) {
            for (int n : numSet) {
                if (sumSet.contains(arr[i] - n)) {
                    answer = arr[i];
                    break;
                }
            }
            if (answer != 0) {
                break;
            }
        }

        System.out.println(answer);
    }
}
