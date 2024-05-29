package 프로그래머스;

import java.util.Arrays;

public class 징검다리_건너기 {
    public static void main(String[] args) {
        징검다리_건너기 Main = new 징검다리_건너기();
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        System.out.println(Main.solution(stones, k));
    }

    // 디딤돌의 숫자는 한 번 밟을 때마다 1씩 줄어듭니다.
    // 0이 되면 더 이상 밟을 수 없으며, 한번에 여러 칸을 건너 뛸 수 있습니다. k = 건너뛸수있는 최대 값
    // 다음으로 밟을 수 있는 디딤돌이 여러 개인 경우 무조건 가장 가까운 디딤돌로만 건너뛸 수 있습니다.
    // 최대 몇 명 이동가능?
    public int solution(int[] stones, int k) {
        int N = stones.length;
        int answer = 0;

        int min = 0;
        int max = 200_000_000;

        while (min <= max) {
            int mid = (min + max) / 2;
            if (valid(mid, k, stones)) {
                min = mid + 1;
                answer = mid;
            } else {
                max = mid - 1;
            }
        }

        return answer;
    }

    private boolean valid(int mid, int k, int[] stones) {
        int count = 0;
        for (int i = 0; i < stones.length; i++) {
            if (stones[i] < mid) {
                count++;
                if (count >= k) {
                    return false;
                }
            } else {
                count = 0;
            }
        }
        return true;
    }
}
