package 프로그래머스;

import java.util.*;

public class 혼자_놀기의_달인 {
    public static void main(String[] args) {

    }

    public int solution(int[] cards) {
        int answer = 0;

        int N = cards.length;

        // 여러개의 그룹이 생길 수 있다.
        // 가장 큰 2개의 그룹의 상자 개수 곱하기
        // 그룹이 1개면 0

        // 사이클
        boolean[] visit = new boolean[N];
        List<Integer> groupSizes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (visit[i]) {
                continue;
            }

            int cur = i;
            int count = 0;

            while (!visit[cur]) {
                visit[cur] = true;
                count++;
                cur = cards[cur] - 1;
            }

            groupSizes.add(count);
        }

        groupSizes.sort(Comparator.reverseOrder());

        answer = groupSizes.size() == 1 ? 0 : groupSizes.get(0) * groupSizes.get(1);

        return answer;
    }
}
