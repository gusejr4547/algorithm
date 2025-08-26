package 코드트리.ACPC2025;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EC2_인스턴트_안정화 {
    static int N, original, answer;
    static int[] power, select;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        power = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            power[i] = Integer.parseInt(st.nextToken());
        }

        // 2개의 인스턴스를 선택해서 버스트 모드로 전환
        // 버스트 모드는 성능 2배
        // 안정성 점수 계산 |p[i] - p[i+1]| 의 합
        // 안정성 점수 최대로


        // 기본 안정성 점수 계산
        original = 0;
        for (int i = 0; i < N - 1; i++) {
            original += Math.abs(power[i] - power[i + 1]);
        }

        // 2개의 인스턴스 선택 => 5000 * 4999 / 2 = 12000000
        answer = 0;
        select = new int[2];
        selectBurstMode(0, 0);

        System.out.println(answer);
    }

    private static void selectBurstMode(int count, int idx) {
        if (count == 2) {
            int score = original;
            int i = select[0];
            int j = select[1];
            // 인접
            if (i + 1 == j) {
                if (i > 0) {
                    score -= Math.abs(power[i - 1] - power[i]);
                    score += Math.abs(power[i - 1] - power[i] * 2);
                }
                score -= Math.abs(power[i] - power[j]);
                score += Math.abs(power[i] * 2 - power[j] * 2);

                if (j < N - 1) {
                    score -= Math.abs(power[j] - power[j + 1]);
                    score += Math.abs(power[j] * 2 - power[j + 1]);
                }
            }
            // 떨어짐
            else {
                if (i > 0) {
                    score -= Math.abs(power[i - 1] - power[i]);
                    score += Math.abs(power[i - 1] - power[i] * 2);
                }
                score -= Math.abs(power[i] - power[i + 1]);
                score += Math.abs(power[i] * 2 - power[i + 1]);

                score -= Math.abs(power[j - 1] - power[j]);
                score += Math.abs(power[j - 1] - power[j] * 2);

                if (j < N - 1) {
                    score -= Math.abs(power[j] - power[j + 1]);
                    score += Math.abs(power[j] * 2 - power[j + 1]);
                }
            }

            answer = Math.max(answer, score);
            return;
        }

        for (int i = idx; i < N; i++) {
            select[count] = i;
            selectBurstMode(count + 1, i + 1);
        }
    }
}
