package 프로그래머스.KAKAO_WINTER_INTERNSHIP_2024;

import java.util.HashSet;
import java.util.Set;

public class N1_카드게임 {
    public static void main(String[] args) {
        N1_카드게임 Main = new N1_카드게임();
        int coin = 3;
        int[] cards = {1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12};
        System.out.println(Main.solution(coin, cards));
    }

    // 1. n/3장 카드 뽑아 가짐
    // 2. 각 라운드 마다 카드 2장 뽑기. 카드 뭉치에 남은 카드 없으면 종료. 뽑은 카드 1장 가지려면 코인 1개 소모. 안가지면 버림.
    // 3. 카드 적힌 수의 합이 n+1이 되도록 2장 내면 다음 라운드. 없으면 종료.
    public int solution(int coin, int[] cards) {
        Set<Integer> handCard = new HashSet<>();
        Set<Integer> pickCard = new HashSet<>();
        int n = cards.length;
        for (int i = 0; i < n / 3; i++) {
            handCard.add(cards[i]);
        }
        int idx = n / 3;
        int answer = 0;
        while (true) {
            answer++;
            if (idx == cards.length) break;
            // 카드 뽑기
            for (int i = 0; i < 2; i++) {
                pickCard.add(cards[idx + i]);
            }
            idx += 2;

            boolean canNext = false;
            // 코인 0개
            for (int num : handCard) {
                int needNum = n + 1 - num;
                if (handCard.contains(needNum)) {
                    handCard.remove(num);
                    handCard.remove(needNum);
                    canNext = true;
                    break;
                }
            }
            if (canNext)
                continue;

            // 코인 1개
            if (coin >= 1) {
                for (int num : handCard) {
                    int needNum = n + 1 - num;
                    if (pickCard.contains(needNum)) {
                        handCard.remove(num);
                        pickCard.remove(needNum);
                        canNext = true;
                        coin -= 1;
                        break;
                    }
                }
            }
            if (canNext)
                continue;
            // 코인 2개
            if (coin >= 2) {
                for (int num : pickCard) {
                    int needNum = n + 1 - num;
                    if (pickCard.contains(needNum)) {
                        pickCard.remove(num);
                        pickCard.remove(needNum);
                        canNext = true;
                        coin -= 2;
                        break;
                    }
                }
            }
            if (!canNext) {
                break;
            }
        }

        return answer;
    }
}
