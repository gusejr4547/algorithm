package NYPC.기출2025.Round1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 같이_던전_도실래요 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] playableTime = new int[N * 2];
        for (int i = 0; i < N * 2; i++) {
            playableTime[i] = Integer.parseInt(st.nextToken());
        }

        // 방 1개 클리어 M분
        // 플레이 시간은 두명중에 작은 값
        // 시너지 = 시간합 - 시간차이절대값
        // 최종스코어 = 시너지 * 클리어 방 수
        // 모든 사람을 이용해 2인팟을 만든다음에 최종 스코어가 최대로

        // 최대한 플레이 시간이 같은 사람끼리
        Arrays.sort(playableTime);

        long answer = 0;
        for (int i = 0; i < N * 2; i += 2) {
            // i, i+1 사람끼리 파티
            long synergy = playableTime[i] + playableTime[i + 1] - (Math.abs(playableTime[i] - playableTime[i + 1]));
            long time = Math.min(playableTime[i], playableTime[i + 1]);
            // 방몇개?
            long clearCnt = time / M;
            answer += synergy * clearCnt;
        }

        System.out.println(answer);
    }
}
