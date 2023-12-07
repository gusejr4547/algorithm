package 프로그래머스;

import java.util.Arrays;

public class 사단_고음 {
    public static void main(String[] args) {
        사단_고음 Main = new 사단_고음();
        int n = Integer.MAX_VALUE;
        System.out.println(Main.solution(n));
    }

    public int solution(int n) {
        int answer = 0;

        // Math.pow(3, N) + 2*N  최소값
        // Math.pow((3 + 2*N), N) 최대값

        // N개의 *를 통해 나타낼 수 있는 범위 최소 > N-1개의 *를 통해 나타낼 수 있는 범위 최대
        // N개의 *를 통해 나타낼 수 있는 범위 최소 < N+1개의 *를 통해 나타낼 수 있는 범위 최소
        // 문자열 맨뒤는 무조건 ++ 이다

        int N = 1;
        // 3^20 은 int 범위를 넘음
        int[] min = new int[20];
        min[0] = 1;

        for (int i = 1; i < 20; i++) {
            min[i] = (int) Math.pow(3, i) + 2 * i;
        }

        // 각 고음 반복의 최소 숫자보다 작은 경우가 max 반복횟수임
        int iterate = min.length;
        for (int i = 1; i < min.length; i++) {
            if (n < min[i]) {
                iterate = i - 1;
                break;
            }
        }

        answer = dfs(iterate, n - 2, 0, 2);


        return answer;
    }

    public int dfs(int maxIterate, int curr, int multipleCount, int plusCount) {
        if (curr < 3) {
            return 0;
        }
        if (multipleCount > maxIterate) {
            return 0;
        }
        if (plusCount > maxIterate * 2) {
            return 0;
        }

        if (curr == 3 && plusCount == 2) {
            return 1;
        } else if(curr == 3) {
            return 0;
        }


        int sum = 0;
        if (curr % 3 == 0 && plusCount >= 2) {
            sum += dfs(maxIterate, curr / 3, multipleCount + 1, plusCount - 2);
        }
        sum += dfs(maxIterate, curr - 1, multipleCount, plusCount + 1);

        return sum;
    }
}
