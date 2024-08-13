package 프로그래머스;

import java.util.Arrays;

public class 스티커_모으기2 {
    public static void main(String[] args) {
        스티커_모으기2 Main = new 스티커_모으기2();
        int[] sticker = {3};
        System.out.println(Main.solution(sticker));
    }

    public int solution(int sticker[]) {
        int stickerSize = sticker.length;
        int[] memo1 = new int[stickerSize]; // 1번째 스티커 뜯고 시작, memo[i] i번째 스티커를 뜯는 경우 최대 숫자
        int[] memo2 = new int[stickerSize]; // 2번째 스티커 뜯고 시작, memo[i] i번째 스티커를 뜯는 경우 최대 숫자

        if (stickerSize == 1) {
            return sticker[0];
        } else if (stickerSize == 2) {
            return Math.max(sticker[0], sticker[1]);
        }

        for (int i = 0; i < stickerSize - 1; i++) {
            if (i < 2) {
                memo1[i] = sticker[i];
            } else if (i < 3) {
                memo1[i] = memo1[i - 2] + sticker[i];
            } else {
                memo1[i] = Math.max(memo1[i - 2] + sticker[i], memo1[i - 3] + sticker[i]);
            }
        }

        for (int i = 1; i < stickerSize; i++) {
            if (i < 2) {
                memo2[i] = sticker[i];
            } else if (i < 3) {
                memo2[i] = memo2[i - 2] + sticker[i];
            } else {
                memo2[i] = Math.max(memo2[i - 2] + sticker[i], memo2[i - 3] + sticker[i]);
            }
        }

//        System.out.println(Arrays.toString(memo1));
//        System.out.println(Arrays.toString(memo2));

        int max = 0;
        for (int i = 0; i < stickerSize; i++) {
            max = Math.max(max, Math.max(memo1[i], memo2[i]));
        }
        return max;
    }
}
