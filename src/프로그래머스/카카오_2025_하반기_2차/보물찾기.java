package 프로그래머스.카카오_2025_하반기_2차;

import java.util.function.Function;

public class 보물찾기 {
    // 각 열의 최대 깊이를 담은 1차원 정수 배열 depth와 사용 가능한 총비용을 나타내는 정수 money가 매개변수로 주어집니다.
    // 또한 굴착 로봇이 특정 열을 파도록 하는 excavate 함수가 주어집니다.
    // 비용은 col의 depth만큼 필요
    // excavate는 보물을 찾은 경우 0, 보물이 왼쪽 방향에 있다면 -1, 오른쪽 방향에 있다면 1을 return

    public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {
        int w = depth.length;
        int l = 0;
        int r = w - 1;

        while (l <= r) {
            int mid = (l + r) / 2;
            int res = excavate.apply(mid);
            if (res == 0) {
                return mid;
            } else if (res == 1) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return 0;
    }
}
