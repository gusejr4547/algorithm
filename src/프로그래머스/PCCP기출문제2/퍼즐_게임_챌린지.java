package 프로그래머스.PCCP기출문제2;

public class 퍼즐_게임_챌린지 {
    public static void main(String[] args) {
        퍼즐_게임_챌린지 Main = new 퍼즐_게임_챌린지();
        int[] diffs = {1, 5, 3};
        int[] times = {2, 4, 7};
        long limit = 30;

        System.out.println(Main.solution(diffs, times, limit));
    }

    // diff <= level 안틀리고 time_cur 만큼 시간
    // diff > level, diff-level 번 틀림, 틀릴때마다 time_cur만큼 시간 소요 => (diff-level)*(time_cur + time_prev) + time_cur 만큼 시간 소요
    public int solution(int[] diffs, int[] times, long limit) {

        // diff범위기 1 <= diff <= 100000 이므로 level도 같다고 생각
        int answer = 0;
        int levelMin = 1;
        int levelMax = 100000;

        while (levelMin <= levelMax) {
            int level = (levelMin + levelMax) / 2;
            long totalTime = calTotalTime(level, diffs, times, limit);
            if (totalTime > limit) {
                levelMin = level + 1;
            } else {
                answer = level;
                levelMax = level - 1;
            }
        }

        return answer;
    }

    private long calTotalTime(int level, int[] diffs, int[] times, long limit) {
        // diffs[0] = 1 이니까 무조건 풀수 있다.
        int size = diffs.length;
        long totalTime = times[0];

        for (int i = 1; i < size; i++) {
            if (diffs[i] > level) {
                totalTime += (diffs[i] - level) * (times[i] + times[i - 1]) + times[i];
            } else {
                totalTime += times[i];
            }

            if (totalTime > limit) {
                return limit + 1;
            }
        }

        return totalTime;
    }
}
