package 프로그래머스;

import java.util.*;

public class 광물_캐기 {
    public static void main(String[] args) {
        광물_캐기 Main = new 광물_캐기();
        int[] picks = {1, 3, 2};
        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
        System.out.println(Main.solution(picks, minerals));
    }

    // 피로도 표
    /*
      다 철 돌
    다 1 1 1
    철 5 1 1
    돌 25 5 1
     */

    // 광물 5개를 캔 후에는 더 이상 사용할 수 없음.
    // 곡괭이 선택 잘해서 피로도 최소.
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;

        int pickTotal = Arrays.stream(picks).sum();

        // 광물 5개씩 잘라

        List<int[]> tiredList = new ArrayList<>();

        int pickCount = 0;
        for (int i = 0; i < minerals.length; i += 5) {
            int end = i + 5 < minerals.length ? 5 : minerals.length - i;

            int[] tired = new int[3];
            for (int j = 0; j < end; j++) {
                String mineral = minerals[i + j];
                if ("diamond".equals(mineral)) {
                    tired[0] += 25;
                    tired[1] += 5;
                    tired[2] += 1;
                } else if ("iron".equals(mineral)) {
                    tired[0] += 5;
                    tired[1] += 1;
                    tired[2] += 1;
                } else {
                    tired[0] += 1;
                    tired[1] += 1;
                    tired[2] += 1;
                }
            }
            tiredList.add(tired);
            pickCount++;
            if (pickCount == pickTotal) {
                break;
            }
        }

        tiredList.sort((o1, o2) -> o2[0] - o1[0]);

        // 피로도 큰거부터 다이아 곡괭이 사용
        for (int[] tired : tiredList) {
            if (picks[0] > 0) {
                answer += tired[2];
                picks[0]--;
            } else if (picks[1] > 0) {
                answer += tired[1];
                picks[1]--;
            } else if (picks[2] > 0) {
                answer += tired[0];
                picks[2]--;
            }

//            System.out.println(Arrays.toString(tired));
        }

        return answer;
    }
}
