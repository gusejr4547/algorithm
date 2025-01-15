package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Base64;
import java.util.StringTokenizer;

// 계란으로 계란을 치게 되면 각 계란의 내구도는 상대 계란의 무게만큼 깎이게 된다
// N <= 8
// 완탐 => 7^8 >> 가능
public class P16987_계란으로_계란치기 {
    static int N, answer;
    static EggInfo[] eggInfos;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        eggInfos = new EggInfo[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggInfos[i] = new EggInfo(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int[] eggDurabilityArr = Arrays.stream(eggInfos).mapToInt(eggInfo -> eggInfo.durability).toArray();

        breakEgg(0, eggDurabilityArr);
        System.out.println(answer);
    }

    private static void breakEgg(int eggIdx, int[] eggDurabilityArr) {
        // 종료
        if (eggIdx == N) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if (eggDurabilityArr[i] <= 0) cnt++;
            }
            answer = Math.max(answer, cnt);
            return;
        }

        // eggIdx의 egg가 깨진거면 다음 egg로
        if (eggDurabilityArr[eggIdx] <= 0) {
            breakEgg(eggIdx + 1, eggDurabilityArr);
        } else {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if (eggIdx == i) continue;

                if (eggDurabilityArr[i] >= 0) {
                    cnt++;
                    // 내구도 줄이기
                    eggDurabilityArr[eggIdx] -= eggInfos[i].weight;
                    eggDurabilityArr[i] -= eggInfos[eggIdx].weight;
                    breakEgg(eggIdx + 1, eggDurabilityArr);
                    // 내구도 복구
                    eggDurabilityArr[eggIdx] += eggInfos[i].weight;
                    eggDurabilityArr[i] += eggInfos[eggIdx].weight;
                }
            }

            // 들고있는 계란 말고 다른 계란 없는 경우
            if (cnt == 0) {
                breakEgg(eggIdx + 1, eggDurabilityArr);
            }
        }
    }

    private static class EggInfo {
        int durability, weight;

        public EggInfo(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }
}
