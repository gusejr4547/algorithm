package 프로그래머스.KAKAO_WINTER_INTERNSHIP_2024;


import java.util.HashMap;
import java.util.Map;

public class 가장_많이_받은_선물 {
    public static void main(String[] args) {
        가장_많이_받은_선물 Main = new 가장_많이_받은_선물();
        String[] friends = {"joy", "brad", "alessandro", "conan", "david"};
        String[] gifts = {"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"};
        System.out.println(Main.solution(friends, gifts));
    }

    // A -> B
    public int solution(String[] friends, String[] gifts) {
        int friendSize = friends.length;
        Map<String, Integer> friendIdx = new HashMap<>();
        int[][] total = new int[friendSize][friendSize];
        int[] giftPoint = new int[friendSize];

        for (int i = 0; i < friendSize; i++) {
            friendIdx.put(friends[i], i);
        }

        for (String gift : gifts) {
            String[] giftArr = gift.split(" ");
//            System.out.println(Arrays.toString(giftArr));
            total[friendIdx.get(giftArr[0])][friendIdx.get(giftArr[1])]++;
            giftPoint[friendIdx.get(giftArr[0])]++;
            giftPoint[friendIdx.get(giftArr[1])]--;
        }

        int answer = 0;

        for (int i = 0; i < friendSize; i++) {
            int receiveCnt = 0;
            for (int j = 0; j < friendSize; j++) {
                if (i == j) continue;
                if ((total[i][j] == 0 && total[j][i] == 0) || total[i][j] == total[j][i]) {
                    if (giftPoint[i] > giftPoint[j]) {
                        receiveCnt++;
                    }
                } else {
                    if (total[i][j] > total[j][i]) {
                        receiveCnt++;
                    }
                }
            }
            answer = Math.max(answer, receiveCnt);
        }


        return answer;
    }
}
