package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P20159_동작_그만_밑장_빼기냐 {
    static int N;
    static int[] cards;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        cards = new int[N];
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        int[] odd = new int[N / 2 + 1];
        int[] even = new int[N / 2 + 1];

        int idx = 0;
        for (int i = 1; i <= N / 2; i++) {
            odd[i] = odd[i - 1] + cards[idx++];
            even[i] = even[i - 1] + cards[idx++];
        }

//        System.out.println(Arrays.toString(odd));
//        System.out.println(Arrays.toString(even));

        // 밑장 안뺐을 때
        int result = odd[N / 2];

        // 내 차례 밑장 빼기
        for (int i = 0; i <= N / 2; i++) {
            int sum = odd[i] + (even[N / 2] - even[i]);
//            System.out.println(sum);
            result = Math.max(result, sum);
        }

        // 상대 차례 밑장 빼기
        for (int i = 0; i < N / 2; i++) {
            int sum = odd[i + 1];
            sum += even[N / 2 - 1] - even[i];
//            System.out.println(sum);
            result = Math.max(result, sum);
        }
        System.out.println(result);
    }
}
