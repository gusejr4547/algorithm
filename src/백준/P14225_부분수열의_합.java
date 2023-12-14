package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class P14225_부분수열의_합 {
    static int N;
    static List<Integer> numberList = new ArrayList<>();
    static boolean[] makeNumber;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numberList.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(numberList);
        int maxSum = 0;
        for (Integer n : numberList) {
            maxSum += n;
        }

        makeNumber = new boolean[maxSum + 1];

        for (int count = 1; count <= N; count++) {
            combination(0, count, 0, 0);
        }

        int answer = 0;
        for (int i = 1; i <= maxSum; i++) {
            if (!makeNumber[i]) {
                answer = i;
                break;
            }
        }
        if(answer == 0){
            answer = maxSum + 1;
        }

        System.out.println(answer);
    }

    public static void combination(int count, int limit, int idx, int sum) {
        if (count == limit) {
            makeNumber[sum] = true;
            return;
        }

        for (int i = idx; i < N; i++) {
            combination(count + 1, limit, i + 1, sum + numberList.get(i));
        }
    }
}
