package 프로그래머스;

public class 금과_은_운반하기 {
    public static void main(String[] args) {
        금과_은_운반하기 Main = new 금과_은_운반하기();
        int a = 90;
        int b = 500;
        int[] g = {70, 70, 0};
        int[] s = {0, 0, 500};
        int[] w = {100, 100, 2};
        int[] t = {4, 8, 1};
        System.out.println(Main.solution(a, b, g, s, w, t));
    }

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = Long.MAX_VALUE;

        long left = 0;
        long right = 2_000_000_000L * 100_000 * 2; // (a 최대값 1e9 + b 최대값 1e9) * (편도 시간 최대값 1e5 * 2)
        while (left <= right) {
            long mid = (left + right) / 2;

            if (isPossible(mid, a, b, g, s, w, t)) {
                answer = Math.min(mid, answer);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    public boolean isPossible(long time, int a, int b, int[] g, int[] s, int[] w, int[] t) {
        int len = g.length;
        long total = 0;
        long gold = 0;
        long silver = 0;

        for (int i = 0; i < len; i++) {
            long moveCnt = time / (t[i] * 2L);
            if (time % (t[i] * 2L) >= t[i])
                moveCnt++;

            long totalWeight = Math.min(moveCnt * w[i], g[i] + s[i]);
            total += totalWeight;
            gold += Math.min(totalWeight, g[i]);
            silver += Math.min(totalWeight, s[i]);
        }

        return a + b <= total && a <= gold && b <= silver;
    }
}
