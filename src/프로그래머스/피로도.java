package 프로그래머스;

public class 피로도 {
    public static void main(String[] args) {
        피로도 Main = new 피로도();
        int k = 80;
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
        System.out.println(Main.solution(k, dungeons));
    }

    boolean[] visit;
    int[] order;
    int max;

    public int solution(int k, int[][] dungeons) {
        visit = new boolean[dungeons.length];
        order = new int[dungeons.length];
        max = 0;
        permutation(0, dungeons, k);
        return max;
    }

    private void permutation(int depth, int[][] dungeons, int k) {
        if (depth == order.length) {
            // 던전 순서대로 입장
            int enterCnt = 0;
            for (int o : order) {
                // 입장가능?
                if (k >= dungeons[o][0]) {
                    k -= dungeons[o][1];
                } else {
                    break;
                }
                enterCnt++;
            }

            max = Math.max(max, enterCnt);
            return;
        }

        for (int i = 0; i < dungeons.length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                order[depth] = i;
                permutation(depth + 1, dungeons, k);
                visit[i] = false;
            }
        }
    }
}
