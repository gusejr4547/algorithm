package 프로그래머스;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 에어컨 {
    public static void main(String[] args) {
        에어컨 Main = new 에어컨();
        int temperature = -10;
        int t1 = -5;
        int t2 = 5;
        int a = 5;
        int b = 1;
        int[] onboard = {0, 0, 0, 0, 0, 1, 0};
        System.out.println(Main.solution(temperature, t1, t2, a, b, onboard));
    }

    // 에어컨의 희망온도와 실내온도가 다르다면 매 분 전력을 a만큼 소비하고
    // 희망온도와 실내온도가 같다면 매 분 전력을 b만큼 소비합니다.
    // 에어컨이 꺼져 있다면 전력을 소비하지 않으며, 에어컨을 켜고 끄는데 필요한 시간과 전력은 0이라고 가정합니다.
    // 차내에 승객이 탑승 중일 때, 실내온도를 t1 ~ t2도 사이로 유지하면서, 에어컨의 소비전력을 최소화
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {

        // 온도 범위 -10 ~ 40 이므로 전체적으로 +10
        temperature += 10;
        t1 += 10;
        t2 += 10;

        // 설정할 수 있는 온도 범위
        int maxTemperature = Math.max(t2, temperature);
        int minTemperature = Math.min(t1, temperature);

        // 1000초 * a의 가장 큰값
        int maxValue = 1000 * 100;

        int[][] dp = new int[onboard.length][Math.max(temperature, t2) + 1]; // [a][b] a시간에 b온도를 맞추기 위한 최소비용

        for (int i = 0; i < onboard.length; i++) {
            Arrays.fill(dp[i], maxValue);
        }

        // 0초의 온도는 temperature
        dp[0][temperature] = 0;

        for (int time = 1; time < onboard.length; time++) {
            int maxTemp = maxTemperature;
            int minTemp = minTemperature;

            if (onboard[time] == 1) {
                minTemp = t1;
                maxTemp = t2;
            }

            for (int tp = minTemp; tp <= maxTemp; tp++) {
                int minEnergy = maxValue;
                if (tp > temperature) {
                    if (tp - 1 >= 0) {
                        minEnergy = Math.min(minEnergy, dp[time - 1][tp - 1] + a);
                    }
                    if (tp + 1 < dp[0].length) {
                        minEnergy = Math.min(minEnergy, dp[time - 1][tp + 1]);
                    }
                    minEnergy = Math.min(minEnergy, dp[time - 1][tp] + b);
                } else if (tp < temperature) {
                    if (tp - 1 >= 0) {
                        minEnergy = Math.min(minEnergy, dp[time - 1][tp - 1]);
                    }
                    if (tp + 1 < dp[0].length) {
                        minEnergy = Math.min(minEnergy, dp[time - 1][tp + 1] + a);
                    }
                    minEnergy = Math.min(minEnergy, dp[time - 1][tp] + b);
                } else if (tp == temperature) {
                    if (tp + 1 < dp[0].length) {
                        minEnergy = Math.min(minEnergy, dp[time - 1][tp + 1]);
                    }
                    if (tp - 1 >= 0) {
                        minEnergy = Math.min(minEnergy, dp[time - 1][tp - 1]);
                    }
                    minEnergy = Math.min(minEnergy, dp[time - 1][tp]);
                }

                dp[time][tp] = minEnergy;
            }
//            System.out.println(Arrays.toString(dp[time]));
        }
        int answer = maxValue;

        for (int i = minTemperature; i <= maxTemperature; i++) {
            answer = Math.min(dp[onboard.length - 1][i], answer);
        }

        return answer;
    }
}
