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
        for (int i = 0; i < N; i++) {
            card[i] = Integer.parseInt(st.nextToken());
            divisors.put(card[i], new ArrayList<>());
            numToIdx.put(card[i], i);
        }

        score = new int[N];

        // 안됨
//        for (int i = 0; i < N; i++) {
//            for (int j = i + 1; j < N; j++) {
//                // i와 j가 대결
//
//            }
//        }

        // 약수
        for (int i = 0; i < N; i++) {
            for (int num = 1; num * num <= card[i]; num++) {
                if (card[i] % num == 0) {
                    divisors.get(card[i]).add(num);
                    if (card[i] / num != num) {
                        divisors.get(card[i]).add(card[i] / num);
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int div : divisors.get(card[i])) {
                if (numToIdx.containsKey(div)) {
                    score[i]--;
                    score[numToIdx.get(div)]++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            sb.append(score[i]).append(" ");
        }

        System.out.println(sb);


    }
}
