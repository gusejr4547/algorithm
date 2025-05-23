package 프로그래머스.PCCP모의고사1;

public class 체육대회 {
    public static void main(String[] args) {
        체육대회 Main = new 체육대회();
        int[][] ability = {{40, 10, 10}, {20, 5, 0}, {30, 30, 30}, {70, 0, 70}, {100, 100, 100}};
        System.out.println(Main.solution(ability));
    }

    // dp or 조합
    int N, M, answer;
    boolean[] select;

    public int solution(int[][] ability) {
        N = ability.length;
        M = ability[0].length;
        select = new boolean[N];

        combination(0, 0, ability);

        return answer;
    }

    private void combination(int roleIdx, int sum, int[][] ability) {
        if (roleIdx == M) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (select[i]) continue;
            select[i] = true;
            combination(roleIdx + 1, sum + ability[i][roleIdx], ability);
            select[i] = false;
        }
    }
}
