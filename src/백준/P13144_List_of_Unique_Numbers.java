package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P13144_List_of_Unique_Numbers {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] seq = new int[N];
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

//        int N = 100000;
//        int[] seq = new int[N];
//        int n = 1;
//        for (int i = 0; i < N; i++) {
//            seq[i] = n;
//            n++;
//        }

        boolean[] number = new boolean[100001];
        long answer = 0;
        int left = 0;
        for (int i = 0; i < N; i++) {
            int target = seq[i];
            if (number[target]) {
                // 중복
                for (int j = left; j < i; j++) {
                    answer += i - j;
                    left++;
                    number[seq[j]] = false;
                    if (target == seq[j])
                        break;
                }
            }
            number[target] = true;
        }

        for (int i = left; i < N; i++) {
            answer += N - i;
        }

        System.out.println(answer);
    }
}
