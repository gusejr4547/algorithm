package 프로그래머스.KAKAO_BLIND_RECRUITMENT_2023;

import java.util.Arrays;

public class 이모티콘_할인행사 {
    public static void main(String[] args) {
        이모티콘_할인행사 Main = new 이모티콘_할인행사();
        int[][] users = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        int[] emoticons = {1300, 1500, 1600, 4900};
        System.out.println(Arrays.toString(Main.solution(users, emoticons)));
    }

    // 할인율은 10%, 20%, 30%, 40% 중 하나
    int[] discountArr = {10, 20, 30, 40};
    int[] select;
    int[] answer;

    public int[] solution(int[][] users, int[] emoticons) {
        select = new int[emoticons.length];
        answer = new int[2];
        selectDiscount(0, users, emoticons);
        return answer;
    }

    private void selectDiscount(int depth, int[][] users, int[] emotions) {
        if (depth == select.length) {
            int plusService = 0;
            int saleSum = 0;
            for (int i = 0; i < users.length; i++) {
                int sum = 0;
                for (int j = 0; j < emotions.length; j++) {
                    if (select[j] >= users[i][0]) {
                        sum += emotions[j] * (100 - select[j]) / 100;
                    }
                }
                if (sum >= users[i][1]) {
                    plusService++;
                } else {
                    saleSum += sum;
                }
            }

            // 더 이득인거 저장
            if (plusService > answer[0]) {
                answer[0] = plusService;
                answer[1] = saleSum;
            } else if (plusService == answer[0] && saleSum > answer[1]) {
                answer[1] = saleSum;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            select[depth] = discountArr[i];
            selectDiscount(depth + 1, users, emotions);
        }
    }
}
