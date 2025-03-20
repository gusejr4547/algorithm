package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 오름차순으로 정렬했을 때 K번째 수
public class P2877_4와_7 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long K = Long.parseLong(br.readLine());

        includeZero(K);

        // 일단 필요한 자리수 계산
        long count = 1;
        int bit = 0;
        while (K >= count) {
            K -= count;
            count *= 2;
            bit++;
        }

//        for (; K >= count; K -= count, count *= 2, bit++) ;

//        System.out.println(bit);
//        System.out.println(K);
        // K를 2진수로 나타내고 0은 4, 1은 7로 변환

        int[] arr = new int[bit];
//        Arrays.fill(arr, 4);

        int idx = bit - 1;
        while (K > 0) {
            arr[idx] = (int) (K % 2);
            K /= 2;
            idx--;
        }

//        System.out.println(Arrays.toString(arr));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i] == 0 ? 4 : 7);
        }

        System.out.println(sb);
    }

    // 0이 포함된 수라면? 0, 4를 사용한다고 가정
    static void includeZero(long K) {
        // 0은 맨 앞에 나오면 안되니까 무조건 다른 수를 새워두고 계산해야함.
        // 1자리인 경우 '4' 1가지 경우 뿐임
        K -= 1;

        // 이외의 경우
        int bit = 1; // 무조건 1자리 차지함
        long count = 1;
        while (K >= count) {
            K -= count;
            count *= 2;
            bit++;
        }

        int[] arr = new int[bit];
        arr[0] = 1; // 맨 앞은 0이 될 수 없음. 고정

        int idx = bit - 1;
        while (K > 0) {
            arr[idx] = (int) (K % 2);
            K /= 2;
            idx--;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i] == 0 ? 0 : 4);
        }

        System.out.println(sb);
    }
}
