package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P27172_수_나누기_게임 {
    static int N;
    static int[] card, score;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        card = new int[N];
        Map<Integer, List<Integer>> divisors = new HashMap<>();
        Map<Integer, Integer> numToIdx = new HashMap<>();

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            card[i] = Integer.parseInt(st.nextToken());
            divisors.put(card[i], new ArrayList<>());
            numToIdx.put(card[i], i);
            max = Math.max(max, card[i]);
        }

        score = new int[N];

        // 에라스토테네스 체
        int[] pos = new int[max + 1];
        Arrays.fill(pos, -1);
        for (int i = 0; i < N; i++) {
            pos[card[i]] = i;
        }

        for (int i = 0; i < N; i++) {
            for (int num = card[i] * 2; num <= max; num += card[i]) {
                if (pos[num] != -1) {
                    score[i]++;
                    score[pos[num]]--;
                }
            }
        }


        // 약수
//        for (int i = 0; i < N; i++) {
//            for (int num = 1; num * num <= card[i]; num++) {
//                if (card[i] % num == 0) {
//                    divisors.get(card[i]).add(num);
//                    if (card[i] / num != num) {
//                        divisors.get(card[i]).add(card[i] / num);
//                    }
//                }
//            }
//        }
//
//        for (int i = 0; i < N; i++) {
//            for (int div : divisors.get(card[i])) {
//                if (numToIdx.containsKey(div)) {
//                    score[i]--;
//                    score[numToIdx.get(div)]++;
//                }
//            }
//        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(score[i]).append(" ");
        }

        System.out.println(sb);


    }
}
