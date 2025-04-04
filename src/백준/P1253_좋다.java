package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P1253_좋다 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<Integer> goodNumber = new HashSet<>();
        int[] numArr = new int[N];
        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(numArr);

        int answer = 0;

        for (int i = 0; i < N; i++) {
            if (makeNum(i, N - 1, numArr)) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static boolean makeNum(int curIdx, int max, int[] numArr) {
        int target = numArr[curIdx];
        int left = 0;
        int right = max;

        while (left < right) {
            if (left == curIdx) left++;
            if (right == curIdx) right--;

            if (left >= right) break;

            if (target < numArr[left] + numArr[right]) {
                right--;
            } else if (target > numArr[left] + numArr[right]) {
                left++;
            } else {
                return true;
            }
        }
        return false;
    }
}
