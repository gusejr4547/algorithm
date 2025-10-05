package 프로그래머스.KAKAO_WINTER_INTERNSHIP_2024;


import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 가장_많이_받은_선물 {
    public static void main(String[] args) {
        가장_많이_받은_선물 Main = new 가장_많이_받은_선물();
        String[] friends = {"joy", "brad", "alessandro", "conan", "david"};
        String[] gifts = {"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"};
        System.out.println(Main.solution(friends, gifts));
    }

    public int solution(String[] friends, String[] gifts) {
        // 두 사람 사이에 선물 많이 준사람이 다음달에 선물을 하나 받음.
        // 없거나 같으면, 선물 지수가 큰사람이 작은사람에게 하나 받는다.
        // 선물지수 = 준선물 - 받은선물
        // 선물 지수 같으면 주고받지 않음.

        // 선물 가장 많이 받는 사람 선물 수?

        int N = friends.length;
        Map<String, Integer> nameMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            nameMap.put(friends[i], i);
        }

        int[][] gift = new int[N][N]; // 누가 누구한테 선물 줬는지
        int[] giftWeight = new int[N]; // 선물 지수
        for (String g : gifts) {
            StringTokenizer st = new StringTokenizer(g);
            int a = nameMap.get(st.nextToken());
            int b = nameMap.get(st.nextToken());
            // a -> b
            gift[a][b]++;
            // 준사람
            giftWeight[a]++;
            // 받은사람
            giftWeight[b]--;
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            int receive = 0; // i가 받는 선물 수
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }
                // 하나도 없거나 같으면
                if ((gift[i][j] == 0 && gift[j][i] == 0) || gift[i][j] == gift[j][i]) {
                    // 선물지수
                    if (giftWeight[i] > giftWeight[j]) {
                        receive++;
                    }
                } else {
                    if (gift[i][j] > gift[j][i]) {
                        receive++;
                    }
                }
            }
            answer = Math.max(answer, receive);
        }

        return answer;
    }

    // A -> B
//    public int solution(String[] friends, String[] gifts) {
//        int friendSize = friends.length;
//        Map<String, Integer> friendIdx = new HashMap<>();
//        int[][] total = new int[friendSize][friendSize];
//        int[] giftPoint = new int[friendSize];
//
//        for (int i = 0; i < friendSize; i++) {
//            friendIdx.put(friends[i], i);
//        }
//
//        for (String gift : gifts) {
//            String[] giftArr = gift.split(" ");
////            System.out.println(Arrays.toString(giftArr));
//            total[friendIdx.get(giftArr[0])][friendIdx.get(giftArr[1])]++;
//            giftPoint[friendIdx.get(giftArr[0])]++;
//            giftPoint[friendIdx.get(giftArr[1])]--;
//        }
//
//        int answer = 0;
//
//        for (int i = 0; i < friendSize; i++) {
//            int receiveCnt = 0;
//            for (int j = 0; j < friendSize; j++) {
//                if (i == j) continue;
//                if ((total[i][j] == 0 && total[j][i] == 0) || total[i][j] == total[j][i]) {
//                    if (giftPoint[i] > giftPoint[j]) {
//                        receiveCnt++;
//                    }
//                } else {
//                    if (total[i][j] > total[j][i]) {
//                        receiveCnt++;
//                    }
//                }
//            }
//            answer = Math.max(answer, receiveCnt);
//        }
//
//
//        return answer;
//    }
}
