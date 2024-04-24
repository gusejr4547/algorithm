package 프로그래머스.KAKAO_BLIND_RECRUITMENT_2023;

import java.util.ArrayDeque;
import java.util.Arrays;

public class 택배_배달과_수거하기 {
    public static void main(String[] args) {
        택배_배달과_수거하기 Main = new 택배_배달과_수거하기();
        int cap = 2;
        int n = 7;
        int[] deliveries = {1, 0, 2, 0, 1, 0, 2};
        int[] pickups = {0, 2, 0, 1, 0, 2, 0};
        System.out.println(Main.solution(cap, n, deliveries, pickups));
    }

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int delivery = 0;
        int pickup = 0;
        for (int i = n - 1; i >= 0; i--) {
            delivery -= deliveries[i];
            pickup -= pickups[i];

            while (delivery < 0 || pickup < 0) {
                delivery += cap;
                pickup += cap;
                answer += (i + 1) * 2L;
            }
        }

        return answer;
    }
}
