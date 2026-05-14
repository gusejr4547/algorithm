package 프로그래머스;

import java.util.Arrays;

public class 자신감_점수 {
    public static void main(String[] args) {
        자신감_점수 Main = new 자신감_점수();
        int N = 10;
        int[] warriors = {5,3,7,5,3,3,6,1,8,7};
        System.out.println(Main.solution(N, warriors));
    }

    // N명 원형 배치
    // 인접한 양옆의 전투력을 확인할 수 있다.
    // 양옆이 모두 자기보다 낮으면, 자신감이 있는 상태가 된다.
    // 자신감 점수 = 자신감 있는 상태의 사람 수

    // 한명을 뺄 수 있다
    // 한명을 빼서 자신감 점수 최대 구하기

    // 4 <= N <= 1000

    public int solution(int N, int[] warriors) {
        int answer = 0;

        // 1명을 빼서 자신감 점수 구하기
        for (int i = 0; i < N; i++) {
            // i번을 배고
            int[] b = new int[N - 1];
            int idx = 0;
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    b[idx++] = warriors[j];
                }
            }
            int res = getScore(N - 1, b);
            answer = Math.max(answer, res);
        }

        return answer;
    }

    private int getScore(int n, int[] warriors) {
//        System.out.println(Arrays.toString(warriors));
        int score = 0;

        for (int i = 0; i < n; i++) {
            int prev = (i + n - 1) % n;
            int next = (i + 1) % n;

//            System.out.println(prev + "," + next);
            if (warriors[i] > warriors[prev] && warriors[i] > warriors[next]) {
                score++;
            }
        }

        return score;
    }
}
